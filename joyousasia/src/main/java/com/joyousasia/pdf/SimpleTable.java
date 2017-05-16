package com.joyousasia.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
 
import java.io.File;
 
public class SimpleTable {
    public static final String DEST = "./src/main/webapp/sandbox/simple_table.pdf";
 
    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable().manipulatePdf(DEST);
    }
 
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
 
        Table table = new Table(8);
        for (int i = 0; i < 16; i++) {
            table.addCell("hi");
        }
        doc.add(table);
 
        doc.close();
    }
}
