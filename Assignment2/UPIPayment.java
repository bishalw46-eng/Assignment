package Assignment2;

class UPIPayment extends Payment {
    private String upiId;

    public UPIPayment(double amount, String payerName, String upiId) {
        super(amount, payerName);
        this.upiId = upiId;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing UPI payment for " + upiId + "...");
        status = "Success";
        System.out.println("Success");
    }
}
