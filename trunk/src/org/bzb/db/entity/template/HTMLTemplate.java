package org.bzb.db.entity.template;

import java.util.Hashtable;
import org.bzb.db.entity.DBObject;

public class HTMLTemplate extends DBObject {
	private String name;
	private String content;

	public String parse(Hashtable properties) {
		throw new UnsupportedOperationException();
	}
}