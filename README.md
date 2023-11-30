# Bill Splitter
This repository contains the Bill Splitter program

This program is meant to solve the issue of bills being split at restaurants where the restaurant can only take one card down as a payment. This program will split the bill so that each person is only responsible for what they ordered. Additionally, this program also assigns the burden of covering tax/tip proportionally relating to how much each person contributed to the overall subtotal of the bill. This program can also account for when certain individuals out of the group are having their bill covered by everyone else.

## How To Use:
The program works through the command line, where it accepts inputs.

The program first prompts for the total number of users who contributed towards the bill.
**Make sure to account for everyone who contributed towards the bill, even if their portion of the bill is being covered by the rest of the group.**

Next, the program will ask for the names of each person, their individual contributions towards the bill **before tax/tip**, and whether or not their bill is being covered by the rest of the group.

Finally, the program will ask for the tax and the tip on the final bill.

The program will then output each person's name and how much they are responsible for.

## Justification for this method:
This program hopes to make bill splitting as fair as possible. A traditional bill-splitting program is flawed for many reasons, which will be highlighted in the given example:

Let's say two people, Alice and Bob, go out to eat at Mama Chang's Restaurant. Alice orders $4 worth of food, while Bob orders $6 worth of food. The bill comes out as follows:

- Food item 1: $4
- Food item 2: $6
- Subtotal: $10
- Tax: $0.50
- Tip: $0.50
- Total: $11

For simplicity of explanation, we will combine the tax and tip by saying tax/tip. So tax/tip is $1.00

The restaurant only accepts 1 card for payment, so Alice puts their card down, and Bob is expected to pay Alice back later. The traditional bill splitting method would be as follows: Alice pays for their $4 order, Bob pays for their $6, Alice pays for half of the tax+tip ($0.50), and Bob pays for half of the tax+tip ($0.50). So in the end, Alice pays $4.50 and Bob pays $6.50. This method is **flawed**, as it is not fair to Alice that the tax+tip is divided by the number of people (2, for Alice and Bob), when tax+tip goes up directly in response to the amount on the subtotal.

This program solves that unfairness issue with a simple fix: the percentage of money each person was responsible for on the subtotal is the same percentage of money each person is responsible for on the tax+tip. If we apply this to the Alice and Bob example, here is how it changes: Instead of paying half of the tax+tip ($0.50), Alice would pay 40% of the tax+tip because Alice was responsible for 40% of the subtotal ($4 out of $10), and Bob would pay 60% of the tax+tip because Bob was responsible for 60% of the subtotal ($6 out of $10). So in the end, Alice pays $4.40 and Bob pays $6.60. The difference is a few cents, sure, but this is the most fair method.

The benefit of this program can be further shown in a more extreme example:

Let's say Alice, Bob, and Carli went out to dinner at Mama Chang's Restaurant. Alice bought $5 worth of food, Bob bought $5 worth of food, and Carli bought $890 worth of food. The Bill comes out as follows:

- Food item 1: $5
- Food item 2: $5
- Food item 3: $890
- Subtotal: $900
- Tax: $50
- Tip: $50
- (For simplicity, tax+tip = $100)
- Total: $1000

In the traditional bill-splitting algorithm, Alice, Bob, and Carli would each pay $33.33 to cover the tax+tip. This is clearly unfair to Alice and Bob, who purchased much less than Carli and therefore contributed much less to the subtotal. **Remember, the tax+tip goes up directly in response to the subtotal going up.** Under the new algorithm, the percentage of each person's contribution to the subtotal would be the same percentage of their coverage of the tax+tip. So instead of Alice and Bob paying $38.33 for their meals ($5 for the food, $33.33 for the tax+tip), Alice and Bob would each pay $5.56 ($5 for the food, $0.56 for their contribution to the overall tax+tip) while Carli would pay $988.89 ($890 for the food, $98.89 for their contribution to the overall tax+tip).

This program, therefore, results in the most fair bill splitting.

