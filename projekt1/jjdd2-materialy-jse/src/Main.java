import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner pobierz = new Scanner(System.in);

        System.out.println("podaj liczbe 1:");
        double a = pobierz.nextDouble();
        System.out.println("podaj liczbe 2:");
        double b = pobierz.nextDouble();


        System.out.println("wynik="+(a+b));

        pobierz.close();



    }


}
