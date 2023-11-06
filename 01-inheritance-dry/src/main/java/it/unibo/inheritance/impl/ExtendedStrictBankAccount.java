package it.unibo.inheritance.impl;

public class ExtendedStrictBankAccount extends SimpleBankAccount{

    private static final double ATM_TRANSACTION_FEE = 1;
    private static final double MANAGEMENT_FEE = 5;
    private static final double TRANSACTION_FEE = 0.1;
 
    private int transactions;
    private double balance;

    public ExtendedStrictBankAccount(final int id, final double balance) {
        super(id, balance);
        //this.balance = balance;
    }

    @Override
    public void deposit(final int id, final double amount) {
        if(checkUser(id)){
            this.transactionOp(id, amount);
        }
    }

    @Override
    public void depositFromATM(final int id, final double amount) {
        if(checkUser(id)) {
            this.deposit(id, amount - ExtendedStrictBankAccount.ATM_TRANSACTION_FEE);
        }
    }
    
    @Override
    public void withdraw(final int id, final double amount) {
        if(isWithdrawAllowed(amount) && checkUser(id)) {
            this.transactionOp(id, -amount);
        }
    }

    @Override
    public void withdrawFromATM(final int id, final double amount){
        if(isWithdrawAllowed(amount) && checkUser(id)){
            this.withdraw(id, amount + ExtendedStrictBankAccount.ATM_TRANSACTION_FEE);
        }
    }

    @Override
    public void chargeManagementFees(final int id) {
        final double feeAmount = MANAGEMENT_FEE + super.getTransactionsCount() * ExtendedStrictBankAccount.TRANSACTION_FEE;
        if(checkUser(id) && isWithdrawAllowed(feeAmount)) {
            this.transactionOp(id, -feeAmount);
            super.resetTransactions();
        }
    }

    private void transactionOp(final int id, final double amount) {
        if (checkUser(id)) {
            this.balance += amount;
            this.incrementTransactions();
            super.setBalance(balance);
        }
    }

    private boolean isWithdrawAllowed(final double amount) {
        return balance >= amount;
    }

    
}
