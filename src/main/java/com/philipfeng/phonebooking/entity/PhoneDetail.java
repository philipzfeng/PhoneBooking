package com.philipfeng.phonebooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phone_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDetail {
    @Id
    private Long id;

    private String name;

    private String technology;

    private String twoGBands;

    private String threeGBands;

    private String fourGBands;
}