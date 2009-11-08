package org.bzb.db.entity.i18n;

import java.util.ArrayList;
import org.bzb.db.entity.DBObject;

public class LexiconTopic extends DBObject {
	private String name;
	ArrayList<LexiconEntry> lexiconEntry = new ArrayList<LexiconEntry>();
}