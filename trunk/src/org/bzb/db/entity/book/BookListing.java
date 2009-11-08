package org.bzb.db.entity.book;

import java.util.Date;
import org.bzb.db.entity.DBObject;
import org.bzb.db.entity.user.User;

public class BookListing extends DBObject {
	private int userId;
	private Book book;
	private User user;
	private double price;
	private String comment;
	private String currency;
	private int edition;
	private Date listDate;
	private String condition;
	private boolean isActive;
	private int isbn;
	User unnamed_User_;
	BookOrder unnamed_BookOrder_;
	Book unnamed_Book_;
}