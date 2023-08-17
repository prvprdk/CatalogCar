package com.example.catalogOfCars.repo;

import com.example.catalogOfCars.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository

public interface  CarRepo  extends JpaRepository <Car, Long>, RepoCustomizedFilter<Car> {



   @Query ("SELECT c.date FROM Car c ORDER BY ID ASC limit 1")
   LocalDateTime getFirst ();

   @Query ("SELECT c.date FROM Car c ORDER BY ID DESC limit 1")
   LocalDateTime getLast ();

   Boolean existsByNumber (String number);
}
