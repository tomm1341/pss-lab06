package it.unibo.inheritance.impl;

public class ExtendedStrictBankAccount extends SimpleBankAccount{

    private static final double ATM_TRANSACTION_FEE = 1;
    private static final double MANAGEMENT_FEE = 5;
    private static final double TRANSACTION_FEE = 0.1;
 


    public ExtendedStrictBankAccount(int id, double balance) {
        super(id, balance);

    }
    
    @Override
    public void withdraw(int id, double amount) {
        if(isWithdrawAllowed(amount)) {
            withdraw(id, -amount);
        }
    }

    private void transactionOp(int id, double amount){
        if(checkUser(id)) {
            this.balance += amount;
            this.incrementTransactions();
        }
    }

    private boolean isWithdrawAllowed(final double amount) {
        return this.balance >= amount;
    }
    
}
