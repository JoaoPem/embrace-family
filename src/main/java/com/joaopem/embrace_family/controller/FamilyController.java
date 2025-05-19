package com.joaopem.embrace_family.controller;

import com.joaopem.embrace_family.dto.FamilyPostRequestDTO;
import com.joaopem.embrace_family.mappers.FamilyMapper;
import com.joaopem.embrace_family.model.Family;
import com.joaopem.embrace_family.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/family")
@PreAuthorize("hasRole('ADOPTIVE_PARENT')")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;
    private  final FamilyMapper familyMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFamily(@RequestBody FamilyPostRequestDTO familyPostRequestDTO){
        Family family = familyMapper.toEntity(familyPostRequestDTO);
        familyService.createFamily(family);
    }
}
