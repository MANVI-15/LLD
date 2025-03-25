package Proxy;

public class EmployeeDBProxy implements Employee{
    EmployeeDB obj;
    @Override
    public void create(String client, Employee obj) throws Exception{
        if(client.equals("ADMIN"))
        {
            obj.create(client,obj);
            return;
        }
        throw new Exception("Access denied");
    }

    @Override
    public void delete(String client, int employeeId) throws Exception{
        if(client.equals("ADMIN"))
        {
            obj.delete(client,employeeId);
            return;
        }
        throw new Exception("Access denied");
    }

    @Override
    public Employee get(String client, int employeeId) throws Exception{
        if(client.equals("ADMIN")|| client.equals("USER"))
        {
            return obj.get(client,employeeId);

        }
        throw new Exception("Access denied");
    }
}
