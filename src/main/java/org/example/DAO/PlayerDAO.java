package org.example.DAO;


import org.example.Models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PlayerDAO extends JpaRepository<Player, Integer> {

    Optional<Player> findByName(String name);

}
