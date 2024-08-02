package iterator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

interface FileSystemNode {
    String getName();
}

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

interface Iterator<T> {
    boolean hasNext();

    T next();
}

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
