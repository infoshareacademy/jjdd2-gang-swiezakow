import java.util.Scanner;

public class Main {

    public static final String KOMENDA_WYJSCIA = "exit";

    public static void main(String[] args) {

        System.out.println("\n~~wspanialy pomagacz w zakupach internetowych~~");
        System.out.println("-----------------------------------------------\n");
        System.out.println("1. Polecenie nr 1");
        System.out.println("2. Polecenie nr 2");
        System.out.println("3. Polecenie nr 3");
        System.out.println("4. Polecenie nr 4");
        System.out.println();
        System.out.println("Aby wyjść wybierz: exit");
        System.out.println();
        System.out.println("Podaj numer polecenia: ");

        Scanner odczyt = new Scanner(System.in);
        String linia;
        while ( !(linia = odczyt.nextLine()).equals(KOMENDA_WYJSCIA) ) {

            switch (linia) {
                case ("1"):
                    // wejscie do polecenia 1
                    break;
                case ("2"):
                    // wejście do polcenia 2
                    break;
                case ("3"):
                    // wejście do polecenia 3
                    break;
                case("4"):
                    // wejście do polecenia 4
                    break;
                default:
                    System.out.println("Niepoprawny numer. Podaj liczbę.");
            }
        }
    }
}