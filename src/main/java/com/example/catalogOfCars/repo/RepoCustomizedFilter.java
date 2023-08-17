package com.example.catalogOfCars.repo;

import com.example.catalogOfCars.domain.Car;

import java.util.List;

public interface RepoCustomizedFilter <T> {
    List<Car> getCarsByFilter (String query);
}
