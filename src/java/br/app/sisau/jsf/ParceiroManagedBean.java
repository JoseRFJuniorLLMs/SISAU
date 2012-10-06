/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.sisau.jsf;

import br.app.sisau.beans.ParceirosBean;
import br.app.sisau.security.RbacConstantes;
import br.app.sisau.security.service.SecurityService;
import br.app.sisau.service.AuditoriaService;
import br.app.sisau.service.ReportService;
import br.app.sisau.service.Service;
import java.io.InputStream;
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
 * @author Ewerton
 */
@ManagedBean(name = "parceiroMB")
@ViewScoped
public class ParceiroManagedBean {

    private static Logger logger = Logger.getLogger(MedicoManagedBean.class);
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
    private List<ParceirosBean> listaParceiros = new ArrayList<ParceirosBean>();
    private ParceirosBean parceiros = new ParceirosBean();
    private List<SelectItem> listaParceirosItems = new ArrayList<SelectItem>();
    private int idParceirosSelecionada;
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

        listaParceiros = Service.getInstance().listarParceiros();

        editarRegistrosTerceiros = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_EDITAR_TERCEIROS);
        excluirRegistrosTerceiros = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_EXCLUIR_TERCEIROS);
        criarRegistro = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_CRIAR_REGISTRO);
        exibirRegistrosTerceiros = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_EXIBIR_TERCEIROS);


    }

    public boolean isAdicionarState() {
        return ADICIONAR_STATE.equals(this.getCurrentState());
    }

    public boolean isEditarState() {
        return EDITAR_STATE.equals(this.getCurrentState());

    }

    public void pesquisarParceiros() {
        listaParceiros = Service.getInstance().pesquisarParceirosCriteria(pesquisa);
    }

    public void prepareEditar() {
        this.setCurrentState(EDITAR_STATE);
        getListaParceiros().addAll(getListaParceiros());
        logger.debug("PARCEIRO--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + parceiros);
        logger.debug("CURRENTSTATES--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + currentState);
    }

    public void clear() {
        this.parceiros = new ParceirosBean();
    }

    public void prepareAdicionar() {
        logger.debug("==========Preparando==========");
        this.setCurrentState(ADICIONAR_STATE);
        parceiros = new ParceirosBean();
        logger.debug("PARCEIRO--->>>>>>>>>>>>>>>>>>" + parceiros);

//        this.clear();
//        this.setCurrentState(ADICIONAR_STATE);
//        
        logger.debug("PARCEIRO--->>>>>>>>>>>>>>>>>>" + parceiros);
        logger.debug("CURRENTSTATES--->>>>>>>>>>>" + currentState);
    }

    public void gravar() {
        logger.debug("===========gravando...============");
        //this.setCurrentState(ADICIONAR_STATE);
        if (ADICIONAR_STATE.equals(this.currentState)) {
            logger.debug("========ADICIONAR REGISTRO" + currentState);
            Service.getInstance().cadastrarParceiros(parceiros);
            logger.debug("========PARCEIROS ADD" + parceiros);
            Service.getInstance().atualizarParceiros(parceiros);
            logger.debug("========PARCEIROS UPDATE" + parceiros);
            AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "CADASTRO DE PARCEIRO", "CADASTRO DE PARCEIRO", "CADASTRO DE PARCEIRO");

        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("========EDITAR REGISTRO");
            Service.getInstance().atualizarParceiros(getParceiros());
            AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "EDITAR PARCEIRO", "EDITAR PARCEIRO", "EDITAR PARCEIRO");

        }
        this.pesquisarParceiros();
    }

    public void excluir() {
        logger.debug("EXCLUIR REGISTRO");
        Service.getInstance().excluirParceiros(getParceiros());
        this.pesquisarParceiros();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("teste"));
        AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "APGAR MÉDICO", "APAGAR MÉDICO", "APAGAR MÉDICO");
    }

    public StreamedContent downloadReportPdf() {
        logger.debug("GERAR RELATORIO PDF");
        InputStream stream = ReportService.getInstance().emiteRelatorioParceiros(getListaParceiros(), ReportService.FORMATO_PDF);
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

    public List<ParceirosBean> getListaParceiros() {
        return listaParceiros;
    }

    public void setListaParceiros(List<ParceirosBean> listaParceiros) {
        this.listaParceiros = listaParceiros;
    }

    public ParceirosBean Parceirosget() {
        return parceiros;
    }

    public void setParceiros(ParceirosBean parceiros) {
        this.parceiros = parceiros;
    }

    public List<SelectItem> getListaParceirosItems() {
        return listaParceirosItems;
    }

    public void setListaParceirosItems(List<SelectItem> listaParceirossItems) {
        this.listaParceirosItems = listaParceirosItems;
    }

    public int getIdParceirosSelecionada() {
        return idParceirosSelecionada;
    }

    public void setIdParceirosSelecionada(int idParceirosSelecionada) {
        this.idParceirosSelecionada = idParceirosSelecionada;
    }

    public ParceirosBean getParceiros() {
        return parceiros;
    }
}
