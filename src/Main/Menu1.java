package Main;
// Liasta1, Main itp dotyczy listy kategorii Allegro
public class Menu1 {
    public static void main(String[] args) {

    }
    private static void lista1() {
        Menu lista = new Menu(1);


        switch (lista.getNumber()) {
            case 1:
                lista.setType(AllegroList.DZIEŁA_I_SZTUKA);
                break;
            case 2:
                lista.setType(AllegroList.KOMPUTERY);
                break;
            case 3:
                lista.setType(AllegroList.MOTORYZACJA);
        }

    }
}