package pl.infoshareacademy;

import java.util.Scanner;

public class UserInput {

    private final Scanner scanner;

    public UserInput() {
        scanner = new Scanner(System.in);
    }

    public UserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public String line(){
        return scanner.nextLine();
    }
}
