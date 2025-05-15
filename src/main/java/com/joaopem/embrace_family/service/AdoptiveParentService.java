package com.joaopem.embrace_family.service;

import com.joaopem.embrace_family.dto.AdoptiveParentResponseDTO;
import com.joaopem.embrace_family.dto.AdoptiveParentUpdateRequestDTO;
import com.joaopem.embrace_family.mappers.AdoptiveParentMapper;
import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.repository.AdoptiveParentRepository;
import com.joaopem.embrace_family.security.SecurityService;
import com.joaopem.embrace_family.validator.AdoptiveParentValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdoptiveParentService {

    private final AdoptiveParentRepository adoptiveParentRepository;
    private final AdoptiveParentMapper adoptiveParentMapper;
    private final AdoptiveParentValidator adoptiveParentValidator;
    private final SecurityService securityService;

    public void updateAdoptiveParent(AdoptiveParentUpdateRequestDTO adoptiveParentUpdateRequestDTO){
        UserAccount userAccount = securityService.getLoggedInUser();
        AdoptiveParent adoptiveParent = adoptiveParentRepository.findByUserAccountId(userAccount.getId()).orElseThrow(() -> new EntityNotFoundException("AdoptiveParent not found"));
        adoptiveParentMapper.updateFromDTO(adoptiveParent, adoptiveParentUpdateRequestDTO);
        adoptiveParentValidator.validateAdoptiveParent(adoptiveParent);
        adoptiveParentRepository.save(adoptiveParent);
    }

    public AdoptiveParentResponseDTO getAdoptiveParentDetails(){
        UserAccount userAccount = securityService.getLoggedInUser();
        AdoptiveParent adoptiveParent = adoptiveParentRepository.findByUserAccountId(userAccount.getId()).orElseThrow(() -> new EntityNotFoundException("AdoptiveParent not found"));
        return adoptiveParentMapper.toDTO(adoptiveParent);
    }

//    private void verifyOwnership(UUID adoptiveParentId){
//        UserAccount userAccount = securityService.getLoggedInUser();
//        AdoptiveParent adoptiveParent = adoptiveParentRepository.findById(adoptiveParentId).orElseThrow(() -> new EntityNotFoundException("AdoptiveParent not found"));
//        if (!adoptiveParent.getUserAccount().getId().equals(userAccount.getId())) {
//            throw new AccessDeniedException("You can only modify your own profile.");
//        }
//    }

}
