package com.bheednexusbysanat.demo.entity;

import com.bheednexusbysanat.demo.entity.types.TravelModeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"aadharNumber"})})
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String mobileNumber;
    @Column(nullable = false,unique=true)
    private String aadharNumber;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TravelModeType travelMode;

    private LocalDate fromDate;
    private LocalDate toDate;
    private String stateName;
    private String districtName;
    private String nearestPoliceStation;
    private Boolean verified = false;


}
