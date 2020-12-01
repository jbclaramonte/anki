package com.publicissapient.anki;

import com.publicissapient.anki.domain.Deck;
import com.publicissapient.anki.domain.Session;
import com.publicissapient.anki.spi.DeckIO;
import com.publicissapient.anki.spi.SessionIO;

public class Anki
{

	private final SessionIO	sessionIO;
	private final DeckIO	deckIO;
	private Session			session	= null;

	public Anki(SessionIO sessionIO, DeckIO deckIO)
	{
		this.sessionIO = sessionIO;
		this.deckIO = deckIO;
	}

	public Session loadDeck(Deck deck)
	{
		session = new Session(sessionIO);
		session.load(deck);
		return session;
	}

	public Session loadSession(String mySession)
	{
		this.session = sessionIO.load(mySession);
		return this.session;
	}

	public Session loadSessionByDeck(String deckName)
	{
		this.session = new Session(this.sessionIO);
		Deck deck = deckIO.load(deckName);

		this.session.load(deck);
		return this.session;
	}
}
