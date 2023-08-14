package com.example.catalogOfCars;

import com.example.catalogOfCars.domain.Car;
import com.example.catalogOfCars.service.FilterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("/application-test.properties")

class CatalogOfCarsApplicationTests {

	@Autowired
	private FilterService filterService;


	@Test
	void testFilter(){
		List<Car> cars = new ArrayList<>();
        cars.add(new Car((long) 1, "mazda", "T434EP", "black", "1994", LocalDateTime.now()));
        cars.add(new Car((long) 2, "ford", "T434EP", "white", "1986", LocalDateTime.now()));
        cars.add(new Car((long) 3, "ferrari", "T444EP", "red", "1994", LocalDateTime.now()));
        cars.add(new Car((long) 4, "lada", "T434EP", "black", "2000", LocalDateTime.now()));
        cars.add(new Car((long) 5, "honda", "T434EP", "black", "1998", LocalDateTime.now()));
        cars.add(new Car((long) 6, "audi", "T434EP", "yellow", "1994", LocalDateTime.now()));



		Map<String, ArrayList<String>> filters = new HashMap<>();
		ArrayList<String> brands = new ArrayList<>();
		brands.add("ferrari");
		brands.add("mazda");

        ArrayList<String> years = new ArrayList<>();
        years.add("1994");

		filters.put("years", years);
		filters.put("brand", brands);

		List <Car> filterCars = filterService.filterCar(cars, filters);
       // filterCars.forEach(a -> System.out.println(a.getBrand() + " " + a.getYearOfIssue() ));
		assertTrue(filterCars.stream().allMatch(c -> years.contains(c.getYearOfIssue()) && brands.contains(c.getBrand())));
	}
}
