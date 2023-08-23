package com.finalexample.demo.repository;

import com.finalexample.demo.model.db.CarListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarListingRepository extends JpaRepository<CarListing, Long> {
}
