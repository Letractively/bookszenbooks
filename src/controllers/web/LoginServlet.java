package controllers.web;

import javax.servlet.http.HttpServletRequest;
import data.DBDriver;
import business.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String forwardUrl = "/login.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher( forwardUrl );

        dispatcher.forward( request, response );
    }

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