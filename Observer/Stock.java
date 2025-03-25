package Observer;

import java.util.ArrayList;

public class Stock implements StockObservable{
    ArrayList<NotificationAlertObserver>l=new ArrayList<>();
    int  stockCount=0;
    @Override
    public void add(NotificationAlertObserver obj) {
        l.add(obj);
    }

    @Override
    public void remove(NotificationAlertObserver obj) {
     l.remove(obj);
    }

    @Override
    public void notified() {
      for(NotificationAlertObserver o:l)
      {
          o.update();
      }
    }

    @Override
    public void setStockCount(int stock) {
        if(this.stockCount==0)
        {
            notified();
        }
        this.stockCount=this.stockCount+stock;
    }

    @Override
    public int getStockCount() {
        return this.stockCount;
    }


}
