package com.joaopem.embrace_family.validator;

import com.joaopem.embrace_family.exceptions.DuplicateRecordException;
import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.model.UserRole;
import com.joaopem.embrace_family.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccountValidator {

    private final UserAccountRepository userAccountRepository;

    public void validateUserAccount(UserAccount userAccount){
        if (userAccountAlreadyRegistered(userAccount)){
            throw new DuplicateRecordException("The email has already been taken");
        }
        validateUserRole(userAccount);
    }

    private boolean userAccountAlreadyRegistered(UserAccount userAccount){
                return userAccountRepository.optionalFindByEmail(userAccount.getEmail())
                .filter(
                        existingUserAccount -> !existingUserAccount.getId().equals(userAccount.getId())
                ).isPresent();
    }

    private void validateUserRole(UserAccount userAccount){
        UserRole userRole = userAccount.getUserRole();
        if (userRole == UserRole.ADMIN){
            throw new IllegalArgumentException("Cannot create an ADMIN via this endpoint");
        }
    }

}
