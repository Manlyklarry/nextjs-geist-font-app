package com.spms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "reminders")
public class Reminder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private LocalTime reminderTime;
    
    private LocalDateTime createdAt = LocalDateTime.now();
    
    private boolean taken = false;
    
    private boolean snoozed = false;
    
    private LocalDateTime snoozeUntil;
    
    // Constructors
    public Reminder() {}
    
    public Reminder(Prescription prescription, User user, LocalTime reminderTime) {
        this.prescription = prescription;
        this.user = user;
        this.reminderTime = reminderTime;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Prescription getPrescription() {
        return prescription;
    }
    
    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public LocalTime getReminderTime() {
        return reminderTime;
    }
    
    public void setReminderTime(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public boolean isTaken() {
        return taken;
    }
    
    public void setTaken(boolean taken) {
        this.taken = taken;
    }
    
    public boolean isSnoozed() {
        return snoozed;
    }
    
    public void setSnoozed(boolean snoozed) {
        this.snoozed = snoozed;
    }
    
    public LocalDateTime getSnoozeUntil() {
        return snoozeUntil;
    }
    
    public void setSnoozeUntil(LocalDateTime snoozeUntil) {
        this.snoozeUntil = snoozeUntil;
    }
}
