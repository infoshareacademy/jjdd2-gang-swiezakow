package Main;

import java.util.Scanner;

public class Menu {

    Integer selectedValue;
    Menu[] typesArray = Menu.values();

    public void runHomework(Menu1 mainMenu) {
        Scanner scanner = new Scanner(System.in);


        boolean isIncorrect = true;
        while (isIncorrect) {
            printInfo();
            selectedValue = scanner.nextInt();

            if (selectedValue == 0) {
                break;
            }

            isIncorrect = checkIfIncorrect(selectedValue);

            if (isIncorrect) {
                System.out.println("niepoprawna wartość, wybierz ponownie..");
                continue;
            }

            setProperEnum(selectedValue, mainMenu);
        }
    }

    private void printInfo() {
        System.out.println("Dostępne opcje:");
        for (int i = 0; i < typesArray.length; i++) {
            System.out.println((i + 1) + " " + typesArray[i]);
        }
        System.out.println("wybierz opcję: ");
    }

    private void setProperEnum(Integer selectedValue, Menu1 mainMenu) {
        mainMenu.setType(typesArray[selectedValue-1]);
    }

    private boolean checkIfIncorrect(Integer selectedValue) {
        return (selectedValue < 0
                || selectedValue > typesArray.length);
    }
}
