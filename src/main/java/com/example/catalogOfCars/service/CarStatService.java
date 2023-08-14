package com.example.catalogOfCars.service;

import com.example.catalogOfCars.domain.CarStat;
import com.example.catalogOfCars.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarStatService {
    @Autowired
    CarRepo carRepo;

    public CarStat get() {
        return CarStat
                .builder()
                .firstRecord(carRepo.getFirst())
                .lastRecord(carRepo.getLast())
                .count(String.valueOf(carRepo.count()))
                .build();
    }

}
