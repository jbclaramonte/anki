package com.publicissapient.anki;

import com.publicissapient.anki.domain.Deck;
import com.publicissapient.anki.domain.Session;
import com.publicissapient.anki.spi.DeckIO;
import com.publicissapient.anki.spi.DeckIOException;
import com.publicissapient.anki.spi.SessionIO;
import com.publicissapient.anki.spi.SessionIOException;

public class Anki
{
	public static final String DEFAULT_VAR_DIRECTORY = 	System.getProperty("user.dir") + "/var";

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

	public Session getSession(){
		return this.session;
	}

	public Session loadSession(String mySession)
	{
		this.session = sessionIO.load(mySession);
		return this.session;
	}

	public Session loadSessionByDeck(String deckName) throws DeckIOException
	{
		this.session = new Session(this.sessionIO);
		Deck deck = deckIO.load(deckName);

		this.session.load(deck);
		return this.session;
	}

	public void saveSession(String sessionName) throws SessionIOException
	{
		if ((null != this.sessionIO) && (null != this.session))
		{
			this.sessionIO.save(session, sessionName);
		}
	}
}
