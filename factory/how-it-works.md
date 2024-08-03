# Intent

Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to change the type of objects that will be created.

# Structure

![structure](/factory/factory.png)

# Components:

## 1. Product
This is an interface or abstract class that defines the common interface for all objects that the factory method will create. It declares the methods that will be implemented by concrete products.

```java
interface Pizza {
    void prepare();
}
```

## 2. ConcreteProduct
These are the classes that implement the Product interface. Each ConcreteProduct represents a specific type of product that the factory can create.

```java
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
```

## 3. Creator (or Base)
This is an abstract class or interface that declares the factory method. This method returns an object of type Product. The Creator class may also provide a default implementation of the factory method or include additional methods that can use the Product objects created by the factory method.

```java
abstract class PizzaStore {
    public abstract Pizza createPizza();

    public void orderPizza() {
        Pizza pizza = createPizza();
        pizza.prepare();
    }
}
```

## 4. ConcreteCreator
This class implements the factory method declared in the Creator class. It overrides the factory method to return an instace of a ConcreteProduct

```java
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
```

# Example

```java
class Factory {
    public static void main(String[] args) {
        PizzaStore nyStore = new NewYorkPizzaStore();
        nyStore.orderPizza();

        PizzaStore chicagoStore = new ChicagoPizzaStore();
        chicagoStore.orderPizza();
    }
}
```

*Output:*
```
Preparing Cheese Pizza...
Preparing Pepperoni Pizza...
```

# When to use it

- Use the Factory Pattern when you don't know beforehand the exact types and dependencies of the objects your code should work with.
- Use the Factory Pattern when you want to provide users of your library or framework with a way to extend its internal components
- Use the Factory Pattern when you want to save system resources by reusing existing objects instead of rebuilding them each time. 

# Cons

- Adds additional layers of abstraction, making the system more complex.
- The indirection can make it harder to trace the actual instantiation of objects, complicating debugging.
- As new product types are added, the factory may need to be updated, potentially violating the Open/Closed Principle.
