package Observer;

public class EmailNotification implements NotificationAlertObserver{

    StockObservable obj;
    String emailId;

    EmailNotification(StockObservable obj,String emailId)
    {
        this.obj=obj;
        this.emailId=emailId;
    }
    @Override
    public void update() {
        sendEmail(emailId);
    }

    private void sendEmail(String emailId)
    {
        System.out.println("Mail is sent to"+" "+emailId);
    }
}
