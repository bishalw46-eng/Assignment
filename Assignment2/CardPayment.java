package Assignment2;

class CardPayment extends Payment {
    private String cardNumber;
    private String bankName;

    public CardPayment(double amount, String payerName, String cardNumber, String bankName) {
        super(amount, payerName);
        this.cardNumber = cardNumber;
        this.bankName = bankName;
    }

    @Override
    public void processPayment() {
        status = "Completed";
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        System.out.println("Card payment of $" + amount + " processed through " + bankName
                + " using card ending with " + lastFourDigits + ".");
    }
}
