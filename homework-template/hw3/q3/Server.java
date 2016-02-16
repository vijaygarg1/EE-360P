public class Server {
  public static void main (String[] args) {
    int N;
    int tcpPort;
    int udpPort;
    if (args.length != 4) {
      System.out.println("ERROR: Provide 4 arguments");
      System.out.println("\t(1) <N>: the total number of available seats");
      System.out.println("\t\t\tassume the seat numbers are from 1 to N");
      System.out.println("\t(2) <tcpPort>: the port number for TCP connection");
      System.out.println("\t(3) <udpPort>: the port number for UDP connection");
      System.out.println("\t(4) <file>: the file of inventory");

      System.exit(-1);
    }
    N = Integer.parseInt(args[0]);
    tcpPort = Integer.parseInt(args[1]);
    udpPort = Integer.parseInt(args[2]);
    String fileName = args[3];

    // parse the inventory file

    // TODO: handle request from clients
  }
}
