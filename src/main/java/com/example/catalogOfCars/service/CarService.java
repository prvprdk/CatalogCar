package com.example.catalogOfCars.service;

import com.example.catalogOfCars.Errors.AppError;
import com.example.catalogOfCars.domain.Car;
import com.example.catalogOfCars.repo.CarRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Slf4j
@Service
public class CarService {
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private SortingService sortedService;
    @Autowired
    private FilterService filterService;

    public List<Car> getList(String sort, Map<String, ArrayList<String>> filter) {

        if ((sort != null && !sort.isEmpty()) && (filter != null && !filter.isEmpty())) {
            return sortedService.sorted(sort, filterService.filterCar(carRepo.findAll(), filter));
        }

        if (sort != null && !sort.isEmpty()) {
            return sortedService.sorted(sort, carRepo.findAll());
        }

        if (filter != null && !filter.isEmpty()) {
            return filterService.filterCar(carRepo.findAll(), filter);
        }

        return carRepo.findAll();
    }

    public ResponseEntity<?> add(Car car) {

        if (car.getId() != null && carRepo.existsById(car.getId())) {
            log.info(String.format("Car id:%d exists. Save is not possible", car.getId()));
            return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.value(), "Car exists"), HttpStatus.CONFLICT);

        }
        car.setDate(LocalDateTime.now());
        carRepo.save(car);
        log.info("Add " + car);

        return new ResponseEntity<>(car, HttpStatus.OK);

    }



    public ResponseEntity<?> deleteCar(Long id) {
        try {
            Car car = carRepo.findById(id).orElseThrow();
            carRepo.delete(car);
            log.info(String.format("Car id:%d deleted", id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage() + "Delete is not possible");
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    String.format("Car with id %d not found", id)), HttpStatus.NOT_FOUND);
        }
    }
}
