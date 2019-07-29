import javax.swing.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class serverThread extends Thread {

    public static void main(String args[]) {

        serverThread server = new serverThread();
        server.start();
    }

    public void run() {
        System.out.println("Server is running...");
        JOptionPane.showMessageDialog(null, "Server is running...");
        while (true) {

            int number = 1, temp;
            ServerSocket ss = null;
            Socket s1 = null;
            Scanner sc = null;

            try {
                ss = new ServerSocket(1342);
                s1 = ss.accept();
                sc = new Scanner(s1.getInputStream());
                System.out.println("Client request from " + s1.getLocalSocketAddress() + ".");
                try {
                    number = sc.nextInt();
                } catch (NoSuchElementException e) {
                    System.out.println("Client disconnected from " + s1.getLocalSocketAddress() + ".");
                }
                temp = number * 2;

                if (temp == 0) {
                    this.interrupt();
                    System.out.println("Server stopped.");
                }

                PrintStream p = new PrintStream(s1.getOutputStream());
                p.println(temp);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    s1.close();
                    ss.close();
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
