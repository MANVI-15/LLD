package Template;

public class PayToMerchant extends PaymentFlow{

    public void validateRequest(){
        System.out.println("Request is validated for Pay to merchant");
    }
    public void calculateFees(){
        System.out.println("Fees is 0");
    }
    public void debitAmount(){
        System.out.println("Amount debited 600");
    }

    public void creditAmount(){
        System.out.println("Amount credited 600");
    }

}
