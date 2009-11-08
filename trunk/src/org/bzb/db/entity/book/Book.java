package org.bzb.db.entity.book;

import java.util.Date;
import org.bzb.db.entity.DBObject;

public class Book extends DBObject {
	private int isbn;
	private int categoryId;
	private String title;
	private String author;
	private String publisher;
	private Date publishDate;
	private int pages;
	private String language;
	private BookCategory category;
}