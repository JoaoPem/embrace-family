package com.joaopem.embrace_family.model;

import com.joaopem.embrace_family.enums.InvitationStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "family_invitations")
@Data
@EntityListeners(AuditingEntityListener.class)
public class FamilyInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_adoptive_parent_id", nullable = false)
    private AdoptiveParent invitedAdoptiveParent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inviter_adoptive_parent_id", nullable = false)
    private AdoptiveParent inviterAdoptiveParent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "family_id", nullable = false)
    private Family family;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvitationStatus status = InvitationStatus.PENDING;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
