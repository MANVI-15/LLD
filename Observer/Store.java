package Observer;

public class Store {
    public static void main(String[] args) {
        StockObservable mixer=new Stock();

        EmailNotification user1=new EmailNotification(mixer,"manvi");
        EmailNotification user2=new EmailNotification(mixer,"janvi");


        mixer.add(user1);
        mixer.add(user2);
        mixer.setStockCount(5);
    }
}
