import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import  java.util.Scanner;
import java.net.Socket;

public class clientThread extends Thread {

    public static void main(String[] args) {

        clientThread client = new clientThread();
        client.start();
    }

    public void run() {
        System.out.println("Client is running.");
        Scanner sc = null;
        Socket s = null;
        Scanner sc1 = null;
        PrintStream p = null;
        try {
            do {
                try {
                    int number = 1, temp;

                    sc = new Scanner(System.in);
                    s = new Socket("127.0.0.1", 1342);
                    sc1 = new Scanner(s.getInputStream());
                    System.out.println("Enter any number or Exit.");
                    try {
                        number = sc.nextInt();
                    } catch (InputMismatchException e) {
                        break;
                    }
                    p = new PrintStream(s.getOutputStream());
                    p.println(number);
                    temp = sc1.nextInt();
                    System.out.println(temp);

                    if (temp == 0) {
                        this.interrupt();
                        System.out.println("Client stopped.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (!s.getInputStream().equals("Exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            s.close();
            sc.close();
            sc1.close();
            p.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
