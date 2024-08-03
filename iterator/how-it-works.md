# Intent

Iterator is a behavioral design pattern that lets you traverse element of a collection without exposing its underlying representation (list, stack, tree etc)

# Structure

![structure](/iterator/iterator.png)

# Components:

## 1. Iterator
An interface that declares methods like hasNext and next to iterate over the collection's elements. It hides the underlying representation of the collection, providing a standard way to traverse it.

```java
interface Iterator<T> {
    boolean hasNext();

    T next();
}
```

## 2. Concrete Iterator
Implements the Iterator interface and keeps track of the current position within the collection. It handles the iteration logic specific to the type of traversal being used.

```java
class DFSIterator implements Iterator<FileSystemNode> {
    private Stack<FileSystemNode> stack;

    public DFSIterator(FileSystemNode root) {
        this.stack = new Stack<>();
        stack.push(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public FileSystemNode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        FileSystemNode curr = stack.pop();

        if (curr instanceof Directory) {
            List<FileSystemNode> children = ((Directory) curr).getChildren();

            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }

        return curr;
    }
}

class BFSIterator implements Iterator<FileSystemNode> {
    private Queue<FileSystemNode> queue;

    public BFSIterator(FileSystemNode root) {
        this.queue = new LinkedList<>();
        this.queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public FileSystemNode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        FileSystemNode curr = queue.poll();

        if (curr instanceof Directory) {
            List<FileSystemNode> children = ((Directory) curr).getChildren();
            queue.addAll(children);
        }

        return curr;
    }
}
```

## 3. Collection
Defines an interface for creating an iterator. It provides a method to return an iterator instance, allowing clients to traverse its elements without knowing the internal details of the collection. In our example, The FileSystemNode interface defines the methods for accessing and managing child nodes, used by directories to create iterators, so it doesn't directrly create iterators, but it's used by directories to provide iterators.

```java
interface FileSystemNode {
    String getName();
}
```

## 4. Concrete Collection
Implements the Aggregate interface and returns an instance of a ConcreteIterator. It encapsulates the collection’s data and provides a way to access its elements via the iterator. In our example, The Directory class implements the FileSystemNode interface, holds child nodes, and provides methods to create and return DFSIterator or BFSIterator for traversal.

```java
class File implements FileSystemNode {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

class Directory implements FileSystemNode {
    private String name;
    private List<FileSystemNode> children;

    public Directory(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public List<FileSystemNode> getChildren() {
        return this.children;
    }

    public void add(FileSystemNode node) {
        this.children.add(node);
    }
}
```

# Example

```java
public class IteratorDemo {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");

        Directory subDir1 = new Directory("subDir1");
        File file3 = new File("file3.txt");
        Directory subDir2 = new Directory("subDir2");

        root.add(file1);
        root.add(file2);
        root.add(subDir1);
        subDir1.add(file3);
        subDir1.add(subDir2);

        System.out.println("DFS Traversal");
        Iterator<FileSystemNode> dfsIterator = new DFSIterator(root);
        while (dfsIterator.hasNext()) {
            System.out.println(dfsIterator.next().getName());
        }

        System.out.println("BFS Traversal");
        Iterator<FileSystemNode> bfsIterator = new BFSIterator(root);
        while (bfsIterator.hasNext()) {
            System.out.println(bfsIterator.next().getName());
        }
    }
}
```

*Output:*
```
DFS Traversal
root
file1.txt
file2.txt
subDir1
file3.txt
subDir2
BFS Traversal
root
file1.txt
file2.txt
subDir1
file3.txt
subDir2
```

# When to use it

- Use the Iterator pattern when your collection has a complex data structure under the hood, but you want to hide its complexity from clients (either for convenience or security reasons). 
- Use the Iterator pattern to reduce duplication of the traversal code across your app.
- Use the Iterator when you want your code to be able to traverse different data structures or when types of these structures are unknown beforehand.

# Cons

- The Iterator pattern can sometimes be an overkill if your application only works with simple data structures. 
- Using an iterator may be less efficient than going through elements of some specialized collections directly.
