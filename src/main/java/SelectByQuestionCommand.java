import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.List;
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

        Category cat = CategoryUtils.buildCategoriesAndReturnRoot();


        List<Category> sameLevelCategories = cat.getSubcategories();
        int size = sameLevelCategories.size();
        boolean isFound = false;

        for (int i=0; i < size; i++) {
            Category category = sameLevelCategories.get(i);
            boolean isChosen = askQuestion(category.getName());
            if (!isChosen) {
                continue;

            }

            boolean hasSubcategories = !category.getSubcategories().isEmpty();
            if (hasSubcategories) {
                //zamieniamy sameLevelCategories na category.getSubcategories()
                sameLevelCategories = category.getSubcategories();
                //reset i
                i = -1; //i++ zrobi 0
                //size sie mogl zmienic, wiec tez reset
                size = sameLevelCategories.size();
                continue;

            } else {
                System.out.println("Link to: " + category.getLinkUrl());
                isFound = true;
                break; //koniec petli
            }

        }

        if (!isFound) {
            // skoro nie wyszlismy z metody znaczy ze nie znalezlismy odpowiedzi
            System.out.println("Nic nie pasowało, spróbuj innej kategorii lub skorzystaj z wyszukiwarki :)");
        }


/*

       // for (Typ zmienna_dla_elementu : kolekcja){
        //  przechodzi po kolei po elementach kolekcji
       // }
        for (Category sub : sameLevelCategories) {
            boolean isChosen = askQuestion(sub.getName());
            if (isChosen) {
                //todo obsluga podkategorii..
                System.out.println("Link to: " + sub.getLinkUrl());
                return; //znlezlsmy to konczymy metode
            }
            // domyslnie przechodzimy do kolejnego pytania
        }
        // skoro nie wyszlismy z metody znaczy ze nie znalezlismy odpowiedzi
        System.out.println("Nic nie pasowało, spróbuj innej kategorii lub skorzystaj z wyszukiwarki :)");
*/

    }

    //zwraca true, jesli podana kategoria zostala wskazana
    private boolean askQuestion(String categoryName) {
        String question = String.format("Czy szukasz informacji o produktach z kategorii %s?", categoryName);
        String options = "\t[T]ak\n\t[N]ie";

        System.out.println(question);
        System.out.println(options);

        boolean answer = readAnwser();

        return answer;
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
