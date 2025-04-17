package com.joaopem.embrace_family.validator;

import com.joaopem.embrace_family.exceptions.DuplicateRecordException;
import com.joaopem.embrace_family.model.User;
import com.joaopem.embrace_family.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validateUser(User user){
        if (usersAlreadyRegistered(user)){
            throw new DuplicateRecordException("The email/phone has already been taken");
        }
    }

    private boolean usersAlreadyRegistered(User user){
        return userRepository.findByEmailOrPhone(user.getEmail(), user.getPhone())
                .filter(
                        existingUser -> !existingUser.getId().equals(user.getId())
                ).isPresent();
    }

}
