package br.app.sisau.jsf;

import br.app.sisau.beans.EspecialidadesBean;
import br.app.sisau.beans.ObitosBean;
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
 * @author Jr.
 */
@ManagedBean(name = "obitoMB")
@ViewScoped
public class ObitoManagedBean implements Serializable {

    private static Logger logger = Logger.getLogger(ObitoManagedBean.class);
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
    private List<ObitosBean> listaObitos = new ArrayList<ObitosBean>();
    private ObitosBean medicos = new ObitosBean();
    private List<SelectItem> listaObitosItems = new ArrayList<SelectItem>();
    private int idObitosSelecionada;
    
    //COMBO
    private List<EspecialidadesBean> listaEspecialidade = new ArrayList<EspecialidadesBean>();
    private EspecialidadesBean especialidade = new EspecialidadesBean();
    private List<SelectItem> listaEspecialidadeItems = new ArrayList<SelectItem>();
    private int idEspetcialidadeSelecionada;
    
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

        listaObitos = Service.getInstance().listarObitos();
        
        editarRegistrosTerceiros = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_EDITAR_TERCEIROS);
        excluirRegistrosTerceiros = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_EXCLUIR_TERCEIROS);
        criarRegistro = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_CRIAR_REGISTRO);
        exibirRegistrosTerceiros = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.PESSOA_EXIBIR_TERCEIROS);

        //COMBO especialidade
        setListaEspecialidade(Service.getInstance().listaEspecialidade());
        for (EspecialidadesBean sec : listaEspecialidade){
            listaEspecialidadeItems.add(new SelectItem(sec.getPkEspecialidade(),sec.getEspecialidade()));
        }
    }

    public boolean isAdicionarState() {
        return ADICIONAR_STATE.equals(this.getCurrentState());
    }

    public boolean isEditarState() {
        return EDITAR_STATE.equals(this.getCurrentState());
    
    }
    
    public void pesquisarObitos() {
        listaObitos = Service.getInstance().pesquisarObitosCriteria(pesquisa);
   }
  
    public void prepareEditar() {
        this.setCurrentState(EDITAR_STATE);
        getListaObitos().addAll(getListaObitos());
        logger.debug("MEDICO--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + medicos);
        logger.debug("CURRENTSTATES--->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +currentState);
    }
    
     public void clear() {
        this.medicos = new ObitosBean();
    }
     
    public void prepareAdicionar() {
        logger.debug("==========Preparando==========");
        this.setCurrentState(ADICIONAR_STATE);
        medicos = new ObitosBean();
        logger.debug("MEDICO--->>>>>>>>>>>>>>>>>>" + medicos);
    
//        this.clear();
//        this.setCurrentState(ADICIONAR_STATE);
//        
        logger.debug("MEDICO--->>>>>>>>>>>>>>>>>>" + medicos);
        logger.debug("CURRENTSTATES--->>>>>>>>>>>" +currentState);
    }

    public void gravar() {
        logger.debug("===========gravando...============");
        //this.setCurrentState(ADICIONAR_STATE);
        if (ADICIONAR_STATE.equals(this.currentState)) {
            logger.debug("========ADICIONAR REGISTRO"+currentState);
            Service.getInstance().cadastrarObitos(medicos);
            logger.debug("========OBITOS ADD"+medicos);
            Service.getInstance().atualizarObitos(medicos);
            logger.debug("========OBITOS UPDATE"+medicos);
            AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "CADASTRO DE OBITO", "CADASTRO DE OBITO", "CADASTRO DE OBITO");

        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("========EDITAR REGISTRO");
            Service.getInstance().atualizarObitos(getObitos());
            AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "EDITAR OBITO", "EDITAR OBITO", "EDITAR OBITO");

        }
        this.pesquisarObitos();
    }
    public void excluir() {
        logger.debug("EXCLUIR REGISTRO");
        Service.getInstance().excluirObitos(getObitos());
        this.pesquisarObitos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("teste"));
                    AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "APGAR OBITO", "APAGAR OBITO", "APAGAR OBITO");
    }

    public StreamedContent downloadReportPdf() {
        logger.debug("GERAR RELATORIO PDF");
        InputStream stream = ReportService.getInstance().emiteRelatorioObitos(getListaObitos(), ReportService.FORMATO_PDF);
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

    public List<ObitosBean> getListaObitos() {
        return listaObitos;
    }

    public void setListaObitos(List<ObitosBean> listaObitos) {
        this.listaObitos = listaObitos;
    }

    public ObitosBean getObitos() {
        return medicos;
    }

    public void setObitos(ObitosBean medicos) {
        this.medicos = medicos;
    }

    public List<SelectItem> getListaObitosItems() {
        return listaObitosItems;
    }

    public void setListaObitosItems(List<SelectItem> listaObitosItems) {
        this.listaObitosItems = listaObitosItems;
    }

    public int getIdObitosSelecionada() {
        return idObitosSelecionada;
    }

    public void setIdObitosSelecionada(int idObitosSelecionada) {
        this.idObitosSelecionada = idObitosSelecionada;
    }

     public void setListaEspecialidade(List<EspecialidadesBean> listaEspecialidade) {
        this.listaEspecialidade = listaEspecialidade;
    }

    public List<SelectItem> getListaEspecialidadeItems() {
        return listaEspecialidadeItems;
    }

    public void setListaEspecialidadeItems(List<SelectItem> listaEspecialidadeItems) {
        this.listaEspecialidadeItems = listaEspecialidadeItems;
    }

    public EspecialidadesBean getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadesBean especialidade) {
        this.especialidade = especialidade;
    }

    public int getIdEspetcialidadeSelecionada() {
        return idEspetcialidadeSelecionada;
    }

    public void setIdEspetcialidadeSelecionada(int idEspetcialidadeSelecionada) {
        this.idEspetcialidadeSelecionada = idEspetcialidadeSelecionada;
    }
    
//    public void especSelec(){
//        logger.debug("Validando combo.....");
//        EspecialidadesBean espec = new EspecialidadesBean();
//        espec.setPkEspecialidade(idEspetcialidadeSelecionada);
//        if(listaEspecialidade.contains(espec)){
//            int index = listaEspecialidade.indexOf(espec);
//            especialidade = listaEspecialidade.get(index);
//            medicos.setFkEspecialidade(especialidade);
//        }
//    }
}
