//package com.joaopem.embrace_family.model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.Table;
//import lombok.Data;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Entity
//@Table(name = "")
//@Data
//@EntityListeners(AuditingEntityListener.class)
//public class Children {
//
//    private UUID id;
//
//    private UserAccount registeredBy;
//
//    private String childsName;
//
//    private String birthDate;
//
//    private String mothersName;
//
//    private String mothersNacionality;
//
//    private String mothersBirthDate;
//
//    private String mothersEmail;
//
//    private String mothersNumber;
//
//    private String reasonForDonation;
//
//    @CreatedDate
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//}
