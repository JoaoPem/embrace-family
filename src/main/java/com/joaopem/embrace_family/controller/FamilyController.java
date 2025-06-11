package com.joaopem.embrace_family.controller;

import com.joaopem.embrace_family.dto.family.FamilyUpdateRequestDTO;
import com.joaopem.embrace_family.dto.family.FamilyPostRequestDTO;
import com.joaopem.embrace_family.dto.family.FamilyResponseDTO;
import com.joaopem.embrace_family.mappers.FamilyMapper;
import com.joaopem.embrace_family.service.FamilyInvitationService;
import com.joaopem.embrace_family.service.FamilyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/family")
@PreAuthorize("hasRole('ADOPTIVE_PARENT')")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;
    private final FamilyMapper familyMapper;
    private final FamilyInvitationService familyInvitationService;

    @PostMapping
    public ResponseEntity<FamilyResponseDTO> createOwnFamily(@RequestBody @Valid FamilyPostRequestDTO familyPostRequestDTO){
        FamilyResponseDTO familyResponseDTO = familyService.createOwnFamily(familyPostRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(familyResponseDTO);
    }

    @PutMapping("update-my-family")
    public ResponseEntity<FamilyResponseDTO> updateOwnFamily(@RequestBody @Valid FamilyUpdateRequestDTO familyUpdateRequestDTO){
        FamilyResponseDTO familyResponseDTO =  familyService.updateOwnFamily(familyUpdateRequestDTO);
        return ResponseEntity.ok().body(familyResponseDTO);
    }

    @DeleteMapping("delete-my-family")
    public ResponseEntity<Void> deleteOwnFamily(){
        familyService.deleteOwnFamily();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("show-my-family")
    public ResponseEntity<FamilyResponseDTO> showOwnFamily(){
        FamilyResponseDTO familyResponseDTO = familyService.showOwnFamily();
        return ResponseEntity.ok().body(familyResponseDTO);
    }

//    @PutMapping(value = "/my-family", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<FamilyResponseDTO> updateOwnFamily(@RequestBody @Valid FamilyUpdateRequestDTO familyUpdateRequestDTO){
//        Family family = familyMapper.toEntity(familyUpdateRequestDTO);
//        familyService.updateOwnFamily(family);
//        return ResponseEntity.ok(family);
//    }

}
