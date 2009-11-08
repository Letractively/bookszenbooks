package org.bzb.collection;

import java.util.Hashtable;
import org.bzb.db.entity.setting.SystemSetting;

public class SystemConfig {
	private Hashtable config;

	public SystemSetting getSetting(String key) {
		throw new UnsupportedOperationException();
	}

	public boolean load() {
		throw new UnsupportedOperationException();
	}

	public boolean clearCache() {
		throw new UnsupportedOperationException();
	}

	private boolean loadCache() {
		throw new UnsupportedOperationException();
	}
}