package com.joyousasia.pdf;

import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class PrintingExample {

    public static void main(String args[]) throws Exception {

        PDDocument document = PDDocument.load(new File("C:\\apache-tomcat-7.0.57\\webapps\\joyous_asia\\sandbox\\simple_table.pdf"));

        PrintService printService = findPrinterService();

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.setPrintService(printService);
        job.print();

    }       

    public static PrintService findPrinterService() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        if(printJob.printDialog()) {
            return printJob.getPrintService();          
        }
        else {
            return null;
        }
    }
}
