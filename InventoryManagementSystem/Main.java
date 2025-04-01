package Questions.InventoryManagementSystem;

import java.util.*;

enum OrderStatus {CREATED,DELIVERED,PAYMENT_COMPLETED}
enum CategoryType {FOOD,COSMETIC}

class Product{
    int id;
    String name;
    int exp_date;
    int cost;

    Product(int id,String name,int cost,int exp_date) {
        this.name=name;
        this.id=id;
        this.cost=cost;
        this.exp_date=exp_date;
    }

    String getName(){
        return this.name;
    }

    int getCost() {
        return this.cost;
    }

}

class Category{
    HashMap<Product,Integer>productList;
    CategoryType type;

    Category(CategoryType type) {
        this.type=type;
        this.productList=new HashMap<>();
    }

    void addProduct(Product p,int quantity) {
        productList.put(p,quantity);
    }

    HashMap<Product, Integer> getAllProducts(){
        return productList;
    }

    CategoryType getCategoryType() {
        return this.type;
    }

    void removeProduct(Product p,int quantity) {
        int curr_quantity=productList.get(p);
        productList.put(p,curr_quantity-quantity);
    }
}

class Warehouse{
    ArrayList<Category>categoriesList;
    String city;

    Warehouse(String city){
        this.city=city;
        this.categoriesList=new ArrayList<>();
    }

    void addCategories(Category c){
        categoriesList.add(c);
    }

    Category getProductCategory(CategoryType type)
    {
        for(int i=0;i<categoriesList.size();i++)
        {
            if(categoriesList.get(i).getCategoryType()==type){
                return categoriesList.get(i);
            }
        }
        return null;
    }

    ArrayList<Category> getCategories(){
        return categoriesList;
    }
}

class WareHouseMgr{
    HashMap<String,Warehouse>warehouseList = new HashMap<>();
    static WareHouseMgr  instance;

    WareHouseMgr(){}

    public static WareHouseMgr getInstance() {
        if(instance==null)
        {
            instance=new WareHouseMgr();
        }
        return instance;
    }
    void addWarehouse(String city) {
        warehouseList.put(city,new Warehouse(city));
    }

    Warehouse getWarehouse(String city) {
        return warehouseList.get(city);

    }
}

class Order{
    int id;
    int totalCost;
    HashMap<Product,Integer>productList;
    OrderStatus orderStatus;

    Order(int id,int totalCost,HashMap<Product,Integer> productList) {
        this.id=id;
        this.productList=productList;
        this.orderStatus = OrderStatus.CREATED;
        this.totalCost=totalCost;
    }

    void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus=orderStatus;
    }

    int getId(){
        return id;
    }


    int getAmount(){
        return totalCost;
    }

    // write getters and setters
}
class OrderMgr{
   int idx=0;
   HashMap<Integer,Order>orderList = new HashMap<>();
   static OrderMgr instance;

   OrderMgr(){}

    public static OrderMgr getInstance() {
        if(instance==null)
        {
            instance=new OrderMgr();
        }
        return instance;
    }

   Order addOrder(int amount,HashMap<Product,Integer>productsList) {
       Order order=new Order(idx++,amount,productsList);
       order.setOrderStatus(OrderStatus.CREATED);
       orderList.put(idx,order);
       return order;
   }

   Order getOrder(int id) {
       return orderList.get(id);
   }
}

class Cart{
    HashMap<Product,Integer>productList=new HashMap<>();

    void addProducts(Product p,int quantity){
        if(productList.get(p)==null)
        {
            productList.put(p,quantity);
            return;
        }

        productList.put(p,productList.get(p)+1);
    }

    int getTotalAmount() {
        int amount=0;
        for(Map.Entry<Product,Integer> entry:productList.entrySet())
        {
            amount+=entry.getKey().getCost()*entry.getValue();
        }
        return amount;
    }

    void updateQuantity(Product p,int quantity) {
        productList.put(p,quantity);
    }

    HashMap<Product,Integer> getProductListWithQuantity() {
        return productList;
    }

}

class User{
    String name;
    String email;
    Cart cart;
    ArrayList<Integer>ordersId;

    User(String name,String email) {
        this.email=email;
        this.name=name;
        this.cart=new Cart();
        this.ordersId=new ArrayList<>();
    }

    void addProductsToCart(Product p,int quantity) {
        cart.addProducts(p,quantity);
    }

    void addOrders(int id) {
        ordersId.add(id);
    }

    String getName(){
        return this.name;
    }

    Cart getCart() {
        return cart;
    }

    void updateQuantity(Product p,int quantity) {
        cart.updateQuantity(p,quantity);
    }
}

interface PaymentStrategy{
    boolean makePayment(int amount);
}

class CreditCardPaymentStrategy implements PaymentStrategy{
    @Override
    public boolean makePayment(int amount) {
        System.out.println("Credit card payment is completed");
        // Implement logic for credit card payment
        return true;
    }
}

class UPIPaymentStrategy implements PaymentStrategy{
    @Override
    public boolean makePayment(int amount) {
        System.out.println("UPI payment is completed");
        // Implement logic for upi payment
        return false;
    }
}

class PaymentMgr
{
    CreditCardPaymentStrategy creditCardPaymentStrategy = new CreditCardPaymentStrategy();
    UPIPaymentStrategy upiPaymentStrategy = new UPIPaymentStrategy();
    static PaymentMgr instance;

    private PaymentMgr(){}

    public  static PaymentMgr getPaymentMgr(){
        if(instance==null){
            instance= new PaymentMgr();
        }
        return instance;
    }

    void makeCreditCardPayment(int amount) {
        creditCardPaymentStrategy.makePayment(amount);
    }

    void makeUPIPayment(int amount){
        upiPaymentStrategy.makePayment(amount);
    }
}

class InventoryManagementSystemFacade {
    PaymentMgr paymentMgr;
    OrderMgr orderMgr;
    WareHouseMgr wareHouseMgr;

    public void initializeInventoryManagementSystem(){
        paymentMgr = PaymentMgr.getPaymentMgr();
        orderMgr=OrderMgr.getInstance();
        wareHouseMgr=WareHouseMgr.getInstance();
    }

    void searchProduct(String city,CategoryType type){
        Warehouse warehouse=wareHouseMgr.getWarehouse(city);
        Category category = warehouse.getProductCategory(type);
        HashMap<Product,Integer>productList=category.getAllProducts();

       for(Map.Entry<Product,Integer> entry:productList.entrySet())
       {
          System.out.println(entry.getKey().getName());
       }
    }

    void addProductToCart(User user,Product p,int quantity){
        System.out.println("Products added to cart");
        user.addProductsToCart(p,quantity);

    }

    void updateQunatityInCart(User user,Product p,int quantity) {
        System.out.println("Quantity updated in cart");
        user.updateQuantity(p,quantity);
    }

    Order placeOrder(User user,String city) {
        Cart cart = user.getCart();
        HashMap<Product, Integer> productList = cart.getProductListWithQuantity();

        for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
            Warehouse warehouses = wareHouseMgr.getWarehouse(city);
            Product product = entry.getKey();
            ArrayList<Category> categories = warehouses.getCategories();

            for (int j = 0; j < categories.size(); j++) {
                HashMap<Product, Integer> products = categories.get(j).getAllProducts();
                if (products.containsKey(product)) {


                    if (entry.getValue() > products.get(product)) {
                        System.out.println("Order cannot be completed insufficient inventory");
                        return null;
                    }

                }
            }
        }

        for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
            Warehouse warehouses = wareHouseMgr.getWarehouse(city);
            Product product = entry.getKey();
            ArrayList<Category> categories = warehouses.getCategories();
            for (int j = 0; j < categories.size(); j++) {
                HashMap<Product, Integer> products = categories.get(j).getAllProducts();
                if (products.containsKey(product)) {
                    categories.get(j).removeProduct(product,entry.getValue());

                }
            }

        }

        Order order=OrderMgr.getInstance().addOrder(cart.getTotalAmount(),cart.getProductListWithQuantity());
        user.addOrders(order.getId());
        System.out.println("Order is placed");
        return order;
    }


    void makePayment(Order order)
    {
        System.out.println("Select mode of payment");
        System.out.println("Press 1"+" UPI Payment");
        System.out.println("Press 2"+" Credit Card Payment");


        Scanner scanner = new Scanner(System.in);
        int val=scanner.nextInt();

        switch(val){
            case 1:{
                paymentMgr.makeUPIPayment(order.getAmount());
                break;
            }
            case 2:{
                paymentMgr.makeCreditCardPayment(order.getAmount());
                break;
            }

        }
        order.setOrderStatus(OrderStatus.PAYMENT_COMPLETED);
        System.out.println("payment has been completed " + order.getAmount());

    }


}


public class Main {

    public static void main(String[] args){
        Product p1=new Product(0,"Apple",10,2);
        Product p2=new Product(0,"Orange",20,2);
        Product p3=new Product(0,"Cream",10,2);
        Product p4=new Product(0,"Concealer",10,2);

        WareHouseMgr.getInstance().addWarehouse("Delhi");
        WareHouseMgr.getInstance().addWarehouse("Gwalior");

        Category c1= new Category(CategoryType.FOOD);
        c1.addProduct(p1,2);
        c1.addProduct(p2,3);

        Category c2= new Category(CategoryType.COSMETIC);
        c2.addProduct(p3,4);
        c2.addProduct(p4,5  );


        Warehouse w1 = WareHouseMgr.getInstance().getWarehouse("Delhi");
        w1.addCategories(c1);
        w1.addCategories(c2);

        Warehouse w2 = WareHouseMgr.getInstance().getWarehouse("Gwalior");
        w2.addCategories(c1);
        w2.addCategories(c2);

        InventoryManagementSystemFacade inventoryManagementSystem = new InventoryManagementSystemFacade();
        inventoryManagementSystem.initializeInventoryManagementSystem();

        User user=new User("Manvi","maniv@gmail.com");

        inventoryManagementSystem.searchProduct("Delhi",CategoryType.FOOD);
        inventoryManagementSystem.addProductToCart(user,p1,2);
        inventoryManagementSystem.addProductToCart(user,p2,5);


        Order order1 =inventoryManagementSystem.placeOrder(user,"Delhi");
        if(order1!=null) {
            inventoryManagementSystem.makePayment(order1);
        }

        inventoryManagementSystem.updateQunatityInCart(user,p1,2);
        inventoryManagementSystem.updateQunatityInCart(user,p2,2);

        Order order2 =inventoryManagementSystem.placeOrder(user,"Delhi");
        if(order2!=null) {
            inventoryManagementSystem.makePayment(order2);
        }


    }
}

