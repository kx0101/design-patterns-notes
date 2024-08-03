package strategy;

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardNameHolder;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardNameHolder, String cvv) {
        this.cardNumber = cardNumber;
        this.cardNameHolder = cardNameHolder;
        this.cvv = cvv;
    }

    public void pay(double amount) {
        System.out.printf("Paying %.2f euros, using credit card...\n", amount);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void pay(double amount) {
        System.out.printf("Paying %.2f euros, using paypal...\n", amount);
    }
}

class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void execute(double amount) {
        if (this.paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy is not set");
        }

        this.paymentStrategy.pay(amount);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}

public class Strategy {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234", "Liakos", "123"));
        context.execute(250.75);

        context.setPaymentStrategy(new PayPalPayment("liakos.koulaxis@yahoo.com", "1234"));
        context.execute(120.50);
    }
}
