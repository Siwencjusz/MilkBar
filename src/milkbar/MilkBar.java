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

    public MilkBar(int howManyCustomers) {
        working = false;
        meals = new LinkedStack<>();
        chief = new Chief(this);
        chief.setName("chief");
        customers = new Customer[howManyCustomers];
        for (int i = 0; i < howManyCustomers; i++) {
            Customer tmp = new Customer(this);
            tmp.setName("customer " + i);
            customers[i] = tmp;
        }

    }

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        MilkBar bar = new MilkBar(10);
        bar.OpenBar();
        Thread.sleep(10);        
        bar.CloseBar();
        if (bar.IsReadyToSummarry())bar.WriteSummary();
        
        
    }

    public void OpenBar() throws InterruptedException {

        for (Customer customer : customers) {
            customer.start();
        }
                
        chief.start();
        working = true;
    }

    public boolean IsReadyToSummarry(){
    
    boolean hasProgramFinished = false;
        while (!hasProgramFinished) {
            int counter = 0;
            for (Customer customer : customers) {
                if (customer.Interrupt()) {
                    counter++;
                }
            }
            if (counter==customers.length) {
                hasProgramFinished=true;
            }
        }
        return hasProgramFinished;
    }
    public void CloseBar() throws InterruptedException {

        
        for (Customer customer : customers) {
            customer.interrupt();
        }
        chief.interrupt();
        working = false;
        
    }

    public void WriteSummary() {

        
        System.out.println("================================ \n");
        int sold = 0;
        for (Customer cust : customers) {
            int atenMeals = cust.GetNumberOfatenDishes();
            sold += atenMeals;
            System.out.println("Customer" + cust.getName() + " ate " + atenMeals + " meals \n");
        }

        System.out.println("Sold meals: " + sold+"\n");
        int producedMeals = chief.getNumberOfMeals();
        System.out.println("Chief produced " + producedMeals + " meals"+"\n");
    }

    public synchronized void DoMeal(Meal item) throws InterruptedException {
        WaitForWorking();
        meals.push(item);
        notifyAll();
    }

    public synchronized int GetMeal() throws InterruptedException {
        WaitForNotEmpty();
        meals.pop();
        notifyAll();
        return 1;
    }

    private synchronized void WaitForWorking() throws InterruptedException {
        while (!working) {
            System.out.println(Thread.currentThread().getName() + " is waiting for meal");
            wait();
        }
    }

    private synchronized void WaitForEmpty() throws InterruptedException {
        while (!meals.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " is waiting for customers to get meal");
            wait();
        }
    }

    private synchronized void WaitForNotEmpty() throws InterruptedException {
        while (meals.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " is waiting for meal");
            wait();
        }
    }
}
