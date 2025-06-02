package com.joaopem.embrace_family.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "families")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Image> familyPhotos = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "adoptive_parent1_id", nullable = false, unique = true)
    private AdoptiveParent adoptiveParent1;

    @OneToOne
    @JoinColumn(name = "adoptive_parent2_id", unique = true)
    private AdoptiveParent adoptiveParent2;

    @Column(name = "family_name", nullable = false, length = 50)
    private String familyName;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "family_culture", length = 1000)
    private String familyCulture;

    @Column(name = "religious_traditions", length = 1000)
    private String religiousTraditions;

    @Column(name = "shared_hobbies", length = 1000)
    private String sharedHobbies;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
