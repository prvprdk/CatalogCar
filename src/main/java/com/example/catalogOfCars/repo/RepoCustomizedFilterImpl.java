package com.example.catalogOfCars.repo;

import com.example.catalogOfCars.domain.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class RepoCustomizedFilterImpl implements RepoCustomizedFilter{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> getCarsByFilter(String query) {
        return entityManager.createQuery(query,Car.class).getResultList();
    }
}
