package com.publicissapient.anki.spi.file;

import com.publicissapient.anki.domain.Card;
import com.publicissapient.anki.domain.Deck;
import com.publicissapient.anki.spi.DeckIO;
import com.publicissapient.anki.spi.DeckIOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class FileDeckIO implements DeckIO {

  @Override
  public Deck load(String deckName) throws DeckIOException {
    Deck deck = new Deck();
    try {
      URL resource = getClass().getClassLoader().getResource(deckName + ".deck");
      File file = new File(resource.toURI());
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      bufferedReader.lines()
          .forEach(s -> {
            String[] split = s.trim().split(";");
            deck.addCard(new Card(split[0], split[1]));
          });
    } catch (Exception e) {
      throw new DeckIOException(e.getMessage(), e);
    }
    return deck;
  }
}
