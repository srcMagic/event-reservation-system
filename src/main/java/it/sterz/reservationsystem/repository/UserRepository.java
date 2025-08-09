package it.sterz.reservationsystem.repository;

import it.sterz.reservationsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
