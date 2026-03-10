package kz.kbtu.lecture3.service;

import kz.kbtu.lecture3.api.dto.CarDto;
import kz.kbtu.lecture3.domain.Car;
import kz.kbtu.lecture3.repository.CarsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CarService {
    @Autowired
    private CarsRepository carsRepository;

    public void createCar(
            String brand,
            String name,
            Integer horsePower
    ) {
        Car car = Car
                .builder()
                .brand(brand)
                .name(name)
                .horsePower(horsePower)
                .build();

         carsRepository.save(car);
         log.info("Car with brand {} name{} horse power{} has been created", brand, name, horsePower);
    }

    public CarDto findById(UUID id) {
        Car car = carsRepository.findById(id).orElseThrow();

        return CarDto.builder()
                .id(car.getId())
                .brandAndName(car.getBrand() + " " + car.getName())
                .horsePower(car.getHorsePower())
                .build();


    }

    public List<CarDto> findAllByName(String name) {
        return carsRepository.findAllByName(name);
    }
}
