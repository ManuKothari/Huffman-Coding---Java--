import java.io.IOException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Manu
 * Date: 11/10/13
 * Time: 10:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String args[]) throws IOException {
        Pqueue pq = new Pqueue();
        int ch;
        do{
            System.out.println("1.ENCODE\n2.Display Table\n3.Exit");
            System.out.print("Enter your choice: ");
            Scanner in = new Scanner(System.in);
            ch=in.nextInt();
            switch(ch){
                case 1: pq.encode(); break;
                case 2: pq.display(); break;
                case 3: System.exit(0);
            }
        }while(ch!=4);

    }
}
