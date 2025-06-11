package com.joaopem.embrace_family.validator;

import com.joaopem.embrace_family.exceptions.DuplicateRecordException;
import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccountValidator {

    private final UserAccountRepository userAccountRepository;

    public void validateUserAccount(UserAccount userAccount){
        validateEmailUniqueness(userAccount);
    }

    private void validateEmailUniqueness(UserAccount userAccount){
        boolean emailTaken = userAccountRepository.findByEmail(userAccount.getEmail())
                .isPresent();

        if (emailTaken) {
            throw new DuplicateRecordException("The email has already been taken");
        }


    }

}
