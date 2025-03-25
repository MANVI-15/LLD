package Proxy;

public class Main {
    public static void main(String[] args) throws Exception {
        Employee emp= new EmployeeDBProxy();

        emp.create("ADMIN",new EmployeeDB());
        emp.create("USER",new EmployeeDB());

    }
}
