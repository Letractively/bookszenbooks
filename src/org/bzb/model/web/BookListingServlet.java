package org.bzb.model.web;

import javax.servlet.http.HttpServletRequest;
import org.bzb.db.DBDriver;

public class BookListingServlet {
	private DBDriver db;
	private HttpServletRequest request;
	private String[] REQUIRED_FIELDS;

	public String outputIsbnForm() {
		throw new UnsupportedOperationException();
	}

	public String outputListingForm() {
		throw new UnsupportedOperationException();
	}

	public String outputListing() {
		throw new UnsupportedOperationException();
	}

	public boolean bookExists(Object int_isbn) {
		throw new UnsupportedOperationException();
	}

	public boolean createBook() {
		throw new UnsupportedOperationException();
	}

	public boolean createListing() {
		throw new UnsupportedOperationException();
	}
}