package com.publicissapient.anki;

import com.publicissapient.anki.domain.Deck;
import com.publicissapient.anki.domain.Session;
import com.publicissapient.anki.spi.SessionIO;

public class Anki
{

	private final SessionIO sessionIO;
	private Session session = null;

	public Anki(SessionIO sessionIO) {
		this.sessionIO = sessionIO;
	}


	public Session loadDeck(Deck deck)
	{
		session = new Session(sessionIO);
		session.load(deck);
		return session;
	}

	public Session loadSession(String mySession) {
		this.session = sessionIO.load(mySession);
		return this.session;
	}
}
