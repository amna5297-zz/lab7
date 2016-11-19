
package toystopinventorymanagementsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Fahad Satti
 */
public class ToyStopService implements Serializable {
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Store> stores = new ArrayList<>();
    ArrayList<Toy> toys = new ArrayList<>();
        
    public void initEmployees() throws ClassNotFoundException, IOException{
        //Create a List of first 200 Employees
        for(int i=0; i<200; i++){
            Employee myEmployee = new Employee();
            myEmployee.setUID(i);
            myEmployee.setRandomName();
            myEmployee.setEmail(myEmployee.getName()+"@toystop.org");
            
            employees.add(myEmployee);
        }
    }
    
    public void initStores(){
        //Create a List of Stores in a region
        for(int i=0; i<100; i++){
            Store myStore = new Store();
            myStore.setUID(Util.getSaltNum(-1));
            myStore.addRandomEmployees(employees);
            stores.add(myStore);
            myStore.setAddress(Util.getSaltAlphaNumString());
            myStore.setContactNo("+92"+Util.getSaltNum(9));
            Email storeEmail = new Email();
            storeEmail.setEmailAddress(myStore.getUID()+"@toystop.org");
            myStore.setEmail(storeEmail);           
            
        }
        
    }
    
    public void initToys(){
        //Add Toys in random stores
        for(int i=0; i<200000; i++){
            Toy newToy = new Toy();
            newToy.setUID(Util.getSaltNum(-1));
            newToy.setMinAge(Util.getSaltNum(1));
            newToy.setMaxAge(Util.getSaltNum(18));
            newToy.setPrice(Util.getSaltNum(1000));
            newToy.setName(Util.getSaltAlphaString());
            newToy.setAddedOn(LocalDateTime.now());
            
            Random randStore = new Random();
            int index = randStore.nextInt(stores.size());
            Store selectedStore = (Store)stores.get(index);
            selectedStore.addToy(newToy);
            
        }
    }
    //Only creates a new employee and returns the UID
    public int addEmployee(){
            Employee myEmployee = new Employee();
            
            myEmployee.setRandomName();
            myEmployee.setEmail(myEmployee.getName()+"@toystop.org");
            myEmployee.setUID(employees.size()+1);
            employees.add(myEmployee);
                        
            return myEmployee.getUID();
    }
    
    //Creates a new store
    public int addStore(){
            Store myStore = new Store();
            myStore.setUID(Util.getSaltNum(-1));
            //This will assign any new employees or the ones remaining after previous store insertions.
            myStore.addRandomEmployees(employees);
            
            myStore.setAddress(Util.getSaltAlphaNumString());
            myStore.setContactNo("+92"+Util.getSaltNum(9));
            Email storeEmail = new Email();
            storeEmail.setEmailAddress(myStore.getUID()+"@toystop.org");
            myStore.setEmail(storeEmail);
            stores.add(myStore);                    
            
            return myStore.getUID();
    }
    
    //Creates a new Toy
    public int addToy(){
            Toy myToy = new Toy();
            myToy.setUID(toys.size()+1);            
            myToy.setName(Util.getSaltAlphaNumString());
            myToy.setPrice(Util.getSaltNum(9));
            myToy.setMinAge(Util.getSaltNum(1));
            myToy.setMaxAge(Util.getSaltNum(18));
            myToy.setAddedOn(LocalDateTime.now());
            toys.add(myToy);
            
            Random randStore = new Random();
            int index = randStore.nextInt(stores.size());
            Store selectedStore = (Store)stores.get(index);
            selectedStore.addToy(myToy);
                          
            return myToy.getUID();
    }
}
