package Iterator;

import java.util.List;

public class ProductIterator implements Iterator {
    List<Product> list;
    int index=0;
    ProductIterator(List<Product> list)
    {
       this.list=list;
    }

    public boolean hasNext(){
      return index<list.size();
    }

    public Object next(){
        if (hasNext()) {
            return list.get(index++);
        }
        return null;
    }
}
