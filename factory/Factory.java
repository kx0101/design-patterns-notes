interface Pizza {
    void prepare();
}

class CheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing Cheese Pizza...");
    }
}

class PepperoniPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing Pepperoni Pizza...");
    }
}

abstract class PizzaStore {
    public abstract Pizza createPizza();

    public void orderPizza() {
        Pizza pizza = createPizza();
        pizza.prepare();
    }
}

class NewYorkPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza() {
        return new CheesePizza();
    }
}

class ChicagoPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza() {
        return new PepperoniPizza();
    }
}

public class Factory {
    public static void main(String[] args) {
        PizzaStore nyStore = new NewYorkPizzaStore();
        nyStore.orderPizza();

        PizzaStore chicagoStore = new ChicagoPizzaStore();
        chicagoStore.orderPizza();
    }
}
