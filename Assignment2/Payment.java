package Assignment2;

abstract class Payment {
    protected double amount;
    protected String payerName;
    protected String status;

    public Payment(double amount, String payerName) {
        this.amount = amount;
        this.payerName = payerName;
        this.status = "Pending";
    }

    public abstract void processPayment();

    public void displayReceipt() {
        System.out.println("Receipt -> Payer: " + payerName + ", Amount: $" + amount + ", Status: " + status);
    }
}
