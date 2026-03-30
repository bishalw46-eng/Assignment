package Assignment2;

class CashPayment extends Payment {
    public CashPayment(double amount, String payerName) {
        super(amount, payerName);
    }

    @Override
    public void processPayment() {
        status = "Completed";
        System.out.println("Cash payment of $" + amount + " received.");
    }
}
