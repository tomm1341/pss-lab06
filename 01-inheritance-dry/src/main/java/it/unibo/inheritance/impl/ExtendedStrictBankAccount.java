package it.unibo.inheritance.impl;

public class ExtendedStrictBankAccount extends SimpleBankAccount{

    private static final double ATM_TRANSACTION_FEE = 1;
    private static final double MANAGEMENT_FEE = 5;
    private static final double TRANSACTION_FEE = 0.1;
 
    private int transactions;
    private double balance;

    public ExtendedStrictBankAccount(final int id, final double balance) {
        super(id, balance);
        this.balance = balance;
    }
    
    @Override
    public void withdraw(final int id, double amount) {
        if(isWithdrawAllowed(amount)) {
            withdraw(id, -amount);
        }
    }

    @Override
    public void withdrawFromATM(final int id, double amount){
        if(isWithdrawAllowed(amount)){
            withdrawFromATM(id, -amount);
        }
    }

    @Override
    public void chargeManagementFees(final int id){
        final double feeAmount = MANAGEMENT_FEE + transactions * StrictBankAccount.TRANSACTION_FEE;
    }

    private void transactionOp(final int id, double amount){
        if(checkUser(id)) {
            this.balance += amount;
            this.incrementTransactions();
        }
    }

    private boolean isWithdrawAllowed(final double amount) {
        return this.balance >= amount;
    }

    
}
