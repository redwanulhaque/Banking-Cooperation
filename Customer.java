
public class Customer implements Comparable<Customer>{

	private String first, last;  //class attributes for first and last name
	private String acctNo;  // class attribute for accountNo. No need to add or subtract
	private double balance;  // class attribute for balance
	
	Customer (String first, String last, String acctNo, double balance){  // setting up the customer class
	this.first = first;
	this.last = last;
	this.acctNo = acctNo;
	this.balance = balance;	}
	
	public String getFirst() {  // get first
		return first;
	}
	
	public void setFirst(String first) {  // set first
		this.first = first;
	}
	
	public String getLast() {  // get last
		return last;
	}
	
	public void setLast(String last) {  // set last
		this.last = last;
	}
	
	public String getAcctNo() {  // get account number
		return acctNo;
	}
	
	public void setAcctNo(String acctNo) {  // set account number
		this.acctNo = acctNo;
	}
	
	public double getBalance() {  // get balance 
		return balance;
	}
	
	public void setBalance(double balance) {  // set balance
		this.balance = balance;
	}
	
	public void deposit(double amount) {  // deposit will add to the existing balance
		if(amount > 0) {
			balance = balance + amount;
		}
	}
	
	public void withdraw(double amount) {  // withdraw will subtract from the balance
		
		if(amount >= 0) {
			balance = balance - amount;
		}
		
		else if(amount > balance) {  // if the amount is grater than the balance than it will do nothing
			return;
		}
	}
	
	@Override
	public boolean equals(Object other) {  // testing equality by account number

		if ((other != null) && this.getClass() == other.getClass()) {
			Customer Other = (Customer) other;

			if ((acctNo == Other.acctNo)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public String toString(){  // printing the bank info to toSting method
		return "[Name= " + first + " " + last + "; Account Number= " + acctNo +"; Balance= " + balance +"]\n";
		}
	
	@Override
	public int compareTo(Customer other) {  // Compare customer type
		
		int value = this.last.compareTo(other.last);  // equality by last name 
		
		if(value != 0) return value;
		
		return this.first.compareTo(first); // equality by first name
	}
}
