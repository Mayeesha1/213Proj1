import java.util.Calendar;
import java.util.StringTokenizer;

/**
This Date class stores the day, month, and year from user input in each 
identified variable and makes sure if a date is valid. 
@author mayeesha, rebecca
*/
public class Date {
	private int year;
	private int month; 
	private int day;
	
	/**
	This is the testbed main for testing if a date input is valid.
	@param args array of string arguments
	*/
	public static void main(String[] args) {
		
		//testbed main for testing isValid() 
		
		//Test 1 (check beyond current date cases)
		Date test1case1 = new Date("10/20/2022");
		System.out.println("this date is " + test1case1.isValid());
		Date test1case2 = new Date("3/1/2021");
		System.out.println("this date is " + test1case2.isValid());
		Date test1case3 = new Date("2/25/2021");
		System.out.println("this date is " + test1case3.isValid());
		//Test 2 (check exceeding minimum year case)
		Date test2 = new Date("03/04/1899");
		System.out.println("this date is " + test2.isValid());
		//Test 3 (check beyond maximum day of month cases(31))
		Date test3case1 = new Date("1/32/2020");
		System.out.println("this date is " + test3case1.isValid());
		Date test3case2 = new Date("3/32/2020");
		System.out.println("this date is " + test3case2.isValid());
		Date test3case3 = new Date("5/32/2020");
		System.out.println("this date is " + test3case3.isValid());
		Date test3case4 = new Date("7/32/2020");
		System.out.println("this date is " + test3case4.isValid());
		Date test3case5 = new Date("8/32/2020");
		System.out.println("this date is " + test3case5.isValid());
		Date test3case6 = new Date("10/32/2020");
		System.out.println("this date is " + test3case6.isValid());
		Date test3case7 = new Date("12/32/2020");
		System.out.println("this date is " + test3case7.isValid());
		//Test 4 (check beyond maximum day of month cases(30))
		Date test4case1 = new Date("4/31/2020");
		System.out.println("this date is " + test4case1.isValid());
		Date test4case2 = new Date("6/31/2020");
		System.out.println("this date is " + test4case2.isValid());
		Date test4case3 = new Date("9/31/2020");
		System.out.println("this date is " + test4case3.isValid());
		Date test4case4 = new Date("11/31/2020");
		System.out.println("this date is " + test4case4.isValid());
		//Test 5 (check non existent month cases)
		Date test5case1 = new Date("13/31/2020");
		System.out.println("this date is " + test5case1.isValid());
		Date test5case2 = new Date("0/4/2020");
		System.out.println("this date is " + test5case2.isValid());
		//Test 6 (check non existent day case)
		Date test6 = new Date("2/0/2021");
		System.out.println("this date is " + test6.isValid());
		//Test 7 (check non existent leap year)
		Date test7case1 = new Date("2/29/2019");
		System.out.println("this date is " + test7case1.isValid());
		Date test7case2 = new Date("2/29/1900");
		System.out.println("this date is " + test7case2.isValid());
		//Test 8 (check existing leap year) 
		Date test8case1 = new Date("2/29/2020");
		System.out.println("this date is " + test8case1.isValid());
		Date test8case2 = new Date("2/29/2000");
		System.out.println("this date is " + test8case2.isValid());
	}
	
	/**
	The method creates the Date object from what was scanned through 
	user input and puts them in their respective variables. The day, month,
	and year are separated using String tokenizer.
	@param date string scanned from user input
	*/
	public Date(String date) { //taking mm/dd/yyyy and create a Date object

	StringTokenizer dt = new StringTokenizer(date,"/");
	int mnth = 0;	
	int dy = 0; 
	int yr = 0;
	
	mnth = Integer.parseInt(dt.nextToken().trim());
	dy = Integer.parseInt(dt.nextToken().trim());
	yr = Integer.parseInt(dt.nextToken().trim());

	this.month = mnth;
	this.day = dy;
	this.year = yr; 
	} 
	
	/**
	The method creates the Date object for the current date using the
	Calendar class. It stores the current day, month, and year in their
	respective variables.
	*/
	public Date() {
		int currDay = 0;
		int currMonth = 0;
		int currYear = 0;
		this.day = currDay;
		this.month = currMonth;
		this.year = currYear; 
		
		Calendar todaysDate = Calendar.getInstance();
		currMonth = todaysDate.get(Calendar.MONTH);
		currMonth++; 
		currYear = todaysDate.get(Calendar.YEAR);
		currDay = todaysDate.get(Calendar.DAY_OF_MONTH);
		
		//test to see if todays date is printed out right
		System.out.println("current day is: " + currDay);
		System.out.println("current month is: " + currMonth);
		System.out.println("current year is: " + currYear);
	} 
	
	/**
	Getter method for day so it can be used in other classes
	@return day
	*/
	public int getDay() {
		return day;
	}
	
	/**
	Getter method for month so it can be used in other classes
	@return month
	*/
	public int getMonth() {
		return month;
	}
	
	/**
	Getter method for year so it can be used in other classes
	@return year
	*/
	public int getYear() {
		return year;
	}
	
	/**
	The method checks if the date published passed through input is valid 
	and meets certain requirements to be authentic to be stored in the Kiosk
	library
	@return boolean if date is valid
	*/
	public boolean isValid() {
		int MIN_YEAR=1900; 
		int JAN=1; 
		int FEB=2; 
		int MAR=3; 
		int APR=4; 
		int MAY=5; 
		int JUN=6; 
		int JUL=7; 
		int AUG=8; 
		int SEP=9; 
		int OCT=10; 
		int NOV=11; 
		int DEC=12; 
		int LOW_DAY=1; 
		int NO_LEAP=28; 
		int YES_LEAP=29; 
		int LOW_END=30; 
		int HIGH_END=31; 
		int FIRST_CHECK=4; 
		int SECOND_CHECK=100; 
		int THIRD_CHECK=400; 

		if(year < MIN_YEAR || year>Calendar.getInstance().get(Calendar.YEAR)
				|| (year==Calendar.getInstance().get(Calendar.YEAR) && month>Calendar.getInstance().get(Calendar.MONTH))
				|| (year==Calendar.getInstance().get(Calendar.YEAR) && month==Calendar.getInstance().get(Calendar.MONTH)
					&& day>Calendar.getInstance().get(Calendar.DAY_OF_MONTH))) {
				return false;
			}
			else if((month==JAN || month==MAR || month==MAY || month==JUL || month==AUG || month==OCT
				|| month==DEC) && day>HIGH_END) {
				return false;
			}
			else if((month==APR || month==JUN || month==SEP || month==NOV) && day>LOW_END) {
				return false;
			} else if(month > DEC || month < JAN || day < LOW_DAY) {
				return false;
			} else if(month==FEB) { //check leap year
				if(year%FIRST_CHECK==0) {
					if(year%SECOND_CHECK==0) {
						if(year%THIRD_CHECK==0) {
							if(day>YES_LEAP) {
								return false;
							}
						}
						else {
							if(day>NO_LEAP) {
								return false;
							}
						}
					}
					else {
						if(day>YES_LEAP) {
							return false;
						}
					}
				}
				else {
					if(day>NO_LEAP) {
						return false;
					}
				}
			}
			return true;
	}
	
}
