package toystopinventorymanagementsystem;
import java.util.Date;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;
/**
 *
 * @author Fahad Satti
 */
public class ToyStopInventoryManagementSystem implements Serializable{
    ToyStopService tsService = new ToyStopService();
    public void init() throws ClassNotFoundException, IOException{
        
        tsService.initEmployees();
        tsService.initStores();
        tsService.initToys();
        System.out.println("Init complete");
    }
    
    public void storeData() throws ClassNotFoundException, IOException{
    	//this.init();
        
        try {
           FileOutputStream fileOut =
           new FileOutputStream("myData.ser");
           ObjectOutputStream out = new ObjectOutputStream(fileOut);
           out.writeObject(this.tsService);
           out.close();
           fileOut.close();
           System.out.printf("Serialized data is saved in /tmp");
        }catch(IOException i) {
           i.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        
    	showMenu();
    	Scanner reader = new Scanner(System.in);
    	
    	int i = reader.nextInt();
    	
    	ToyStopInventoryManagementSystem tsims = new ToyStopInventoryManagementSystem();
        //tsims.init();
        
    	
    	
    	switch(i) {
	 	   case 0 :
	 	      // Statements
	 	      break; // optional
	 	   
	 	   case 1 :
	 	      // Statements
	 		  //	SHOW ALL DATA
	 		  tsims.loadData();
	 		   
	 	      break; // optional
	 	   
	 	   case 2 :
	 	      // Statements
	 		  //   ADD A NEW STORE 
	 		  int b = tsims.tsService.addStore();
	 		 System.out.print("Store added: "+b);
	 	      break; // optional
	 	   
	 	   case 3 :
	 	      // Statements
	 		  //   ADD NEW EMPLOYEE
	 		  int a = tsims.tsService.addEmployee();
	 		 System.out.print("Employee Added: "+a);
	 	      break; // optional
	 	      
	 	  case 4 :
	 	      // Statements
	 		  //	ADD A NEW TOY
	 		 int c = tsims.tsService.addToy();
	 		 System.out.print("Toy Added: "+c);
	 	      break; // optional
	 	   
	 	   // You can have any number of case statements.
	 	   default : // Optional
	 	      // Statements
	 	}
    	
    	tsims.storeData();
    	
    	//FileInputStream in = null;
       // FileOutputStream out = null;
        
       // in = new FileInputStream("myData.txt");
        //out = new FileOutputStream("myData.txt");
    	
        //ToyStopInventoryManagementSystem tsims = new ToyStopInventoryManagementSystem();
        //tsims.init();
        //tsims.loadData();
        
        //load previous data
        //tsims.loadData();
        
        //int a = tsims.tsService.addEmployee();
        //int b = tsims.tsService.addStore();
        
        //System.out.print(a+","+b);
        
        
        //tsims.storeData();
        
        //tsims.showMenu();
        //tsims.printAll();
        
    }
    private void loadData() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        
        //ToyStopService e = null;
        
        try {
           FileInputStream fileIn = new FileInputStream("myData.ser");
           ObjectInputStream in = new ObjectInputStream(fileIn);
           tsService = (ToyStopService) in.readObject();
           in.close();
           fileIn.close();
        }catch(IOException i) {
           i.printStackTrace();
           return;
        }catch(ClassNotFoundException c) {
           System.out.println("class not found");
           c.printStackTrace();
           return;
        }
        
        System.out.println("Deserialized...");
                
    }
    private static void showMenu() {
        System.out.println("Welcome to Toy Stop Inventory Management System");
        System.out.println("Enter 1 to show all data");
        System.out.println("Enter 2 to add a new Store");
        System.out.println("Enter 3 to add a new Employee");
        System.out.println("Enter 4 to add a new Toy");
        System.out.println("Enter 0 to save state");
    }
    private void printAll() {
        System.out.println(this.tsService.stores);
    }
    
}