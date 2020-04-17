package ch.hearc.todont.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.todont.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
}