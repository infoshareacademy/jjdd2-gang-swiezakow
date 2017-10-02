package pl.infoshareacademy.webapp;

import pl.infoshareacademy.AllegroLink;
import pl.infoshareacademy.SearchByQuestions;
import pl.infoshareacademy.SearchResult;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/form")
public class SearchByQuestionsServlet extends HttpServlet {

    @Inject
    private SearchByQuestions searchByQuestions;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //0. Ustawienia kodowania
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        //1. obsluga parametrow
        String theAnswer = req.getParameter("theAnswer");
        String categoryStringId = req.getParameter("categoryId");

        if (categoryStringId == null || theAnswer == null) {
            //domyslne watosci
            categoryStringId = "0";
            theAnswer = "Tak";
        }

        boolean isChosen = "Tak".equals(theAnswer);
        int categoryId = Integer.parseInt(categoryStringId);

        //2. SearchResult
        SearchResult searchResult = null;
        boolean isResultPresent = true;
        if (isChosen) {
            searchResult = searchByQuestions.chooseCategory(categoryId);
        } else {
            Optional<SearchResult> optionalOmitResult = searchByQuestions.omitCategory(categoryId);
            if (!optionalOmitResult.isPresent()) {
                isResultPresent = false;
            } else {
                searchResult = optionalOmitResult.get();
            }
        }

        //3. Przygotowanie wyjcia
        String output;
        if (!isResultPresent) {
            output = "Nie udalo znalezc sie poszukiwanego rezultatu.";
        } else {
            String foundCategoryName = searchResult.getCategoryName();
            int foundCategoryId = searchResult.getCategoryId();
            boolean isLink = searchResult.isLink();

            if (isLink) {
                String link = AllegroLink.makeLink(foundCategoryName, foundCategoryId);
                output = "Czy chodzi ci o kategorię " + foundCategoryName + ". Link to <a href=" + link + ">" + link + "</a>";
            } else {
                output = "Czy jesteś zainteresowany produktami z kategorii " + foundCategoryName + "?<br/>" +
                        "<form method=\"GET\"> " +
                        "   <input type=\"hidden\" name=\"categoryId\" value=\"" + foundCategoryId + "\"/>" +
                        "   <input type=\"submit\" name=\"theAnswer\" value=\"Tak\"/>" +
                        "   <input type=\"submit\" name=\"theAnswer\" value=\"Nie\"/>" +
                        "</form>";
            }
        }

        //4. wyswietlanie
        PrintWriter writer = resp.getWriter();
        writer.write("<html><head><meta charset=\"utf-8\" /></head><title>Search By Questions</title><body>" + output + "</body></html>");
    }
}
