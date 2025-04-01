package Questions.ATM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

enum AccountType {SAVINGS,FIXED_DEPOSIT,CURRENT}
enum OperationType {WITHDRAWL,DEPOSIT}

class Card {
    int pin;
    int card_id;

    Card(int id, int pin) {
        this.pin= pin;
        this.card_id=id;
    }

    int getPin() {
        return this.pin;
    }

    int getCard_id(){
        return this.card_id;
    }

}

class Transaction{
    int date;
    String description;
    int amount;
    int transaction_id;
    
    Transaction(int transaction_id,String description,int amount,int date){
        this.date=date;
        this.transaction_id=transaction_id;
        this.description=description;
        this.amount=amount;
    }
}

class AccountCardManager{
    HashMap<Integer,Account>cardAndAccountMapping = new HashMap<>();
    static AccountCardManager instance;

    private AccountCardManager(){}

    public static AccountCardManager getInstance(){
        if(instance==null)
        {
            instance=new AccountCardManager();
        }

        return instance;
    }

    Account getAccountWithCardId(int id){
        return cardAndAccountMapping.get(id);
    }

    void insertAccountWithCard(int id,Account a) {
        cardAndAccountMapping.put(id,a);

    }
}

class Account{
    AccountType  type;
    ArrayList<Transaction>transactions;
    int amount;
    int id;
    Card card;
    
    Account(int id,AccountType type,int card_pin,int amount) {
        this.id=id;
        this.amount =0;
        this.type=type;
        this.transactions = new ArrayList<>();
        this.card = new Card(1234,card_pin);
        this.amount=amount;
        AccountCardManager.getInstance().insertAccountWithCard(this.card.getCard_id(),this);

    }
    
    int getAmount(){
        return this.amount;
    }

    ArrayList<Transaction> getTransactions(){
        return this.transactions;
    }

    int getId(){
        return this.id;
    }
    
    AccountType getType(){
        return this.type;
    }

    void addMoney(int amount) {
        this.amount+=amount;
    }

    void withdrawMoney(int amount){
        this.amount-=amount;
    }

    void addTransaction(int date,String desp,int amount){
        transactions.add(new Transaction(transactions.size(),desp,amount,date));
    }

    Card getCard(){
        return card;
    }
}

class User{
    HashMap<Integer,Account>accounts;
    int id;
    String name;
    String email;
    
    User(int id,String name,String email){
        this.id=id;
        this.name=name;
        this.email=email;
        this.accounts=new HashMap<>();
    }
    
    String getName(){
        return name;
    }
    
    String getEmail(){
        return email;
    }
    
    int getId(){
        return id;
    }
    
     Iterable<Account> getAccounts(){
        return accounts.values();
    }

    void addAccount(Account a) {
        accounts.put(a.getId(),a);
    }
    
    void deleteAccount(Account a){
        accounts.remove(a.getId());
    }
}
interface ATMState{
    void deposit(Card card,int amount);
    void withdraw(Card card,int amount);
    int getBalanceInquiry(Card card);
    void enterPin(Card card);
    ArrayList<Transaction> getTransactionDetails(Card card);
    void removeCard();
    void insertCard(Card card);
    void selectOperation(OperationType type);
}

class DepositState implements  ATMState{
    ATMMachine machine;
    DepositState(ATMMachine machine){
        this.machine =machine;
    }

    @Override
    public void deposit(Card card, int amount) {
        Account account=AccountCardManager.getInstance().getAccountWithCardId(card.getCard_id());
        account.addMoney(amount);
        account.addTransaction(2,"amount deposited",amount);
        System.out.println("Money deposition completed");
        machine.setState(new SelectOperationState(machine));
    }

    @Override
    public void withdraw(Card card,int amount) {
       System.out.println("You haven't completed the deposit process");
    }



    @Override
    public int getBalanceInquiry(Card card) {
        System.out.println("You haven't completed the deposit process");
        return 0;
    }

    @Override
    public void enterPin(Card card) {
        System.out.println("PIN has already been entered");
    }


    @Override
    public ArrayList<Transaction> getTransactionDetails(Card card) {
        System.out.println("You haven't completed the deposit process");
        return null;
    }

    @Override
    public void removeCard() {
        System.out.println("completed the deposit process");
        machine.setState(new SelectOperationState(machine));
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card has already been inserted");
        machine.setState(new SelectOperationState(machine));
    }

    @Override
    public void selectOperation(OperationType type) {
        System.out.println("Operation is still in progress");
    }
}

class WithdrawState implements ATMState{
    ATMMachine machine;
    WithdrawState(ATMMachine machine){
        this.machine=machine;
    }
    @Override
    public void deposit(Card card, int amount) {
        System.out.println("You haven't completed the withdrawal process");
    }

    @Override
    public void withdraw(Card card, int amount) {
        Account account=AccountCardManager.getInstance().getAccountWithCardId(card.getCard_id());
        if(account.getAmount()>=amount) {
            System.out.println("Withdraw completed");
            account.withdrawMoney(amount);
            account.addTransaction(3,"money withdrawn",amount);
        }
        else {
            System.out.println("Cannot be completed insufficient balance");
        }
        machine.setState(new SelectOperationState(machine));
    }



    @Override
    public int getBalanceInquiry(Card card) {
        System.out.println("You haven't completed the withdrawal process");
        return 0;
    }

    @Override
    public void enterPin(Card card) {
        System.out.println("PIN has already been entered");
    }


    @Override
    public ArrayList<Transaction> getTransactionDetails(Card card) {
        System.out.println("You haven't completed the withdrawal process");
        return null;
    }

    @Override
    public void removeCard() {
        System.out.println("completed the withdrawl process");
        machine.setState(new SelectOperationState(machine));
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card has already been inserted");
        machine.setState(new SelectOperationState(machine));
    }

    @Override
    public void selectOperation(OperationType type) {
        System.out.println("Operation is still in progress");
    }
}

class idleState implements ATMState{
    ATMMachine machine;
    idleState(ATMMachine machine){
        this.machine=machine;
    }

    @Override
    public void deposit(Card card, int amount) {
        System.out.println("You cannot use this functionality before entering the PIN");
    }

    @Override
    public void withdraw(Card card, int amount) {
        System.out.println("You cannot use this functionality before entering the PIN");
    }


    @Override
    public int getBalanceInquiry(Card card) {
        System.out.println("You cannot use this functionality before entering the card");
        return 0;
    }

    @Override
    public void enterPin(Card card) {
        System.out.println("You cannot use this functionality before entering the card");
    }

    @Override
    public ArrayList<Transaction> getTransactionDetails(Card card) {
        System.out.println("You cannot use this functionality before entering the card");
        return null;
    }

    @Override
    public void removeCard() {
        System.out.println("Insert the card first");
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card has  been inserted");
        machine.setState(new insertCardState(machine));
    }

    @Override
    public void selectOperation(OperationType type) {
        System.out.println("Operation is still in progress");
    }
}

class insertCardState implements ATMState{

    ATMMachine machine;
    insertCardState(ATMMachine machine){
       this.machine=machine;
    }

    @Override
    public void deposit(Card card, int amount) {
        System.out.println("You cannot use this functionality before entering the PIN");
    }

    @Override
    public void withdraw(Card card, int amount) {
        System.out.println("You cannot use this functionality before entering the PIN");
    }


    @Override
    public int getBalanceInquiry(Card card) {
        System.out.println("You cannot use this functionality before entering the PIN");
        return 0;
    }

    @Override
    public void enterPin(Card card) {
        System.out.println("Enter the pin");
        Scanner scanner=new Scanner(System.in);
        int pin=scanner.nextInt();
        int i=0;

        while(card.getPin()!=pin && i<5)
        {
            System.out.println("Incorrect pin enter again");
            scanner=new Scanner(System.in);
            pin=scanner.nextInt();
            i++;
        }

        if(i<5) {
            System.out.println("Entered in the system");
            machine.setState(new SelectOperationState(machine));
        }
        else {
            System.out.println("Sytem is locked");
        }



    }

    @Override
    public ArrayList<Transaction> getTransactionDetails(Card card) {
        System.out.println("You cannot use this functionality before entering the PIN");
        return null;
    }

    @Override
    public void removeCard() {
        System.out.println("Card is removed");
        machine.setState(new idleState(machine));
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card has already been inserted");
        machine.setState(new SelectOperationState(machine));
    }

    @Override
    public void selectOperation(OperationType type) {
        System.out.println("Operation is still in progress");
    }
}


class SelectOperationState implements  ATMState{

    ATMMachine machine;
    SelectOperationState(ATMMachine machine){
        this.machine=machine;
    }

    @Override
    public void selectOperation(OperationType type) {
       switch(type){
           case WITHDRAWL -> machine.setState(new WithdrawState(machine));
           case DEPOSIT -> machine.setState(new DepositState(machine));
       }
    }


    @Override
    public void deposit(Card card, int amount) {
       System.out.println("Select the operation first from he list");
    }

    @Override
    public void withdraw(Card card, int amount) {
        System.out.println("Select the operation first from he list");
    }


    @Override
    public int getBalanceInquiry(Card card) {
        System.out.println("Select the operation first from he list");
        return 0;
    }

    @Override
    public void enterPin(Card card) {
        System.out.println("PIN has already been entered");
    }

    @Override
    public ArrayList<Transaction> getTransactionDetails(Card card) {
        System.out.println("Select the operation first from he list");
        return null;
    }

    @Override
    public void removeCard() {
        System.out.println("Please collect your card");
        machine.setState(new insertCardState(machine));
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card has already been inserted");
        machine.setState(new SelectOperationState(machine));
    }
}

class ATMMachine{
    ATMState state;

    void initializeATMMachine(){
        this.state=new idleState(this);
    }

    void setState(ATMState state) {
        this.state=state;
    }

    void deposit(Card card,int amount){
       state.deposit(card,amount);
    }
    void withdraw(Card card,int amount){
      state.withdraw(card,amount);
    }
    int getBalanceInquiry(Card card){
       return state.getBalanceInquiry(card);
    }

    void enterPin(Card card){
       state.enterPin(card);
    }

    ArrayList<Transaction> getTransactionDetails(Card card){
       return state.getTransactionDetails(card);
    }

    void removeCard(){
      state.removeCard();
    }

    void insertCard(Card card){
        state.insertCard(card);
    }

    void selectOperations(OperationType type) {
        state.selectOperation(type);
    }


}
public class ATM {
    public static void main(String[] args){
        ATMMachine atm=new ATMMachine();
        atm.initializeATMMachine();

        User user1 = new User(0,"Manvi","manvi@gmail.com");
        Account a1= new Account(111,AccountType.CURRENT,1234,400);
        Card card1 = a1.getCard();

        user1.addAccount(a1);
        
        atm.insertCard(card1);
        atm.enterPin(card1);
        atm.selectOperations(OperationType.WITHDRAWL);
        atm.withdraw(card1,200);
        atm.selectOperations(OperationType.DEPOSIT);
        atm.deposit(card1,200);
        atm.removeCard();
    }
}
