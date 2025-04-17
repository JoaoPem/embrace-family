package com.joaopem.embrace_family.controller;

import com.joaopem.embrace_family.dto.RequestUserDTO;
import com.joaopem.embrace_family.dto.ResponseError;
import com.joaopem.embrace_family.dto.ResponseUserDTO;
import com.joaopem.embrace_family.mappers.UserMapper;
import com.joaopem.embrace_family.model.User;
import com.joaopem.embrace_family.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody @Valid RequestUserDTO requestUserDTO){
        User user = userMapper.toEntity(requestUserDTO);
        userService.saveUser(user);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<ResponseUserDTO>getUserDetails(@PathVariable UUID uuid){
        return userService.getById(uuid).map(
                user -> {
                    ResponseUserDTO userDTO = userMapper.toDTO(user);
                    return ResponseEntity.ok(userDTO);
                }
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> getAllUsers(){
        List<User> userList= userService.getAllUsers();
        List<ResponseUserDTO> userDTOS = userList.stream().map(userMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(userDTOS);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID uuid){
        return userService.getById(uuid).map(
                user -> {
                    userService.deleteUser(user);
                    return ResponseEntity.noContent().build();
                }
        ).orElseGet( () -> ResponseEntity.notFound().build());
    }

//    @PutMapping("{uuid}")
//    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable UUID uuid, @RequestBody @Valid RequestUserDTO requestUserDTO){
//        ResponseUserDTO updatedUser = userService.updateUser(uuid, requestUserDTO);
//        return ResponseEntity.ok(updatedUser);
//    }
}
