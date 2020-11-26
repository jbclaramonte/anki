package com.publicissapient.anki;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

import com.publicissapient.anki.domain.Card;
import com.publicissapient.anki.domain.Deck;

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
	public void test_anki_loads_deck_in_redbox()
	{
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

	@Test
	public void test_anki_pull_card_from_redbox()
	{
		// Given
		Anki anki = new Anki();
		Deck deck = new Deck();
		Card card1 = new Card("Q1", "R1");
		Card card2 = new Card("Q2", "R2");
		deck.addCard(card1);
		deck.addCard(card2);
		anki.load(deck);

		// When
		Card myCard1 = anki.pullCard();

		// Then
		Assert.assertTrue(anki.getBoxesManager().getRedBox().getCards().contains(myCard1));
	}

	@Test
	public void test_reponse_for_card_is_correct()
	{
		// Given
		Anki anki = new Anki();
		Deck deck = new Deck();
		Card card1 = new Card("Q1", "R1");
		Card card2 = new Card("Q2", "R2");
		deck.addCard(card1);
		deck.addCard(card2);
		anki.load(deck);
		Card pullCard = anki.pullCard();

		// When
		anki.correctAnswer(pullCard);

		// Then
		Assert.assertTrue(anki.getBoxesManager().getGreenBox().getCards().contains(pullCard));
		Assert.assertFalse(anki.getBoxesManager().getRedBox().getCards().contains(pullCard));
	}

	@Test
	public void test_reponse_for_card_is_not_correct()
	{
		// Given
		Anki anki = new Anki();
		Deck deck = new Deck();
		Card card1 = new Card("Q1", "R1");
		Card card2 = new Card("Q2", "R2");
		deck.addCard(card1);
		deck.addCard(card2);
		anki.load(deck);
		Card pullCard = anki.pullCard();

		// When
		anki.uncorrectAnswer(pullCard);

		// Then
		Assert.assertTrue(anki.getBoxesManager().getRedBox().getCards().contains(pullCard));
		Assert.assertFalse(anki.getBoxesManager().getGreenBox().getCards().contains(pullCard));
	}

}
