package com.joaopem.embrace_family.controller;

import com.joaopem.embrace_family.dto.AdoptiveParentResponseDTO;
import com.joaopem.embrace_family.dto.AdoptiveParentUpdateRequestDTO;
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

    @PutMapping("{uuid}")
    public ResponseEntity<Void> updateAdoptiveParent(@PathVariable UUID uuid, @RequestBody @Valid AdoptiveParentUpdateRequestDTO adoptiveParentUpdateRequestDTO){
        adoptiveParentService.updateAdoptiveParent(uuid, adoptiveParentUpdateRequestDTO);
        return ResponseEntity.noContent().build();
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

    @GetMapping
    public ResponseEntity<List<AdoptiveParentResponseDTO>> getAllAdoptiveParents(){
        List<AdoptiveParent> adoptiveParentList = adoptiveParentService.getAllAdoptiveParents();
        List<AdoptiveParentResponseDTO> adoptiveParentDTOList = adoptiveParentList.stream()
                .map(adoptiveParentMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(adoptiveParentDTOList);
    }
}

