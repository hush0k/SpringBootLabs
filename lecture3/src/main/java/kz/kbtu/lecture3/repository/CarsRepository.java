package kz.kbtu.lecture3.repository;

import kz.kbtu.lecture3.api.dto.CarDto;
import kz.kbtu.lecture3.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarsRepository extends JpaRepository<Car, UUID> {
    List<CarDto> findAllByName(String name);
}
