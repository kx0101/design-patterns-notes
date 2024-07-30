# Intent

Builder is a creational design pattern that lets you construct complex objects step by step. The pattern allows you to produce different types and representations of an object using the same consturction code.

# Components:

## 1. Product
The complex object that is being built. This class will often have a variety of fields and configurations.

```java
class Computer {
    private String CPU;
    private String GPU;
    private String RAM;
    private String storage;
    private String coolingSystem;

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setCoolingSystem(String coolingSystem) {
        this.coolingSystem = coolingSystem;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", GPU=" + GPU + ", RAM=" + RAM +
                ", Storage=" + storage + ", CoolingSystem=" + coolingSystem + "]";
    }
}
```

## 2. Builder
An abstract interface or class that defines the steps requried to build a product. It typically includes methods to build different parts of the product.

```java
interface ComputerBuilder {
    void buildCPU();

    void buildGPU();

    void buildRAM();

    void buildStorage();

    void buildCoolingSystem();

    Computer getResult();
}
```

## 3. ConcreteBuilder
Implements the builder interface and provides specific implementations for consturcting the parts of the product. It also keeps track of the product instance and provides a method to retrieve it.

```java
class GamingBuilder implements ComputerBuilder {
    private Computer computer = new Computer();

    @Override
    public void buildCPU() {
        computer.setCPU("High-end Gaming CPU");
    }

    @Override
    public void buildGPU() {
        computer.setGPU("High-end Gaming GPU");
    }

    @Override
    public void buildRAM() {
        computer.setRAM("16GB RAM");
    }

    @Override
    public void buildStorage() {
        computer.setStorage("1TB SSD");
    }

    @Override
    public void buildCoolingSystem() {
        computer.setCoolingSystem("Advanced Cooling");
    }

    @Override
    public Computer getResult() {
        return computer;
    }
}

class WorkstationPCBuilder implements ComputerBuilder {
    private Computer computer = new Computer();

    @Override
    public void buildCPU() {
        computer.setCPU("High-performance Workstation CPU");
    }

    @Override
    public void buildGPU() {
        computer.setGPU("Professional GPU");
    }

    @Override
    public void buildRAM() {
        computer.setRAM("32GB RAM");
    }

    @Override
    public void buildStorage() {
        computer.setStorage("2TB HDD + 512GB SSD");
    }

    @Override
    public void buildCoolingSystem() {
        computer.setCoolingSystem("Efficient Air Cooling");
    }

    @Override
    public Computer getResult() {
        return computer;
    }
}
```

## 4. Director (Optional)
Responsible for controlling the consturction process. It uses a Builder instance to construct the product according to a specific sequence of steps.

```java
class ComputerDirector {
    ComputerBuilder builder;

    public ComputerDirector(ComputerBuilder builder) {
        this.builder = builder;
    }

    public void setComputerBuilder(ComputerBuilder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildCPU();
        builder.buildGPU();
        builder.buildRAM();
        builder.buildStorage();
        builder.buildCoolingSystem();
    }
}
```

# Example

```java
public class Builder {
    public static void main(String[] args) {
        ComputerBuilder gamingPCBuilder = new GamingBuilder();
        ComputerDirector director = new ComputerDirector(gamingPCBuilder);
        director.construct();

        Computer gamingPC = gamingPCBuilder.getResult();
        System.out.println("Gaming PC: " + gamingPC);

        ComputerBuilder workstationPCBuilder = new WorkstationPCBuilder();
        director.setComputerBuilder(workstationPCBuilder);
        director.construct();

        Computer workstationPC = workstationPCBuilder.getResult();
        System.out.println("Workstation PC: " + workstationPC);
    }
}
```

*Output:*
```
Gaming PC: Computer [CPU=High-end Gaming CPU, GPU=High-end Gaming GPU, RAM=16GB RAM, Storage=1TB SSD, CoolingSystem=Advanced Cooling]
Workstation PC: Computer [CPU=High-performance Workstation CPU, GPU=Professional GPU, RAM=32GB RAM, Storage=2TB HDD + 512GB SSD, CoolingSystem=Efficient Air Cooling]
```

# When to use it

- Use the Builder pattern to get rid of a "telescoping constructor" (A constructor with ten optional parameters)
- Use the Builder pattern when you want your code to be able to create different representations of some product (for example, gaming and workstation computers)
- Use the Builder pattern to construct Composite trees or other complex objects

# Cons

- Can introduce additional complexity due to the extra classes needed (e.g., builders for each product type).
- May be overkill for simple objects with few attributes or configurations.
- If the object being constructed is simple, using a builder might seem redundant compared to direct instantiation.
