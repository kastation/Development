package victorModules;

import java.sql.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class MiscFunctions {
	
	public void connectDB(){
		try {
			final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			final String DB_URL = "jdbc:mysql://localhost/victor_test"; //need to add the database name
			
			final String USER = "root";
			final String PASS = "Interstella8";
			
			Connection conn = null;
			Statement stmt = null;
			
			// table name is example, (id int, data varchar), 2 records
			
			Class.forName("com.mysql.jdbc.Driver");
			
			//Connecting to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT id, data FROM example";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      while(rs.next()){
		          //Retrieve by column name
		          int id  = rs.getInt("id");
		          String data = rs.getString("data");
		          
		          //Display values
		          System.out.print("ID: " + id);
		          System.out.print(", Data: " + data);
		          
		       }
		       //STEP 6: Clean-up environment
		       rs.close();
		       stmt.close();
		       conn.close();
			
		}catch(SQLException se){
			se.printStackTrace();
			System.out.println("Connection failed, check output console");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Where is your MySQL driver");
		}
	}
	
	public void readXML(){
		// This code will try to read the xml file from the file system
		String userXMLHome = System.getProperty("user.home");
		System.out.println("The user home is "+ userXMLHome);
		
		// will improve this to read add the properties file		
		try {
			File fXmlFile = new File(userXMLHome + "/Dropbox/victorTest.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			NodeList nList = doc.getElementsByTagName("staff");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
						
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Staff id : " + eElement.getAttribute("id"));
					System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

				}
			}
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		}
}
