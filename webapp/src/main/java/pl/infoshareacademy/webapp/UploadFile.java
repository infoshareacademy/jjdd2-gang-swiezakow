package pl.infoshareacademy.webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet("/index")
@MultipartConfig
public class UploadFile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<body>");

        writer.println("<form action=\"index\" method=\"post\" enctype=\"multipart/form-data\">");

        writer.println("<p>Wskaż swój plik XML: ");
        writer.println("<input type=\"file\" name=\"fileXML\"/>");
        writer.println("</p>");

        writer.println("<button type=\"submit\">Wyslij</button>");

        writer.println("</form>");

        writer.println("</body>");
        writer.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {


        try {
            Part fileLST = null;
            fileLST = req.getPart("fileLST");
            InputStream inputStreamLST = null;
            inputStreamLST = fileLST.getInputStream();
            String tmpDir = System.getProperty("java.io.tmpdir");
            String LSTDir = tmpDir + "/plik.xml";
            OutputStream outputStreamLST = null;
            outputStreamLST = new FileOutputStream(new File(LSTDir));
            int readXML = 0;
            byte[] bytesXML = new byte[10240];

            while ((readXML = inputStreamLST.read(bytesXML)) != -1) {
                outputStreamLST.write(bytesXML, 0, readXML);
            }
            resp.getWriter().println("done");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
