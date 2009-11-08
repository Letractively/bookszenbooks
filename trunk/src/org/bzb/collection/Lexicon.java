package org.bzb.collection;

import java.util.Hashtable;
import org.bzb.db.entity.i18n.LexiconEntry;

public class Lexicon {
	private Hashtable lexicons;

	public LexiconEntry getLexicon(String language, String topic, String key) {
		throw new UnsupportedOperationException();
	}

	public boolean load(String language, String topic) {
		throw new UnsupportedOperationException();
	}

	public boolean clearCache() {
		throw new UnsupportedOperationException();
	}

	public boolean exists(String key) {
		throw new UnsupportedOperationException();
	}

	private boolean loadCache(String language, String topic) {
		throw new UnsupportedOperationException();
	}
}