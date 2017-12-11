package doc.cba;

import java.util.*;
import java.io.*;

public class Parser {

				private HashMap<String, Account> accounts;
				private HashSet<String> actions;
				public Parser(HashMap<String, Account> accounts) {
								this.actions = new HashSet<String>();
								this.accounts = accounts;
								actions.add("deposit");
								actions.add("withdraw");
								actions.add("quit");
				}

				public String parse(String input) {
								String output = "";
								String[] tokens = input.split("\\s+");
								if(tokens.length == 0) {
												output = "";
								}
								else {
												tokens[0] = tokens[0].toLowerCase();
								}
								if(tokens.length == 1) {
												if(actions.contains(tokens[0])) {
																output += tokens[0];
												}
								}
								else if(tokens.length == 3) {
												if(actions.contains(tokens[0])) {
																if(tokens[1].matches("[0-9]+")) {
																				if(accounts.containsKey(tokens[2])) {
																								output = tokens[0] + " " + tokens[2] + " " + tokens[1];
																				}
																}
												}
								}
								return output;
				}
}

