/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplojasper;

import utilities.MySQLConnection;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author elena
 */
public class EjemploJasper {

    public void ejecutar() throws JRException, IOException {
        File jrxml = new File("src/ejemplojasper/PedidosCiudad.jrxml");

        //JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\elena\\Escritorio\\Workspace\\DI\\Tarea 5\\EjemploJasper\\src\\ejemplojasper\\PedidosCiudad.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(jrxml.getAbsolutePath());
        String query = "select * from pedidos";
        JRDesignQuery update = new JRDesignQuery();
        update.setText(query);
        jasperDesign.setQuery(update);
        File jasper = new File("src/ejemplojasper/PedidosCiudad.jasper");
        JasperCompileManager.compileReportToFile(jasperDesign, jasper.getAbsolutePath());
        JasperReport jreport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> params = new HashMap<>();
        params.put("ENCABEZADO_INFORME", "Informe de pruebitas");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jreport, params, MySQLConnection.getMySQLConnection());
        JasperExportManager.exportReportToPdfFile(jasperPrint, "informe.pdf");
        File path = new File("informe.pdf");
        Desktop.getDesktop().open(path);
        // JasperViewer.viewReport(jasperPrint, true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JRException, IOException {
        EjemploJasper informe = new EjemploJasper();
        informe.ejecutar();
    }

}
