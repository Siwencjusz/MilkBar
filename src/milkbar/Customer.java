package milkbar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Atencjusz
 */
public class Customer extends Thread {
    private MilkBar milkBar;
    private int AteMeals;
    boolean interrupt;
    Customer(MilkBar bar) {
            milkBar=bar;
            AteMeals=0;
            interrupt= false;
    }
    
    @Override
    public void run()
    {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " want meal");
                AteMeals+=milkBar.GetMeal();
                System.out.println(Thread.currentThread().getName() + " recived meal");
            }
        }catch (InterruptedException exception)
        {
            System.out.println(Thread.currentThread().getName() + " interrupted");
            interrupt=true;
        }
    }
    public boolean Interrupt(){
        return interrupt;
    }
    public int GetNumberOfatenDishes(){
        return AteMeals;
    }
}
