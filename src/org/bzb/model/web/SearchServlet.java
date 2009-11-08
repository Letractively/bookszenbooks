package org.bzb.model.web;

import java.util.ArrayList;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import org.bzb.db.DBDriver;

public class SearchServlet {
	private DBDriver db;
	private HttpServletRequest request;

	public Hashtable buildSearchParams() {
		throw new UnsupportedOperationException();
	}

	public ArrayList getList(Hashtable criteria) {
		throw new UnsupportedOperationException();
	}

	public String outputResults(ArrayList list) {
		throw new UnsupportedOperationException();
	}
}