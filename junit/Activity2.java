package junit;

import org.testng.annotations.Test;

public class Activity2 {
    private Integer balance;
    
    // Create a constructor
    public Activity2(Integer initialBalance) {
        balance = initialBalance;
    }

    // Add method to calculate
    // balance amount after withdrawal
    public Integer withdraw(Integer amount) {
        if (balance < amount) {
            throw new NotEnoughFundsException(amount, balance);
        }
        balance -= amount;
        return balance;
    }

}
