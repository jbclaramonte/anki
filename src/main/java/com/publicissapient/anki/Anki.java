package com.publicissapient.anki;

import com.publicissapient.anki.domain.Deck;
import com.publicissapient.anki.domain.Session;

public class Anki
{
	private Session session = null;

	public Session load(Deck deck)
	{
		session = new Session(null);
		session.load(deck);
		return session;
	}
}
