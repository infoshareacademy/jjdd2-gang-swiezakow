/**
 * Created by tomas on 06.09.2017.
 */
public class Menu {
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    private int number = 1;
    private String text = "start menu";
    private MenuType type;

    public Menu() {
    }

    public Menu(int number) {
        this.number = number;
    }

    public String concatNumberText(String separator) {
        return number + separator + text;
    }




}
