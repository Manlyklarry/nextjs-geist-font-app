package com.spms.service;

import com.spms.entity.Prescription;
import com.spms.entity.User;
import com.spms.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {
    
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    
    public List<Prescription> getActivePrescriptionsByUser(User user) {
        return prescriptionRepository.findByUserAndActiveTrue(user);
    }
    
    public List<Prescription> getAllPrescriptionsByUser(User user) {
        return prescriptionRepository.findByUser(user);
    }
    
    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }
    
    public void deletePrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id).orElse(null);
        if (prescription != null) {
            prescription.setActive(false);
            prescriptionRepository.save(prescription);
        }
    }
    
    public Prescription findById(Long id) {
        return prescriptionRepository.findById(id).orElse(null);
    }
    
    public long getActivePrescriptionCount(User user) {
        return prescriptionRepository.countByUserAndActiveTrue(user);
    }
    
    public Prescription addPrescription(String medicationName, String dosage, String frequency, String doctorName, User user) {
        Prescription prescription = new Prescription(medicationName, dosage, frequency, doctorName, user);
        return prescriptionRepository.save(prescription);
    }
}
