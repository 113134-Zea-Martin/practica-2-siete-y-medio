package ar.edu.utn.frc.tup.lciii.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.entities.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundJpaRepository extends JpaRepository<RoundEntity, Long> {

    List<RoundEntity> getAllByMatchIdAndWinnerIsNull(Long matchId);
}
