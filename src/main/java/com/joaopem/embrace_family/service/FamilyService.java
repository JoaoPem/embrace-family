package com.joaopem.embrace_family.service;

import com.joaopem.embrace_family.dto.family.FamilyUpdateRequestDTO;
import com.joaopem.embrace_family.dto.family.FamilyPostRequestDTO;
import com.joaopem.embrace_family.dto.family.FamilyResponseDTO;
import com.joaopem.embrace_family.mappers.FamilyMapper;
import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.model.Family;
import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.repository.AdoptiveParentRepository;
import com.joaopem.embrace_family.repository.FamilyInvitationRepository;
import com.joaopem.embrace_family.repository.FamilyRepository;
import com.joaopem.embrace_family.security.SecurityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final SecurityService securityService;
    private final FamilyMapper familyMapper;
    private final AdoptiveParentRepository adoptiveParentRepository;
    private final FamilyInvitationRepository familyInvitationRepository;

    public FamilyResponseDTO createOwnFamily(FamilyPostRequestDTO familyPostRequestDTO){
        UserAccount loggedUser = securityService.getLoggedInUser();
        AdoptiveParent loggedAdoptiveParent = loggedUser.getAdoptiveParent();
        Family family = familyMapper.toEntity(familyPostRequestDTO);
        if (loggedAdoptiveParent.getFamily() != null){
            throw new IllegalStateException("Adoptive parent is already linked to a family!");
        }
        family.setAdoptiveParent1(loggedAdoptiveParent);
        familyRepository.save(family);
        return familyMapper.toDTO(family);
    }

    public FamilyResponseDTO updateOwnFamily(FamilyUpdateRequestDTO familyUpdateRequestDTO){
        UserAccount loggedUser = securityService.getLoggedInUser();
        AdoptiveParent loggedAdoptiveParent = loggedUser.getAdoptiveParent();

        Family family = loggedAdoptiveParent.getFamily();
        if (family == null){
            throw new AccessDeniedException("AdoptiveParent is not associated with any family.");
        }

        familyMapper.updateFamilyFromDTO(familyUpdateRequestDTO, family);

        familyRepository.save(family);
        return familyMapper.toDTO(family);
    }

    public void deleteOwnFamily(){
        UserAccount loggedUserAccount = securityService.getLoggedInUser();
        AdoptiveParent loggedAdoptiveParent = loggedUserAccount.getAdoptiveParent();
        Family family = loggedAdoptiveParent.getFamily();

        if (family == null){
            throw new EntityNotFoundException("No family found for this adoptive parent.");
        }

        familyRepository.delete(family);
    }

    public FamilyResponseDTO showOwnFamily(){
        UserAccount loggedUserAccount = securityService.getLoggedInUser();
        AdoptiveParent loggedAdoptiveParent = loggedUserAccount.getAdoptiveParent();
        Family family = loggedAdoptiveParent.getFamily();

        if (family == null){
            throw new EntityNotFoundException("No family found for this adoptive parent.");
        }

        return familyMapper.toDTO(family);
    }

//    public void createOwnFamily(Family family){
//        UserAccount loggedUser = securityService.getLoggedInUser();
//        AdoptiveParent adoptiveParent = loggedUser.getAdoptiveParent();
//        if (adoptiveParent.getFamily() != null){
//            throw new IllegalStateException("Adoptive parent is already linked to a family!");
//        }
//        family.setAdoptiveParent1(adoptiveParent);
//        familyRepository.save(family);
//    }

//    public Family updateOwnFamily(Family family){
//        UserAccount loggedUser = securityService.getLoggedInUser();
//        AdoptiveParent adoptiveParent = loggedUser.getAdoptiveParent();
//
//        if (adoptiveParent.getFamily() == null){
//            throw new NotFoundException("Você ainda não criou sua família");
//        }
//
//        familyMapper.toDTO(family);
//
//        return familyRepository.save(family);
//
//    }

//    @Transactional
//    public void inviteAdoptiveParent(FamilyInvitationRequestDTO familyInvitationRequestDTO){
//        UserAccount loggedUser = securityService.getLoggedInUser();
//        AdoptiveParent inviterParent = loggedUser.getAdoptiveParent();
//
//        Family family = inviterParent.getFamily();
//
//        AdoptiveParent invitedParent = adoptiveParentRepository
//                .findById(familyInvitationRequestDTO.invitedAdoptiveParentId())
//                .orElseThrow(() -> new EntityNotFoundException("Adoptive Parent not found"));
//
//        boolean invitationExistis = familyInvitationRepository.existsByInviterAdoptiveParentAndInvitedAdoptiveParent(inviterParent, invitedParent);
//        if (invitationExistis){
//            throw new DuplicateRecordException("You have already invited this adoptive parent.");
//        }
//
//        FamilyInvitation invitation = new FamilyInvitation();
//        invitation.setInviterAdoptiveParent(inviterParent);
//        invitation.setInvitedAdoptiveParent(invitedParent);
//        invitation.setFamily(family);
//        invitation.setStatus(InvitationStatus.PENDING);
//        familyInvitationRepository.save(invitation);
//
//    }
}
