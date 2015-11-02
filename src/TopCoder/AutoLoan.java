/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * 
Problem Statement
    
Auto dealerships frequently advertise tempting loan offers in order to make it easier for people to afford the "car of their dreams". A typical sales tactic is to show you various cars, and then talk in terms of what your monthly payment would be, to say nothing of how much you are actually paying for the car, how much interest you pay, or how long you have to make payments.
A typical auto loan is calculated using a fixed interest rate, and is set up so that you make the same monthly payment for a set period of time in order to fully pay off the balance. The balance of your loan starts out as the sticker price of the car. Each month, the monthly interest is added to your balance, and the amount of your payment is subtracted from your balance. (The payment is subtracted after the interest is added.) The monthly interest rate is 1/12 of the yearly interest rate. Thus, if your annual percentage rate is 12%, then 1% of the remaining balance would be charged as interest each month.
You have been checking out some of the cars at your local dealership, TopAuto. An excited salesman has just approached you, shouting about how you can have the car you are looking at for a payment of only monthlyPayment for only loanTerm months! You are to return a double indicating the annual percentage rate of the loan, assuming that the initial balance of the loan is price.
Definition
    
Class:
AutoLoan
Method:
interestRate
Parameters:
double, double, int
Returns:
double
Method signature:
double interestRate(double price, double monthlyPayment, int loanTerm)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
Because of the way interest is compounded monthly, the actual interest accrued over the course of a year is not necessarily the same as (balance * yearly interest rate). In fact, it's usually more.
-
In a real situation, information like this would typically need to be disclosed, but since you aren't at a point of signing any paperwork, the salesman has no legal obligation to tell you anything.
-
The return value must be within 1e-9 absolute or relative error of the actual result.
Constraints
-
price will be between 1 and 1000000, inclusive.
-
monthlyPayment will be between 0 and price / 2, inclusive.
-
loanTerm will be between 1 and 600, inclusive.
-
The resulting interest rate will be between 0 and 100, inclusive.
Examples
0)

    
6800
100
68
Returns: 1.3322616182218813E-13
Noting that 68 payments of 100 equals the total price of 6800, so there is no interest.
1)

    
2000
510
4
Returns: 9.56205462458368
Here, we do pay a little interest. At 9.562% annual interest, that means each month we pay 0.7968% of the balance in interest. Our payment schedule looks like this:
Month | + Interest | - Payment | = Balance
------------------------------------------
      |            |           |  2000.00
   1  |     15.94  |   510.00  |  1505.94
   2  |     12.00  |   510.00  |  1007.94
   3  |      8.03  |   510.00  |   505.97
   4  |      4.03  |   510.00  |     0.00
2)

    
15000
364
48
Returns: 7.687856394581649
This is similar to what purchasing a new car with no money down might look like, if you make payments for 4 years.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
 */
package TopCoder;

/**
 *
 * @author cvielma
 */
public class AutoLoan {
    final static double EPSILON = 1e-12;
    
    public static double interestRate(final double price, final double monthlyPayment, final int loanTerm){
	double lo = 0;
	double hi = 100;
	
	while (hi-lo > EPSILON) {
		double middle = lo +(hi-lo)/2;
		double monthlyInterest = middle * ((double)1/12);
		double term = loanTerm;
		double sum = price;
		while (term > 0) { 
			sum += (sum * (monthlyInterest / 100));
			sum = sum - monthlyPayment;
			term--;
		}
 
		if (sum > EPSILON) {
			hi=middle;
		} else {
			lo=middle;
		}
 
	}
 
	return lo;
    }   
    
    public static void main(String[] args) {
        System.out.println(interestRate(6800, 100, 68));
        System.out.println(interestRate(2000, 510, 4));
        System.out.println(interestRate(15000, 364, 48));
    }
}
