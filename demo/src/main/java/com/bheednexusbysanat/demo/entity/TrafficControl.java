package com.bheednexusbysanat.demo.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "traffic_control")
public class TrafficControl {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stateName;
    private String districtName;
    private String name;
    private String mobileNo;
    @Column(unique = true)
    private String trafficInchargeId;
    private Boolean checkRoutesAvailable;
    private String travelModeChecked; // "ROAD","TRAIN","FLIGHT" or "ALL"

    public Object getTrafficInchargeName() {
        return name;
    }

    public Object getTrafficInchargeMobile() {
        return mobileNo;
    }

    public void setTrafficInchargeName(Object trafficInchargeName) {
    }

    public void setTrafficInchargeMobile(Object trafficInchargeMobile) {

    }
}
