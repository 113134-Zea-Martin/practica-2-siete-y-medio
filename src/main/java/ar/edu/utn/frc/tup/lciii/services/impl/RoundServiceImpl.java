package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.RoundEntity;
import ar.edu.utn.frc.tup.lciii.models.Round;
import ar.edu.utn.frc.tup.lciii.models.RoundWinner;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.RoundJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.RoundService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoundServiceImpl implements RoundService {

    @Autowired
    private RoundJpaRepository roundJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void forceEndOfRound(Long id) {
        RoundEntity roundEntity = roundJpaRepository.getReferenceById(id);
        roundEntity.setWinner(RoundWinner.APP);
        roundJpaRepository.save(roundEntity);
    }

    @Override
    public Round getRoundById(Long id) {
        RoundEntity roundEntity = roundJpaRepository.getReferenceById(id);
        if(roundEntity != null) {
            return modelMapper.map(roundEntity, Round.class);
        }else {
            throw new EntityNotFoundException(String.format("The round id %s not found", id));
        }
    }

    @Override
    public void endRound(Long id, RoundWinner roundWinner) {
        RoundEntity roundEntity = roundJpaRepository.getReferenceById(id);
        roundEntity.setWinner(roundWinner);
        roundJpaRepository.save(roundEntity);
    }

    @Override
    public List<Round> getUnfinishedRounds(Long matchId) {
        List<Round> rounds = new ArrayList<>();
        List<RoundEntity> roundEntities = roundJpaRepository.getAllByMatchIdAndWinnerIsNull(matchId);
        if(roundEntities != null && !roundEntities.isEmpty()) {
            roundEntities.forEach(roundEntity -> rounds.add(modelMapper.map(roundEntity, Round.class)));
        }
        return rounds;
    }

    @Override
    public Round saveRound(Round round) {
        RoundEntity roundEntity = roundJpaRepository.save(modelMapper.map(round, RoundEntity.class));
        return modelMapper.map(roundEntity, Round.class);
    }

}
