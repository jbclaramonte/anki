package com.publicissapient.anki.domain;

import com.publicissapient.anki.spi.SessionIO;

public class Session
{
	private BoxesManager	boxesManager	= new BoxesManager();
	private SessionIO		io				= null;

	public Session(SessionIO p_io)
	{
		this.io = p_io;
	}

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

	public void save()
	{
		this.io.save(this);
	}
}
