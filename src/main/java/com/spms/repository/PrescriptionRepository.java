package com.spms.repository;

import com.spms.entity.Prescription;
import com.spms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByUserAndActiveTrue(User user);
    List<Prescription> findByUser(User user);
    long countByUserAndActiveTrue(User user);
}
