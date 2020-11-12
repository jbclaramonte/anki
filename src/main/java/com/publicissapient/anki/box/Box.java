package com.publicissapient.anki.box;

import java.util.ArrayList;
import java.util.List;

public class Box 
{
	private List<Card> cardList;
	
	public Box(){
		this.cardList = new ArrayList<Card>();
	}
	
	
	public List<Card> getCards(){
		return this.cardList;
	}
	
	
	public void addCards(List<Card> cardList)
	{
		this.cardList.addAll(cardList);
	}
	
	
	public void clearCards()
	{
		this.cardList.clear();
	}
}
