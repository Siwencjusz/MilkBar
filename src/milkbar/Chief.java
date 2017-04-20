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
public class Chief extends Thread {
    private int numberOfMeals;
    private MilkBar milkBar;
    private int limit;
    public Chief(MilkBar Bar){
        milkBar=Bar;
        numberOfMeals=0;
    }
    
    @Override
    public void run(){
        try{
            while(true){
                milkBar.DoMeal(new Meal());
                numberOfMeals++;
            }
        }catch(InterruptedException exception)
        {
           System.out.print(Thread.currentThread().getName()+" Bar is closed. I have finished my work");
        }
    
    }

    public int getNumberOfMeals() {
        return numberOfMeals;
    }
    
}
