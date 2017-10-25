package pl.infoshareacademy.webapp.raportSender;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.infoshareacademy.webapp.dao.DetailedStatisticsResult;
import pl.infoshareacademy.webapp.dao.StatisticResult;
import pl.infoshareacademy.webapp.dao.StatisticsResultsBean;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RaportPdfGenerator {

    @Inject
    StatisticsResultsBean statisticsResultsBean;

    private static String FILE = "FirstPdf.pdf";

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 30,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public void generatePDF(StatisticsResultsBean statisticsResultsBean) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document, statisticsResultsBean);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("Raport");
        document.addSubject(LocalDate.now().toString());
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Zakupy app system");
        document.addCreator("GangSwierzakow");
    }

    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 10);
        // Lets write a big header
        preface.add(new Paragraph("Raport z aplikacji Zakupy", catFont));

        addEmptyLine(preface, 5);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Wygenerowano dnia, " + LocalDateTime.now(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));

        document.add(preface);
        // Start a new page
        document.newPage();
    }

    private void addContent(Document document, StatisticsResultsBean statisticsResultsBean) throws DocumentException {
        Anchor anchor = new Anchor("Ilosc odwiedzin", catFont);
        anchor.setName("Ilosc odwiedzin");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("z ostatnich 30 dni", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));
        createTable(subCatPart, statisticsResultsBean);


        subPara = new Paragraph("ekstrema", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));

        // add a list
        createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);


        // now add all this to the document
        document.add(catPart);

        // Next section
        anchor = new Anchor("Wybierane funkcjonalnosci", catFont);
        anchor.setName("Wybierane funkcjonalnosci");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 1);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));

        // now add all this to the document
        document.add(catPart);

    }

    private void createTable(Section subCatPart, StatisticsResultsBean statisticsResultsBean)
            throws BadElementException {
        PdfPTable table = new PdfPTable(2);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Data"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Funkcjonalnosc 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Funkcjonalnosc 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Funkcjonalnosc 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Funkcjonalnosc 4"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Liczba wejsc"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        //statisticsResultsBean.getLast30DaysDetails().stream().forEach(d -> putElementsToTable(table, d));

        subCatPart.add(table);

    }

    private void putElementsToTable(PdfPTable table, DetailedStatisticsResult detailedStatisticsResult) {
        table.addCell(detailedStatisticsResult.getDate());
        table.addCell(String.valueOf(detailedStatisticsResult.getFeature1Quantity()));
        table.addCell(String.valueOf(detailedStatisticsResult.getFeature2Quantity()));
        table.addCell(String.valueOf(detailedStatisticsResult.getFeature3Quantity()));
        table.addCell(String.valueOf(detailedStatisticsResult.getFeature4Quantity()));
        table.addCell(String.valueOf(detailedStatisticsResult.getVisits()));
    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}