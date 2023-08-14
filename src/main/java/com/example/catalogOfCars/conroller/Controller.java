package com.example.catalogOfCars.conroller;

import com.example.catalogOfCars.domain.Car;
import com.example.catalogOfCars.domain.CarStat;
import com.example.catalogOfCars.service.CarService;
import com.example.catalogOfCars.service.CarStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/api/car")
public class Controller {
    @Autowired
    private CarService carService;
    @Autowired
    private CarStatService carStatService;

    @GetMapping
    public List<Car> getList(@RequestParam(required = false) String sort,
                             @RequestBody(required = false) Map<String, ArrayList<String>> filter) {

        return carService.getList(sort, filter);

    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Car car) {
        return carService.add(car);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCat(@PathVariable Long id) {
        return carService.deleteCar(id);
    }

    @GetMapping("/stat")
    public CarStat get() {
        return carStatService.get();
    }
}
