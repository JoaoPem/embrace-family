package com.joaopem.embrace_family.service;

import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.model.Family;
import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.repository.FamilyRepository;
import com.joaopem.embrace_family.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final SecurityService securityService;

    public void createFamily(Family family){
        UserAccount userAccount = securityService.getLoggedInUser();
        AdoptiveParent adoptiveParent = userAccount.getAdoptiveParent();
        family.setAdoptiveParent1(adoptiveParent);
        familyRepository.save(family);
    }
}
