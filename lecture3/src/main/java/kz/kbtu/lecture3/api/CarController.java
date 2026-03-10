package kz.kbtu.lecture3.api;

import kz.kbtu.lecture3.api.dto.CarDto;
import kz.kbtu.lecture3.api.dto.CreateCarRequestDto;
import kz.kbtu.lecture3.domain.Car;
import kz.kbtu.lecture3.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CarController {
    @Autowired
    CarService carService;

    @PostMapping("/car/create")
    public void createCar(@RequestBody CreateCarRequestDto dto){
        carService.createCar(
                dto.brand(),
                dto.name(),
                dto.horsePower()
        );
    }

    @GetMapping("/car/{id}")
    public CarDto getCar(@PathVariable UUID id){
        return carService.findById(id);
    }

    @GetMapping("/car/all/name/{name}")
    public List<CarDto> getAllCarsByName(@PathVariable String name){
        return carService.findAllByName(name);
    }
}
