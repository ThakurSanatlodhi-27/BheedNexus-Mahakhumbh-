package com.bheednexusbysanat.demo.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "police")
public class PoliceStation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stateName;
    private String districtName;
    private String policeStationName;
    private String inchargeName;
    @Column(unique = true)
    private String inchargeId;
    private String inchargeMobile;
    private Boolean verified;

}
