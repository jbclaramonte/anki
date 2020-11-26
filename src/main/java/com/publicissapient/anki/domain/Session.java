package com.publicissapient.anki.domain;

public class Session
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

	public void uncorrectAnswer(Card card)
	{
		boxesManager.getRedBox().getCards().remove(card);
		boxesManager.getOrangeBox().getCards().add(card);
	}
}
