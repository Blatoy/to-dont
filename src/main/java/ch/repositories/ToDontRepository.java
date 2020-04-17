package ch.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.todont.models.ToDont;

public interface ToDontRepository extends CrudRepository<ToDont, UUID> {

}