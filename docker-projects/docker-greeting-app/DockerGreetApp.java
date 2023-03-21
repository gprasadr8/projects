import java.util.Scanner;
public class DockerGreetApp {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();
        System.out.println("Hello "+name+" !");
    }
}