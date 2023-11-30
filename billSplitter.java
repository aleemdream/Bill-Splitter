import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

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
        int number = 0;
        try
        {number = Integer.parseInt(scanner.nextLine());}
        catch(Exception e)
        {
            System.err.println("\nYou did something wrong.");
            System.exit(-1);
        }

        //individual costs for each person
        double[] costs = new double[number];
        //updated costs for each person after adding tax/tip/covers
        double[] perCosts = new double[number];
        //names of people
        String[] names = new String[number];
        //Boolean value to represent if a person's bill is being covered by the rest of the party
        Boolean[] covered = new Boolean[number];
        //number of people being covered
        int coveredFolks = 0;
        //Inputs each person and their individual costs
        for (int i = 0; i < number; i++)
        {
            System.out.print("\nEnter Person " + (1+i) + "'s name: ");
            names[i] = scanner.nextLine();
            System.out.print("\nEnter how much of the cost " + names[i] + " added to the bill: $");
            try
            {costs[i] = Double.parseDouble(scanner.nextLine());}
            catch(Exception e)
            {   
                System.err.println("\nYou did something wrong.");
                System.exit(-1);
            }
            boolean b = true;
            //if the person's bill is going to be covered by the group, it stores that info as a boolean
            while(b)
            {
                System.out.print("\nIs " + names[i] + " bill going to be covered by the rest of the group? Enter 'y' for yes, enter 'n' for no: ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("y"))
                {   
                    covered[i] = true;
                    coveredFolks ++;
                    b = false;
                }
                else if (response.equalsIgnoreCase("n"))
                {
                    covered[i] = false;
                    b = false;
                }
                //forces user to input a valid response
                else
                    System.err.println("Invalid response, try again");
            }
        }
        if (coveredFolks == number)
        {
            System.err.println("\nError: All persons were marked as having their bill covered. Please start over.");
            System.exit(-1);
        }
        //asks for tax and tip, stores values
        double tax = 0;
        System.out.print("\nHow much is the tax on the bill? $");
        try{tax = Double.parseDouble(scanner.nextLine());}
        catch(Exception e)
        {
            System.err.println("\nYou did something wrong.");
            System.exit(-1);
        }
        double tip = 0;
        System.out.print("\nHow much is the tip on the bill? $");
        try{tip = Double.parseDouble(scanner.nextLine());}
        catch(Exception e)
        {
            System.err.println("\nYou did something wrong.");
            System.exit(-1);
        }
        double total = 0;
        //adds up individual totals to get the subtotal
        for (int i = 0; i < number; i++)
        {
            total += costs[i];
        }
        double subtotal = total;
        total += tax;
        total += tip;
        //simplifies math by combining tax and tip
        double taxtip = tax+tip;
        //calculates each persons responsibility over taxtip
        //(individual subtotal contribution / subtotal) * (tax + tip) + individual subtotal contribution = total contribution
        for (int i = 0; i < number; i++)
        {
            double percentage = costs[i] / subtotal;
            //perCosts represents updated final costs per person
            perCosts[i] = costs[i] + (percentage * taxtip);
        }
        //for covered individuals, divides up covered costs amongst the rest of the group
        for (int i = 0; i < number; i++)
        {
            if (covered[i])
            {
                //coveredPerPerson = additional cost to add on to each non-covered person's bill
                double coveredPerPerson = perCosts[i] / (number - coveredFolks);
                perCosts[i] = 0;
                for (int j = 0; j < number; j++)
                {
                    //if both i and j are covered, j will NOT assume i's costs for being covered
                    if (!covered[j])
                        perCosts[j] += coveredPerPerson;
                }
            }
        }
        //prints final bill and personal totals
        System.out.println("");
        for (int i = 0; i < number; i++)
        {
            String moneyString = formatter.format(round(costs[i],2));
            System.out.println(names[i] + "'s order: " + moneyString);
        }
        String moneySubtotal = formatter.format(subtotal);
        System.out.println("Subtotal: " + moneySubtotal);
        String moneyTax = formatter.format(tax);
        System.out.println("Tax: " + moneyTax);
        String moneyTip = formatter.format(tip);
        System.out.println("Tip: " + moneyTip);
        String moneytotal = formatter.format(total);
        System.out.println("Total: " + moneytotal + "\n");
        System.out.println("\nSplit Bill:");
        for (int i = 0; i < number; i++)
        {
            String moneyString = formatter.format(round(perCosts[i],2));
            System.out.println(names[i] + "'s total: " + moneyString);
        }
        System.out.println("Total: " + moneytotal);
        //close scanner
        scanner.close();
    }
}
