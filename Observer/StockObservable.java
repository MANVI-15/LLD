package Observer;

public interface StockObservable {

    void add(NotificationAlertObserver obj);
    void remove(NotificationAlertObserver obj);
    void notified();
    void setStockCount(int stock);
    int getStockCount();
}
