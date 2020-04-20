package ch.hearc.todont.repositories;

import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.ToDont.Visibility;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDontRepository extends JpaRepository<ToDont, UUID> {

    Page<ToDont> findByVisibility(Visibility visibility);
}