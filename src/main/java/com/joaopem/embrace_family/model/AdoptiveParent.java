package com.joaopem.embrace_family.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "adoptive_parents")
@Data
@EntityListeners(AuditingEntityListener.class)
public class AdoptiveParent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_account_id", nullable = false, unique = true)
    private UserAccount userAccount;

    @OneToOne(mappedBy = "adoptiveParent1", fetch = FetchType.EAGER)
    private Family family;

//    @OneToMany(mappedBy = "adoptiveParent", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<Image> images = new ArrayList<>();

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "phone", nullable = false, length = 20, unique = true)
    private String phone;

    @Column(name = "nationality", nullable = false, length = 30 )
    private String nationality;

    @Column(name = "marital_status", nullable = false, length = 20)
    private String maritalStatus;

    @Column(name = "gender_identify", nullable = false, length = 20)
    private String genderIdentify;

    @Column(name = "sexual_orientation", nullable = false, length = 20)
    private String sexualOrientation;

    @Column(name = "education_level", nullable = false, length = 30)
    private String educationLevel;

    @Column(name = "occupation", nullable = false, length = 50)
    private String occupation;

    @Column(name = "monthly_income", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncome;

    @Column(name = "hobbies", nullable = false, length = 1000)
    private  String hobbies;

    @Column(name = "religion", nullable = false, length = 20)
    private String religion;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
