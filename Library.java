
/**
The library stores all the books in an array-based bag. These books can be
added, removed, checked out, returned, and printed in a list.
@author mayeesha, rebecca
*/
public class Library {
		
	private Book[] books; // array-based implementation of the bag data structure
	private int numBooks; // the number of books currently in the bag
	
	/**
	Default constructor for an empty bag
	*/
	public Library() { //default constructor to create an empty bag
		books=new Book[0]; 
		numBooks=0;
	}
	
	/**
	Method used to help other methods find a book in the bag and returns the
	index of the book in the books array/bag
	@param book to find in the bag
	@return index in bag
	*/
	private int find(Book book) { // helper method to find a book in the bag
		int count= 0;
		while(count<numBooks) {
			if(books[count].equals(book)) {
				return count; //index in array not serial num
			}
			count++;
		}
		//return  count; //equals numBooks
		return -1;
	}
	
	/**
	Helper method to grow the bag's capacity by 4 when the bag is full
	*/
	private void grow() { // helper method to grow the capacity by 4
		int GROW_VAR = 4;
		Book[] temp=new Book[books.length+GROW_VAR];
		for(int i=0;i<numBooks;i++) {
			temp[i]=books[i];
		}
		books=temp;
	}
	
	/**
	The method to add a book to the library/bag. Also, we grow the bag capacity 
	when it's full and only add the book if the date published is valid. 
	@param book to be added
	*/
	public void add(Book book) { //add
		if(book.getDatePublished().isValid()) {
			if(books.length==numBooks) { //bag full
				grow();
			} 
			if(numBooks==0) {
				books[0]=book; //all empty slots	
			}
			else {
				int ptr=books.length-1;
				while(books[ptr]==null) { //first empty slot
					ptr--;
				}
				books[ptr+1] = book;
			}
			numBooks++; //increase book count
		} 
	} 
	
	/**
	The method to remove a book from the library/bag. The book is found with the find
	method and the bag maintains the current sequence of books after the removal. If the
	book is not in the library, the method returns false.
	@param book to be removed
	@return true if successfully removed, false if book not in library/can't be removed
	*/
	public boolean remove(Book book) { //remove
		int index = find(book);
		if(index>-1) {
			for(int i=index; i<books.length-1; i++) {
				books[i] = books[i+1];
			}
			if(numBooks == books.length) { //put a null space
				books[books.length-1] = null; 
			}
			numBooks--;
			return true;
		}
		return false;
	}
	
	/**
	The method to check out a book from the library. The desired book is found and the
	checked out status/boolean is changed to true that it's now checked out. The book
	can only be checked out if the book exists in the library and isn't already checked
	out by someone else. Otherwise, the book can't be checked out and false is returned.
	@param book to check out
	@return true if checked out successfully, false if can't check out
	*/
	public boolean checkOut(Book book) { //checkout - have book and not checkedout
		int index=find(book);
		if(find(book) == -1) {
			return false;
		}
		if(index<books.length && !books[index].getCheckedOut()) { //book is there and not checked out
			books[index].setCheckedOut(true);
			return true;
		}
		return false;
	}
	
	/**
	The method to return a book that was previously checked out. The desired book is
	found and if it was checked out before, the status is now changed to not checked out
	anymore (checkedOut is set to false). The book can only be returned if the book exists
	in the library and was previously checked out. Otherwise, the method returns false and
	the return didn't occur.
	@param book to return
	@return true if returned successfully, false if not returned successfully
	*/
	public boolean returns(Book book) { //return
		int index=find(book);
		if(find(book) == -1) {
			return false;
		}
		if(index<books.length && books[index].getCheckedOut()) { //book is there and checked out
			books[index].setCheckedOut(false);
			return true;
		}
		return false;
	}
	
	/**
	Prints the list of books in the bag in the current order/sequence (in array/bag
	order)
	*/
	public void print() { //print the list of books in the bag
		if(numBooks>0) {
			System.out.println("**List of books in the library.");
			for(int i=0;i<numBooks;i++) {
				System.out.println(books[i].toString());
			}
			System.out.println("**End of list");
		}
		else {
			System.out.println("Library catalog is empty!");
		}
	}
	
	/**
	Prints the list of books in order of date published (earliest to latest)
	*/
	public void printByDate() { //print the list of books by datePublished (ascending)
		if(numBooks>0) {
			System.out.println("**List of books by the dates published.");
			
			mergeSortDate(books,0,numBooks-1);
			for(int i=0;i<numBooks;i++) {
				System.out.println(books[i].toString());
			}
			
			System.out.println("**End of list");
		}
		else {
			System.out.println("Library catalog is empty!");
		}
	}
	
	/**
	Helper method to merge sort the books in order of dates published
	@param books array
	@param left index
	@param right index
	*/
	public static void mergeSortDate(Book[] books, int left, int right) { 
		if(right<=left) return;
		int mid=(left+right)/2; //left, right, mid are indexes
		mergeSortDate(books,left,mid);
		mergeSortDate(books,mid+1,right);
		mergeDate(books,left,mid,right);
	}
	
	/**
	Helper method to merge two arrays together so it can be sorted in order of dates published
	@param books array
	@param left index
	@param mid index
	@param right index
	*/
	public static void mergeDate(Book[] books, int left, int mid, int right) {
		Book[] leftBooks=new Book[mid-left+1];
		Book[] rightBooks=new Book[right-mid];
		for(int i=0;i<mid-left+1;i++) {
			leftBooks[i]=books[left+i];
		}
		for(int j=0;j<right-mid;j++) {
			rightBooks[j]=books[mid+j+1];
		}
		int leftIndex=0;
		int rightIndex=0;
		for(int k=left;k<right+1;k++) {
			if(leftIndex<mid-left+1 && rightIndex<right-mid) { //setter, getter constructors for book
				if((leftBooks[leftIndex].getDatePublished().getYear()
								<rightBooks[rightIndex].getDatePublished().getYear())
						|| (leftBooks[leftIndex].getDatePublished().getYear()
								==rightBooks[rightIndex].getDatePublished().getYear() 
							&& leftBooks[leftIndex].getDatePublished().getMonth()
								<rightBooks[rightIndex].getDatePublished().getMonth())
						|| (leftBooks[leftIndex].getDatePublished().getYear()
								==rightBooks[rightIndex].getDatePublished().getYear() 
							&& leftBooks[leftIndex].getDatePublished().getMonth()
								==rightBooks[rightIndex].getDatePublished().getMonth() 
							&& leftBooks[leftIndex].getDatePublished().getDay()
								<rightBooks[rightIndex].getDatePublished().getDay())) {
					books[k]=leftBooks[leftIndex];
					leftIndex++;
				}
				else {
					books[k]=rightBooks[rightIndex];
					rightIndex++;
				}
			}
			else if(leftIndex<mid-left+1) {
				books[k]=leftBooks[leftIndex];
				leftIndex++;
			}
			else if(rightIndex<right-mid) {
				books[k]=rightBooks[rightIndex];
				rightIndex++;
			}
		}
	}
	
	/**
	Prints the list of books in order of serial number (lowest to highest)
	*/
	public void printByNumber() { //print the list of books by number (ascending)
		if(numBooks>0) {
			System.out.println("**List of books by the book numbers.");
			
			mergeSortNum(books,0,numBooks-1);
			for(int i=0;i<numBooks;i++) {
				System.out.println(books[i].toString());
			}
			
			System.out.println("**End of list");
		}
		else {
			System.out.println("Library catalog is empty!");
		}
	}
	
	/**
	Helper method to merge sort the books in order of serial numbers
	@param books array
	@param left index
	@param right index
	*/
	public static void mergeSortNum(Book[] books, int left, int right) {
		if(right<=left) return;
		int mid=(left+right)/2; //left, right, mid are indexes
		mergeSortNum(books,left,mid);
		mergeSortNum(books,mid+1,right);
		mergeNum(books,left,mid,right);
	}
	
	/**
	Helper method to merge two arrays together so it can be sorted in order of serial numbers
	@param books array
	@param left index
	@param mid index
	@param right index
	*/
	public static void mergeNum(Book[] books, int left, int mid, int right) {
		Book[] leftBooks=new Book[mid-left+1];
		Book[] rightBooks=new Book[right-mid];
		for(int i=0;i<mid-left+1;i++) {
			leftBooks[i]=books[left+i];
		}
		for(int j=0;j<right-mid;j++) {
			rightBooks[j]=books[mid+j+1];
		}
		int leftIndex=0;
		int rightIndex=0;
		for(int k=left;k<right+1;k++) {
			if(leftIndex<mid-left+1 && rightIndex<right-mid) { //setter, getter constructors for book
				if(Integer.parseInt(leftBooks[leftIndex].getNumber())<Integer.parseInt
						(rightBooks[rightIndex].getNumber())) {
					books[k]=leftBooks[leftIndex];
					leftIndex++;
				}
				else {
					books[k]=rightBooks[rightIndex];
					rightIndex++;
				}
			}
			else if(leftIndex<mid-left+1) {
				books[k]=leftBooks[leftIndex];
				leftIndex++;
			}
			else if(rightIndex<right-mid) {
				books[k]=rightBooks[rightIndex];
				rightIndex++;
			}
		}
	}

}

