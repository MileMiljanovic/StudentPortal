package com.ftn.student.service.pdfservice;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Zamena;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

@Service
public class PDFGenerator {
	
	public ByteArrayInputStream formularReport(Formular f) {

        Document document = new Document();
        document.addTitle("Izveštaj o zahtevu za studiranje u inostranstvu");
        document.addSubject("Formular");
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
        	
        	Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
        	Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        	Font normalFont = FontFactory.getFont(FontFactory.HELVETICA);
        	
        	PdfPTable head1 = new PdfPTable(1);
        	head1.setWidthPercentage(90);
        	head1.setWidths(new int[]{7});
        	head1.setSpacingAfter(30);
        	
        	PdfPTable head2 = new PdfPTable(1);
        	head2.setWidthPercentage(90);
        	head2.setWidths(new int[]{7});
        	
        	PdfPCell topCell;
        	topCell = new PdfPCell(new Phrase("Univerzitet u Novom Sadu\nFakultet tehničkih nauka", normalFont));
        	topCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        	topCell.setBorder(0);
        	head1.addCell(topCell);
        	
        	PdfPCell titleCell;
        	titleCell = new PdfPCell(new Phrase("Izveštaj o zahtevu za studiranje u inostranstvu", titleFont));
        	titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        	titleCell.setBorder(0);
        	head2.addCell(titleCell);
        	
        	String text = "Obaveštavamo vas da je student " + f.getStudent().getIme() + " " + f.getStudent().getPrezime() 
        			+ " sa brojem indeksa " + f.getStudent().getBrindeksa() + " uspešno popunio formular sa "
        					+ "identifikacionom oznakom " + f.getIdformular() + ". Student menja studijski program " 
        			+ f.getStudent().getStudije().getNaziv() + " na Fakultetu tehničkih nauka sa programom " 
        					+ f.getProgramStrani().getNaziv() + " na stranom fakultetu.";   
        	        	
        	Paragraph paragraph = new Paragraph(text); 
        	paragraph.setSpacingBefore(50);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(75);
            table.setWidths(new int[]{2, 3, 3});

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id Zamene", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Domaći predmet", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Strani predmet", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (Zamena z : f.getZamene()) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(z.getIdzamena()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(z.getPredmetDomaci()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(z.getPredmetStrani()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }
            
            PdfPTable bottom = new PdfPTable(3);
            bottom.setTotalWidth(document.right(document.rightMargin())
            	    - document.left(document.leftMargin()));
            bottom.setWidths(new int[]{7, 7, 7});
            
            PdfPCell signatureCell1;
            signatureCell1 = new PdfPCell(new Phrase("Potpis studenta\n_____________________", normalFont));
            signatureCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            signatureCell1.setBorder(0);
        	bottom.addCell(signatureCell1);
        	
        	PdfPCell signatureCell2;
        	signatureCell2 = new PdfPCell(new Phrase("Potpis šefa programa\n_____________________", normalFont));
        	signatureCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        	signatureCell2.setBorder(0);
        	bottom.addCell(signatureCell2);
        	
        	PdfPCell signatureCell3;
        	signatureCell3 = new PdfPCell(new Phrase("Potpis koordinatora\n_____________________", normalFont));
        	signatureCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        	signatureCell3.setBorder(0);
        	bottom.addCell(signatureCell3);

            PdfWriter w = PdfWriter.getInstance(document, out);
            document.open();
            document.add(head1);
            document.add( Chunk.NEWLINE );
            document.add(head2);
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            document.add(paragraph); 
            document.add( Chunk.NEWLINE );
            document.add(table);
            
            bottom.writeSelectedRows(0, -1,
            	    document.left(document.leftMargin()),
            	    bottom.getTotalHeight() + document.bottom(document.bottomMargin()),
            	    w.getDirectContent());
            
            document.close();

        } catch (DocumentException ex) {

            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
