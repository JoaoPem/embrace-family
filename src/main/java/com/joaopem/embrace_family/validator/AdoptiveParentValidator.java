package com.joaopem.embrace_family.validator;

import com.joaopem.embrace_family.exceptions.DuplicateRecordException;
import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.repository.AdoptiveParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdoptiveParentValidator {

    private final AdoptiveParentRepository adoptiveParentRepository;

    public void validateAdoptiveParent(AdoptiveParent adoptiveParent){
        if (adoptiveParentAlreadyRegistered(adoptiveParent)){
            throw new DuplicateRecordException("The phone has already been taken");
        }
    }

    private boolean adoptiveParentAlreadyRegistered(AdoptiveParent adoptiveParent){
        String phone = adoptiveParent.getPhone();

        if (phone == null || phone.isBlank()){
            return false;
        }
        return adoptiveParentRepository.findByPhone(phone)
                .filter(
                        existingAdoptiveParent -> !existingAdoptiveParent.getId().equals(adoptiveParent.getId())
                ).isPresent();
    }

}
