package com.publicissapient.anki;

import com.publicissapient.anki.domain.BoxesManager;
import com.publicissapient.anki.domain.Card;
import com.publicissapient.anki.domain.Deck;

public class Anki
{
	private BoxesManager boxesManager = new BoxesManager();

	public BoxesManager getBoxesManager()
	{
		return boxesManager;
	}

	public void load(Deck deck)
	{
		boxesManager.getRedBox().addCards(deck.getCardList());
	}

	public Card pullCard()
	{
		return boxesManager.getRedBox().getCards().iterator().next();
	}

	public void correctAnswer(Card card)
	{
		boxesManager.getRedBox().getCards().remove(card);
		boxesManager.getGreenBox().getCards().add(card);
	}

}
