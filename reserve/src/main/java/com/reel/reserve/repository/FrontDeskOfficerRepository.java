package com.reel.reserve.repository;

import com.reel.reserve.models.FrontDeskOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FrontDeskOfficerRepository extends JpaRepository<FrontDeskOfficer, Long> {

    Optional<FrontDeskOfficer> findByEmail(String email);
}
