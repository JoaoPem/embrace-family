package com.joaopem.embrace_family.service;

import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.model.Image;
import com.joaopem.embrace_family.repository.AdoptiveParentRepository;
import com.joaopem.embrace_family.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final FileStorageService fileStorageService;
    private final AdoptiveParentRepository adoptiveParentRepository;
    private final ImageRepository imageRepository;

    public void saveImagesForAdoptiveParent(AdoptiveParent  adoptiveParent, List<MultipartFile> files) throws IOException{
        for ( MultipartFile file : files){
            if (!file.isEmpty()){
                String filePath = fileStorageService.storeFile(file);

                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setPath(filePath);
                image.setUploadedAt(LocalDateTime.now());
                image.setAdoptiveParent(adoptiveParent);

                imageRepository.save(image);
            }
        }
    }

}
