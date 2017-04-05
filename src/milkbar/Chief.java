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
    public int numberOfMeals;
    private MilkBar milkBar;
    public Chief(MilkBar Bar){
        milkBar=Bar;
        numberOfMeals=0;
    }
    
    @Override
    public void run(){
        //try{
            while(true){
                milkBar.DoMeal(new Meal());
                numberOfMeals++;
            }
        //}catch(InterruptedException exception)
        //{
        //    System.out.print(Thread.currentThread().getName()+" Bar is closed. I am finished my work");
        //}
    
    }

    String getNumberOfMeals() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
