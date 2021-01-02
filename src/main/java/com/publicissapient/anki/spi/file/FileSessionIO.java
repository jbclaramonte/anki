package com.publicissapient.anki.spi.file;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.anki.Anki;
import com.publicissapient.anki.domain.Session;
import com.publicissapient.anki.spi.SessionIO;

public class FileSessionIO implements SessionIO
{
	public static final String DEFAULT_SESSIONS_DIR = Anki.DEFAULT_VAR_DIRECTORY + "/sessions";
	public static final String DEFAULT_SESSION_NAME = "~lastSession";

	private String sessionLocation = null;

	public FileSessionIO()
	{
		this.sessionLocation = DEFAULT_SESSIONS_DIR;
	}

	public FileSessionIO(String sessionLocation)
	{
		this.sessionLocation = sessionLocation;
	}

	@Override
	public void save(Session session) throws FileSessionIOException
	{
		if(session.getName() == null)
		{
			this.save(session, DEFAULT_SESSION_NAME);
		}
		else
		{
			this.save(session, session.getName());
		}
	}

	@Override
	public void save(Session session, String fileName) throws FileSessionIOException
	{
		if (null != this.sessionLocation)
		{
			ObjectMapper objectMapper = new ObjectMapper();
			try
			{
				File sessionFile = new File(this.sessionLocation + "/" + fileName);
				objectMapper.writeValue(sessionFile, session);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				throw new FileSessionIOException(e.getMessage(), e);
			}
		}
	}

	@Override
	public Session load(String mySession)
	{
		if (null != this.sessionLocation)
		{
			ObjectMapper objectMapper = new ObjectMapper();
			try
			{
				File sessionFile = new File(this.sessionLocation + "/" + mySession);
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				Session session = objectMapper.readValue(sessionFile, Session.class);
				session.setName(mySession);
				session.start();
				return session;
			}
			catch (IOException  e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}
}
