package com.bheednexusbysanat.demo.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "venue_incharges")
public class VenueIncharge {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inchargeName;

    private String inchargeMobile;
    @Column(unique = true)
    private String inchargeId;
    @Column(length = 1000)
    private String alertInfo;

}
