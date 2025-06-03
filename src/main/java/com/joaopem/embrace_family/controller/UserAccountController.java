package com.joaopem.embrace_family.controller;

import com.joaopem.embrace_family.dto.useraccount.UserAccountPostRequestDTO;
import com.joaopem.embrace_family.dto.useraccount.UserAccountResponseDTO;
import com.joaopem.embrace_family.dto.useraccount.UserAccountUpdateRequestDTO;
import com.joaopem.embrace_family.mappers.UserAccountMapper;
import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;
    private final UserAccountMapper userAccountMapper;

    @PostMapping
    public ResponseEntity<UserAccountResponseDTO> createUserAccount(@RequestBody @Valid UserAccountPostRequestDTO userAccountPostRequestDTO){
        UserAccountResponseDTO createdUserAccountDTO = userAccountService.createUserAccount(userAccountPostRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserAccountDTO);
    }

//    public ResponseEntity<UserAccountResponseDTO> createUserAccount(@RequestBody @Valid UserAccountPostRequestDTO userAccountPostRequestDTO){
//        UserAccount userAccount = userAccountMapper.toEntity(userAccountPostRequestDTO);
//        UserAccount createdUser = userAccountService.createUserAccount(userAccount);
//        return ResponseEntity.status(HttpStatus.CREATED).body(userAccountMapper.toDTO(createdUser));
//    }

    @PatchMapping("/update-me")
    public ResponseEntity<UserAccountResponseDTO> updateOwnUserAccount(@RequestBody @Valid UserAccountUpdateRequestDTO userAccountUpdateRequestDTO){
        UserAccountResponseDTO updatedUserAccountDTO = userAccountService.updateOwnUserAccount(userAccountUpdateRequestDTO);
        return ResponseEntity.ok().body(updatedUserAccountDTO);
    }

    @DeleteMapping("/delete-me")
    public ResponseEntity<Void> deleteOwnUserAccount(){
        userAccountService.deleteOwnUserAccount();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/show-me")
    public ResponseEntity<UserAccountResponseDTO> getUserAccountDetails(){
        UserAccountResponseDTO userAccountResponseDTO = userAccountService.getUserAccountDetails();
        return ResponseEntity.ok(userAccountResponseDTO);
    }


//    @GetMapping("/show-me")
//    public ResponseEntity<UserAccountResponseDTO> getUserAccountDetails(@AuthenticationPrincipal UserAccount userAccount){
//        UserAccountResponseDTO userAccountResponseDTO = userAccountService.getUserAccountDetails(userAccount);
//        return ResponseEntity.ok(userAccountResponseDTO);
//    }

}
