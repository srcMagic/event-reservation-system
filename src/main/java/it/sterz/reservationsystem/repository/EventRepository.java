package it.sterz.reservationsystem.repository;

import it.sterz.reservationsystem.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
