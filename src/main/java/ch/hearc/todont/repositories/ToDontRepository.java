package ch.hearc.todont.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.todont.models.ToDont;

public interface ToDontRepository extends JpaRepository<ToDont, UUID> {

}