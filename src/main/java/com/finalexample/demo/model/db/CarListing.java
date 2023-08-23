package com.finalexample.demo.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CAR_LISTINGS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE_NAME")
    private String imageName;

}
