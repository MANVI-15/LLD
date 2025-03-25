package Singelton;

public class Singelton {
    static Singelton instance;
    public static Singelton getInstance()
    {   // Single Checked
        if (instance == null) {

            synchronized (Singelton.class)
            {
                // Double checked
                if (instance == null) {
                    instance = new Singelton();
                }
            }
        }
        return instance;
    }
}
