package net.javaguides.springboot.model;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "notification")
public class Notification {
    private String message;

    // Getters and Setters
}
