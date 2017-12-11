package doc.cba;

import java.util.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlParser {

				private String fileName;
				private File inputFile;
				private DocumentBuilderFactory dbFactory;
				private DocumentBuilder dBuilder;
				private Document doc;

				//public XmlParser() {
								//fileName = "";
								//loadFile();
				//}

				public XmlParser(String fileName) {
								this.fileName = fileName;
								loadFile();
				}
				
				private void loadFile() {
								try {
												inputFile = new File(fileName);
												dbFactory = DocumentBuilderFactory.newInstance();
												dBuilder = dbFactory.newDocumentBuilder();
												doc = dBuilder.parse(inputFile);
												doc.getDocumentElement().normalize();
								}
								catch (Exception ex) {
												ex.printStackTrace();
								}
				}
				
				private NodeList returnAll(String tagName) {
								return doc.getElementsByTagName(tagName);
				}

				private List<Node> returnChildrenNamed(Node node, String name) {
								List<Node> certainChildren = new ArrayList<Node>();
								NodeList children = node.getChildNodes();
								for(int i = 0; i < children.getLength(); i++) {
												Node nNode = children.item(i);
												if(nNode.getNodeName().equals(name)) {
																certainChildren.add(nNode);
												}
								}
								return certainChildren;
				}

				public HashMap<String, Account> loadAccounts() {
								HashMap<String, Account> accounts = new HashMap<String, Account>();
								Element head = doc.getDocumentElement();
								List<Node> accountNodeList = returnChildrenNamed(head, "account");
								for(Node accountNode : accountNodeList) {
												if (accountNode.getNodeType() == Node.ELEMENT_NODE) {
																Account account = new Account(accountNode);
																if(!accounts.keySet().contains(account.getName())) {
																				accounts.put(account.getName(), account);
																}
												}
								}
								return accounts;
				}

				public void writeAccounts(HashMap<String, Account> accounts) {
								Element head = doc.getDocumentElement();
								List<Node> accountNodes = returnChildrenNamed(head, "account");
								for(Node accountNode : accountNodes) {
												if(accountNode.getNodeType() == Node.ELEMENT_NODE) {
																Element element = (Element) accountNode;
																if(accounts.containsKey(element.getAttribute("name"))) {
																				NodeList accountNodeChildren = accountNode.getChildNodes();
																				for(int i = 0; i < accountNodeChildren.getLength(); i++) {
																								Node accountChildNode = accountNodeChildren.item(i); 
																								switch(accountChildNode.getNodeName()) {
																												case "balance":
																																accountChildNode.setTextContent(Integer.toString(accounts.get(element.getAttribute("name")).getBalance()));
																																break;
																											  default:
																																break;
																								}
																				}
																}
												}
								}
				}

				public void writeFile() {
								try {
												TransformerFactory	transformerFactory = TransformerFactory.newInstance();
												Transformer transformer = transformerFactory.newTransformer();
												DOMSource source = new DOMSource(doc);
												StreamResult result = new StreamResult(inputFile);
												transformer.transform(source, result);

												System.out.println("Done");

								} catch (TransformerException tfe) {
												tfe.printStackTrace();
								}
				}
				public void processFile() {
								System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
								NodeList nList = doc.getElementsByTagName("account");
								System.out.println("----------------------------");
         
								for (int temp = 0; temp < nList.getLength(); temp++) {
												Node nNode = nList.item(temp);
												System.out.println("\nCurrent Element : " + nNode.getNodeName());
            
												if (nNode.getNodeType() == Node.ELEMENT_NODE) {
												Element eElement = (Element) nNode;
												System.out.println("Account Name : " 
																				+ eElement.getAttribute("name"));
												System.out.println("Account Balance : " 
																				+ eElement.getElementsByTagName("balance").item(0).getTextContent());
												}
								}
				}
}
