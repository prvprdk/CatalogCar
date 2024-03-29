package com.example.catalogOfCars.service;

import com.example.catalogOfCars.domain.CarStat;
import com.example.catalogOfCars.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarStatService   {
    @Autowired
    CarRepo carRepo;

    public CarStat get() {
      return new CarStat(carRepo.getFirst(), carRepo.getLast(),String.valueOf(carRepo.count()));
    }

}
