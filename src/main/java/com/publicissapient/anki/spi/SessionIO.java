package com.publicissapient.anki.spi;

import com.publicissapient.anki.domain.Session;

public interface SessionIO
{
	void save(Session session);

	void save(Session session, String fileName) throws SessionIOException;

	Session load(String mySession);

}
