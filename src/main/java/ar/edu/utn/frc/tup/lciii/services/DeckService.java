package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Card;
import ar.edu.utn.frc.tup.lciii.models.Deck;
import org.springframework.stereotype.Service;

@Service
public interface DeckService {

    Deck createDeck();

    void shuffleDeck(Deck deck);

    Card takeCard(Deck deck, Integer deckIndex);
}
