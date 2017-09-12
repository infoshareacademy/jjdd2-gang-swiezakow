package pl.infoshareacademy.jjdd2_gang_swiezakow;

public class CollectionPrinter {

    public static void print(Iterable iterable) {

        Integer liczydlo = 1;
        for (Object o : iterable) {
            System.out.println(liczydlo+ ". " +o.toString());
            liczydlo++;
        }
    }

}
