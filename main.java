import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class main {  
	
	public static void loadCustomer(myBST<Customer> tree, Customer[] AcctDB) {  // class load customer
		
		try {
			File fileReader = new File("data.txt");  // data.txt has the data for the customers
			Scanner scanner = new Scanner(fileReader);  
   
			while (scanner.hasNext()) {
				String line = scanner.nextLine(); 
				String customer[] = line.split(",");  // split on the comma 
				
			    String first = (customer[0].trim());  // first is first comma
			    String last = (customer[1].trim());  // last is second comma
			    String acctNo = (customer[2].trim());  // third is third comma
			    double balance = Integer.parseInt(customer[3].trim());  // turning balance to double type
			    
				Customer customerList = new Customer (first, last, acctNo, balance);  // storing it in the customer class
				tree.insert(customerList);  // inserting the data to the tree
				
				AcctDB[Integer.parseInt(customerList.getAcctNo())] = customerList;  // storing the AcctNo to array
			}
		}
	
		catch (IOException e) {  // if file not found then throw this exception
			System.out.println("File not found!");
		}
		
		tree.inorder();  // just a display to the console to check stuff
	}  // load customer class 
	
	public static void printFile(bstNode t, FileWriter list) throws Exception {  // method to print file in the log.txt
	    
		myBST<Customer> myTree = new myBST<Customer>();  

		if(t != null) {  // getting data from the tree without messing it up
			list.write(t.data.toString());
			printFile(t.leftChild, list);
			printFile(t.rightChild, list);
	    }  // if loop
	}  // printFile method

	public static void main (String[] args) throws Exception {
	
		Customer[] AcctDB = new Customer[9999];  // setting up array customer
		myBST<Customer> myTree = new myBST<Customer>();  
		loadCustomer(myTree, AcctDB);  // calling on load customer
		
		String number = ""; // menu for getting input from the user
		int error; 
		while(number != "6") {
		number = JOptionPane.showInputDialog(null, "1. Make a deposit to existing customer account.\n" + "2. Make a withdrawal from existing customer account.\n"
				+ "3. Check balance of an existing customer account.\n" + "4. Open a new customer account.\n" + "5. Close a customer account.\n" + "6. Exit the program.\n" + 
				"\nPlease enter a number: ");
		
		error = Integer.parseInt(number);  // turn number to integer for error checking
		if(error < 1 || error > 6) {  // if the user input is grater than 6 or less than 1 display this message
			JOptionPane.showMessageDialog(null, "Please enter a valid Input from the display!");
		}

		switch (number) {  // cases
			
		case "1":  // case 1 deposit
			
			String method;  // get input from user  
			int cases = 0;  // cases for two if loop
			method = JOptionPane.showInputDialog(null, "Press 1 if you have your acctount Number. \nPress 2 if you dont have your account number. ");  // get first name 
			cases = Integer.parseInt(method);  // turn to int and save it to cases
			
			if(cases == 1) {  // if user have the account number
				
				String getAcct;  // get input from user  
				getAcct = JOptionPane.showInputDialog(null, "Please enter your account number: ");  // get account number
			
				while(AcctDB[Integer.parseInt(getAcct)] == null) {
					getAcct = JOptionPane.showInputDialog(null, "Please enter a valid account number: ");  // get a valid account number
				}
			
				String dep;  // get amount from the user
				dep = JOptionPane.showInputDialog(null, "Enter the amount you wish to deposit: ");  // get deposit amount from user
			
				double total = Integer.parseInt(dep);
				AcctDB[Integer.parseInt(getAcct)].deposit(total);  // deposit to the account
				System.out.print("The amount " + total + " has been added to account number " + getAcct);  // print statement
				System.out.println();
				break;
		}
			
			else if(cases == 2) {  // if user wants to search by first and last name
				String first1, last1;
				first1 = JOptionPane.showInputDialog(null, "Please enter customer first name: ");  // get first name 
				last1 = JOptionPane.showInputDialog(null, "Please enter customer last name: ");  // get last name
			
				Customer dep = new Customer(first1, last1, "", 0);  // create a new customer
				dep = myTree.search(myTree.root, dep);
			
				if(dep == null) {  // if the customer doesn't exist
					JOptionPane.showMessageDialog(null, "No account exist in that form! Good Bye");
					break;
				}
			
				if(myTree.search(myTree.root, dep) != null) {  // getting customer info from the tree
				
					String deposit;
					deposit = JOptionPane.showInputDialog(null, "Enter the amount you wish to deposit: ");  // get the deposit from user  
					double amount = Integer.parseInt(deposit);  // turn string to double
					dep.deposit(amount);
					System.out.print("The amount " + amount + " has been added to " + first1 + " " + last1);  // print statement
					System.out.println();
					break;  // exit out
				}
				break;
		}  // if method == "2"
			
		case "2":  // case 2 withdraw
			
			String method2;  // get input from user  
			int cases2 = 0;  // cases for two if loop
			method2 = JOptionPane.showInputDialog(null, "Press 1 if you have your acctount Number. \nPress 2 if you dont have your account number. ");  // get first name 
			cases2 = Integer.parseInt(method2);  // turn to int and save it to cases

			if(cases2 == 1) {
				String getAcct2;  // get input from user  
				getAcct2 = JOptionPane.showInputDialog(null, "Please enter your account number: ");  // get account number
				
				while(AcctDB[Integer.parseInt(getAcct2)] == null) {
					getAcct2 = JOptionPane.showInputDialog(null, "Please enter a valid account number: ");  // get a valid account number
				}
				
				String wit;  // get amount from the user
				wit = JOptionPane.showInputDialog(null, "Enter the amount you wish to withdraw: ");  // get withdraw amount from user
				double total2 = Integer.parseInt(wit);
				
				while( total2 < AcctDB[Integer.parseInt(getAcct2)].getBalance() || total2 > AcctDB[Integer.parseInt(getAcct2)].getBalance()) {  // compare total2 with the account balance
					if(total2 < AcctDB[Integer.parseInt(getAcct2)].getBalance()) {  // if the amount is less than the balance
						AcctDB[Integer.parseInt(getAcct2)].withdraw(total2);;  // Withdraw from the account
						System.out.print("The amount " + wit + " has been taken out of account number " + getAcct2);  // print statement
						System.out.println();
						break;  // break from while
					}
					
					while(total2 > AcctDB[Integer.parseInt(getAcct2)].getBalance()) {  // if the amount is greater than the balance
						JOptionPane.showMessageDialog(null, "Cant take out more than the balance amount!");  // can't take out more than the balanced amount
						String newWit; 
						newWit = JOptionPane.showInputDialog(null, "Enter the amount you wish to withdraw: ");  // get a new withdraw statement from the user 
						double anotherTotal2 = Integer.parseInt(newWit);  // turn string to int

						if(anotherTotal2 < AcctDB[Integer.parseInt(getAcct2)].getBalance()) {  // do this only if the withdraw is less than the balance or repeat the while loop
							AcctDB[Integer.parseInt(getAcct2)].withdraw(anotherTotal2);;  // Withdraw from the account
							System.out.print("The amount " + anotherTotal2 + " has been taken out of account number" + getAcct2);  // print statement
							System.out.println();
							break;  //break from if
						}
					}
					break;  // break from while
				}
				break;  // break from case			
		}  // if method == "1"
			
			else if(cases2 == 2) {  // if user wants to search by first and last name
				String first2, last2;
				first2 = JOptionPane.showInputDialog(null, "Please enter customer first name: ");  // get first name 
				last2 = JOptionPane.showInputDialog(null, "Please enter customer last name: ");  // get last name
			
				Customer withdrawl = new Customer(first2, last2, "", 0);  // create a new customer
				withdrawl = myTree.search(myTree.root, withdrawl);
			
				if(withdrawl == null) {  // if the customer doesn't exist
					JOptionPane.showMessageDialog(null, "No account exist in that form! Good Bye");
				break;
			}
			
			if(myTree.search(myTree.root, withdrawl) != null) {  // getting user info from the tree
				String withdraw;
				withdraw = JOptionPane.showInputDialog(null, "Enter the amount you wish to withdraw: ");  // get the withdraw from user  
				double amount2 = Integer.parseInt(withdraw);  // turn string to double
				
				while(amount2 < withdrawl.getBalance()  || amount2 > withdrawl.getBalance()) {
					if(amount2 < withdrawl.getBalance()) {  // if the amount is less than the balance
						withdrawl.withdraw(amount2);  // Withdraw from the account
						System.out.print("The amount " + withdraw + " has been taken out of " + first2 + " " + last2);  // print statement
						System.out.println();
						break;  // break from while
					}
				
				while(amount2 > withdrawl.getBalance()) {  // if the amount is greater than the balance
					JOptionPane.showMessageDialog(null, "Cant take out more than the balance amount!");  // can't take out more than the balanced amount
					String newWithdraw; 
					newWithdraw = JOptionPane.showInputDialog(null, "Enter the amount you wish to withdraw: ");  // get a new withdraw statement from the user 
					double anotherAmount = Integer.parseInt(newWithdraw);  // turn string to int

					if(anotherAmount < withdrawl.getBalance()) {  // do this only if the withdraw is less than the balance or repeat the while loop
					withdrawl.withdraw(anotherAmount);  // Withdraw from the account
					System.out.print("The amount " + anotherAmount + " has been taken out of " + first2 + " " + last2);  // print statement
					System.out.println();
					break;  //break from if
					}
				}
				break; // break from while loop
			}
			break;  // break from while loop
			}
			break;  // from the statement
		}  // if method == "2"
			
		case "3":  // case 3 get balance
			
			String method3;  // get input from user  
			int cases3 = 0;  // cases for two if loop
			method3 = JOptionPane.showInputDialog(null, "Press 1 if you have your acctount Number. \nPress 2 if you dont have your account number. ");  // get first name 
			cases3 = Integer.parseInt(method3);  // turn to int and save it to cases

			if(cases3 == 1) {  // if user have the account number
				String getAcct3;  // get input from user  
				getAcct3 = JOptionPane.showInputDialog(null, "Please enter your account number: ");  // get account number
			
				while(AcctDB[Integer.parseInt(getAcct3)] == null) {
					getAcct3 = JOptionPane.showInputDialog(null, "Please enter a valid account number: ");  // get a valid account number
				}
			
				System.out.print("The account " + getAcct3 + " has balance of " + AcctDB[Integer.parseInt(getAcct3)].getBalance());  // display the user balance
				System.out.println();
				break;  // break out of the statement
		}  // if
			
			else if(cases3 == 2) {  // if user wants to search by first and last name
				String first3, last3;			
				first3 = JOptionPane.showInputDialog(null, "Please enter customer first name: ");  // get first name
				last3 = JOptionPane.showInputDialog(null, "Please enter customer last name: ");  // get last name
			
				Customer username = new Customer(first3, last3, "", 0);  // create a new customer
				username = myTree.search(myTree.root, username);
			
				if(username == null) {  // if the customer doesn't exist
					JOptionPane.showMessageDialog(null, "No account exist in that form! Good Bye");
				break;
				}
			
				if(myTree.search(myTree.root, username) != null) {  // if the customer exist in the tree then return the balance
					System.out.print( first3 + " " + last3 + " has balance of " + username.getBalance() + "\n");  // printing the balance
				break;  // exit out of the statement
			}
		}  // if 
		
		case "4":  // case 4 create a new account
			
			JOptionPane.showMessageDialog(null, "Be sure to capitalize first letter of both first and last name!");  // case sensitive
			String first4, last4, acctNo4, amount4;  // getting data from the user to create their account
			first4 = JOptionPane.showInputDialog(null, "Please enter first name: ");  // first name
			last4 = JOptionPane.showInputDialog(null, "Please enter first last: ");  // last name
			acctNo4 = JOptionPane.showInputDialog(null, "Please enter 4 digit account Number: ");  // 4 digit string value
			while(acctNo4.length() != 4 ) {  // in the event the user dosen't enter 4 digit user number
				acctNo4 = JOptionPane.showInputDialog(null, "Please enter 4 digit account Number again: ");  // 4 digit string value
			}
			
			Customer user = new Customer(first4, last4, "", 0);  // putting the data into customer class
			myTree.insert(user);  // inserting the new data to tree
			JOptionPane.showMessageDialog(null, "Now we will need to deposit some money to create the account!");  // now user need to put their money in the account
			
			amount4 = JOptionPane.showInputDialog(null, "Please enter the amount you wish to add to your account: ");  // user enter amount
			double balance = Integer.parseInt(amount4);  // turning the string to double
			user.deposit(balance);  // inputing the new customer balance
			
			JOptionPane.showMessageDialog(null, "Your account has been created!");  // new account has been created
			break;
			
		case "5":  // case 5 delete a user
			
			String method5;  // get input from user  
			int cases5 = 0;  // cases for two if loop
			method5 = JOptionPane.showInputDialog(null, "Press 1 if you have your acctount Number. \nPress 2 if you dont have your account number. ");  // get first name 
			cases5 = Integer.parseInt(method5);  // turn to int and save it to cases

			if(cases5 == 1) {  // if user have the account number
				String getAcct5;  // get input from user  
				getAcct5 = JOptionPane.showInputDialog(null, "Please enter your account number: ");  // get account number
			
				while(AcctDB[Integer.parseInt(getAcct5)] == null) {
					getAcct5 = JOptionPane.showInputDialog(null, "Please enter a valid account number: ");  // get a valid account number
				}
				
				myTree.delete(AcctDB[Integer.parseInt(getAcct5)]);  // delete user from the array
				System.out.print("The account " + getAcct5 + " has been deleted. Thank you!\n");  // print the statement
				break;  // break out of the statement
			}
			
			else if(cases5 == 2) {  // if user wants to search by first and last name
				JOptionPane.showMessageDialog(null, "Be sure to capitalize first letter of both first and last name!");  // case sensitive
				String first5, last5;  // getting data from user to close the account
				first5 = JOptionPane.showInputDialog(null, "Please enter customer First name: ");  // first name of user
				last5 = JOptionPane.showInputDialog(null, "Please enter customer last name: ");  // last name of user

				Customer deleteUser = new Customer(first5, last5, "", 0);  //inserting the data to customer class
				deleteUser = myTree.search(myTree.root, deleteUser);
			
				if(deleteUser == null) {  // if the customer doesn't exist
					JOptionPane.showMessageDialog(null, "No account exist in that form! Good Bye");
				break;
				}

				if(myTree.search(deleteUser) != null) {  // delete customer from the tree
					System.out.println("The account " + first5 + " " + last5 + " has been deleted! Have a nice day.\n");
					myTree.delete(deleteUser);
				break;
			}
		}  // if loop
			
		case "6":  // case 6 print everything to a new textfile
			
			myTree.preorder();  // just a display to the console to check stuff
		    FileWriter EndList = new FileWriter("log.txt");  // print to new textfile named log.txt
			
			printFile(myTree.root, EndList);  // printing to log.txt
	        EndList.close();

			return;  // exit out of while loop
			
			}  // switch case
		}  //  while loop	
	}  // driver class
}  // main
