package com.publicissapient.anki.spi;

import com.publicissapient.anki.domain.Deck;

public interface DeckIO
{
	Deck load(String deckName) throws DeckIOException;
}
