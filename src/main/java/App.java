public class App {
    //Testowo by uruchomic polecenie
    public static void main(String[] args) {

        System.out.println("Testowe drzewko kategorii:");
        Category root = CategoryUtils.buildCategoriesAndReturnRoot();
        for (Category sub : root.getSubcategories()) {
            System.out.println(" - " + sub.getName());
        }


        System.out.println("Testowy quiz kategorii:");
        SelectByQuestionCommand cmd = new SelectByQuestionCommand();
        cmd.run();
    }
}
