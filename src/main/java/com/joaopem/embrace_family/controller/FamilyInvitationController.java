package com.joaopem.embrace_family.controller;

import com.joaopem.embrace_family.dto.familyinvitation.FamilyInvitationRequestDTO;
import com.joaopem.embrace_family.dto.familyinvitation.FamilyInvitationResponseDTO;
import com.joaopem.embrace_family.service.FamilyInvitationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/family-invitations")
@PreAuthorize("hasRole('ADOPTIVE_PARENT')")
@RequiredArgsConstructor
public class FamilyInvitationController {

    private final FamilyInvitationService familyInvitationService;

    @PostMapping("/send-invitation")
    public ResponseEntity<FamilyInvitationResponseDTO> sendFamilyInvitation(@RequestBody @Valid FamilyInvitationRequestDTO familyInvitationRequestDTO){
        FamilyInvitationResponseDTO familyInvitationResponseDTO = familyInvitationService.sendFamilyInvitation(familyInvitationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(familyInvitationResponseDTO);
    }

    @DeleteMapping("{uuid}/cancel")
    public ResponseEntity<Void> cancelFamilyInvitation(@PathVariable UUID uuid){
        familyInvitationService.cancelFamilyInvitation(uuid);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{uuid}/decline")
    public ResponseEntity<Void> rejectFamilyInvitation(@PathVariable UUID uuid){
        familyInvitationService.rejectFamilyInvitation(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{uuid}/accept")
    public ResponseEntity<Void> acceptFamilyInvitation(@PathVariable UUID uuid){
        familyInvitationService.acceptFamilyInvitation(uuid);
        return ResponseEntity.ok().build();
    }

}
