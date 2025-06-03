package com.joaopem.embrace_family.controller;

import com.joaopem.embrace_family.dto.adoptiveparent.AdoptiveParentResponseDTO;
import com.joaopem.embrace_family.dto.adoptiveparent.AdoptiveParentUpdateRequestDTO;
import com.joaopem.embrace_family.mappers.AdoptiveParentMapper;
import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.service.AdoptiveParentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/adoptive-parents")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADOPTIVE_PARENT')")
public class AdoptiveParentController {

    private final AdoptiveParentService adoptiveParentService;
    private final AdoptiveParentMapper adoptiveParentMapper;

//    @PutMapping(value = "/update-me", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Void> updateAdoptiveParent(
//            @RequestPart("adoptiveParentDTO") @Valid AdoptiveParentUpdateRequestDTO adoptiveParentUpdateRequestDTO,
//            @RequestPart(value = "files", required = false) List<MultipartFile> files
//            ) throws IOException {
//        adoptiveParentService.updateAdoptiveParent(adoptiveParentUpdateRequestDTO, files);
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/update-me")
    public ResponseEntity<AdoptiveParentResponseDTO> updateOwnAdoptiveParent(@RequestBody @Valid AdoptiveParentUpdateRequestDTO adoptiveParentUpdateRequestDTO){
        AdoptiveParentResponseDTO adoptiveParentResponseDTO = adoptiveParentService.updateOwnAdoptiveParent(adoptiveParentUpdateRequestDTO);
        return ResponseEntity.ok().body(adoptiveParentResponseDTO);
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

