import java.util.Scanner;

public class Client {
  public static void main (String[] args) {
    String hostAddress;
    int tcpPort;
    int udpPort;

    if (args.length != 3) {
      System.out.println("ERROR: Provide 3 arguments");
      System.out.println("\t(1) <hostAddress>: the address of the server");
      System.out.println("\t(2) <tcpPort>: the port number for TCP connection");
      System.out.println("\t(3) <udpPort>: the port number for UDP connection");
      System.exit(-1);
    }

    hostAddress = args[0];
    tcpPort = Integer.parseInt(args[1]);
    udpPort = Integer.parseInt(args[2]);

    Scanner sc = new Scanner(System.in);
    while(sc.hasNextLine()) {
      String cmd = sc.nextLine();
      String[] tokens = cmd.split(" ");

      if (tokens[0].equals("setmode")) {
        // TODO: set the mode of communication for sending commands to the server 
        // and display the name of the protocol that will be used in future
      }
      else if (tokens[0].equals("purchase")) {
        // TODO: send appropriate command to the server and display the
        // appropriate responses form the server
      } else if (tokens[0].equals("cancel")) {
        // TODO: send appropriate command to the server and display the
        // appropriate responses form the server
      } else if (tokens[0].equals("search")) {
        // TODO: send appropriate command to the server and display the
        // appropriate responses form the server
      } else if (tokens[0].equals("list")) {
        // TODO: send appropriate command to the server and display the
        // appropriate responses form the server
      } else {
        System.out.println("ERROR: No such command");
      }
    }
  }
}
