package com.example.webscraping.model.dbo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bodyType;
    private String manufacture;
    private String model;
    private LocalDateTime dateOfRegistration;
    private int millage;
    private double engineCapacity;
    @ElementCollection
    private List<String> photoUrls;
    @Enumerated(EnumType.STRING)
    private GearBox gearBox;
    private double price;
}
