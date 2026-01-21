package com.bheednexusbysanat.demo.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "check_points")
public class CheckPoint {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String inchargeName;
    private String inchargeMobile;
    @Column(unique = true)
    private String inchargeId;
    private Boolean checkUserInfo;
    private Boolean checkValidateDate;


}
