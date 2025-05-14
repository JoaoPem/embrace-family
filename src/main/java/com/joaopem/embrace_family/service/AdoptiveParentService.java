package com.joaopem.embrace_family.service;

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

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdoptiveParentService {

    private final AdoptiveParentRepository adoptiveParentRepository;
    private final AdoptiveParentMapper adoptiveParentMapper;
    private final AdoptiveParentValidator adoptiveParentValidator;
    private  final SecurityService securityService;

    public void updateAdoptiveParent(UUID uuid, AdoptiveParentUpdateRequestDTO adoptiveParentUpdateRequestDTO){
        verifyOwnership(uuid);
        AdoptiveParent adoptiveParent = adoptiveParentRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("AdoptiveParent not found"));
        adoptiveParentMapper.updateFromDTO(adoptiveParent, adoptiveParentUpdateRequestDTO);
        adoptiveParentValidator.validateAdoptiveParent(adoptiveParent);
        adoptiveParentRepository.save(adoptiveParent);
    }

    private void verifyOwnership(UUID uuid){
        UserAccount userAccount = securityService.getLoggedInUser();
        if (userAccount == null || !userAccount.getId().equals(uuid)){
            throw new AccessDeniedException("You can only modify your own profile.");
        }
    }

    public List<AdoptiveParent> getAllAdoptiveParents(){
        return adoptiveParentRepository.findAll();
    }

}
