package com.publicissapient.anki.spi;

@SuppressWarnings("serial")
public class DeckIOException extends Exception
{
	public DeckIOException(String message, Exception e)
	{
		super(message, e);
	}
}
