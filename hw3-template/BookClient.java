import java.util.Scanner;
import java.io.*;
import java.util.*;
public class BookClient {
  public static void main (String[] args) {
    String hostAddress;
    int tcpPort;
    int udpPort;
    int clientId;

    if (args.length != 2) {
      System.out.println("ERROR: Provide 2 arguments: commandFile, clientId");
      System.out.println("\t(1) <command-file>: file with commands to the server");
      System.out.println("\t(2) client id: an integer between 1..9");
      System.exit(-1);
    }

    String commandFile = args[0];
    clientId = Integer.parseInt(args[1]);
    hostAddress = "localhost";
    tcpPort = 7000;// hardcoded -- must match the server's tcp port
    udpPort = 8000;// hardcoded -- must match the server's udp port

    try {
        Scanner sc = new Scanner(new FileReader(commandFile));

        while(sc.hasNextLine()) {
          String cmd = sc.nextLine();
          String[] tokens = cmd.split(" ");

          if (tokens[0].equals("setmode")) {
            // TODO: set the mode of communication for sending commands to the server 
          }
          else if (tokens[0].equals("borrow")) {
            // TODO: send appropriate command to the server and display the
            // appropriate responses form the server
          } else if (tokens[0].equals("return")) {
            // TODO: send appropriate command to the server and display the
            // appropriate responses form the server
          } else if (tokens[0].equals("inventory")) {
            // TODO: send appropriate command to the server and display the
            // appropriate responses form the server
          } else if (tokens[0].equals("list")) {
            // TODO: send appropriate command to the server and display the
            // appropriate responses form the server
          } else if (tokens[0].equals("exit")) {
            // TODO: send appropriate command to the server 
          } else {
            System.out.println("ERROR: No such command");
          }
        }
    } catch (FileNotFoundException e) {
	e.printStackTrace();
    }
  }
}
