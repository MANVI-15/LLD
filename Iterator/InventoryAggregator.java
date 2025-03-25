package Iterator;

import java.util.ArrayList;
import java.util.List;

public class InventoryAggregator {
    List<Product> l=new ArrayList<>();


    public void addProducts(Product p)
    {
        l.add(p);
    }

    public void removeProducts(Product p)
    {
        l.remove(p);
    }

    public Iterator createIterator()
    {
        return new ProductIterator(l);
    }
}
