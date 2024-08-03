# Intent

Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they're observing.

# Structure

![structure](/pubsub/pubsub.png)

# Components:

## 1. Subject
The Subject component is the central point of management for observers interested in its state changes. It maintains a collection of observers and offers methods to register (attach) or remove (detach) them. When the Subject's state changes, it notifies all registered observers via the notifyObservers method, making sure that all dependent objects stay updated.

```java
interface Stock {
    void registerInvestor(Investor investor);

    void removeInvestor(Investor investor);

    void notifyInvestors();
}
```

## 2. Observer
The Observer is an interface or abstract class that defines a method, in this case `update`, which is called by the Subject when there is a change in its state. This interface ensures that all concrete observers implement a consistent method for receiving updates, allowing them to react appropriately when the Subject changes.

```java
interface Investor {
    void update(String stockName, float price);
}
```

## 3. Concrete Subject
The Concrete Subject implements the Subject interface and holds the actual data or business logic that is related to the observers. It stores the relevant state (e.g., stock price) and, when this state changes, calls notifyObservers to update all registered observers. This class encapsulates the core functionalities and data basically.

```java
class StockMarket implements Stock {
    private ArrayList<Investor> investors;
    private String stockName;
    private float price;

    public StockMarket(String stockName, float initialPrice) {
        this.investors = new ArrayList<>();
        this.stockName = stockName;
        this.price = initialPrice;
    }

    @Override
    public void registerInvestor(Investor investor) {
        this.investors.add(investor);
    }

    @Override
    public void removeInvestor(Investor investor) {
        this.investors.remove(investor);
    }

    @Override
    public void notifyInvestors() {
        for (Investor investor : this.investors) {
            investor.update(stockName, price);
        }
    }

    public void setStockPrice(float newPrice) {
        this.price = newPrice;
        notifyInvestors();
    }
}
```

## 4. Concrete Observer
Concrete Observers implement the Observer interface and define the update method to handle changes in the subject's state. They maintain a reference to the Subject and update their own state or trigger other behaviors in response to changes.

```java
class IndividualInvestor implements Investor {
    private String name;

    public IndividualInvestor(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, float price) {
        System.out.println("Investor " + name + " notified. " +
                "Stock: " + stockName + " is now " + price);
    }
}

class InvestmentCompany implements Investor {
    private String companyName;

    public InvestmentCompany(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public void update(String stockName, float price) {
        System.out.println("Investment Company " + companyName + " notified. " +
                "Stock: " + stockName + " is now " + price);
    }
}
```

# Example

```java
public class Pubsub {
    public static void main(String[] args) {
        StockMarket googleStock = new StockMarket("Google", 1200.00f);

        Investor investor1 = new IndividualInvestor("Elijah");
        Investor investor2 = new IndividualInvestor("Fani");
        Investor company = new InvestmentCompany("Kappa");

        googleStock.registerInvestor(investor1);
        googleStock.registerInvestor(investor2);
        googleStock.registerInvestor(company);

        googleStock.setStockPrice(1300.00f);
        googleStock.setStockPrice(1000.00f);
    }
}
```

*Output:*
```
Investor Elijah notified. Stock: Google is now 1300.0
Investor Fani notified. Stock: Google is now 1300.0
Investment Company Kappa notified. Stock: Google is now 1300.0
Investor Elijah notified. Stock: Google is now 1000.0
Investor Fani notified. Stock: Google is now 1000.0
Investment Company Kappa notified. Stock: Google is now 1000.0
```

# When to use it

- Use the Observer pattern when changes to the state of one object may require changing other objects, and the actual set of objects is unknown beforehand or changes dynamically.
- Use the Observer pattern when some objects in your app must observe others, but only for a limited time or in specific cases.


# Consistent

- The Observer Pattern does not guarantee the order in which observers are notified. If the order of updates is important, additional mechanisms need to be implemented to ensure a specific sequence.
- Because observers react to changes in the subject's state, it can be challenging to trace the cause and effect during debugging, especially if there are many observers and complex interactions.
- Observers may receive updates that they are not prepared to handle, especially if the subject's state changes unexpectedly
