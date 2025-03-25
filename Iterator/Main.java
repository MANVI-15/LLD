package Iterator;

public class Main {
    public static void main(String[] args) {
        Product p1=new Product("cream",1);
        Product p2=new Product("facewash",2);

        InventoryAggregator inventory=new InventoryAggregator();
        inventory.addProducts(p1);
        inventory.addProducts(p2);

        Iterator itr=inventory.createIterator();

        while(itr.hasNext())
        {
            Product p=(Product)itr.next();
            System.out.println(p.getName());
        }
    }
}
