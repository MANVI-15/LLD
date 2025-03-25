package Template;

public class PayToFriend extends PaymentFlow{

    public void validateRequest(){
        System.out.println("Request is validated for Pay to friend");
    }
    public void calculateFees(){
        System.out.println("Fees is 100");
    }
    public void debitAmount(){
        System.out.println("Amount debited 500");
    }

    public void creditAmount(){
        System.out.println("Amount credited 400");
    }

}
