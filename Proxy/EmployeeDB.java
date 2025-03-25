package Proxy;

public class EmployeeDB implements Employee{
    @Override
    public void create(String client, Employee obj) throws Exception {
        System.out.println("Client created");
    }

    @Override
    public void delete(String client, int employeeId) throws Exception{
        System.out.println("Client deleted");
    }

    @Override
    public Employee get(String client, int employeeId) throws Exception {
        System.out.println("fetching data");
        return this;
    }
}
