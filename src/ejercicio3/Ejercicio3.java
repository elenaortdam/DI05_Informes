/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import utilities.MySQLConnection;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author elena
 */
public class Ejercicio3 {

    private String filePath = "src/ejercicio3/Ejercicio3";

    public void ejecutar() throws JRException, IOException {
        File jrxml = new File(filePath + ".jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(jrxml.getAbsolutePath());
        File jasper = new File(filePath + ".jasper");
        JasperCompileManager.compileReportToFile(jasperDesign, jasper.getAbsolutePath());
        JasperReport jreport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jreport, null, MySQLConnection.getMySQLConnection());
        String pdfName ="src/informes/" + System.currentTimeMillis()+  "informe3.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint,pdfName);
        File path = new File(pdfName);
        Desktop.getDesktop().open(path);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JRException, IOException {
        Ejercicio3 informe = new Ejercicio3();
        informe.ejecutar();
    }

}
