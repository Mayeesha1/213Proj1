
/**
This class defines a book with a (serial) number, name (title), checked out status,
and publishing date (from the date class). It is used in the library class and describes
an individual book with properties. 
@author mayeesha, rebecca
*/
public class Book {	
	
	private String number; //a 5-digit serial number unique to the book
	private String name;
	private boolean checkedOut;
	private Date datePublished;
	//...
	
	/**
	This constructor uses the name of the book and the date published to describe a book.
	The book is not checked out and the book is given a serial number by default. (**Using
	the name of the book and date published, a new book is created and assigned a serial number.)
	@param name of book (title) and date of book
	@param date published
	*/
	public Book(String name, String date) {
		number="0";
		this.name=name;
		checkedOut=false;
		datePublished=new Date(date);
	}
	
	/**
	This constructor uses the serial number to create another book object. (**It is used
	to help find a book that already exists in the library rather than creating a "new" book.)
	@param serial number
	*/
	public Book(String serial) {
		number=serial;
		name=null;
		checkedOut=false;
		datePublished=null;
	}
	
	/**
	Getter method for serial number so it can be used in other classes
	@return serial number
	*/
	public String getNumber() {
		return number;
	}
	
	/**
	Setter method for serial number so the value can be set/changed in other classes
	@param number that keeps track of each book 
	*/
	public void setNumber(String number) {
		this.number=number;
	}
	
	/**
	Getter method for name of book so it can be used in other classes
	@return name of book
	*/
	public String getName() {
		return name;
	}
	
	/**
	Getter method for checked out status so it can be used in other classes
	@return checked out status (true if already checked out, false if not)
	*/
	public boolean getCheckedOut() {
		return checkedOut;
	}
	
	/**
	Setter method for checked out status so the value can be set/changed in other classes
	@param checkedOut boolean (false or true)
	*/
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut=checkedOut;
	}
	
	/**
	Getter method for date published so it can be used in other classes
	@return date published
	*/
	public Date getDatePublished() {
		return datePublished;
	}
	
	/**
	The method checks if books are equal based on their serial numbers.
	@param obj of type book to check current book with another obj of type book
	@return true if books are equal, false if not
	*/
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Book) {
			Book book=(Book) obj;
			return book.number.equals(this.number);
		}
		return false;
	}
	
	/**
	The method creates a string description of the book and its properties (serial
	number, name, date published, and checked out status).
	@return string description
	*/
	@Override
	public String toString() {
		if(checkedOut) {
			return "Book#"+number+"::"+name+"::"+String.valueOf(datePublished.getMonth())
					+"/"+String.valueOf(datePublished.getDay())+"/"
					+String.valueOf(datePublished.getYear())+"::is checked out.";
		}
		return "Book#"+number+"::"+name+"::"+String.valueOf(datePublished.getMonth())
			+"/"+String.valueOf(datePublished.getDay())+"/"
			+String.valueOf(datePublished.getYear())+"::is available.";
	}
	
}

