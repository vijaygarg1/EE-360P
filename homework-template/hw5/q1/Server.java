import java.util.Scanner;

public class Server {
  public static void main (String[] args) {

    Scanner sc = new Scanner(System.in);
    int myID = sc.nextInt();
    int numServer = sc.nextInt();
    String inventoryPath = sc.next();

    for (int i = 0; i < numServer; i++) {
      // TODO: parse inputs to get the ips and ports of servers
    }
   
    // TODO: start server socket to communicate with clients and other servers
    
    // TODO: parse the inventory file

    // TODO: handle request from client
  }
}
