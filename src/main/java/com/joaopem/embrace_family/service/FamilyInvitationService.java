package com.joaopem.embrace_family.service;

import com.joaopem.embrace_family.dto.familyinvitation.FamilyInvitationRequestDTO;
import com.joaopem.embrace_family.dto.familyinvitation.FamilyInvitationResponseDTO;
import com.joaopem.embrace_family.enums.InvitationStatus;
import com.joaopem.embrace_family.mappers.FamilyInvitationMapper;
import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.model.Family;
import com.joaopem.embrace_family.model.FamilyInvitation;
import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.repository.AdoptiveParentRepository;
import com.joaopem.embrace_family.repository.FamilyInvitationRepository;
import com.joaopem.embrace_family.repository.FamilyRepository;
import com.joaopem.embrace_family.security.SecurityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FamilyInvitationService {

    private final SecurityService securityService;
    private final FamilyInvitationMapper familyInvitationMapper;
    private final FamilyInvitationRepository familyInvitationRepository;
    private final AdoptiveParentRepository adoptiveParentRepository;
    private final FamilyRepository familyRepository;

    public FamilyInvitationResponseDTO sendFamilyInvitation(FamilyInvitationRequestDTO familyInvitationRequestDTO){
        UserAccount loggedUserAccount = securityService.getLoggedInUser();
        AdoptiveParent inviterAdoptiveParent = loggedUserAccount.getAdoptiveParent();

        if (inviterAdoptiveParent.getFamily() == null){
            throw new IllegalStateException("Inviter must belong to a family to send invitations.");
        }

        Family family = inviterAdoptiveParent.getFamily();

        if (family.getAdoptiveParent2() != null){
            throw new IllegalStateException("Cannot send invitation: family already has a second adoptive parent.");
        }

        UUID invitedAdoptiveParentId = familyInvitationRequestDTO.invitedAdoptiveParentId();

        if (invitedAdoptiveParentId.equals(inviterAdoptiveParent.getId())){
            throw new IllegalArgumentException("Cannot invite yourself.");
        }

        AdoptiveParent invitedAdoptiveParent = adoptiveParentRepository.findById(
                invitedAdoptiveParentId).orElseThrow(() -> new EntityNotFoundException("Invited adoptive parent not found.")
        );

        boolean familyInvitationAlreadyExists = familyInvitationRepository.existsByFamilyAndStatus(
              inviterAdoptiveParent.getFamily(), InvitationStatus.PENDING
        );

        if (familyInvitationAlreadyExists){
            throw new IllegalStateException("There is already a pending invitation for this family.");
        }

        FamilyInvitation familyInvitation = familyInvitationMapper.toEntity(familyInvitationRequestDTO);
        familyInvitation.setInviterAdoptiveParent(inviterAdoptiveParent);
        familyInvitation.setInvitedAdoptiveParent(invitedAdoptiveParent);
        familyInvitation.setFamily(inviterAdoptiveParent.getFamily());
        familyInvitation.setStatus(InvitationStatus.PENDING);
        familyInvitationRepository.save(familyInvitation);
        return familyInvitationMapper.toDTO(familyInvitation);
    }

    public void cancelFamilyInvitation(UUID uuid){
        UserAccount loggedUserAccount = securityService.getLoggedInUser();
        AdoptiveParent inviterAdoptiveParent = loggedUserAccount.getAdoptiveParent();

        FamilyInvitation familyInvitation = familyInvitationRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Invitation not found."));

        if (!familyInvitation.getInviterAdoptiveParent().getId().equals(inviterAdoptiveParent.getId())){
            throw new AccessDeniedException("You are not allowed to cancel this invitation.");
        }

        if (familyInvitation.getStatus() != InvitationStatus.PENDING){
            throw new IllegalStateException("Only pending invitations can be canceled.");
        }

        familyInvitation.setStatus(InvitationStatus.CANCELED);
        familyInvitationRepository.save(familyInvitation);

    }

    public void rejectFamilyInvitation(UUID uuid){
        UserAccount loggedUserAccount = securityService.getLoggedInUser();
        AdoptiveParent invitedAdoptiveParent = loggedUserAccount.getAdoptiveParent();

        FamilyInvitation familyInvitation = familyInvitationRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Invitation not found."));

        if (!familyInvitation.getInvitedAdoptiveParent().getId().equals(invitedAdoptiveParent.getId())){
            throw new AccessDeniedException("You are not authorized to reject this invitation.");
        }

        if (familyInvitation.getStatus() != InvitationStatus.PENDING){
            throw new IllegalStateException("Only pending invitations can be rejected.");
        }

        familyInvitation.setStatus(InvitationStatus.DECLINED);
        familyInvitationRepository.save(familyInvitation);
    }

    public void acceptFamilyInvitation(UUID uuid){
        UserAccount loggedUserAccount = securityService.getLoggedInUser();
        AdoptiveParent invitedAdoptiveParent = loggedUserAccount.getAdoptiveParent();

        FamilyInvitation familyInvitation = familyInvitationRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Invitation not found."));

        if (!familyInvitation.getInvitedAdoptiveParent().getId().equals(invitedAdoptiveParent.getId())){
            throw new AccessDeniedException("You are not authorized to accept this invitation.");
        }

        if (familyInvitation.getStatus() != InvitationStatus.PENDING){
            throw new IllegalStateException("Only pending invitations can be accepted.");
        }

        Family family = familyInvitation.getFamily();

        if (family.getAdoptiveParent2() != null){
            throw new IllegalStateException("This family already has a second adoptive parent.");
        }

        familyInvitation.setStatus(InvitationStatus.ACCEPTED);
        family.setAdoptiveParent2(invitedAdoptiveParent);
        familyInvitationRepository.save(familyInvitation);
        familyRepository.save(family);
        
    }
}
