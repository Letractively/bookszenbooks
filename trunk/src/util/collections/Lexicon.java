package util.collections;

import business.LexiconEntry;
import java.util.HashMap;

public class Lexicon {
	private HashMap<String, String> lexicons;

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