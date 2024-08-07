package facade;

class Light {
    public void on() {
        System.out.println("Light is on.");
    }

    public void off() {
        System.out.println("Light is off.");
    }
}


class Thermostat {
    public void setTemperature(int temperature) {
        System.out.println("Thermostat is set to " + temperature + " degrees.");
    }
}


class SecuritySystem {
    public void activate() {
        System.out.println("Security system activated.");
    }

    public void disactivate() {
        System.out.println("Security system disactivated.");
    }
}


class SmartHomeFacade {
    private Light light;

    private Thermostat thermostat;

    private SecuritySystem securitySystem;

    public SmartHomeFacade(Light light, Thermostat thermostat, SecuritySystem securitySystem) {
        this.light = light;
        this.thermostat = thermostat;
        this.securitySystem = securitySystem;
    }

    public void leaveHome() {
        System.out.println("Leaving home...");
        light.off();
        thermostat.setTemperature(18);
        securitySystem.activate();
    }

    public void arriveHome() {
        System.out.println("Arriving home...");
        light.on();
        thermostat.setTemperature(22);
        securitySystem.disactivate();
    }
}


public class Facade {
    public static void main(String[] args) {
        Light light = new Light();
        Thermostat thermostat = new Thermostat();
        SecuritySystem securitySystem = new SecuritySystem();

        SmartHomeFacade smartHome = new SmartHomeFacade(light, thermostat, securitySystem);

        smartHome.leaveHome();
        smartHome.arriveHome();
    }
}






