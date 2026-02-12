package kz.kbtu.tsis3.repository;

import kz.kbtu.tsis3.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
