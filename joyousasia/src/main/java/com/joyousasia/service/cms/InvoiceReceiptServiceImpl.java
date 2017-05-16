package com.joyousasia.service.cms;

import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.print.PrintService;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
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
import com.joyousasia.dao.cms.EventDao;
import com.joyousasia.dao.cms.InvoiceReceiptDao;
import com.joyousasia.dao.cms.ItemDao;
import com.joyousasia.dao.cms.TransactionDao;
import com.joyousasia.model.CustomerDTO;
import com.joyousasia.model.EventDTO;
import com.joyousasia.model.InvoiceReceiptDTO;
import com.joyousasia.model.ItemDTO;
import com.joyousasia.model.TransactionDTO;
import com.joyousasia.util.ConnConfig;

/**
 * @author Vincent Geng
 */

@Service
public class InvoiceReceiptServiceImpl extends BaseService {
	
	public static final String LOGO_PATH = ConnConfig.getWebAppPath()+"\\img\\joyous\\";
	public static final String LOGO_PATH_SAVE = ConnConfig.getLogoPathSave();
	public static final Integer IMAGE_HEIGHT = 95;
	public static final Integer IMAGE_WIDTH = 155;
	
	@Autowired  
	private InvoiceReceiptDao invoiceReceiptDao;
	
	@Autowired  
	private TransactionDao transactionDao;
	
	@Autowired  
	private ItemDao itemDao;
	
	@Autowired  
	private EventDao eventDao;
	
    public void createPdf(String dest, CustomerDTO customer) throws Exception {
    	
    	File file = new File(dest);
        file.getParentFile().mkdirs();
    	
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
        
        InvoiceReceiptDTO invoiceReciept = invoiceReceiptDao.findOne(1); 
        
        Style invoDetailTitle = new Style();
        invoDetailTitle.setBold();
        invoDetailTitle.setFontSize(8);
        invoDetailTitle.setBorder(Border.NO_BORDER);
        
        Style invoDetailContent = new Style();
        invoDetailContent.setFontSize(8);
        invoDetailContent.setBorder(Border.NO_BORDER);
        
        Style companyDetail = new Style();
        companyDetail.setFontSize(5);
        companyDetail.setBorder(Border.NO_BORDER);
        
        /* ------------------------------- Company Information Table -------------------------------------- */
        Table companyInfoTable = new Table(new float[]{6, 1, 3, 5, 2, 4});
        companyInfoTable.setWidthPercent(100);
        companyInfoTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        Image logo = new Image(ImageDataFactory.create(invoiceReciept.getLogo()));
        logo.setHeight(95);
        logo.setWidth(155);
        
        companyInfoTable.addCell(new Cell(5, 1).add(logo.setAutoScale(true)).setBorder(Border.NO_BORDER));
        companyInfoTable.addCell(new Cell(5, 1).setBorder(Border.NO_BORDER));
        
        companyInfoTable.addCell(new Cell(1, 1).add("Inv No.:").addStyle(invoDetailTitle));
        companyInfoTable.addCell(new Cell(1, 1).add(constructInvoiceNumByCustomerId(customer.getCustomerId())).addStyle(invoDetailContent));
        companyInfoTable.addCell(new Cell(1, 1).add("Date:").addStyle(invoDetailTitle));
        companyInfoTable.addCell(new Cell(1, 1).add(new SimpleDateFormat("dd MMM yyyy").format(new Date())).addStyle(invoDetailContent));
        
        companyInfoTable.addCell(new Cell(1, 1).add("Term:").addStyle(invoDetailTitle));
        companyInfoTable.addCell(new Cell(1, 3).add(getTermByPayByDetail(customer.getPayByDetail())).addStyle(invoDetailContent));
        
        companyInfoTable.addCell(new Cell(1, 1).add("Address:").addStyle(companyDetail));
        companyInfoTable.addCell(new Cell(1, 3).add(invoiceReciept.getAddress()).addStyle(companyDetail).setMarginLeft(-25));
        
        companyInfoTable.addCell(new Cell(1, 1).add("Tel:").addStyle(companyDetail));
        companyInfoTable.addCell(new Cell(1, 1).add(invoiceReciept.getTel()).addStyle(companyDetail).setMarginLeft(-25));
        companyInfoTable.addCell(new Cell(1, 1).add("Web:").addStyle(companyDetail));
        companyInfoTable.addCell(new Cell(1, 1).add(invoiceReciept.getWebsite()).addStyle(companyDetail).setMarginLeft(-15));
        
        companyInfoTable.addCell(new Cell(1, 1).add("Email:").addStyle(companyDetail));
        companyInfoTable.addCell(new Cell(1, 3).add(invoiceReciept.getEmail()).addStyle(companyDetail).setMarginLeft(-25));
        document.add(companyInfoTable);
        /* ------------------------------- Company Information Table -------------------------------------- */
        
        Style studentInfoLabel = new Style();
        studentInfoLabel.setFontSize(7);
        studentInfoLabel.setBorder(Border.NO_BORDER);
        
        Style studentInfoContent = new Style();
        studentInfoContent.setFontSize(7);
        
        /* ------------------------------- Student Information Table -------------------------------------- */
        Table studentInfoTable = new Table(new float[]{3, 8, 1, 3, 8});
        studentInfoTable.setMarginTop(10);
        studentInfoTable.setWidthPercent(100);
        studentInfoTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        
        studentInfoTable.addCell(new Cell(1, 1).add("Name:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(customer.getName()).addStyle(studentInfoContent));
        studentInfoTable.addCell(new Cell(8, 1).setBorder(Border.NO_BORDER));
        studentInfoTable.addCell(new Cell(1, 1).add("Contact:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(customer.getContactNum()).addStyle(studentInfoContent));
        
        studentInfoTable.addCell(new Cell(1, 5).setBorder(Border.NO_BORDER));
        
        studentInfoTable.addCell(new Cell(1, 1).add("Address:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(3, 1).add(customer.getAddress()).addStyle(studentInfoContent));
        studentInfoTable.addCell(new Cell(1, 1).add("NRIC:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(customer.getIdentificationNum()).addStyle(studentInfoContent));
        studentInfoTable.addCell(new Cell(2, 1).setBorder(Border.NO_BORDER));
        studentInfoTable.addCell(new Cell(1, 2).setBorder(Border.NO_BORDER));
        studentInfoTable.addCell(new Cell(1, 1).add("Email:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(customer.getEmail()).addStyle(studentInfoContent));
        
        studentInfoTable.addCell(new Cell(1, 5).setBorder(Border.NO_BORDER));
        
        studentInfoTable.addCell(new Cell(1, 1).add("Uni/Sch:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(customer.getSchool()).addStyle(studentInfoContent));
        studentInfoTable.addCell(new Cell(1, 1).add("Faculty:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(customer.getFaculty()).addStyle(studentInfoContent).setFontSize(6));
        
        studentInfoTable.addCell(new Cell(1, 5).setBorder(Border.NO_BORDER));
        
        studentInfoTable.addCell(new Cell(1, 1).add("Return Info:").addStyle(studentInfoLabel));
        
        
        studentInfoTable.addCell(new Cell(1, 4).add(constructReturnInfo(customer)).addStyle(studentInfoContent).setFontSize(6));
        studentInfoTable.addCell(new Cell(1, 5).setBorder(Border.NO_BORDER));
        
        Barcode128 code128 = new Barcode128(pdf);
        code128.setFont(null);
        code128.setCode(constructInvoiceNumByCustomerId(customer.getCustomerId()));
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = new Image(code128.createFormXObject(pdf));
        studentInfoTable.addCell(new Cell(1, 1).add("Event:").addStyle(studentInfoLabel));
        studentInfoTable.addCell(new Cell(1, 1).add(customer.getEvent()).setFontSize(6));
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
        purchaseInfoHeader.setFontSize(7);
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
        
        purchaseInfoTable.addCell(new Cell(1, 1).add("0000").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("Gown Rental\nHeight:"+customer.getHeight()+" cm, Weight:"+customer.getWeight()+" kg").addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add("1").setFontSize(7).setBorder(Border.NO_BORDER));
        purchaseInfoTable.addCell(new Cell(1, 1).add(String.format("%.2f", (double)customer.getRental())).addStyle(purchaseInfoData));
        purchaseInfoTable.addCell(new Cell(1, 1).add(String.format("%.2f", (double)customer.getRental())).addStyle(purchaseInfoData));
        
        List<TransactionDTO> transactionList = transactionDao.findByCustomerId(customer.getCustomerId());
        for (TransactionDTO transaction : transactionList) {
        	if(transaction.getItemQuantity() > 0) {
        		ItemDTO item = itemDao.findOne(transaction.getItemId());
	        	purchaseInfoTable.addCell(new Cell(1, 1).add(constructItemCodeByItemId(item.getItemId())).addStyle(purchaseInfoData));
	            purchaseInfoTable.addCell(new Cell(1, 1).add(item.getItemName()).addStyle(purchaseInfoData));
	            purchaseInfoTable.addCell(new Cell(1, 1).add((transaction.getItemQuantity()).toString()).addStyle(purchaseInfoData));
	            purchaseInfoTable.addCell(new Cell(1, 1).add(String.format("%.2f", (Double)item.getItemPrice())).addStyle(purchaseInfoData));
	            purchaseInfoTable.addCell(new Cell(1, 1).add(calculateSubtotal((Double)item.getItemPrice(), transaction.getItemQuantity())).addStyle(purchaseInfoData));
        	}
        }
        
        document.add(purchaseInfoTable);
        /* ------------------------------- Purchase Information Table -------------------------------------- */
        
        /* -------------------------------------- Summary Table -------------------------------------- */
        Table summaryTable = new Table(new float[]{9, 1, 3, 3});
        summaryTable.setMarginTop(20);
        summaryTable.setWidthPercent(100);
        summaryTable.setFixedPosition(document.getLeftMargin(), document.getBottomMargin()+30, ps.getWidth()-document.getLeftMargin()-document.getRightMargin());
        summaryTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        
        
        summaryTable.addCell(new Cell(1, 1).add("Terms & Conditions:").setBold().setFontSize(6).setBorder(Border.NO_BORDER));
        summaryTable.addCell(new Cell(9, 1).setBorder(Border.NO_BORDER));
        summaryTable.addCell(new Cell(1, 1).add("R.DEPOSIT").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add(String.format("%.2f", (double)customer.getDeposit())).setFontSize(7));
        summaryTable.addCell(new Cell(8, 1).add(invoiceReciept.getTermsAndConditions()).setFontSize(6).setBorder(Border.NO_BORDER));
        summaryTable.addCell(new Cell(1, 1).add("RENTAL").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add(String.format("%.2f", (double)customer.getRental())).setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("PAID AMT").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add(String.format("%.2f", calculateTotalPaid((double)customer.getDeposit(), (double)customer.getRental(), transactionList, customer.getTransferAmount()))).setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("PUR AMT").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add(String.format("%.2f", calculatePurchaseTotal(transactionList, customer.getTransferAmount()))).setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).setHeight(14));
        summaryTable.addCell(new Cell(1, 1).setHeight(14));
        summaryTable.addCell(new Cell(1, 1).add("GST").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("0.00").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("GRAND").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add(String.format("%.2f", calculateTotalPaid((double)customer.getDeposit(), (double)customer.getRental(), transactionList, customer.getTransferAmount()) - calculateRefund((double)customer.getDeposit(), transactionList, customer.getTransferAmount(), customer.getEvent(), customer.getRentalReturnDate()) )).setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("REFUND").setFontSize(7));
        summaryTable.addCell(new Cell(1, 1).add("("+String.format("%.2f", calculateRefund((double)customer.getDeposit(), transactionList, customer.getTransferAmount(), customer.getEvent(), customer.getRentalReturnDate()))+")").setFontSize(7));
        summaryTable.addCell(new Cell(1, 2).setBorder(Border.NO_BORDER));
        
        document.add(summaryTable);
        /* -------------------------------------- Summary Table -------------------------------------- */
        document.close();
    }

	private String getTermByPayByDetail(int payByDetail) {
		String term = "Cash";
        switch(payByDetail){    
	        case 0:    
		        break; 
	        case 1:    
	        	term = "Credit Card";   
		        break;
	        case 2:    
	        	term = "NETS";   
		        break;
	        case 3:    
	        	term = "Others";   
		        break;
        }
        return term;
	}
    
    private Double calculateTotalPaid(Double deposit, Double rental, List<TransactionDTO> transactionList, Double transferAmount) {
    	Double totalPaid = 0.0;
    	Double purchaseTotal = calculatePurchaseTotal(transactionList, transferAmount);
    	if(deposit > purchaseTotal) {
    		totalPaid = deposit + rental;
    	}else {
    		totalPaid = purchaseTotal + rental;
    	}
    	return totalPaid;
    }
    
    private Double calculatePenalty(String eventName, Date rentalReturnDate) {
    	
    	EventDTO event = eventDao.findByEventName(eventName).get(0);
    	Double penalty = 0.0;
    	Long currentDateTime = (new Date()).getTime();
    	Long rentalReturnDateTime = (long) 0;
    	int delayDays = 0 ;
    	
    	if(rentalReturnDate == null) {
    		rentalReturnDateTime = event.getGownReturnDate().getTime();
    		delayDays = (int) ((currentDateTime - rentalReturnDateTime)/86400000);
    	}else {
    		Long rentalToDateTime = event.getGownReturnDate().getTime();
    		rentalReturnDateTime = rentalReturnDate.getTime();
    		delayDays = (int) ((rentalReturnDateTime - rentalToDateTime)/86400000);
    	}

    	if(delayDays>0) {
    		if(delayDays == 1){
				penalty = 30.0;
			}else{
				penalty = 30.0 + delayDays*10;
			}
    	}
    	return penalty;
    }
    
    private Double calculateRefund(Double deposit, List<TransactionDTO> transactionList, Double transferAmount, String eventName, Date rentalReturnDate) {
    	Double refund = 0.0;
    	Double purchaseTotal = calculatePurchaseTotal(transactionList, transferAmount);
    	if(deposit > purchaseTotal) {
    		refund = deposit - purchaseTotal - calculatePenalty(eventName, rentalReturnDate);
    	}else {
    		refund -= calculatePenalty(eventName, rentalReturnDate);
    	}
    	if(refund<0) {
    		refund = 0.0;
    	}
    	return refund;
    }
    
    private Double calculatePurchaseTotal(List<TransactionDTO> transactionList, Double transferAmount) {
    	Double purchaseTotal = 0.0;
    	for(TransactionDTO transaction : transactionList) {
    		ItemDTO item = itemDao.findOne(transaction.getItemId());
    		Double itemSubtotal = (Double)item.getItemPrice() * transaction.getItemQuantity();
    		purchaseTotal += itemSubtotal;
    	}
    	purchaseTotal += transferAmount;
    	return purchaseTotal;
    }
    
    private String calculateSubtotal(Double price, Integer quantity) {
    	Double subtotal = price * quantity;
    	return String.format("%.2f", subtotal);
    }

	private String constructReturnInfo(CustomerDTO customer) {
		String returnInfo = "Date: "+SDF_DD_MMM_YYYY.format(customer.getRentalTo())+" "+customer.getReturnGownFromtimeTotime()+", Location: "+customer.getRentalVenue();
		return returnInfo;
	}
	
	private String constructItemCodeByItemId(Integer itemId) {
		String itemCode = "";
        String itemIdString = itemId.toString();
        if(itemIdString.length()<4) {
        	StringBuilder sb = new StringBuilder();
        	for (int toPrepend=4-itemIdString.length(); toPrepend>0; toPrepend--) {
        	    sb.append('0');
        	}
        	sb.append(itemIdString);
        	String itemIdformatted = sb.toString();
        	itemCode = itemIdformatted;
        }else {
        	itemCode = itemIdString;
        }
        return itemCode;
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


	public void printPdf(String PDF_PATH) throws IOException {
		File pdfFile = null;
		PDDocument document = null;
		try {
			log.info("| InvoiceReceiptServiceImpl | printPdf | printPdf entry.");
			pdfFile = new File(PDF_PATH);
			document = PDDocument.load(pdfFile);
//			PrintService printService = findPrinterService();
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPageable(new PDFPageable(document));
//			job.setPrintService(printService);
			job.print();
			log.info("| InvoiceReceiptServiceImpl | printPdf | printPdf exit.");
		} catch (Exception e) {
			log.info(e,e);
			log.info("| InvoiceReceiptServiceImpl | printPdf | Print PDF failed.");
		} finally {
			document.close();
			pdfFile.delete();
		}
        
	}
	
	public PrintService findPrinterService() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        if(printJob.printDialog()) {
            return printJob.getPrintService();          
        }
        else {
            return null;
        }
    }
	
	public void cleanUpDirectory(File dir) {
		try {
			for(File file: dir.listFiles()) {
			    if (!file.isDirectory()) {
			    	log.info("| InvoiceReceiptServiceImpl | cleanUpDirectory | Delete file name: "+file.getName());
			        file.delete();
			    }
			}
		} catch (Exception e) {
			log.info(e,e);
			log.info("| InvoiceReceiptServiceImpl | cleanUpDirectory | Clean up directory failed.");
		}
        
	}
	
	@Transactional
	public InvoiceReceiptDTO getInvoiceReceiptDTO() {
		InvoiceReceiptDTO invoiceReceiptDTO = invoiceReceiptDao.findOne(1);
		return invoiceReceiptDTO;
	}
	
	@Transactional
	public void saveInvoiceReceiptInfomation(InvoiceReceiptDTO invoiceReceiptDTO, MultipartFile image) throws Exception{
		
		String outputFilename = "";
		Date currentDate = new Date();
		Long unixTimestamp = currentDate.getTime() / 1000;
		String EXT = "";
		if(!StringUtil.isBlank(image.getOriginalFilename())) {
			String[] nameArray = (image.getOriginalFilename()).split("\\.");
			EXT = nameArray[1];
			outputFilename = LOGO_PATH+"logo_"+unixTimestamp+"."+EXT;
			OutputStream out = new FileOutputStream(outputFilename);
			IOUtils.copy(image.getInputStream(), out);
			BufferedImage originalImage = ImageIO.read(new File(outputFilename));
			ImageIO.write(originalImage, EXT, new File(outputFilename));
			invoiceReceiptDTO.setLogo(LOGO_PATH_SAVE+"logo_"+unixTimestamp+"."+EXT);
		}
		
		invoiceReceiptDao.save(invoiceReceiptDTO);
		
	}
	
}
