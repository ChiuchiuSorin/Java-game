package game.repository;

import game.model.SaveGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveGameRepository extends JpaRepository<SaveGame, Long> {
}
