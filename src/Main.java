import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n" + "~~wspanialy pomagacz w zakupach internetowych~~");
        System.out.println("-----------------------------------------------\n\n");
        System.out.println("1. Wyszukiwanie produktu\n2. Katalog kategorii\n3. Pomoc\n\n\nPodaj numer kategorii:");
        Scanner odczyt = new Scanner(System.in);

        while (odczyt.hasNextInt()) {
            int liczba = odczyt.nextInt();
            switch (liczba) {
                case (1):
                    // wejscie do kategorii
                    break;
                case (2):
                    // wejście do kategorii
                    break;
                case (3):
                    // wejście do kategorii
                default:
                    System.out.print("Niepoprawny numer. Podaj liczbę.");
            }


        }
    }
}