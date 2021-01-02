package com.publicissapient.anki.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.publicissapient.anki.spi.SessionIO;
import com.publicissapient.anki.spi.SessionIOException;
import com.publicissapient.anki.spi.file.FileSessionIO;
import com.publicissapient.anki.spi.file.FileSessionIOException;

import java.util.Set;

public class Session
{
	private BoxesManager	boxesManager	= new BoxesManager();
	private SessionIO		io				= null;
	private Card            PulledCard      = null;
	private String          name            = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Session(){
		this.io = new FileSessionIO();
	}

	public Session(SessionIO p_io) {
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


	public Card getPulledCard(){
		return  PulledCard;
	}

	public Card pullCard() {
		Set<Card> cardSet = boxesManager.getRedBox().getCards();
		if(! cardSet.isEmpty()){
			PulledCard = cardSet.iterator().next();
		}
		else{
			PulledCard = null;
		}
		return PulledCard;
	}

	public boolean answer(String response) {
		boolean success = false;
		if(null != PulledCard) {
			if(PulledCard.getAnswer().equalsIgnoreCase(response)){
				this.correctAnswer(PulledCard);
				success = true;
			}
			else{
				this.uncorrectAnswer(PulledCard);
				success = false;
			}
		}

		return success;
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

	public void save() throws FileSessionIOException {
		System.out.println("save() : io=" + this.io);
		this.io.save(this);
	}


	public void start(){
		this.getBoxesManager().getRedBox().addCards(this.boxesManager.getOrangeBox().getCards());
		this.getBoxesManager().getOrangeBox().clearCards();
		this.getBoxesManager().getOrangeBox().addCards(this.getBoxesManager().getGreenBox().getCards());
		this.getBoxesManager().getGreenBox().clearCards();
	}

}
