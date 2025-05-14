package com.joaopem.embrace_family.controller;

import com.joaopem.embrace_family.dto.UserAccountPostRequestDTO;
import com.joaopem.embrace_family.dto.UserAccountResponseDTO;
import com.joaopem.embrace_family.dto.UserAccountUpdateRequestDTO;
import com.joaopem.embrace_family.mappers.UserAccountMapper;
import com.joaopem.embrace_family.model.UserAccount;
import com.joaopem.embrace_family.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;
    private final UserAccountMapper userAccountMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUserAccount(@RequestBody @Valid UserAccountPostRequestDTO userAccountPostRequestDTO){
        UserAccount userAccount = userAccountMapper.toEntity(userAccountPostRequestDTO);
        userAccountService.saveUserAccount(userAccount);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserAccountResponseDTO> updateMyProfile(@PathVariable UUID uuid, @Valid @RequestBody UserAccountUpdateRequestDTO userAccountUpdateRequestDTO){
        UserAccountResponseDTO userAccountResponseDTO = userAccountService.updateUserAccount(uuid, userAccountUpdateRequestDTO);
        return ResponseEntity.ok(userAccountResponseDTO);
    }

    @DeleteMapping("{uuid}")
    public void deleterUserAccount(@PathVariable UUID uuid){
        userAccountService.deleteUserAccount(uuid);
    }

//    @PutMapping("{id}")
//    public ResponseEntity<Object> updateUserAccount(@PathVariable UUID id, @RequestBody @Valid UserAccountPostRequestDTO userAccountPostRequestDTO){
//        return userAccountService.getById(id).map( userAccount -> {
//            UserAccount userAccountEntity = userAccountMapper.toEntity(userAccountPostRequestDTO);
//            userAccountEntity.setId(id);
//            userAccountService.updateUserAccount(userAccountEntity);
//            return ResponseEntity.noContent().build();
//        }).orElseGet(() -> ResponseEntity.notFound().build());
//
//    }

    @GetMapping
    public ResponseEntity<List<UserAccountResponseDTO>> getAllUsersAccounts(){
        return ResponseEntity.ok(userAccountService.getAllUserAccounts());
    }

}
