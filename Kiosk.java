import java.util.Scanner;
import java.util.StringTokenizer;

/**
The kiosk class is used to run all the commands in the library. Based on the
inputted commands, the kiosk uses the library methods as needed to add, remove,
check out, return, and print (lists of) books.
@author mayeesha, rebecca
*/
public class Kiosk {
	
	//...
	private Library library; //constructor for kiosk
	
	/**
	This method runs the Library Kiosk so that the library can add, 
	remove, check out, and return books, as well as display the list
	of books by the date published or the books' serial numbers.
	(**no param or return tags, better description)
	 */
	public void run() { //change var names
		System.out.println("Library Kiosk running.");
		Scanner scanner=new Scanner(System.in);
		String line=scanner.nextLine();
		library=new Library();
		int serialCount=10000;
		while(!line.equals("Q")) {
			StringTokenizer st=new StringTokenizer(line,",",false);
			String command=st.nextToken(); 
			if(command.equals("A")) { //add 
				String name=st.nextToken();
				String date=st.nextToken();
				Book book=new Book(name,date); 				
				if(book.getDatePublished().isValid()) {
					serialCount++;
					book.setNumber(String.valueOf(serialCount));
					library.add(book); //serial
					System.out.println(book.getName()+" added to the Library.");
				}
				else {
					System.out.println("Invalid Date!");
				}
			}
			else if(command.equals("R")) { //remove
				String serial=st.nextToken();
				Book book=new Book(serial); 
				if(library.remove(book)) {//true 
					System.out.println("Book#" + serial + " removed.");
				}
				else { //false
					System.out.println("Unable to remove, the library does not have this book.");
				}
			}
			else if(command.equals("O")) { //check out
				String serial=st.nextToken();
				Book book=new Book(serial);
				if(library.checkOut(book)) {
					System.out.println("You've checked out Book#" + serial + ". Enjoy!");
				}
				else { 
					System.out.println("Book#" + serial + " is not available.");
				}
			}
			else if(command.equals("I")) { //return
				String serial=st.nextToken();
				Book book=new Book(serial);
				if(library.returns(book)) {
					System.out.println("Book#" + serial + " return has completed. Thanks!");
				}
				else {
					System.out.println("Unable to return Book#" + serial + ".");
				}
			}
			else if(command.equals("PA")) { //print current sequence
				library.print(); 
			}
			else if(command.equals("PD")) { //print by dates
				library.printByDate();
			}
			else if(command.equals("PN")) { //print by book numbers
				library.printByNumber();
			}
			else {
				System.out.println("Invalid command!");
			}
			line=scanner.nextLine();
		}
		System.out.println("Kiosk session ended."); //quit
		
		scanner.close();
	}
	
}
