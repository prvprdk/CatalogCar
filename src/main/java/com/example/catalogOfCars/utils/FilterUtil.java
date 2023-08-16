package com.example.catalogOfCars.utils;

import com.example.catalogOfCars.domain.Car;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FilterUtil {

    public List<Car> filterCar (List <Car> cars, Map<String, ArrayList <String>> filters){

        List <Car> carCopy = cars.stream().toList();

        if (filters.containsKey("brand")){
              carCopy = carCopy.stream().filter(c -> filters.get("brand").contains(c.getBrand())).collect(Collectors.toList());
        }
        if (filters.containsKey("color")){
              carCopy  =  carCopy.stream().filter(c -> filters.get("color").contains(c.getColor())).collect(Collectors.toList());
        }
        if (filters.containsKey("years")){
            carCopy  =  carCopy.stream().filter(c -> filters.get("years").contains(c.getYearOfIssue())).collect(Collectors.toList());
        }

        return carCopy;
    }
}
