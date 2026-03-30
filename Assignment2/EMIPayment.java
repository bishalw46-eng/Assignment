package Assignment2;

class EMIPayment extends Payment {
    private int months;

    public EMIPayment(double amount, String payerName, int months) {
        super(amount, payerName);
        this.months = months;
    }

    @Override
    public void processPayment() {
        double monthlyInstallment = amount / months;
        status = "Scheduled";
        System.out.println("EMI payment scheduled for " + months + " months.");
        System.out.println("Monthly installment: $" + monthlyInstallment);
    }
}
