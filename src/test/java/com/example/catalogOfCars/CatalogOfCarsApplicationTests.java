package com.example.catalogOfCars;

import com.example.catalogOfCars.domain.Car;
import com.example.catalogOfCars.repo.CarRepo;
import com.example.catalogOfCars.utils.QueryFilterUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class CatalogOfCarsApplicationTests {

    @Autowired
    private CarRepo carRepo;


    @Test
    void testFilter() {

        Map<String, ArrayList<String>> filters = new HashMap<>();
        ArrayList<String> brands = new ArrayList<>();
        brands.add("ferrari");
        brands.add("mazda");
        brands.add("audi");

        ArrayList<String> years = new ArrayList<>();
        years.add("1994");


        ArrayList<String> color = new ArrayList<>();
        color.add("white");
        color.add("black");


        filters.put("yearOfIssue", years);
        filters.put("color", color);
        filters.put("brand", brands);

        String query = QueryFilterUtil.getQuery(filters);

        List<Car> filterCars = carRepo.getCarsByFilter(query);
        System.out.println(filterCars);
        assertTrue(filterCars.stream().allMatch(c ->
                color.contains(c.getColor())
                        && brands.contains(c.getBrand())
                        && years.contains(c.getYearOfIssue())));
    }
}
