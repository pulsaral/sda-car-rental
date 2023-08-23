package com.finalexample.demo.service;

import com.finalexample.demo.model.db.Car;
import com.finalexample.demo.model.db.CarListing;
import com.finalexample.demo.model.request.RegisterCarRequest;
import com.finalexample.demo.repository.CarListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final CarListingRepository carListingRepository;

    private final ImageService imageService;

    public void registerNewCar(RegisterCarRequest registerCarRequest) throws IOException {
        CarListing carListing = new CarListing();

        carListing.setTitle(registerCarRequest.getTitle());
        carListing.setDescription(registerCarRequest.getDescription());
        carListing.setPrice(registerCarRequest.getPrice());
        carListing.setCar(buildCar(registerCarRequest));

        CarListing savedCarListing = carListingRepository.save(carListing);
        String imageName = imageService.saveImage(registerCarRequest.getImage(), savedCarListing.getId());
        savedCarListing.setImageName(imageName);

        carListingRepository.save(savedCarListing);
    }

    private Car buildCar(RegisterCarRequest registerCarRequest) {
        Car car = new Car();

        car.setBrand(registerCarRequest.getBrand());
        car.setFuelType(registerCarRequest.getFuelType());
        car.setModel(registerCarRequest.getModel());
        car.setPower(registerCarRequest.getPower());
        car.setProductionYear(registerCarRequest.getYear());

        return car;
    }

}
