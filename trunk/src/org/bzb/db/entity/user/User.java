package org.bzb.db.entity.user;

import org.bzb.db.entity.book.BookOrder;
import java.util.ArrayList;
import java.util.Date;
import org.bzb.db.entity.DBObject;
import org.bzb.db.entity.book.BookListing;

public class User extends DBObject {
	private int userId;
	private boolean isAdmin;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String state;
	private String country;
	private String postalCode;
	private Date joinDate;
	private Date birthDate;
	private boolean isValidated;
	private String phone;
	ArrayList<BookListing> unnamed_BookListing_ = new ArrayList<BookListing>();
	BookOrder places;
}