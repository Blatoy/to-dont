package ch.hearc.todont.repositories;

import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ToDontRepository extends JpaRepository<ToDont, UUID> {

    @Query(
        "SELECT t "
        + "FROM ToDont t "
        + "WHERE t.visibility = 0 "
        + "AND t.owner = :ownerId "
        + "AND t.name LIKE %:title% "
    )
    Page<ToDont> findPublicToDonts(
        @Param("title") String title,
        @Param("ownerId") User user,
        Pageable pageable
    );

    @Query(
        "SELECT t "
        + "FROM ToDont t "
        + "WHERE t.visibility = 0 "
        + "AND t.name LIKE %:title% "
    )
    Page<ToDont> findPublicToDonts(
        @Param("title") String title,
        Pageable pageable
    );
}