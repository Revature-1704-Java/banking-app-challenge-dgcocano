package doc.cba;

import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.*;

public class Account {

				private String name;
				private int balance;

				public Account() {
								name = "Default";
								balance = 0;
				}

				public Account(String name) {
								this();
								this.name = name;
				}

				public Account(int balance) {

								this();
								this.balance = balance;
				}

				public Account(String name, int balance) {
								this.name = name;
								this.balance = balance;
				}

				public Account(Node node) {
								Element element = (Element) node;
								name = element.getAttribute("name");
								NodeList detailNodes = node.getChildNodes();
								for(int i = 0; i < detailNodes.getLength(); i++) {
												String nodeName = detailNodes.item(i).getNodeName();
												switch(nodeName) {
																case "balance":
																				balance = Integer.parseInt(detailNodes.item(i).getTextContent());
																				break;
																case "default":
																				break;
												}
								}
				}
				
				public boolean withdraw(int amount) {
								//Could also be done with a result String 
								//to determine what outcome, success, negative balance, etc
								//But which one belongs to the class, and which belongs to the classes that use account
								//Probably boolean here, and logic for outcome strings to the classes that interact with it
								//Lets people change outcome without having to go the class itself
								boolean result = false;
								if(amount >= 0 && balance >= amount) {
												result = true;
												setBalance(balance-amount);
								}
								return result;
				}

				public boolean deposit(int amount) {
								boolean result = false;
								if(amount >= 0) {
												result = true;
												setBalance(balance + amount);
								}
								return result;
				}

				@Override
				public String toString() {
								return "Account Name: " + name + "\nBalance : " + balance;
				}

				//Getters and Setters
			  public String getName() {
							return name;
				}

				public void setName(String name) {
								this.name = name;
				}
				public int getBalance() {
								return balance;
				}
				public void setBalance(int balance) {
								this.balance = balance;
				}
}
