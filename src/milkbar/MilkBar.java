/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkbar;

/**
 *
 * @author Atencjusz
 */
public class MilkBar {
    private Chief chief;
    private Customer[] customers;
    private LinkedStack<Meal> meals;
    private boolean working;
    
    public MilkBar(int howManyCustomers){
        meals= new LinkedStack<Meal>();
        chief = new Chief(this);
        chief.setName("chief");
        customers= new Customer[howManyCustomers];
        for (int i = 0; i < howManyCustomers; i++) {
            Customer tmp=new Customer(this);
            tmp.setName("customer "+i);
            customers[i]= tmp;
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        MilkBar bar = new MilkBar(2);
        bar.OpenBar();
        Thread.sleep(1000);
        bar.CloseBar();
        Thread.sleep(1000);
        int sold=0;
        for (Customer cust : customers) {
            sold+=cust.AteMeals;
            System.out.println("Customer" + cust.getName() " ate " + cust.AteMeals +" meals");
        }
        
       System.out.println("Sold meals: " + sold );
       
       System.out.println("Chief produced " + chief.getNumberOfMeals() + " meals" );
    }
    
    
    public void OpenBar()
    {
        working= true;
        chief.start();
        for (Customer customer : customers) {
            customer.start();
        }
    }
    
    public void CloseBar(){
        working= false;
        chief.interrupt();
        for (Customer customer : customers) {
            customer.interrupt();
        }
    }
    
      public synchronized void DoMeal(Meal item) throws InterruptedException
    {
        waitForWorking();
        meals.push(item);
        notifyAll();
    }
     public synchronized int GetMeal() throws InterruptedException
    {
        waitForNotEmpty();
        meals.pop();
        notifyAll();
        return 1;
    }
    private  synchronized void waitForWorking() throws InterruptedException
    {
        while (!working)
        {
            System.out.println(Thread.currentThread().getName() + " is waiting for meal");
            wait();
        }
    }

    private synchronized void waitForEmpty() throws InterruptedException
    {
        while (!meals.isEmpty())
        {
            System.out.println(Thread.currentThread().getName()+" is waiting for customers to get meal");
            wait();
        }
    }

    
    private synchronized void waitForNotEmpty() throws InterruptedException
    {
        while(meals.isEmpty())
        {
            System.out.println(Thread.currentThread().getName()+" is waiting for meal");
            wait();
        }
    }
}
