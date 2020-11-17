package com.publicissapient.anki.domain;

import static org.junit.Assert.assertNotNull;

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
		
		Assert.assertNotNull(myAnki.getBoxManager().GreenBox());
		Assert.assertNotNull(myAnki.getBoxManager().getOrangeBox());
		Assert.assertNotNull(myAnki.getBoxManager().getRedBox());
	}
}
