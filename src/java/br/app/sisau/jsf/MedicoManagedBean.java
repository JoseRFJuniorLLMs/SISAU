package br.app.sisau.jsf;

import br.app.sisau.beans.EspecialidadesBean;
import br.app.sisau.beans.MedicosBean;
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
import javax.faces.event.ActionListener;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Jr.
 */
@ManagedBean(name = "medicoMB")
@ViewScoped
public class MedicoManagedBean implements Serializable {

    private static Logger logger = Logger.getLogger(MedicoManagedBean.class);
    private String pesquisa;
   
    //IMAGENS
    private boolean podeEditarMedico;
    private boolean podeCriarMedico;
    private boolean excluirRegistrosTerceiros;
    private boolean editarRegistrosTerceiros;
    private boolean criarRegistro;
    private boolean exibirRegistrosTerceiros;
   
    private String imagemPassword = "/images/icons/password.png";
    private String imagemEditarRegistro = "/images/icons/editar.png";
    private String imagemExcluirRegistro = "/images/icons/delete.png";
    private String imagemCriarRegistro = "/images/icons/add1.png";
    
    private static String ADICIONAR_STATE = "adicionar";
    private static String EDITAR_STATE = "editar";
    private String currentState = ADICIONAR_STATE;
    
    //COMBO
    private List<MedicosBean> listaMedicos = new ArrayList<MedicosBean>();
    private MedicosBean medicos = new MedicosBean();
    private List<SelectItem> listaMedicosItems = new ArrayList<SelectItem>();
    private int idMedicosSelecionada;
    
    //COMBO
    private List<EspecialidadesBean> listaEspecialidade = new ArrayList<EspecialidadesBean>();
    private EspecialidadesBean especialidade = new EspecialidadesBean();
    private List<SelectItem> listaEspecialidadeItems = new ArrayList<SelectItem>();
    private int idEspetcialidadeSelecionada;
    
    //PAULO
    String mensagemProcessamento = "";
    FacesMessage.Severity tipoMensagem = FacesMessage.SEVERITY_INFO;
    
    @ManagedProperty(value = "#{userSessionMB}")
    private UserSessionManagedBean userSessionMB;

    public UserSessionManagedBean getUserSessionMB() {
        return userSessionMB;
    }
    //img
    public String getImagemCriarRegistro() {
        if (podeCriarMedico) {
            imagemCriarRegistro = "/images/icons/add1.png";
        } else {
            imagemCriarRegistro = "/images/icons/add1bw.png";
        }
        return imagemCriarRegistro;
    }

    public void setImagemCriarRegistro(String imagemCriarRegistro) {
        this.imagemCriarRegistro = imagemCriarRegistro;
    }

    public String getImagemEditarRegistro() {
        if (podeEditarMedico) {
            imagemEditarRegistro = "/images/icons/editar.png";
        } else {
            imagemEditarRegistro = "/images/icons/editarbw.png";
        }
        return imagemEditarRegistro;
    }

    public void setImagemEditarRegistro(String imagemEditarRegistro) {
        this.imagemEditarRegistro = imagemEditarRegistro;
    }

    public boolean isPodeCriarMedico() {
        return podeCriarMedico;
    }

    public void setPodeCriarMedico(boolean podeCriarMedico) {
        this.podeCriarMedico = podeCriarMedico;
    }

    public boolean isPodeEditarMedico() {
        return podeEditarMedico;
    }

    public void setPodeEditarMedico(boolean podeEditarMedico) {
        this.podeEditarMedico = podeEditarMedico;
    }

    //img

    public void setUserSessionMB(UserSessionManagedBean userSessionMB) {
        this.userSessionMB = userSessionMB;
    }

    @PostConstruct
    public void postConstruct() {

        listaMedicos = Service.getInstance().listarMedicos();
        
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
    
    public void pesquisarMedicos() {
        listaMedicos = Service.getInstance().pesquisarMedicosCriteria(pesquisa);
   }
  
    public void prepareEditar() {
        this.setCurrentState(EDITAR_STATE);
        getListaMedicos().addAll(getListaMedicos());
    }
    
     public void clear() {
        this.medicos = new MedicosBean();
    }
     
    public void prepareAdicionar() {
        logger.debug("==========Preparando==========");
        this.setCurrentState(ADICIONAR_STATE);
        medicos = new MedicosBean();
    }

    public void gravar(ActionListener action) {
        logger.debug("===========gravando...============");
        if (ADICIONAR_STATE.equals(this.currentState)) {
            try {
                
                Service.getInstance().cadastrarMedicos(medicos);
                Service.getInstance().atualizarMedicos(medicos);

                tipoMensagem = FacesMessage.SEVERITY_INFO;
                mensagemProcessamento = "Médico Cadastrado Com sucesso !\n"+"NOME.:" + getMedicos().getNome()+"\n"+"CRM.:"+ getMedicos().getCrm();
                AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "CADASTRO DE MÉDICO", "CADASTRO DE MÉDICO", "CADASTRO DE MÉDICO");

            } catch (Exception e) {
                tipoMensagem = FacesMessage.SEVERITY_ERROR;
                mensagemProcessamento = "Erro na Gravação do Médico: \n" + e.getMessage();

            } finally {
                FacesMessage message = new FacesMessage(tipoMensagem, mensagemProcessamento, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                mensagemProcessamento = "";
            }
        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("========EDITAR REGISTRO");
            Service.getInstance().atualizarMedicos(getMedicos());
            AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "EDITAR MÉDICO", "EDITAR MÉDICO", "EDITAR MÉDICO");

        }
        this.pesquisarMedicos();
    }

    public void excluir() {
      try {  
        logger.debug("EXCLUIR REGISTRO");
        
        Service.getInstance().excluirMedicos(getMedicos());
        this.pesquisarMedicos();
      
        tipoMensagem = FacesMessage.SEVERITY_INFO;
        mensagemProcessamento = "Médico Excluido Com sucesso !\n"+"NOME.:" + getMedicos().getNome()+"\n"+"CRM.:"+ getMedicos().getCrm();
        
        AuditoriaService.getInstance().gravarAcaoUsuario(getUserSessionMB().getLoggedUser(), "APGAR MÉDICO", "APAGAR MÉDICO", "APAGAR MÉDICO");
        
      } catch (Exception e) {
                tipoMensagem = FacesMessage.SEVERITY_ERROR;
                mensagemProcessamento = "Erro na Apagar o Médico: \n" + e.getMessage();
            } finally {
                FacesMessage message = new FacesMessage(tipoMensagem, mensagemProcessamento, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                mensagemProcessamento = "";
            }        
    }

    public StreamedContent downloadReportPdf() {
        logger.debug("GERAR RELATORIO PDF");
        InputStream stream = ReportService.getInstance().emiteRelatorioMedicos(getListaMedicos(), ReportService.FORMATO_PDF);
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

    public String getImagemExcluirRegistro() {
        return imagemExcluirRegistro;
    }

    public void setImagemExcluirRegistro(String imagemExcluirRegistro) {
        this.imagemExcluirRegistro = imagemExcluirRegistro;
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

    public List<MedicosBean> getListaMedicos() {
        return listaMedicos;
    }

    public void setListaMedicos(List<MedicosBean> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }

    public MedicosBean getMedicos() {
        return medicos;
    }

    public void setMedicos(MedicosBean medicos) {
        this.medicos = medicos;
    }

    public List<SelectItem> getListaMedicosItems() {
        return listaMedicosItems;
    }

    public void setListaMedicosItems(List<SelectItem> listaMedicosItems) {
        this.listaMedicosItems = listaMedicosItems;
    }

    public int getIdMedicosSelecionada() {
        return idMedicosSelecionada;
    }

    public void setIdMedicosSelecionada(int idMedicosSelecionada) {
        this.idMedicosSelecionada = idMedicosSelecionada;
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
    
    public void especSelec(){
        logger.debug("Validando combo.....");
        EspecialidadesBean espec = new EspecialidadesBean();
        espec.setPkEspecialidade(idEspetcialidadeSelecionada);
        if(listaEspecialidade.contains(espec)){
            int index = listaEspecialidade.indexOf(espec);
            especialidade = listaEspecialidade.get(index);
            medicos.setFkEspecialidade(especialidade);
        }
    }
}
