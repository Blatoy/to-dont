package ch.hearc.todont.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.todont.models.Pledge;
import ch.hearc.todont.models.PledgeKey;
import ch.hearc.todont.models.ToDont;
import ch.hearc.todont.models.User;

public interface PledgeRepository extends JpaRepository<Pledge, PledgeKey> {
    Pledge findByUserAndToDont(User user, ToDont toDont);
}