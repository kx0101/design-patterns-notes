# Intent

Strategy is a behavioral pattern that lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangable.

# Structure

![structure](/strategy/strategy.png)

# Components:

## 1. Context
The Context class is configured with a Strategy object. It maintains a reference to the current Strategy object and delegates the algorithm execution to this object.

```java
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
```

## 2. Strategy
The strategy interface defines a common interface for all supported algorithms. It declares a method that the context uses to execute the algorithm.

```java
interface PaymentStrategy {
    void pay(double amount);
}
```

## 3. Concrete Strategy
We also need to define the actual implementation of each algorithm, by implementing the Strategy interface.

```java
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
```

# Example

```java
public class Strategy {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234", "Liakos", "123"));
        context.execute(250.75);

        context.setPaymentStrategy(new PayPalPayment("liakos.koulaxis@yahoo.com", "1234"));
        context.execute(120.50);
    }
}
```

*Output:*
```
Paying 250.75 euros, using credit card...
Paying 120.50 euros, using paypal...
```

# When to use it

- Use the Strategy pattern when you want to use different variants of an algorithm within an object and be able to switch from one algorithm to another during runtime.
- Use the Strategy pattern when you have a lot of similar classes that only differ in the way they execute some behavior
- Use the Strategy pattern to isolate business logic of a class from the implementation details of algorithms that may not be as important in the context of that logic.
- Use the Strategy pattern when your class has a massive conditional statement that switches between different variants of the same algorithm.

# Cons
- If you only have a couple of algorithms and they rarely change, there’s no real reason to overcomplicate the program with new classes and interfaces that come along with the pattern.
- Clients must be aware of the different strategies and understand when to use each one. 
- The pattern relies on the client to select the appropriate strategy. There is no built-in mechanism to ensure that the chosen strategy is suitable for a particular context or input
- If strategies need to access the context or share some state, they may require additional parameters to be passed around, potentially leading to code duplication or the need for complex data structures to manage shared state.
