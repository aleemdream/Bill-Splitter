import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;
//import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.Locale;

public class billSplitter
{
    //Method to round to the nearest hundreths for doubles
    public static double round(double value, int places)
    {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
    }    
    //Main Method
    public static void main(String args[])
    {
        //to format decimals as currency
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        //to read inputs
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Bill Splitting Program\n");
        System.out.print("Please enter the total amount of people who contributed to the bill: ");
        int number = Integer.parseInt(scanner.nextLine());

        //individual costs for each person
        double[] costs = new double[number];
        //updated costs for each person after adding tax/tip/covers
        double[] perCosts = new double[number];
        //names of people
        String[] names = new String[number];
        //Boolean value to represent if a person's bill is being covered by the rest of the party
        Boolean[] covered = new Boolean[number];
        //Inputs each person and their individual costs
        for (int i = 0; i < number; i++)
        {
            System.out.print("\nEnter Person " + (1+i) + "'s name: ");
            names[i] = scanner.nextLine();
            System.out.print("\nEnter how much of the cost " + names[i] + " added to the bill: $");
            costs[i] = Double.parseDouble(scanner.nextLine());
            boolean b = true;
            while(b)
            {
                System.out.print("\nIs " + names[i] + " bill going to be covered by the rest of the group? Enter 'y' for yes, enter 'n' for no: ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("y"))
                {   
                    covered[i] = true;
                    b = false;
                }
                else if (response.equalsIgnoreCase("n"))
                {
                    covered[i] = false;
                    b = false;
                }
                else
                    System.err.println("Invalid response, try again");
            }
        }
        System.out.print("\nHow much is the tax on the bill? $");
        double tax = Double.parseDouble(scanner.nextLine());
        System.out.print("\nHow much is the tip on the bill? $");
        double tip = Double.parseDouble(scanner.nextLine());
        double total = 0;
        for (int i = 0; i < number; i++)
        {
            total += costs[i];
        }
        double subtotal = total;
        total += tax;
        total += tip;
        double taxtip = tax+tip;
        for (int i = 0; i < number; i++)
        {
            double percentage = costs[i] / subtotal;
            perCosts[i] = costs[i] + (percentage * taxtip);
        }
        for (int i = 0; i < number; i++)
        {
            if (covered[i])
            {
                double coveredPerPerson = perCosts[i] / (number-1);
                perCosts[i] = 0;
                for (int j = 0; j < number; j++)
                {
                    if (j !=i)
                    {
                        perCosts[j] += coveredPerPerson;
                    }
                }
            }
        }

        System.out.println("\n\nSplit Bill:");
        String moneySubtotal = formatter.format(subtotal);
        System.out.println("Subtotal: $" + moneySubtotal);
        String moneyTax = formatter.format(tax);
        System.out.println("Tax: $" + moneyTax);
        String moneyTip = formatter.format(tip);
        System.out.println("Tip: $" + moneyTip);
        for (int i = 0; i < number; i++)
        {
            String moneyString = formatter.format(round(perCosts[i],2));
            System.out.println(names[i] + "'s total: $" + moneyString);
            check += perCosts[i];
        }
        String moneytotal = formatter.format(total);
        System.out.println("Total: $" + moneytotal);
        scanner.close();
    }
}