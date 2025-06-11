package com.joaopem.embrace_family.repository;

import com.joaopem.embrace_family.enums.InvitationStatus;
import com.joaopem.embrace_family.model.AdoptiveParent;
import com.joaopem.embrace_family.model.Family;
import com.joaopem.embrace_family.model.FamilyInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FamilyInvitationRepository extends JpaRepository<FamilyInvitation, UUID> {

    boolean existsByFamilyAndStatus(Family family, InvitationStatus status);

}
