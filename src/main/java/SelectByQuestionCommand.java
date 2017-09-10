import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.Scanner;

/*Zamysł na pytania:

Witaj! Czy szukasz sprzetu elektronicznego> T/N
        -->Czy chodzi i AGD/RTV (T/N)? jeśli tak to wyswietla link do dzialu RTV/AGD.
        -->To może jednak telefony komórkowe? (T/N) jeśli tak to link do dzialu z telefonami
        -->A może TV? (T/N)?    link do dzialu z telewizorami
        --> Czyżby aparat fotograficzny? link do fotografii
        --> Może rozrywka na kosoli? link do działu konsol
        --> Nic nie pasowało, spróbuj innej kategorii lub skorzystaj z wyszukiwarki :)
    Może interesuje Ciebie Moda? (T/N)
        --> Dział dla pań?
            -->Czy chodzi o bieliznę? (link do działu bielizna damska)
            -->Czy chodzi o odzież? (link do odzieży damskiej)
            -->Czy chodzi o obuwie? (link do obuwia)
            --> Nic nie pasowało, spróbuj innej kategorii lub skorzystaj z wyszukiwarki :)
        --> Dla niego?
            -->Czy chodzi o bieliznę? (link do bielizny)
            -->Czy chodzi o obuwie (link do obuwia)
            -->Czy chodzi o odzież (link do odzieży)
            --> Nic nie pasowało, spróbuj innej kategorii lub skorzystaj z wyszukiwarki :)
    Potrzebujesz wyposażenia do mieszkania lub ogrodu?
        --> Czy chodzi o oświetlenie? (Link do oświetlenia)
        --> Czy chodzi o materiały budowlane? (link do budownictwa)
        --> Czy chodzi o meble? (link do mebli)
        --> Potrzebujesz narzędzi? (link do narzędzi)
        --> Szukasz elementów łazienki? (link do łazienka- armatura i cała reszta)
        --> Nic nie pasowało, spróbuj innej kategorii lub skorzystaj z wyszukiwarki :)
    Dział motoryzacji to to co potrzebujesz?
        --> Czy chodzi o samochody?
            --> czy szukasz aut osobowych? (link do osobowych)
            --> czy szukasz aut dostaczych do 3,5t? (link do 3,5t)
            --> auta dostawcze pow. 3,5t? (link)
            --> auta zabytkowe (link)
            --> autobusy?
            --> Nic nie pasowało, spróbuj innej kategorii lub skorzystaj z wyszukiwarki :)
        --> Może części samochodowe? (link do części)
        --> Chemia motoryzacyjna? (link do chemii)
        --> Może potrzebujesz znaleźć motor lub quad? (link do motocykle i quady)
        --> Rozglądasz się za maszynami? (link do maszyn)
        --> Może potrzebujesz przyczepę? (link do przyczep i naczep)
        --> Nic nie pasowało, spróbuj innej kategorii lub skorzystaj z wyszukiwarki :)
    Nic nie pasowało, spróbuj innej kategorii lub skorzystaj z wyszukiwarki :)


    Dzialanie:

    1. Zadaj pytanie, czy chodzi o dana kategorie.
        Gdy brakuje kategorii, wyswietl "Nic nie pasowało, spróbuj innej kategorii lub skorzystaj z wyszukiwarki :)"
    2. Jesli uzytkownik odpowie NIE, przejdz do pkt 1. (pytajac o następną kategorie)
       Jesli Uzytkownik odpowie TAK przejdz do pkt 3.
    3. Jesli kategoria ma podkategorie, przejdz do pkt 1. (pytajac o kolejne podkategorie)
       Jesli kategoria nie ma podkategorii zwroc Link


*/
public class SelectByQuestionCommand {
    public void run () {
        System.out.println("Wyszukiwanie kategorii na podstawie pytań.\n----------------------------------------------\n");
        askQuestion();

    }

    private void askQuestion() {
        String question = "Czy szukasz informacji o produktach z kategorii Elektronika?" ;
        String options = "\t[T]ak\n\t[N]ie";

        System.out.println(question);
        System.out.println(options);

        boolean answer = readAnwser();

        if (answer) {
            System.out.println("Proponowanana kategoria to Elektronika");
        } else {
            System.out.println("Niestety nei możemy Ci pomóc. Jesteśmy koniem.");
        }
    }

    private boolean readAnwser() {
        String prompt = "Moja odpowiedź [T/N]: ";
        System.out.print(prompt);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("T")) {
            return true;
        } else if (input.equals("N")) {
            return false;
        } else {
            System.out.println("Bledna odpowiedz, sprobuj ponownie.");
            return readAnwser();
        }
    }
}
