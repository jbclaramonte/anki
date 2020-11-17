package com.publicissapient.anki;

import static org.junit.Assert.assertNotNull;

import com.publicissapient.anki.domain.Card;
import com.publicissapient.anki.domain.Deck;

import org.junit.Assert;
import org.junit.Test;

public class AnkiTest 
{
	@Test
	public void test_WeHave3Boxes()
	{
		// Given : 
		Anki myAnki = new Anki();
		
		// When :
		// Then :
		
		assertNotNull(myAnki.getBoxesManager().getGreenBox());
		assertNotNull(myAnki.getBoxesManager().getOrangeBox());
		assertNotNull(myAnki.getBoxesManager().getRedBox());
	}

	@Test
	public void test_anki_loads_deck_in_redbox() {
		// Given
		Anki anki = new Anki();
		Deck deck = new Deck();
		Card card = new Card("Q", "R");
		deck.addCard(card);

		// When
		anki.load(deck);

		// Then
		Assert.assertEquals(1, anki.getBoxesManager().getRedBox().size());
		Assert.assertTrue(anki.getBoxesManager().getRedBox().getCards().contains(card));

	}


}
