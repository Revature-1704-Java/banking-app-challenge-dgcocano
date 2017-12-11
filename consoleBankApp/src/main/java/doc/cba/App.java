package doc.cba;

import java.util.*;
/**
 * Hello world!
 *
 */
public class App 
{
				private static HashMap<String, Account> accounts;
				private static Scanner input;
				private static Parser parser;
				private static XmlParser xmlParser;
				public static void main( String[] args )
				{
								String line = "";
								String command = "";
								System.out.println("Command format is (action) (accountName) (amount)");
								System.out.print("\n> ");
								xmlParser = new XmlParser("./files/accounts.xml");				
								accounts = xmlParser.loadAccounts();
								parser = new Parser(accounts);
								input = new Scanner(System.in);
								for(Map.Entry<String, Account> entry : accounts.entrySet()) {
												System.out.println(entry.getValue());
								}
								while((line = input.nextLine()) != null) {
												String result = "Invalid command";
												command = parser.parse(line) + " ";
												String[] commandParts = command.split("\\s+");
												switch(commandParts.length) {
																case 1:
																				if(commandParts[0].equals("quit")) {
																								quit();
																				}
																				break;
																case 3:
																				if(commandParts[0].equals("withdraw")) {
																								if(accounts.get(commandParts[1]).withdraw(Integer.parseInt(commandParts[2]))) {
																												result = "Successful withdrawal of " + commandParts[2] + " from " + commandParts[1];
																								}
																								else {
																												result = "Unsuccessful withdrawal";
																								}
																				}
																				else if(commandParts[0].equals("deposit")){
																								if(accounts.get(commandParts[1]).deposit(Integer.parseInt(commandParts[2]))) {
																												result = "Successful deposit of " + commandParts[2] + " from " + commandParts[1];
																								}
																								else {
																												result = "Unsuccessful deposit";
																								}
																				}
																				break;
												}
												System.out.println(result);
												System.out.println("Command format is (action) (accountName) (amount)");
												for(Map.Entry<String, Account> entry : accounts.entrySet()) {
																System.out.println(entry.getValue());
												}
												System.out.print("\n> ");
								}
				}

				public static void quit() {
								input.close();
								xmlParser.writeAccounts(accounts);
								xmlParser.writeFile();
								System.out.println("Quit");
								System.exit(0);
				}
}
