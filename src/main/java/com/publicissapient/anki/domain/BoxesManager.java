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

	public Box getGreenBox() {
		return greenBox;
	}

	public Box getOrangeBox() {
		return orangeBox;
	}

	public Box getRedBox() {
		return redBox;
	}

}
