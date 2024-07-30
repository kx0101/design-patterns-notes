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

interface ComputerBuilder {
    void buildCPU();

    void buildGPU();

    void buildRAM();

    void buildStorage();

    void buildCoolingSystem();

    Computer getResult();
}

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
