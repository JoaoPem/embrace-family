package com.joaopem.embrace_family.service;

import com.joaopem.embrace_family.dto.useraccount.UserAccountPostRequestDTO;
import com.joaopem.embrace_family.dto.useraccount.UserAccountResponseDTO;
import com.joaopem.embrace_family.dto.useraccount.UserAccountUpdateRequestDTO;
import com.joaopem.embrace_family.enums.UserRole;
import com.joaopem.embrace_family.mappers.UserAccountMapper;
import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.repository.UserAccountRepository;
import com.joaopem.embrace_family.security.SecurityService;
import com.joaopem.embrace_family.validator.UserAccountValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountValidator userAccountValidator;
    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecurityService securityService;

    public UserAccountResponseDTO createUserAccount(UserAccountPostRequestDTO userAccountPostRequestDTO){
        UserAccount userAccount = userAccountMapper.toEntity(userAccountPostRequestDTO);
        userAccount.setEmail(userAccount.getEmail().toLowerCase());
        userAccountValidator.validateUserAccount(userAccount);
        userAccount.setUserRole(UserRole.ADOPTIVE_PARENT);
        var password =  userAccount.getPassword();
        userAccount.setPassword(passwordEncoder.encode(password));
        createAdoptiveParent(userAccount);
        userAccountRepository.save(userAccount);
        return userAccountMapper.toDTO(userAccount);
    }

//    public UserAccount createUserAccount(UserAccount userAccount){
//        userAccount.setEmail(userAccount.getEmail().toLowerCase());
//        userAccountValidator.validateUserAccount(userAccount);
//        userAccount.setUserRole(UserRole.ADOPTIVE_PARENT);
//        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
//        createAdoptiveParent(userAccount);
//        return userAccountRepository.save(userAccount);
//    }

    private void createAdoptiveParent(UserAccount userAccount) {
        AdoptiveParent adoptiveParent = new AdoptiveParent();
        adoptiveParent.setUserAccount(userAccount);
        userAccount.setAdoptiveParent(adoptiveParent);
    }

    public  UserAccountResponseDTO updateOwnUserAccount(UserAccountUpdateRequestDTO userAccountUpdateRequestDTO){
        UserAccount userAccount = securityService.getLoggedInUser();

        updateEmailIfPresent(userAccount, userAccountUpdateRequestDTO);
        updatePasswordIfPresent(userAccount, userAccountUpdateRequestDTO);

        userAccountRepository.save(userAccount);
        return userAccountMapper.toDTO(userAccount);
    }

    private void updateEmailIfPresent(UserAccount userAccount, UserAccountUpdateRequestDTO userAccountUpdateRequestDTO){
        if (userAccountUpdateRequestDTO.email() != null && !userAccountUpdateRequestDTO.email().isBlank()){
            userAccount.setEmail(userAccountUpdateRequestDTO.email());
            userAccountValidator.validateUserAccount(userAccount);
        }
    }

    private void updatePasswordIfPresent(UserAccount userAccount, UserAccountUpdateRequestDTO userAccountUpdateRequestDTO){
        if (userAccountUpdateRequestDTO.password() != null && !userAccountUpdateRequestDTO.password().isBlank()){
            if (userAccountUpdateRequestDTO.passwordConfirmation() == null || userAccountUpdateRequestDTO.passwordConfirmation().isBlank()){
                throw new IllegalArgumentException("Password confirmation is required when changing password.");
            }
            if (!userAccountUpdateRequestDTO.password().equals(userAccountUpdateRequestDTO.passwordConfirmation())){
                throw new IllegalArgumentException("Password confirmation does not match or is missing.");
            }
            var password = userAccountUpdateRequestDTO.password();
            userAccount.setPassword(passwordEncoder.encode(password));
        }
    }

    public void deleteOwnUserAccount(){
        UserAccount loggedUser = securityService.getLoggedInUser();
        userAccountRepository.delete(loggedUser);
    }

    public UserAccountResponseDTO getUserAccountDetails(){
        UserAccount userAccount = securityService.getLoggedInUser();
        return userAccountMapper.toDTO(userAccount);
    }


//    public UserAccountResponseDTO getUserAccountDetails(UserAccount userAccount){
//        return userAccountMapper.toDTO(userAccount);
//    }

//    public UserAccountResponseDTO getUserAccountDetails(UUID uuid){
//        UserAccount userAccount = userAccountRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("UserAccount not found"));
//        return userAccountMapper.toDTO(userAccount);
//    }


    public UserAccount getByEmail(String email){
        return userAccountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
