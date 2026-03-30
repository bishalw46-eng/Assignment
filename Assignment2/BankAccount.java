package Assignment2;

class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;
    private String accountType;
    private int transactionCount;

    public BankAccount(String accountNumber, String holderName, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.accountType = accountType;
        this.transactionCount = 0;

        if (balance < 0) {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public void deposit(double amt) {
        if (amt <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amt;
        transactionCount++;
        System.out.println("Deposited $" + amt + " into " + accountNumber);
    }

    public void withdraw(double amt) {
        if (amt <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amt > balance) {
            System.out.println("Insufficient balance in account " + accountNumber);
            return;
        }
        balance -= amt;
        transactionCount++;
        System.out.println("Withdrawn $" + amt + " from " + accountNumber);
    }

    public void transfer(BankAccount target, double amt) {
        if (target == null) {
            System.out.println("Target account cannot be null.");
            return;
        }
        if (amt <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }
        if (amt > balance) {
            System.out.println("Transfer failed due to insufficient balance in " + accountNumber);
            return;
        }

        balance -= amt;
        target.balance += amt;
        transactionCount++;
        target.transactionCount++;
        System.out.println("Transferred $" + amt + " from " + accountNumber + " to " + target.accountNumber);
    }

    public void displayStatement() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: $" + balance);
        System.out.println("Transactions: " + transactionCount);
        System.out.println("--------------------------------------");
    }
}
