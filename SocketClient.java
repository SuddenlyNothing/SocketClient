import java.net.*;
import java.io.*;

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