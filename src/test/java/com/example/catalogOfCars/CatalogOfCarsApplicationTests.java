//package com.example.catalogOfCars;
//
//import com.example.catalogOfCars.domain.Car;
//import com.example.catalogOfCars.service.FilterService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//
//class CatalogOfCarsApplicationTests {
//
//	@Autowired
//	private FilterService filterService;
//
//
//	@Test
//	void testFilter(){
//		List<Car> cars = new ArrayList<>();
//
//
//		Map<String, ArrayList<String>> filters = new HashMap<>();
//		ArrayList<String> brands = new ArrayList<>();
//		brands.add("ferrari");
//		brands.add("mazda");
//
//        ArrayList<String> years = new ArrayList<>();
//        years.add("1994");
//
//		filters.put("years", years);
//		filters.put("brand", brands);
//
//		List <Car> filterCars = filterService.filterCar(cars, filters);
//		assertTrue(filterCars.stream().allMatch(c -> years.contains(c.getYearOfIssue()) && brands.contains(c.getBrand())));
//
//	}
//}
