package br.app.sisau.service;

import br.app.sisau.beans.MedicosBean;
import br.app.sisau.beans.PessoaBean;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
public class ReportService {
    
    private static ReportService instance = new ReportService();
    Logger logger = Logger.getLogger(this.getClass());
    public static final int FORMATO_PDF = 1;
    public static final int FORMATO_XLS = 2;
    public static final int FORMATO_RTF = 3;

    private ReportService() {
    }

    public static ReportService getInstance() {
        return instance;
    }
    
    public InputStream emiteRelatorioPessoas(List<PessoaBean> listaPessoas, int formato) {
        Map parametros = new HashMap();
        return this.emiteRelatorio(listaPessoas, "listaPessoas.jasper", parametros, formato);
    }
    
    private InputStream emiteRelatorio(List listaObjetos, String arquivoJasper, Map parametros, int formato) {
        arquivoJasper = "../reports/" + arquivoJasper;
        JasperReport jr;
        JasperPrint jasperPrint = null;
        InputStream relatorio = null;

        Object[] registros = listaObjetos.toArray(new Object[listaObjetos.size()]);
        JRBeanArrayDataSource ds = new JRBeanArrayDataSource(registros);

        try {
            jr = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResourceAsStream(arquivoJasper.toString()));
            jr.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

            jasperPrint = JasperFillManager.fillReport(jr, parametros, ds);

            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            switch (formato) {
                case 1:
                    JasperExportManager.exportReportToPdfStream(jasperPrint, arrayOutputStream);
                    break;
                case 2:
                    JRXlsExporter exporterXls = new JRXlsExporter();
                    exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                    exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, arrayOutputStream);
                    exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                    exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                    exporterXls.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                    exporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                    exporterXls.exportReport();
                    break;
                case 3:
                    JRRtfExporter exporterRtf = new JRRtfExporter();
                    exporterRtf.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporterRtf.setParameter(JRExporterParameter.OUTPUT_STREAM, arrayOutputStream);
                    exporterRtf.exportReport();
                    break;
                default:
                    JasperExportManager.exportReportToPdfStream(jasperPrint, arrayOutputStream);
                    break;
            }

            relatorio = new ByteArrayInputStream(arrayOutputStream.toByteArray());

        } catch (JRException ex) {
            logger.debug(ex);
        }
        return relatorio;
    }

    public InputStream emiteRelatorioMedicos(List<MedicosBean> listaMedicos, int FORMATO_PDF) {
        
        Map parametros = new HashMap();
        return this.emiteRelatorio(listaMedicos, "listaPessoas.jasper", parametros);
    }

    private InputStream emiteRelatorio(List<MedicosBean> listaMedicos, String listaPessoasjasper, Map parametros) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
