package com.example.catalogOfCars.service;

import com.example.catalogOfCars.domain.Car;
import com.example.catalogOfCars.errors.AppError;
import com.example.catalogOfCars.errors.ValidError;
import com.example.catalogOfCars.repo.CarRepo;
import com.example.catalogOfCars.utils.QueryFilterUtil;
import com.example.catalogOfCars.utils.SortUtil;
import com.example.catalogOfCars.utils.ValidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

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
    private SortUtil sortedService;
    @Autowired
    private ValidUtils validUtils;

    public List<Car> getList(String sort, Map<String, ArrayList<String>> filter) {

        boolean ofFilter = filter != null && !filter.isEmpty();
        boolean ofSort = sort != null && !sort.isEmpty();

        assert filter != null;
        String query = QueryFilterUtil.getQuery(filter);

        if (ofFilter && ofSort) {
            return sortedService.sorted(sort, carRepo.getCarsByFilter(query));
        } else if (ofSort) {
            return sortedService.sorted(sort, carRepo.findAll());
        } else if (ofFilter) {
            return carRepo.getCarsByFilter(query);
        } else {
            return carRepo.findAll();
        }
    }

    public ResponseEntity<?> add(Car car, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(new ValidError(HttpStatus.BAD_REQUEST.value(),
                    "not valid",
                    LocalDateTime.now(),
                    validUtils.getErrorsValid(bindingResult)), HttpStatus.BAD_REQUEST);
        }


        Boolean carFromDb = carRepo.existsByNumber(car.getNumber());

        if (carFromDb) {
            return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.value(),
                    String.format("Car number: %s exists. Save is not possible", car.getNumber()),
                    LocalDateTime.now()),
                    HttpStatus.CONFLICT);
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
                    String.format("Car with id %d not found", id), LocalDateTime.now()), HttpStatus.NOT_FOUND);
        }
    }

}
