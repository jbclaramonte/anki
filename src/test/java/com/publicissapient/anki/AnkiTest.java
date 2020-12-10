package com.publicissapient.anki;

import com.publicissapient.anki.spi.DeckIOException;
import com.publicissapient.anki.spi.file.FileDeckIO;
import java.io.File;
import java.nio.file.Paths;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.publicissapient.anki.domain.Card;
import com.publicissapient.anki.domain.Deck;
import com.publicissapient.anki.domain.Session;
import com.publicissapient.anki.spi.DeckIO;
import com.publicissapient.anki.spi.SessionIO;

public class AnkiTest
{

	@Test
	public void test_reponse_for_card_is_correct()
	{
		// Given
		Anki anki = new Anki(null, null);
		Deck deck = new Deck();
		Card card1 = new Card("Q1", "R1");
		Card card2 = new Card("Q2", "R2");
		deck.addCard(card1);
		deck.addCard(card2);
		Session ankiSession = anki.loadDeck(deck);
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
		Anki anki = new Anki(null, null);
		Deck deck = new Deck();
		Card card1 = new Card("Q1", "R1");
		Card card2 = new Card("Q2", "R2");
		deck.addCard(card1);
		deck.addCard(card2);
		Session ankiSession = anki.loadDeck(deck);
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
		Anki anki = new Anki(null, null);
		Deck deck = new Deck();
		Card card1 = new Card("Q1", "R1");
		Card card2 = new Card("Q2", "R2");
		deck.addCard(card1);
		deck.addCard(card2);

		// When
		Session session = anki.loadDeck(deck);

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

	@Test
	public void test_when_session_is_loaded_load_interface_is_called()
	{
		// Given
		SessionIO sessionIO = Mockito.mock(SessionIO.class);
		Anki anki = new Anki(sessionIO, null);

		// When
		Session session = anki.loadSession("MySession");

		// Then
		Mockito.verify(sessionIO, Mockito.times(1)).load("MySession");
	}

	@Test
	public void test_when_deck_is_loaded_by_name_deckio_is_called() throws DeckIOException {
		// Given
		SessionIO sessionIO = Mockito.mock(SessionIO.class);
		DeckIO deckIO = Mockito.mock(DeckIO.class);
		Mockito.when(deckIO.load("MyDeckName")).thenReturn(new Deck());

		Anki anki = new Anki(sessionIO, deckIO);

		// When
		Session session = anki.loadSessionByDeck("MyDeckName");

		// Then
		Mockito.verify(deckIO, Mockito.times(1)).load("MyDeckName");
	}

	@Test
	public void test_when_deckAnglais_is_loaded_session_contains_deckAnglais_in_redbox()
			throws DeckIOException {
		// Given
		FileDeckIO deckIO = new FileDeckIO();
		Anki anki = new Anki(null, deckIO);

		// When
		Session session = anki.loadSessionByDeck("Anglais");

		// Then
		Assert.assertNotNull(session);
		Assert.assertEquals(4, session.getBoxesManager().getRedBox().getCards().size());
		Assert.assertEquals(0, session.getBoxesManager().getGreenBox().getCards().size());
		Assert.assertEquals(0, session.getBoxesManager().getOrangeBox().getCards().size());

		Card card1 = new Card("Question1", "Answer1");
		Assert.assertTrue(session.getBoxesManager().getRedBox().getCards().contains(card1));
	}

	@Test
	public void test_when_session_is_saved_then_a_file_is_created() {

		// Given
		String sessionLocation = System.getProperty("user.dir");
		FileSessionIO fileSessionIO = new FileSessionIO(sessionLocation);
		Anki anki = new Anki(fileSessionIO, null);
		String sessionName = "MySession";

		// When
		anki.saveSession(sessionName);

		// Then
		String dir = anki.getSessionLocation();
		File file = new File(dir + "/" + sessionName);
		Assert.assertTrue(file.exists());

	}

}
