import java.net.*;
import java.io.*;

/*
* java .\SocketClient.java djxmmx.net 17
* java .\SocketClient.java time.nist.gov 13
* java .\SocketClient.java tcpbin.com 4242
*/

public class SocketClient {
  public static void main(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException("Must have at least 2 arguments");
    }

    try (Socket socket = new Socket(args[0], Integer.valueOf(args[1]))) {
      socket.setSoTimeout(15000);

      if (args.length > 2) {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader echo = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        for (int i = 2; i < args.length; i++) {
          out.println(args[i]);
          System.out.println(echo.readLine());
        }
      } else {
        InputStream in = socket.getInputStream();

        StringBuilder output = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(in, "ASCII");

        for (int c = reader.read(); c != -1; c = reader.read()) {
          output.append((char) c);
        }
        System.out.println(output);
      }

    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}

// import java.io.*;
// import java.net.*;

// public class SocketClient {
// public static void main(String[] args) throws IOException {

// if (args.length != 2) {
// System.err.println(
// "Usage: java EchoClient <host name> <port number>");
// System.exit(1);
// }

// String hostName = args[0];
// int portNumber = Integer.parseInt(args[1]);

// try (
// Socket echoSocket = new Socket(hostName, portNumber);
// PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
// BufferedReader in = new BufferedReader(
// new InputStreamReader(echoSocket.getInputStream()));
// BufferedReader stdIn = new BufferedReader(
// new InputStreamReader(System.in))) {
// String userInput;
// while ((userInput = stdIn.readLine()) != null) {
// out.println(userInput);
// System.out.println("echo: " + in.readLine());
// }
// } catch (UnknownHostException e) {
// System.err.println("Don't know about host " + hostName);
// System.exit(1);
// } catch (IOException e) {
// System.err.println("Couldn't get I/O for the connection to " +
// hostName);
// System.exit(1);
// }
// }
// }