package name;
import java.net.*;
public class NameRmiClient {
    public static void main(String args[]) {
        try {
            NameService r = (NameService) 
                           java.rmi.Naming.lookup("rmi://mario.ece.utexas.edu/MyNameServer");
            if (r == null) 
               System.out.println("Could not find the service.");
            else {
               r.insert("p1", "tick.ece", 2058);
               InetSocketAddress entry = r.search("p1");
               if (entry != null)
                  System.out.println(entry.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
