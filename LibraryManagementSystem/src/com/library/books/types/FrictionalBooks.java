package com.library.books.types;

import com.library.books.Book;

public class FrictionalBooks implements Book {
	private String bookTitle;
	private boolean bookStatus;
	public FrictionalBooks(String bookTitle) {
		this.bookTitle = bookTitle;
		this.bookStatus = false;
	}

	@Override
	public void borrow() {
		if(bookStatus==false)
		{
			bookStatus=true;
			System.out.println(bookTitle+" You got the Book");
		}
		else {
			bookStatus=false;
			System.out.println(bookTitle+" is already borrowed");
		}
	}

	@Override
	public void returned() {
		if(bookStatus==true)
		{
			bookStatus=false;
			System.out.println(bookTitle+" is returned");
		}
		else {
			bookStatus=false;
			System.out.println(bookTitle+" is not borrowed");
		}
		
	}

	@Override
	public boolean isBoorowed() {
		return bookStatus;
	}
	
	public String getBookTitle()
	{
		return bookTitle;
	}

}
