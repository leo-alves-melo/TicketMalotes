/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Model.DottedCell;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.BaseColor;
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
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import static java.nio.channels.spi.AsynchronousChannelProvider.provider;
import org.krysalis.barcode4j.ChecksumMode;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author leonardoalvesdemelo
 */
public class TicketGenerator {
    
    String path;
    
    public TicketGenerator(String path) {
        this.path = path;
    }
    
    public void generatePage(ArrayList<String[]> pouchs) {
        String espacoesquerda = "         ";
        
        Document doc = new Document();
        
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(this.path + ".pdf"));
	
            doc.open();
            
            for(int i = 0; i < pouchs.size(); i++) {
                String pouch[] = pouchs.get(i);
                
                String prefixo = new String(pouch[0]);
                while(prefixo.length() < 7) {
                    prefixo = "0" + prefixo;
                }
                
                
                
                /* Cria a imagem que eh o logo do BB */
                Image logo = Image.getInstance("files/images/bb_logo.png");
                logo.scaleAbsolute(new Rectangle(80,20));

                Paragraph malote_bb = new Paragraph("MALOTE BB\n ", FontFactory.getFont(FontFactory.TIMES, 20));

                /* A tabela de 1 devera conter o ticket inteiro */
                PdfPTable table = new PdfPTable(1);

                PdfPCell cell = new PdfPCell();
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setCellEvent(new DottedCell(PdfPCell.BOX));
                
                Paragraph p = new Paragraph();
                p.add(new Chunk(espacoesquerda));
                p.add( new Chunk(logo, 0, 0, true));
                p.setAlignment(Paragraph.ALIGN_MIDDLE);
                
                p.add(new Chunk("                Prefixo: "));
                System.out.println(prefixo);
                
                p.add(new Chunk(prefixo.substring(0, 3) + " " + prefixo.substring(3, 7), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));

                p.add(new Chunk("             Sub: "));
                p.add(new Chunk(prefixo.substring(0, 3), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                
                Paragraph espaco = new Paragraph("\n");
                cell.addElement(espaco);
                    
                cell.addElement(p);
                
                
                Paragraph linha2 = new Paragraph(espacoesquerda + "Agência: ");
                linha2.add(new Chunk(pouch[1], FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                

                Paragraph roteiro = new Paragraph(espacoesquerda + "Roteiro: ");
                roteiro.add(new Chunk("A-" + pouch[8] + "/ B-" + pouch[10], FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
//                roteiro.add(new Chunk(" Malha: "));
//                roteiro.add(new Chunk(pouch[5], FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                roteiro.add(new Chunk(espacoesquerda + "Frequência: "));
                roteiro.add(new Chunk(pouch[6], FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                //roteiro.setAlignment(Paragraph.ALIGN_CENTER);

                Paragraph malha = new Paragraph(espacoesquerda + "Malha: ");
                malha.add(new Chunk(pouch[5], FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                malha.add(new Chunk(" Município: "));
                malha.add(new Chunk(pouch[2], FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                malha.add(new Chunk(" UF: "));
                malha.add(new Chunk(pouch[4], FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                
                
                Paragraph linha = new Paragraph("___________________________________________");
                linha.setAlignment(Paragraph.ALIGN_CENTER);

//                Paragraph linha2 = new Paragraph("   Prefixo: ");
//                linha2.add(new Chunk(pouch[0], FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
//                linha2.add(new Chunk(" Agência: "));
//                linha2.add(new Chunk(pouch[1], FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
//                linha2.add(new Chunk(" Sub: "));
//                linha2.add(new Chunk(pouch[0].substring(0, 3), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                //linha2.setAlignment(Paragraph.ALIGN_CENTER);

                Paragraph linha3 = new Paragraph("__________________________________________");
                linha3.setAlignment(Paragraph.ALIGN_CENTER);

                Paragraph linha4 = new Paragraph(espacoesquerda + "Empresa: ");
                linha4.add(new Chunk(pouch[11] + "\n\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
//                linha4.add(new Chunk(" UF: "));
//                linha4.add(new Chunk(pouch[4], FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
//                linha4.add(new Chunk(" Município: "));
//                linha4.add(new Chunk(pouch[2] + "\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                //linha4.setAlignment(Paragraph.ALIGN_CENTER);

                Image qrCode = new QRCodeGenerator().generate("0001" + prefixo + "010000000");
                qrCode.scaleAbsolute(new Rectangle(200,200));

                Barcode128 code128 = new Barcode128();
                code128.setFont(null);
                code128.setCode("0001" + prefixo + "010000000");
                code128.setCodeType(Barcode128.CODE128);
                Image img = code128.createImageWithBarcode(writer.getDirectContent(), null, null);

                img.scaleAbsolute(new Rectangle(200,200));
                img.setAlignment(Paragraph.ALIGN_CENTER);
                Paragraph cod = new Paragraph("0001" + prefixo + "010000000");
                cod.setAlignment(Paragraph.ALIGN_CENTER);

                Paragraph imgs = new Paragraph();
                img.scalePercent(120);
                img.setAlignment(Paragraph.ALIGN_MIDDLE);
                qrCode.scalePercent(50);
                imgs.add(new Chunk(img, 0, 0, true));
                imgs.add(new Chunk(qrCode, 0, 0, true));
                imgs.setAlignment(Paragraph.ALIGN_CENTER);

                
                cell.addElement(linha2);
                cell.addElement(roteiro);
                cell.addElement(malha);
                
                //cell.addElement(linha3);
                
                //cell.addElement(qrCode);
                
                cell.addElement(imgs);
                //cell.addElement(new Chunk(qrCode));
                cell.addElement(cod);
                cell.addElement(linha4);
                
                

                table.addCell(cell);
                
                //PdfPCell outSideCell = new PdfPCell(table);
                //outSideCell.setPadding(0);
                //PdfPTable outerTable = new PdfPTable(1);
                //outerTable.addCell(outSideCell);
                doc.add(table);
                doc.add(new Paragraph("\n"));
            }
            
            
           
            doc.close();
            
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
