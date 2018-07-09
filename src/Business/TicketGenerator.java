/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import com.itextpdf.text.Chunk;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author leonardoalvesdemelo
 */
public class TicketGenerator {
    
    public TicketGenerator() {
        
    }
    
    public void generatePage(ArrayList<String[]> pouchs) {
        String pouch[] = pouchs.get(0);
        
        Document doc = new Document();
        
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("files/teste.pdf"));
            
            
			
            doc.open();
            
            /* Cria a imagem que eh o logo do BB */
            Image logo = Image.getInstance("files/images/bb_logo.png");
            logo.scaleAbsolute(new Rectangle(115,27));
            
            Paragraph malote_bb = new Paragraph("MALOTE BB\n ", FontFactory.getFont(FontFactory.TIMES, 20));
//            Paragraph paragrafo = new Paragraph();
//            Paragraph paragrafo2 = new Paragraph();			
//            Paragraph valor = new Paragraph("Valor: ", FontFactory.getFont(FontFactory.TIMES_BOLD, 14));
//            Paragraph vencimento = new Paragraph("Vencimento: ", FontFactory.getFont(FontFactory.TIMES_BOLD, 14));
//            Paragraph observacao = new Paragraph("Observação: ", FontFactory.getFont(FontFactory.TIMES_BOLD, 14));
//            Paragraph assinatura1 = new Paragraph("_____________________________", FontFactory.getFont(FontFactory.TIMES_BOLD, 14));
//            Paragraph assinatura2 = new Paragraph("DMELO ASSESSORIA DOCUMENTAL S/C LTDA", FontFactory.getFont(FontFactory.TIMES, 10));
//
//
//            titulo.setAlignment(Paragraph.ALIGN_CENTER);

            /* A tabela de 1 devera conter o ticket inteiro */
            PdfPTable table = new PdfPTable(1);
            
            PdfPCell cell = new PdfPCell();
            Paragraph p = new Paragraph();
            p.add( new Chunk(logo, 0, 0, true));
            p.setAlignment(Paragraph.ALIGN_CENTER);
            //p.add("MALOTE BB");
            cell.addElement(p);
            
           
            
            table.addCell(cell);
            doc.add(table);
            doc.close();
            
            
        }
        catch(Exception e) {
            
        }
    }
}
