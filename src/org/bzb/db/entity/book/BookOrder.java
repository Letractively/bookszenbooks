package org.bzb.db.entity.book;

import java.util.Date;
import org.bzb.db.entity.user.User;

public class BookOrder {
	private int orderId;
	private int buyerId;
	private int sellerId;
	private int isbn;
	private Date orderDate;
	private String paymentMethod;
	private User user;
	private BookListing bookListing;
	User places;
	BookListing unnamed_BookListing_;
}