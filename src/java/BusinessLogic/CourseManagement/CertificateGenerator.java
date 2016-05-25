/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.CourseManagement;

import BusinessLogic.UserManagement.PrivilegeVerifier;
import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import com.itextpdf.text.BadElementException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.net.MalformedURLException;
import java.text.DateFormat;


/**
 * Clase encargada de la generación de certificados.
 * @author manu
 */
public class CertificateGenerator implements Serializable {
    
    /**
     * 
     * @param course
     * @return verdadero en caso de que el documento se haya generado exitosamente.
     * @throws FileNotFoundException - en caso de no encontrar el archivo pdf
     * @throws DocumentException - en caso de que en la generación del documento haya un error.
     * @throws java.net.MalformedURLException - en caso de que la imagen no pueda ser cargada
     */
    public boolean createPDF(Courses course) throws FileNotFoundException, DocumentException, MalformedURLException, BadElementException, IOException
    {
        try{
            User user = new PrivilegeVerifier().getUserLogged();

            Document document=new Document();
            PdfName name = new PdfName("//home//arqsoft//CapacitateUN//web//pages//user//certificate.pdf");
            new PdfAction().remove(name);
            
            File file = new File("//home//arqsoft//CapacitateUN//web//pages//user//certificate.pdf");
            PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
            
            Font font = new Font(Font.FontFamily.COURIER);
            
            document.addTitle("certificado de inscripción");
            font.setStyle(Font.ITALIC);
            Chunk chunk = new Chunk(DateFormat.getDateInstance(DateFormat.FULL).format(new Date()));
            chunk.setFont(font);
            document.add(chunk);
            
            Image image1 = Image.getInstance("logo.png");
            image1.scaleAbsolute(100, 100);
            document.add(image1);
            
            Paragraph paragraph = new Paragraph();
            font.setStyle(Font.BOLDITALIC);
            font.setSize(16);
            paragraph.setFont(font);
            paragraph.add("\t\tCapacitateUN");
            document.add(paragraph);
            //Chunk chunk = new Chunk("iText Test");
            //font.setStyle(Font.BOLDITALIC);
            //chunk.setFont(font);
            //chunk.setBackground(BaseColor.LIGHT_GRAY);
            //document.add(chunk);

            paragraph = new Paragraph();
            font.setStyle(Font.BOLDITALIC);
            font.setSize(16);
            paragraph.setFont(font);
            paragraph.add("\n\n\n\n\n\ncertificado de inscripción\n\n\n\n\n\n\n");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            
            paragraph = new Paragraph();
            font.setFamily("Helvetica");
            font.setSize(12);
            font.setStyle(Font.NORMAL);
            paragraph.setFont(font);
            paragraph.add("El sistema CapacitateUN certifica que ");
            paragraph.add( user.getFirstname()+ " " + user.getLastname());
            paragraph.add(" identificado(a) con cedula "+ user.getId());
            paragraph.add(" se encuentra registrado(a) en el curso ");
            if (course != null){
                paragraph.add(course.getName());
                paragraph.add(" programado para el día ");
                String date = course.getDate().toString();
                paragraph.add(date.substring(0, date.length()-2));
            }else{
                paragraph.add("curso no encontrado");
            }
            paragraph.add(" al momento de expedición de este certificado.");
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(paragraph);
            
            paragraph = new Paragraph();
            font.setFamily("Times New Roman");
            font.setSize(9);
            font.setStyle(Font.ITALIC);
            paragraph.setFont(font);
            paragraph.setAlignment(Element.ALIGN_BOTTOM);
            paragraph.add("\n\n\n\n\n\n\n\n\n\n\n*Este documento no constituye ningun tipo de certificación legal");
            paragraph.add(" de conocimientos adquiridos por parte del usuario o previamente entregados por la empresa.");
            document.add(paragraph);
            document.close();
            return true;
        }catch (FileNotFoundException | DocumentException | MalformedURLException e) {
                throw e;
        }
        //return false;
    }
}
