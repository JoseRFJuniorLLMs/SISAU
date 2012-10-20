package br.app.sisau.jsf;

import br.app.sisau.beans.CriancasBean;
import br.app.sisau.beans.FamiliasBean;
import br.app.sisau.security.RbacConstantes;
import br.app.sisau.security.service.SecurityService;
import br.app.sisau.service.AuditoriaService;
import br.app.sisau.service.ReportService;
import br.app.sisau.service.Service;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Jonh Lennon
 */
@ManagedBean(name = "criancaMB")
@ViewScoped
public class CriancaManagedBean implements Serializable {

    private static Logger logger = Logger.getLogger(CriancaManagedBean.class);
    private String pesquisa;
   
    //IMAGENS
    private String imagemPassword = "/images/icons/password.png";
    private String imagemEditarRegistro = "/images/icons/editar.png";
    private String imagemExcluirRegistro = "/images/icons/delete.png";
    private String imagemCriarRegistro = "/images/icons/add1.png";
    private boolean excluirRegistrosTerceiros;
    private boolean editarRegistrosTerceiros;
    private boolean criarRegistro;
    private boolean exibirRegistrosTerceiros;
   
    private static String ADICIONAR_STATE = "adicionar";
    private static String EDITAR_STATE = "editar";
    private String currentState = ADICIONAR_STATE;
    
    //COMBO
    private List<CriancasBean> listaCriancas = new ArrayList<CriancasBean>();
    private CriancasBean criancas = new CriancasBean();
    private List<SelectItem> listaCriancasItems = new ArrayList<SelectItem>();
    private int idCriancasSelecionada;
    
    //COMBO
    private List<FamiliasBean> listaFamilia = new ArrayList<FamiliasBean>();
    private FamiliasBean familia = new FamiliasBean();
    private List<SelectItem> listaFamiliaItems = new ArrayList<SelectItem>();
    private int idFamiliaSelecionada;
    
    @ManagedProperty(value = "#{userSessionMB}")
    private UserSessionManagedBean userSessionMB;

    public UserSessionManagedBean getUserSessionMB() {
        return userSessionMB;
    }

    public void setUserSessionMB(UserSessionManagedBean userSessionMB) {
        this.userSessionMB = userSessionMB;
    }

    @PostConstruct
    public void postConstruct() {

        listaCriancas = Service.getInstance().listarCriancas();
        
        editarRegistrosTerceiros = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_EDITAR_TERCEIROS);
        excluirRegistrosTerceiros = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_EXCLUIR_TERCEIROS);
        criarRegistro = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_CRIAR_REGISTRO);
        exibirRegistrosTerceiros = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_EXIBIR_TERCEIROS);

        //COMBO familia
        setListaFamilia(Service.getInstance().listaFamilia());
        for (FamiliasBean sec : listaFamilia){
            listaFamiliaItems.add(new SelectItem(sec.getPkFamilia(),sec.getNome()));
        }
    }

    public boolean isAdicionarState() {
        return ADICIONAR_STATE.equals(this.getCurrentState());
    }

    public boolean isEditarState() {
        return EDITAR_STATE.equals(this.getCurrentState());
    
    }
    
    public void pesquisarCriancas() {
        listaCriancas = Service.getInstance().pesquisarCriancasCriteria(pesquisa);
   }
  
    public void prepareEditar() {
        this.setCurrentState(EDITAR_STATE);
        getListaCriancas().addAll(getListaCriancas());
        logger.debug("CRIANCA--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + criancas);
        logger.debug("CURRENTSTATES--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +currentState);
    }
    
     public void clear() {
        this.criancas = new CriancasBean();
    }
     
    public void prepareAdicionar() {
        logger.debug("==========Preparando==========");
        this.setCurrentState(ADICIONAR_STATE);
        criancas = new CriancasBean();
        logger.debug("CRIANCA--->>>>>>>>>>>>>>>>>>" + criancas);
    
//        this.clear();
//        this.setCurrentState(ADICIONAR_STATE);
//        
        logger.debug("CRIANCA--->>>>>>>>>>>>>>>>>>" + criancas);
        logger.debug("CURRENTSTATES--->>>>>>>>>>>" +currentState);
    }

    public void gravar() {
        logger.debug("===========gravando...============");
        //this.setCurrentState(ADICIONAR_STATE);
        if (ADICIONAR_STATE.equals(this.currentState)) {
            logger.debug("========ADICIONAR REGISTRO"+currentState);
            Service.getInstance().cadastrarCriancas(criancas);
            logger.debug("========CRIANCAS ADD"+criancas);
            Service.getInstance().atualizarCriancas(criancas);
            logger.debug("========CRIANCAS UPDATE"+criancas);
            AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "CADASTRO DE CRIANÇA", "CADASTRO DE CRIANÇA", "CADASTRO DE CRIANÇA");

        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("========EDITAR REGISTRO");
            Service.getInstance().atualizarCriancas(getCriancas());
            AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "EDITAR CRIANÇA", "EDITAR CRIANÇA", "EDITAR CRIANÇA");

        }
        this.pesquisarCriancas();
    }
    public void excluir() {
        logger.debug("EXCLUIR REGISTRO");
        Service.getInstance().excluirCriancas(getCriancas());
        this.pesquisarCriancas();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("teste"));
                    AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "APGAR CRIANÇAS", "APAGAR CRIANÇAS", "APAGAR CRIANÇAS");
    }

    public StreamedContent downloadReportPdf() {
        logger.debug("GERAR RELATORIO PDF");
        InputStream stream = ReportService.getInstance().emiteRelatorioCriancas(getListaCriancas(), ReportService.FORMATO_PDF);
        StreamedContent file = new DefaultStreamedContent(stream, "application/pdf", "report.pdf");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("teste"));
        return file;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger aLogger) {
        logger = aLogger;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String getImagemPassword() {
        return imagemPassword;
    }

    public void setImagemPassword(String imagemPassword) {
        this.imagemPassword = imagemPassword;
    }

    public String getImagemEditarRegistro() {
        return imagemEditarRegistro;
    }

    public void setImagemEditarRegistro(String imagemEditarRegistro) {
        this.imagemEditarRegistro = imagemEditarRegistro;
    }

    public String getImagemExcluirRegistro() {
        return imagemExcluirRegistro;
    }

    public void setImagemExcluirRegistro(String imagemExcluirRegistro) {
        this.imagemExcluirRegistro = imagemExcluirRegistro;
    }

    public String getImagemCriarRegistro() {
        return imagemCriarRegistro;
    }

    public void setImagemCriarRegistro(String imagemCriarRegistro) {
        this.imagemCriarRegistro = imagemCriarRegistro;
    }

    public boolean isExcluirRegistrosTerceiros() {
        return excluirRegistrosTerceiros;
    }

    public void setExcluirRegistrosTerceiros(boolean excluirRegistrosTerceiros) {
        this.excluirRegistrosTerceiros = excluirRegistrosTerceiros;
    }

    public boolean isEditarRegistrosTerceiros() {
        return editarRegistrosTerceiros;
    }

    public void setEditarRegistrosTerceiros(boolean editarRegistrosTerceiros) {
        this.editarRegistrosTerceiros = editarRegistrosTerceiros;
    }

    public boolean isCriarRegistro() {
        return criarRegistro;
    }

    public void setCriarRegistro(boolean criarRegistro) {
        this.criarRegistro = criarRegistro;
    }

    public boolean isExibirRegistrosTerceiros() {
        return exibirRegistrosTerceiros;
    }

    public void setExibirRegistrosTerceiros(boolean exibirRegistrosTerceiros) {
        this.exibirRegistrosTerceiros = exibirRegistrosTerceiros;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public List<CriancasBean> getListaCriancas() {
        return listaCriancas;
    }

    public void setListaCriancas(List<CriancasBean> listaCriancas) {
        this.listaCriancas = listaCriancas;
    }

    public CriancasBean getCriancas() {
        return criancas;
    }

    public void setCriancas(CriancasBean criancas) {
        this.criancas = criancas;
    }

    public List<SelectItem> getListaCriancasItems() {
        return listaCriancasItems;
    }

    public void setListaCriancasItems(List<SelectItem> listaCriancasItems) {
        this.listaCriancasItems = listaCriancasItems;
    }

    public int getIdCriancasSelecionada() {
        return idCriancasSelecionada;
    }

    public void setIdCriancasSelecionada(int idCriancasSelecionada) {
        this.idCriancasSelecionada = idCriancasSelecionada;
    }

     public void setListaFamilia(List<FamiliasBean> listaFamilia) {
        this.listaFamilia = listaFamilia;
    }

    public List<SelectItem> getListaFamiliaItems() {
        return listaFamiliaItems;
    }

    public void setListaFamiliaItems(List<SelectItem> listaFamiliaItems) {
        this.listaFamiliaItems = listaFamiliaItems;
    }

    public FamiliasBean getFamilia() {
        return familia;
    }

    public void setFamilia(FamiliasBean familia) {
        this.familia = familia;
    }

    public int getIdFamiliaSelecionada() {
        return idFamiliaSelecionada;
    }

    public void setIdFamiliaSelecionada(int idFamiliaSelecionada) {
        this.idFamiliaSelecionada = idFamiliaSelecionada;
    }
    
    public void familSelec(){
        logger.debug("Validando combo.....");
        FamiliasBean famil = new FamiliasBean();
        famil.setPkFamilia((long)idFamiliaSelecionada);
        if(listaFamilia.contains(famil)){
            int index = listaFamilia.indexOf(famil);
            familia = listaFamilia.get(index);
            criancas.setFkFamilia(familia);
        }
    }
}
