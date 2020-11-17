package com.publicissapient.anki;

import com.publicissapient.anki.domain.BoxesManager;
import com.publicissapient.anki.domain.Deck;

public class Anki {
  private BoxesManager boxesManager = new BoxesManager();

  public BoxesManager getBoxesManager() {
    return boxesManager;
  }
  
  
  public void load(Deck deck)
  {
	  boxesManager.getRedBox().addCards(deck.getCardList());
  }
}
