package com.joyousasia.pdf;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;

/**
 * @author Vincent Geng
 */

@Service
public class InvoiceReciept {
	
	public static final String VAR_INVNO = "JE/000001";
	public static final String VAR_TERM = "Cash";
	public static final String VAR_ADDRESS = "114 Lavender Street, CT Hub 2, #03-71, Singapore 338729";
	public static final String VAR_TEL = "+65 68447142";
	public static final String VAR_WEB = "www.joyousasia.com";
	public static final String VAR_EMAIL = "support@joyousasia.com";
	
	public static final String VAR_NAME = "Koh Soon Keong Alvin";
	public static final String VAR_CONTACT = "96938849";
	public static final String VAR_STUDENT_ADDRESS = "Blk 121 Paya Lebar Way  #07-2847 S381121";
	public static final String VAR_NRIC = "S8849149A";
	public static final String VAR_STUDENT_EMAIL = "alvinkoh.sk@gmail.com";
	public static final String VAR_SCHOOL = "UNLV Singapore";
	public static final String VAR_FACULTY = "Bachelor of Science, Hospitality Management";
	public static final String VAR_RETURN_INFO = "13-Jan-2014, from 01:00 PM to 07:30 PM, No 1 Sims Lane, #04-04, One Sims Lane";
	public static final String VAR_EVENT = "UNLV Singapore Graduation 2014 (AM)";
	
	public static final String VAR_TNC = 
			"Penalty charges of $10.00/day plus $20.00 dry cleaning fee will be imposed if gowns are not returned after above dateline.\n"
			+ "i) Refundable deposit will be refunded in cheque\n"
			+ "ii) Refundable deposit will be issued to the gown collection individual only.\n"
			+ "I hereby agree to pay the required rental and deposit gown charges to Joyous Asia Events during the collection of the regalia."
			+ "In addition, I will return the regalia at the stipulated date in good condition. In case of any loss, damaged or non-returned regalia,"
			+ "I understand my deposit shell be forfeited and I will also pay the extra cose incurred listed above. In the event of any dispute,"
			+ "Joyous Asia Events decision shall be final and no correspondence shall be entertained thereafter.";
	
//	public static final String LOGO = "src/main/webapp/img/joyous/logo_design.gif";
	public static final String DEST = "./src/main/webapp/sandbox/invoice_reciept.pdf";
	
	public final PdfFont regular;
    public final PdfFont bold;
	 
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new InvoiceReciept().createPdf(DEST);
    }
    
    public InvoiceReciept() throws IOException {
        this.regular = PdfFontFactory.createFont(FontConstants.HELVETICA);
        this.bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
    }
 
    public void createPdf(String dest) throws IOException {
    	
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PageSize ps = PageSize.A5;
        
        System.out.println("A5 Height: "+ ps.getHeight());
        System.out.println("A5 Width: "+ ps.getWidth());
 
        // Initialize document
        Document document = new Document(pdf, ps);
        document.setTopMargin(22);
        System.out.println("Left Margin: "+ document.getLeftMargin());
        System.out.println("Right Margin: "+ document.getRightMargin());
        System.out.println("Top Margin: "+ document.getTopMargin());
        System.out.println("Bottom Margin: "+ document.getBottomMargin());
        
        // Add Footer
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new TextFooterEventHandler(document));
        
        Style invoDetailTitle = new Style();
        invoDetailTitle.setBold();
        invoDetailTitle.setFont(bold);
        invoDetailTitle.setFontSize(8);
        invoDetailTitle.setBorder(Border.NO_BORDER);
        
        Style invoDetailContent = new Style();
        invoDetailContent.setFont(regular);
        invoDetailContent.setFontSize(8);
        invoDetailContent.setBorder(Border.NO_BORDER);
        
        Style companyDetail = new Style();
        companyDetail.setFont(regular);
        companyDetail.setFontSize(5);
        companyDetail.setBorder(Border.NO_BORDER);
        
        /* ------------------------------- Company Information Table -------------------------------------- */
        Table companyInfoTable = new Table(new float[]{6, 1, 3, 5, 2, 4});
        companyInfoTable.setWidthPercent(100);
        companyInfoTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        Image logo = new Image(ImageDataFactory.create("C:\\apache-tomcat-7.0.57\\webapps\\joyous_asia\\img\\joyous\\logo_design.gif"));
//        System.out.println("before height: "+logo.getImageHeight());
//        System.out.println("before width: "+logo.getImageWidth());
//        System.out.println("scaled height: "+logo.getImageScaledHeight());
//        System.out.println("scaled width: "+logo.getImageScaledWidth());
//        Image scaled = logo.setAutoScale(true);
//        System.out.println("after height: "+scaled.getImageHeight());
//        System.out.println("after width: "+scaled.getImageWidth());
        
        companyInfoTable.addCell(new Cell(5, 1).add(logo.setAutoScale(true)).setBorder(Border.NO_BORDER));
        companyInfoTable.addCell(new Cell(5, 1).setBorder(Border.NO_BORDER));
        
        companyInfoTable.addCell(new Cell(1, 1).add("Inv No.:").addStyle(invoDetailTitle));
        companyInfoTable.addCell(new Cell(1, 1).add(VAR_INVNO).addStyle(invoDetailContent));
        companyInfoTable.addCell(new Cell(1, 1).add("Date:").addStyle(invoDetailTitle));
        companyInfoTable.addCell(new Cell(1, 1).add(new SimpleDateFormat("dd MMM yyyy").format(new Date())).addStyle(invoDetailContent));
        
        companyInfoTable.addCell(new Cell(1, 1).add("Term:").addStyle(invoDetailTitle));
        companyInfoTable.addCell(new Cell(1, 3).add(VAR_TERM).addStyle(invoDetailContent));
        
        companyInfoTable.addCell(new Cell(1, 1).add("Address:").addStyle(companyDetail));
        companyInfoTable.addCell(new Cell(1, 3).add("114 Lavender Street, CT Hub 2, #03-71, Singapore 338729").addStyle(companyDetail).setMarginLeft(-25));
        
        companyInfoTable.addCell(new Cell(1, 1).add("Tel:").addStyle(companyDetail));
        companyInfoTable.addCell(new Cell(1, 1).add("+65 68447142").addStyle(companyDetail).setMarginLeft(-25));
        companyInfoTable.addCell(new Cell(1, 1).add("Web:").addStyle(companyDetail));
        companyInfoTable.addCell(new Cell(1, 1).add("www.joyousasia.com").addStyle(companyDetail).setMarginLeft(-15));
        
        companyInfoTable.addCell(new Cell(1, 1).add("Email:").addStyle(companyDetail));
        companyInfoTable.addCell(new Cell(1, 3).add("support@joyousasia.com").addStyle(companyDetail).setMarginLeft(-25));
        document.add(companyInfoTable);
        /* ------------------------------- Company Information Table -------------------------------------- */
        
        Style studentInfoLabel = new Style();
        studentInfoLabel.setFont(regular);
        studentInfoLabel.setFontSize(6);
        studentInfoLabel.setBorder(Border.NO_BORDER);
        
        Style studentInfoContent = new Style();
        studentInfoContent.setFont(regular);
        studentInfoContent.setFontSize(6);
        
        /* ------------------------------- Student Information Table -------------------------------------- */
        Table studentInfoTable = new Table(new float[]{3, 8, 1, 3, 8});
        studentInfoTable.setMarginTop(10);
        studentInfoTable.setWidthPercent(100);
        studentInfoTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        
        studentInfoTable.addCell(new Cell(1, 1).add("Name:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(VAR_NAME).addStyle(studentInfoContent));
        studentInfoTable.addCell(new Cell(8, 1).setBorder(Border.NO_BORDER));
        studentInfoTable.addCell(new Cell(1, 1).add("Contact:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(VAR_CONTACT).addStyle(studentInfoContent));
        
        studentInfoTable.addCell(new Cell(1, 5).setBorder(Border.NO_BORDER));
        
        studentInfoTable.addCell(new Cell(1, 1).add("Address:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(3, 1).add(VAR_STUDENT_ADDRESS).addStyle(studentInfoContent));
        studentInfoTable.addCell(new Cell(1, 1).add("NRIC:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(VAR_NRIC).addStyle(studentInfoContent));
        studentInfoTable.addCell(new Cell(2, 1).setBorder(Border.NO_BORDER));
        studentInfoTable.addCell(new Cell(1, 2).setBorder(Border.NO_BORDER));
        studentInfoTable.addCell(new Cell(1, 1).add("Email:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(VAR_STUDENT_EMAIL).addStyle(studentInfoContent));
        
        studentInfoTable.addCell(new Cell(1, 5).setBorder(Border.NO_BORDER));
        
        studentInfoTable.addCell(new Cell(1, 1).add("Uni/Sch:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(VAR_SCHOOL).addStyle(studentInfoContent));
        studentInfoTable.addCell(new Cell(1, 1).add("Faculty:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(VAR_FACULTY).addStyle(studentInfoContent).setFontSize(5));
        
        studentInfoTable.addCell(new Cell(1, 5).setBorder(Border.NO_BORDER));
        
        studentInfoTable.addCell(new Cell(1, 1).add("Return Info:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 4).add(VAR_RETURN_INFO).addStyle(studentInfoContent));
        
        studentInfoTable.addCell(new Cell(1, 5).setBorder(Border.NO_BORDER));
        
        Barcode128 code128 = new Barcode128(pdf);
//        code128.setBaseline(-1);
//        code128.setBarHeight(40);
//        code128.fitWidth(150);
        code128.setFont(null);
        code128.setCode(VAR_INVNO);
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = new Image(code128.createFormXObject(pdf));
        studentInfoTable.addCell(new Cell(1, 1).add("Event:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(VAR_EVENT).setFontSize(5));
        studentInfoTable.addCell(new Cell(1, 1).setBorder(Border.NO_BORDER));
        studentInfoTable.addCell(new Cell(1, 1).add("Bar Code:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(code128Image).setBorder(Border.NO_BORDER));
        
        document.add(studentInfoTable);
        /* ------------------------------- Student Information Table -------------------------------------- */
        
        /* ------------------------------- Purchase Information Table -------------------------------------- */
        Table purchaseInfoTable = new Table(new float[]{1, 2, 1, 1, 1});
        purchaseInfoTable.setMarginTop(10);
        purchaseInfoTable.setWidthPercent(100);
        purchaseInfoTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        
        Style purchaseInfoHeader = new Style();
        purchaseInfoHeader.setBackgroundColor(Color.DARK_GRAY);
        purchaseInfoHeader.setFontSize(8);
        purchaseInfoHeader.setFontColor(Color.WHITE);
        purchaseInfoHeader.setBorder(Border.NO_BORDER);
        
        Style purchaseInfoData = new Style();
        purchaseInfoData.setFontSize(7);
        purchaseInfoData.setBorder(Border.NO_BORDER);
        
        purchaseInfoTable.addHeaderCell(new Cell(1, 1).add("Item Code:").addStyle(purchaseInfoHeader));
        purchaseInfoTable.addHeaderCell(new Cell(1, 1).add("Description:").addStyle(purchaseInfoHeader));
        purchaseInfoTable.addHeaderCell(new Cell(1, 1).add("Quantity:").addStyle(purchaseInfoHeader));
        purchaseInfoTable.addHeaderCell(new Cell(1, 1).add("U.Price:").addStyle(purchaseInfoHeader));
        purchaseInfoTable.addHeaderCell(new Cell(1, 1).add("Amount:").addStyle(purchaseInfoHeader));
        
        purchaseInfoTable.addCell(new Cell(1, 1).add("1000").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("Gown Rental\nHeight:168, Weight:65").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("1").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("238.00").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("238.00").addStyle(purchaseInfoData));
        
        purchaseInfoTable.addCell(new Cell(1, 1).add("1011").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("Uni Stage & Group 8R Photos").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("1").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        
        purchaseInfoTable.addCell(new Cell(1, 1).add("1011").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("Uni Stage & Group 8R Photos").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("1").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        
        purchaseInfoTable.addCell(new Cell(1, 1).add("1011").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("Uni Stage & Group 8R Photos").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("1").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        
        purchaseInfoTable.addCell(new Cell(1, 1).add("1011").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("Uni Stage & Group 8R Photos").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("1").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        
        purchaseInfoTable.addCell(new Cell(1, 1).add("1011").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("Uni Stage & Group 8R Photos").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("1").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        
        purchaseInfoTable.addCell(new Cell(1, 1).add("1011").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("Uni Stage & Group 8R Photos").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("1").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        
        purchaseInfoTable.addCell(new Cell(1, 1).add("1011").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("Uni Stage & Group 8R Photos").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("1").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        
        purchaseInfoTable.addCell(new Cell(1, 1).add("1011").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("Uni Stage & Group 8R Photos").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("1").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("80.00").addStyle(purchaseInfoData));
        
        document.add(purchaseInfoTable);
        /* ------------------------------- Purchase Information Table -------------------------------------- */
        
        /* -------------------------------------- Summary Table -------------------------------------- */
        Table summaryTable = new Table(new float[]{9, 1, 3, 3});
        summaryTable.setMarginTop(20);
//        summaryTable.setWidthPercent(100);
        summaryTable.setFixedPosition(document.getLeftMargin(), document.getBottomMargin()+30, ps.getWidth()-document.getLeftMargin()-document.getRightMargin());
        summaryTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        
        
        summaryTable.addCell(new Cell(1, 1).add("Terms & Conditions:").setFont(bold).setFontSize(6).setBorder(Border.NO_BORDER));
        summaryTable.addCell(new Cell(9, 1).setBorder(Border.NO_BORDER));
        summaryTable.addCell(new Cell(1, 1).add("R.DEPOSIT").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("150.00").setFontSize(7));
        summaryTable.addCell(new Cell(8, 1).add("Penalty charges of $10.00/day plus $20.00 dry cleaning fee will be imposed if gowns are not returned after above dateline.\ni) Refundable deposit will be refunded in cheque\nii) Refundable deposit will be issued to the gown collection individual only.\nI hereby agree to pay the required rental and deposit gown charges to Joyous Asia Events during the collection of the regalia. In addition, I will return the regalia at the stipulated date in good condition. In case of any loss, damaged or non-returned regalia, I understand my deposit shell be forfeited and I will also pay the extra cose incurred listed above. In the event of any dispute, Joyous Asia Events decision shall be final and no correspondence shall be entertained thereafter.").setFontSize(6).setBorder(Border.NO_BORDER));
        summaryTable.addCell(new Cell(1, 1).add("RENTAL").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("238.00").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("PAID AMT").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("388.00").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("PUR AMT").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("80.00").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).setHeight(14));
        summaryTable.addCell(new Cell(1, 1).setHeight(14));
        summaryTable.addCell(new Cell(1, 1).add("GST").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("0.00").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("GRAND").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("318.00").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("REFUND").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("(70.00)").setFontSize(7));
        summaryTable.addCell(new Cell(1, 2).setBorder(Border.NO_BORDER));
        
        document.add(summaryTable);
        /* -------------------------------------- Summary Table -------------------------------------- */
        
        document.close();
    }
    
    
    protected class TextFooterEventHandler implements IEventHandler {
        protected Document doc;
 
        public TextFooterEventHandler(Document doc) {
            this.doc = doc;
        }
 
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfCanvas canvas = new PdfCanvas(docEvent.getPage());
            Rectangle pageSize = docEvent.getPage().getPageSize();
            
            canvas.beginText();
            try {
                canvas.setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 7);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            canvas.moveText(doc.getLeftMargin() - 10, pageSize.getBottom() + doc.getBottomMargin() - 10)
                   
            .showText("Aggreed with Terms & Conditions                                               JOYOUS COPY                                                Page "+docEvent.getDocument().getPageNumber(docEvent.getPage())+" of "+docEvent.getDocument().getNumberOfPages())
                    .endText()
                    .release();
        }
    }

}
