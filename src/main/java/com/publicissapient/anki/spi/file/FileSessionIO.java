package com.publicissapient.anki.spi.file;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.anki.domain.Session;
import com.publicissapient.anki.spi.SessionIO;

public class FileSessionIO implements SessionIO
{
	private String sessionLocation = null;

	public FileSessionIO(String sessionLocation)
	{
		this.sessionLocation = sessionLocation;
	}

	@Override
	public void save(Session session)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Session load(String mySession)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Session session, String fileName) throws FileSessionIOException
	{
		if (null != this.sessionLocation)
		{
			ObjectMapper objectMapper = new ObjectMapper();
			try
			{
				objectMapper.writeValue(new File(this.sessionLocation + "/" + fileName), session);
			}
			catch (IOException e)
			{
				throw new FileSessionIOException(e.getMessage(), e);
			}
		}
	}

}
