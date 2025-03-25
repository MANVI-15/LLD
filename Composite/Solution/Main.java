package Composite.Solution;

public class Main {
    public static void main(String[] args) {
        File marigold=new File("marigold");
        Directory trees=new Directory("trees");

        trees.addFileSystem(marigold);
        trees.addFileSystem((new Directory("seeds")));

        trees.ls();
    }
}
