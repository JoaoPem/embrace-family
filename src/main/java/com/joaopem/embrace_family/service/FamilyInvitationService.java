package com.joaopem.embrace_family.service;

import com.joaopem.embrace_family.enums.InvitationStatus;
import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.model.FamilyInvitation;
import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.repository.FamilyInvitationRepository;
import com.joaopem.embrace_family.security.SecurityService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FamilyInvitationService {

    private final FamilyInvitationRepository familyInvitationRepository;
    private final SecurityService securityService;

    public void cancelInvitation(UUID uuid){
        UserAccount userAccount = securityService.getLoggedInUser();
        AdoptiveParent adoptiveParent = userAccount.getAdoptiveParent();

        FamilyInvitation familyInvitation = familyInvitationRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Invitation not found with id " + uuid));;

        if (!familyInvitation.getInviterAdoptiveParent().equals(adoptiveParent)){
            throw new AccessDeniedException("You are not authorized to cancel this invitation");
        }

        if (familyInvitation.getStatus() == InvitationStatus.PENDING) {
            familyInvitationRepository.delete(familyInvitation);
        } else {
            throw new IllegalStateException("Cannot cancel an invitation that is already " + familyInvitation.getStatus());
        }
    }
}
