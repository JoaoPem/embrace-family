package com.joaopem.embrace_family.controller;

import com.joaopem.embrace_family.dto.AdoptiveParentResponseDTO;
import com.joaopem.embrace_family.dto.AdoptiveParentUpdateRequestDTO;
import com.joaopem.embrace_family.dto.UserAccountResponseDTO;
import com.joaopem.embrace_family.mappers.AdoptiveParentMapper;
import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.service.AdoptiveParentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adoptive-parents")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADOPTIVE_PARENT')")
public class AdoptiveParentsController {

    private final AdoptiveParentService adoptiveParentService;
    private final AdoptiveParentMapper adoptiveParentMapper;

    @PutMapping("/update-me")
    public ResponseEntity<Void> updateAdoptiveParent(@RequestBody @Valid AdoptiveParentUpdateRequestDTO adoptiveParentUpdateRequestDTO){
        adoptiveParentService.updateAdoptiveParent(adoptiveParentUpdateRequestDTO);
        return ResponseEntity.noContent().build();
    }

   @GetMapping("/show-me")
    public ResponseEntity<AdoptiveParentResponseDTO> getAdoptiveParentDetails(){
       AdoptiveParentResponseDTO adoptiveParentResponseDTO = adoptiveParentService.getAdoptiveParentDetails();
        return ResponseEntity.ok(adoptiveParentResponseDTO);
   }

//    @GetMapping("{uuid}")
//    public ResponseEntity<AdoptiveParentResponseDTO> getAdoptiveParentDetails(@PathVariable UUID uuid){
//        return adoptiveParentService.getById(uuid).map(
//                adoptiveParent -> {
//                    AdoptiveParentResponseDTO adoptiveParentDTO = adoptiveParentMapper.toDTO(adoptiveParent);
//                    return ResponseEntity.ok(adoptiveParentDTO);
//                }
//        ).orElseGet(() -> ResponseEntity.notFound().build());
//    }

}

