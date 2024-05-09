package com.philipfeng.phonebooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "phone_booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long phoneId;

    private LocalDateTime bookedAt;

    private LocalDateTime returnedAt;

    private String bookedBy;
}