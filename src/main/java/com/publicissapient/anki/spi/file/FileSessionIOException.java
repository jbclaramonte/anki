package com.publicissapient.anki.spi.file;

import com.publicissapient.anki.spi.SessionIOException;

@SuppressWarnings("serial")
public class FileSessionIOException extends SessionIOException
{

	public FileSessionIOException(String message, Exception e)
	{
		super(message, e);
	}
}
