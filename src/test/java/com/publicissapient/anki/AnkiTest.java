package com.publicissapient.anki;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.publicissapient.anki.domain.Card;
import com.publicissapient.anki.domain.Deck;
import com.publicissapient.anki.domain.Session;
import com.publicissapient.anki.spi.SessionIO;

public class AnkiTest
{

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
		Session ankiSession = anki.load(deck);
		Card pullCard = ankiSession.pullCard();

		// When
		ankiSession.correctAnswer(pullCard);

		// Then
		Assert.assertTrue(ankiSession.getBoxesManager().getGreenBox().getCards().contains(pullCard));
		Assert.assertFalse(ankiSession.getBoxesManager().getRedBox().getCards().contains(pullCard));
		Assert.assertFalse(ankiSession.getBoxesManager().getOrangeBox().getCards().contains(pullCard));
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
		Session ankiSession = anki.load(deck);
		Card pullCard = ankiSession.pullCard();

		// When
		ankiSession.uncorrectAnswer(pullCard);

		// Then
		Assert.assertTrue(ankiSession.getBoxesManager().getOrangeBox().getCards().contains(pullCard));
		Assert.assertFalse(ankiSession.getBoxesManager().getRedBox().getCards().contains(pullCard));
		Assert.assertFalse(ankiSession.getBoxesManager().getGreenBox().getCards().contains(pullCard));
	}

	@Test
	public void test_when_we_load_a_deck_for_the_first_time_a_session_is_created()
	{
		// Given
		Anki anki = new Anki();
		Deck deck = new Deck();
		Card card1 = new Card("Q1", "R1");
		Card card2 = new Card("Q2", "R2");
		deck.addCard(card1);
		deck.addCard(card2);

		// When
		Session session = anki.load(deck);

		// Then
		Assert.assertTrue(session.getBoxesManager().getRedBox().getCards().contains(card1));
		Assert.assertTrue(session.getBoxesManager().getRedBox().getCards().contains(card2));
		Assert.assertEquals(2, session.getBoxesManager().getRedBox().getCards().size());
		Assert.assertEquals(0, session.getBoxesManager().getGreenBox().getCards().size());
		Assert.assertEquals(0, session.getBoxesManager().getOrangeBox().getCards().size());

	}

	@Test
	public void test_when_session_is_saved_save_interface_is_called()
	{
		// Given
		SessionIO sessionIO = Mockito.mock(SessionIO.class);
		Session session = new Session(sessionIO);

		// When
		session.save();

		// Then
		Mockito.verify(sessionIO, Mockito.times(1)).save(session);
	}

}
