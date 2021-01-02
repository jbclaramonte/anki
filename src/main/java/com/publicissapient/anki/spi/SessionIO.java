package com.publicissapient.anki.spi;

import com.publicissapient.anki.domain.Session;
import com.publicissapient.anki.spi.file.FileSessionIOException;

public interface SessionIO
{
	void save(Session session) throws FileSessionIOException;

	void save(Session session, String fileName) throws SessionIOException;

	Session load(String mySession);

}
