package org.example.lab5.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Session {
    private User user;
    private Computer computer;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Session(User user, Computer computer) {
        this.user = user;
        this.computer = computer;
        this.startTime = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public void endSession() {
        this.endTime = LocalDateTime.now();
    }

    public long getDurationMinutes() {
        LocalDateTime end = endTime != null ? endTime : LocalDateTime.now();
        return Duration.between(startTime, end).toSeconds();
    }

    public double calculateCost() {
        long minutes = getDurationMinutes() * 30;
        double hours = Math.ceil(minutes / 60.0);
        return computer.getPricePerHour() * hours;
    }
}
