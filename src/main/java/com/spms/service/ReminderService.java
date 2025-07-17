package com.spms.service;

import com.spms.entity.Reminder;
import com.spms.entity.User;
import com.spms.entity.Prescription;
import com.spms.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReminderService {
    
    @Autowired
    private ReminderRepository reminderRepository;
    
    public List<Reminder> getActiveRemindersByUser(User user) {
        return reminderRepository.findActiveRemindersByUser(user, LocalDateTime.now());
    }
    
    public List<Reminder> getAllRemindersByUser(User user) {
        return reminderRepository.findByUser(user);
    }
    
    public Reminder saveReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }
    
    public Reminder createReminder(Prescription prescription, User user, LocalTime reminderTime) {
        Reminder reminder = new Reminder(prescription, user, reminderTime);
        return reminderRepository.save(reminder);
    }
    
    public void markAsTaken(Long reminderId) {
        Reminder reminder = reminderRepository.findById(reminderId).orElse(null);
        if (reminder != null) {
            reminder.setTaken(true);
            reminder.setSnoozed(false);
            reminder.setSnoozeUntil(null);
            reminderRepository.save(reminder);
        }
    }
    
    public void snoozeReminder(Long reminderId, int minutes) {
        Reminder reminder = reminderRepository.findById(reminderId).orElse(null);
        if (reminder != null) {
            reminder.setSnoozed(true);
            reminder.setSnoozeUntil(LocalDateTime.now().plusMinutes(minutes));
            reminderRepository.save(reminder);
        }
    }
    
    public long getTakenCount(User user) {
        return reminderRepository.countByUserAndTakenTrue(user);
    }
    
    public long getPendingCount(User user) {
        return reminderRepository.countByUserAndTakenFalse(user);
    }
    
    public double getComplianceRate(User user) {
        long taken = getTakenCount(user);
        long total = taken + getPendingCount(user);
        
        if (total == 0) return 0.0;
        
        return (double) taken / total * 100;
    }
}
