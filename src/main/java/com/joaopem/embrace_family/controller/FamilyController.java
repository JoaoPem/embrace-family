package com.joaopem.embrace_family.controller;

import com.joaopem.embrace_family.dto.FamilyInvitationRequestDTO;
import com.joaopem.embrace_family.dto.FamilyPostRequestDTO;
import com.joaopem.embrace_family.dto.FamilyResponseDTO;
import com.joaopem.embrace_family.dto.FamilyUpdateRequestDTO;
import com.joaopem.embrace_family.mappers.FamilyMapper;
import com.joaopem.embrace_family.model.Family;
import com.joaopem.embrace_family.repository.AdoptiveParentRepository;
import com.joaopem.embrace_family.service.FamilyInvitationService;
import com.joaopem.embrace_family.service.FamilyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/family")
@PreAuthorize("hasRole('ADOPTIVE_PARENT')")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;
    private final FamilyMapper familyMapper;
    private final FamilyInvitationService familyInvitationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOwnFamily(@RequestBody FamilyPostRequestDTO familyPostRequestDTO){
        Family family = familyMapper.toEntity(familyPostRequestDTO);
        familyService.createOwnFamily(family);
    }

//    @PutMapping(value = "/my-family", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<FamilyResponseDTO> updateOwnFamily(@RequestBody @Valid FamilyUpdateRequestDTO familyUpdateRequestDTO){
//        Family family = familyMapper.toEntity(familyUpdateRequestDTO);
//        familyService.updateOwnFamily(family);
//        return ResponseEntity.ok(family);
//    }

//    @PostMapping("/invite")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void inviteAdoptiveParent(@RequestBody FamilyInvitationRequestDTO familyInvitationRequestDTO){
//        familyService.inviteAdoptiveParent(familyInvitationRequestDTO);
//    }
//
//    @DeleteMapping("/invite/{uuid}")
//    public ResponseEntity<Void> cancelInvitation(@PathVariable UUID uuid){
//        familyInvitationService.cancelInvitation(uuid);
//        return ResponseEntity.noContent().build();
//    }
}
