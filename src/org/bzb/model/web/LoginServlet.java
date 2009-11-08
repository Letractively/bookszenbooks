package org.bzb.model.web;

import javax.servlet.http.HttpServletRequest;
import org.bzb.db.DBDriver;
import org.bzb.db.entity.user.User;

public class LoginServlet {
	private DBDriver db;
	private HttpServletRequest request;

	public User getUser() {
		throw new UnsupportedOperationException();
	}

	public boolean checkRequiredFields() {
		throw new UnsupportedOperationException();
	}

	public void startSession() {
		throw new UnsupportedOperationException();
	}

	public void endSession() {
		throw new UnsupportedOperationException();
	}

	public String outputLoginForm() {
		throw new UnsupportedOperationException();
	}
}