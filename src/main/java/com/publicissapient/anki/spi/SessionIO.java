package com.publicissapient.anki.spi;

import com.publicissapient.anki.domain.Session;

public interface SessionIO
{
	void save(Session session);

  Session load(String mySession);
}
