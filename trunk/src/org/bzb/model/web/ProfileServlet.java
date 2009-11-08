package org.bzb.model.web;

import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import org.bzb.db.DBDriver;

public class ProfileServlet {
	private String[] REQUIRED_FIELDS;
	private DBDriver db;
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

	public boolean updateUser() {
		throw new UnsupportedOperationException();
	}

	public boolean checkCredentials() {
		throw new UnsupportedOperationException();
	}

	public String outputProfileForm() {
		throw new UnsupportedOperationException();
	}
}