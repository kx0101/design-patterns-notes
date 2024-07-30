# Intent
Singleton is a creational design pattern taht lets you ensure that a class has only one instance, while providing a global access point to this instance.

# Components:

## 1. Static Variable
A static variable which holds the single instance of this class. This field is typically private and static to ensure that it belongs to itself rather than any object instance, and it is used to store the only instance of this class.

```java
class Logger {
    private static Logger instance;
}
```

## 2. Private Constructor
The constructor of the class is made private to prevent other classes from creating a new instance, which means it cannot be instatiated from outside the class.

```java
class Logger {
    private static Logger instance;

    private Logger() {

    }
```
## 3. A Method To Return The Instance
A public static method, often named *getInstance*, returns the single instance of this class. The method first checks if the instance already exists; if not, it creates and returns it. This method essentially provides a global access point to the instance.

*In a multi-threaded env, we need to be careful! We need to make sure that the Singleton instance is created only once, we can use synchronization techniques to prevent different threads creating multiple instances.

```java
public static Logger getInstance() {
    if (instance == null) {
        synchronized (Logger.class) {
            if (instance == null) {
                instance = new Logger();
            }
        }
    }
    return instance;
}
```

# Example

```java
public class Singleton {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Starting the application...");

        Logger anotherLogger = Logger.getInstance();

        if (logger == anotherLogger) {
            System.out.println("it's still the same logger!");
        }
    }
}
```

*Output: *

```
Log: Starting the application...
it's still the same logger!
```

# When to use it

- Use the Singleton pattern when a class in your program should have just a single instance available to all clients; for example, a single database object shared by different parts of the program.
- Use the Singleton pattern when you need stricter control over global variables.

# Cons:

- Can introduce issues with testing, as it can be difficult to mock the Singleton instance.
- Can lead to a global state, making the system more difficult to understand and maintain.
- May require additional effort to ensure thread safety in multi-threaded applications.
