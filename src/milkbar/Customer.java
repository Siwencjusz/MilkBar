package milkbar;


import static java.lang.Thread.yield;
import milkbar.MilkBar;

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
    public int AteMeals;
    Customer(MilkBar bar) {
            milkBar=bar;
            AteMeals=0;
    }
    
    @Override
    public void run()
    {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " want meal");
                AteMeals+=milkBar.GetMeal();
                System.out.println(Thread.currentThread().getName() + " recived meal");
                yield();
            }
        }catch (InterruptedException exception)
        {
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    }
}
