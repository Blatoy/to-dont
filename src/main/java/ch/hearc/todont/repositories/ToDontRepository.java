package ch.hearc.todont.repositories;

import ch.hearc.todont.models.ToDont;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDontRepository extends JpaRepository<ToDont, UUID> {

}