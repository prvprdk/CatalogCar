package com.example.catalogOfCars.utils;

import com.example.catalogOfCars.domain.Car;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SortUtil {
    public List<Car> sorted(String sort, List<Car> cars) {
        switch (sort) {
            case "brand" -> {
                return cars.stream()
                        .sorted(Comparator.comparing(Car::getBrand, Comparator.nullsLast(String::compareTo)))
                        .collect(Collectors.toList());
            }
            case "color" -> {
                return cars.stream()
                        .sorted(Comparator.comparing(Car::getColor, Comparator.nullsLast(String::compareTo)))
                        .collect(Collectors.toList());
            }
            case "number" -> {
                return cars.stream()
                        .sorted(Comparator.comparing(Car::getNumber, Comparator.nullsLast(String::compareTo)))
                        .collect(Collectors.toList());
            }
            case "year" -> {
                return cars.stream()
                        .sorted(Comparator.comparing(Car::getYearOfIssue, Comparator.nullsLast(String::compareTo)))
                        .collect(Collectors.toList());
            }
            default -> {
                return cars;
            }
        }
    }
}
