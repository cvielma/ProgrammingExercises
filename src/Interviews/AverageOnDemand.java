/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interviews;

/**
 * Implement a class that can calculate the running average of a stream of input numbers up to a maximum of N numbers. http://www.glassdoor.com/Interview/Google-Software-Engineer-Interview-Questions-EI_IE9079.0,6_KO7,24.htm
 */
public class AverageOnDemand {

    int sum = 0;
    int count = 0;

    public double avg(final int num) {
        sum += num;
        count++;

        return (double) sum / count;
    }
    
    public static void main(String[] args) {
        AverageOnDemand avg = new AverageOnDemand();
        System.out.println(avg.avg(1));
        System.out.println(avg.avg(5)); // 3
        System.out.println(avg.avg(9)); // 5
        System.out.println(avg.avg(13)); // 7
        System.out.println(avg.avg(-28)); //0
        
    }
}
