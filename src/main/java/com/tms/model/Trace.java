package com.tms.model;


import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Entity
@Data
public class Trace extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "Point")
    private Point geo;
    @OneToOne
    @JoinColumn
    private Vehicle vehicle;
    /*@OneToOne
    @JoinColumn
    private Driver driver;*/

}
