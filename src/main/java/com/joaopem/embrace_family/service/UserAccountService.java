package com.joaopem.embrace_family.service;

import com.joaopem.embrace_family.dto.UserAccountResponseDTO;
import com.joaopem.embrace_family.dto.UserAccountUpdateRequestDTO;
import com.joaopem.embrace_family.mappers.UserAccountMapper;
import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.enums.UserRole;
import com.joaopem.embrace_family.repository.UserAccountRepository;
import com.joaopem.embrace_family.security.SecurityService;
import com.joaopem.embrace_family.validator.UserAccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountValidator userAccountValidator;
    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecurityService securityService;

    public void saveUserAccount(UserAccount userAccount){
        userAccount.setEmail(userAccount.getEmail().toLowerCase());
        userAccount.setUserRole(UserRole.ADOPTIVE_PARENT);
        userAccountValidator.validateUserAccount(userAccount);
        var password = userAccount.getPassword();
        userAccount.setPassword(passwordEncoder.encode(password));
        createAdoptiveParent(userAccount);
        userAccountRepository.save(userAccount);
    }

    private static void createAdoptiveParent(UserAccount userAccount) {
        AdoptiveParent adoptiveParent = new AdoptiveParent();
        adoptiveParent.setUserAccount(userAccount);
        userAccount.setAdoptiveParent(adoptiveParent);
    }

    public UserAccountResponseDTO updateUserAccount(UserAccountUpdateRequestDTO userAccountUpdateRequestDTO){
        UserAccount userAccount = securityService.getLoggedInUser();

        if (userAccountUpdateRequestDTO.email() != null){
            userAccount.setEmail(userAccountUpdateRequestDTO.email());
        }
        if (userAccountUpdateRequestDTO.password() != null && !userAccountUpdateRequestDTO.password().isBlank()){
            if (!userAccountUpdateRequestDTO.password().equals(userAccountUpdateRequestDTO.passwordConfirmation())){
                //throw new PasswordConfirmationException("Password confirmation does not match or is missing.");
                throw new IllegalArgumentException("Password confirmation does not match or is missing.");
            }
            userAccount.setPassword(passwordEncoder.encode(userAccountUpdateRequestDTO.password()));
        }
        userAccountValidator.validateUserAccount(userAccount);
        userAccountRepository.save(userAccount);
        return userAccountMapper.toDTO(userAccount);
    }

    public void deleteUserAccount(){
        UserAccount userAccount = securityService.getLoggedInUser();
        userAccountRepository.delete(userAccount);
    }

    public UserAccountResponseDTO getUserAccountDetails(){
        UserAccount userAccount = securityService.getLoggedInUser();
        return userAccountMapper.toDTO(userAccount);
    }

    public UserAccount getByEmail(String email){
        return userAccountRepository.findByEmail(email);
    }

//    private void verifyOwnership(UUID uuid){
//        UserAccount userAccount = securityService.getLoggedInUser();
//        if (userAccount == null || !userAccount.getId().equals(uuid)){
//            throw new AccessDeniedException("You can only modify your own profile.");
//        }
//    }

//    public List<UserAccountResponseDTO> getAllUserAccounts(){
//        List<UserAccount> userAccountList = userAccountRepository.findAll();
//        return userAccountList.stream().map(userAccountMapper::toDTO).collect(Collectors.toList());
//    }




}
