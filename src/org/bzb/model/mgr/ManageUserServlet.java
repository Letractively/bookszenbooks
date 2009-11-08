package org.bzb.model.mgr;

import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import org.bzb.db.DBDriver;
import org.bzb.db.entity.user.User;

public class ManageUserServlet {
	private HttpServletRequest request;
	private DBDriver db;
	private String[] requiredFields;

	public String outputUserForm() {
		throw new UnsupportedOperationException();
	}

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

	public boolean removeUser() {
		throw new UnsupportedOperationException();
	}

	public User getUser() {
		throw new UnsupportedOperationException();
	}
}