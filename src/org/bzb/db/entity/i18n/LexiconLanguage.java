package org.bzb.db.entity.i18n;

import java.util.ArrayList;
import org.bzb.db.entity.DBObject;
import org.bzb.db.entity.i18n.LexiconEntry;

public class LexiconLanguage extends DBObject {
	private String language;
	private String name;
	ArrayList<LexiconEntry> unnamed_LexiconEntry_ = new ArrayList<LexiconEntry>();
}