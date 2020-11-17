package com.publicissapient.anki.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Box 
{
	private Set<Card> cardList;
	
	public Box(){
		this.cardList = new HashSet<Card>();
	}
	
	
	public Set<Card> getCards(){
		return this.cardList;
	}
	
	
	public void addCards(Set<Card> cardList)
	{
		this.cardList.addAll(cardList);
	}
	
	
	public void clearCards()
	{
		this.cardList.clear();		
	}
	
	public int size()
	{
		return this.cardList.size();
	}
}
