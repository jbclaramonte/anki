package com.publicissapient.anki.domain;

import java.util.HashSet;
import java.util.Set;

public class Deck {

	private Set<Card> cardList = new HashSet<Card>();
	
	public Deck()
	{
		
	}
	
	
	public Set<Card> getCardList()
	{
		return cardList;
	}
	
	public void addCard(Card card)
	{
		this.cardList.add(card);
	}
	
	
}
