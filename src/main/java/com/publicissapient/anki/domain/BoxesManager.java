package com.publicissapient.anki.domain;


public class BoxesManager {
	
	private Box greenBox;
	private Box orangeBox;
	private Box redBox;
	
	
	public BoxesManager()
	{
		greenBox = new Box();
		orangeBox = new Box();
		redBox = new Box();
	}
	
	
	public void beginSession() {
		
		redBox.addCards(orangeBox.getCards());
		orangeBox.clearCards();
		orangeBox.addCards(greenBox.getCards());
		greenBox.clearCards();
	}
	
	public void endSession() {
		
	}
}
