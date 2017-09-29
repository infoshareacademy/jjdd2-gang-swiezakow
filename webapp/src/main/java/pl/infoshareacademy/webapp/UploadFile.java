package pl.infoshareacademy.webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/index")
@MultipartConfig
public class UploadFile extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(UploadFile.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<form action=\"index\" method=\"post\" enctype=\"multipart/form-data\">");
        writer.println("<p>Select the XML file with data: ");
        writer.println("<input type=\"file\" name=\"fileXML\"/>");
        writer.println("</p>");
        writer.println("<button type=\"submit\">Send</button>");
        writer.println("</form>");
        writer.println("</body>");
        writer.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Part fileXML = req.getPart("fileXML");
            InputStream inputStreamXML = null;
            inputStreamXML = fileXML.getInputStream();
            String tmpDir = System.getProperty("java.io.tmpdir");
            String XMLDir = tmpDir + "/file.xml";
            OutputStream outputStreamXML = null;
            outputStreamXML = new FileOutputStream(new File(XMLDir));
            int readXML = 0;
            byte[] bytesXML = new byte[1024];
            while ((readXML = inputStreamXML.read(bytesXML)) != -1) { outputStreamXML.write(bytesXML, 0, readXML);
            }
            resp.getWriter().println("done");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Could not read the file");
        } catch (ServletException e) {
            e.printStackTrace();
            logger.error("Something gone wrong, try again");

        }
    }
}
