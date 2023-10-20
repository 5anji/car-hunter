package com.example.webscraping.repository;

import com.example.webscraping.model.dbo.CarDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarDbo, Long> {

}
