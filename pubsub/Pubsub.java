package pubsub;

import java.util.ArrayList;

interface Investor {
    void update(String stockName, float price);
}

interface Stock {
    void registerInvestor(Investor investor);

    void removeInvestor(Investor investor);

    void notifyInvestors();
}

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
