package org.bzb.model.web;

import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import org.bzb.db.DBDriver;

public class RegisterServlet {
	private DBDriver db;
	private String[] REQUIRED_FIELDS;
	private HttpServletRequest request;

	public boolean isValidEmail() {
		throw new UnsupportedOperationException();
	}

	public boolean isValidPassword() {
		throw new UnsupportedOperationException();
	}

	public Hashtable checkRequiredFields() {
		throw new UnsupportedOperationException();
	}

	public boolean createUser() {
		throw new UnsupportedOperationException();
	}
}