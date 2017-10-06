package pl.infoshareacademy.webapp;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/main")
public class MainMenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.write("<html><head><meta charset=\"utf-8\" /></head><title>ShoppingAssistant</title><body>" +
                "<p><a href=\"form1\">Wyszukiwanie na podstawie serii pytań</a></p>" +
                "<p><a href=\"form2\">Wyszukiwanie produktu</a></p>" +
                "<p><a href=\"form3\">Wyszukiwanie produktu w kategoriach</a></p>" +
                "<p><a href=\"form4\">Asysent Allegro</a></p>" +
                "</body></html>");
    }

}
