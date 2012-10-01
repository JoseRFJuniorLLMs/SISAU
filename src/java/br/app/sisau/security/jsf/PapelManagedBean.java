package br.app.sisau.security.jsf;

import br.app.sisau.jsf.UserSessionManagedBean;
import br.app.sisau.security.RbacConstantes;
import br.app.sisau.security.beans.ObjetoProtegidoBean;
import br.app.sisau.security.beans.PapelBean;
import br.app.sisau.security.service.SecurityService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Herick
 */
@ManagedBean(name = "papelMB")
@ViewScoped
public class PapelManagedBean implements Serializable {

    private static Logger logger = Logger.getLogger(PapelManagedBean.class);
    
    private List<PapelBean> listaPapeis = new ArrayList<PapelBean>();
    
    private PapelBean papel = new PapelBean();
    private String pesquisa;
    
    private static String ADICIONAR_STATE = "adicionar";
    private static String EDITAR_STATE = "editar";
    private String currentState = ADICIONAR_STATE;
    
    private DualListModel<ObjetoProtegidoBean> objetosProtegidosDisponiveis;    
    @ManagedProperty(value = "#{userSessionMB}")
    private UserSessionManagedBean userSessionMB;
    private boolean criarEditarExcluirPapel = true;
    
    private String imagemEditar = "/images/icons/edit_user.png";
    private String imagemDeletar = "/images/icons/editdelete.png";
    private String imagemAdicionar = "/images/icons/apply.png";
    
    @PostConstruct
    public void postConstruct() {
        criarEditarExcluirPapel = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.CRIA_EDITA_PAPEIS);
        listaPapeis = SecurityService.getInstance().listarPapeis();
        objetosProtegidosDisponiveis = new DualListModel<ObjetoProtegidoBean>();
    }


     public String getImagemAdicionar() {
        if (isCriarEditarExcluirPapel()) {
            imagemAdicionar = "/images/icons/apply.png";
        } else {
            imagemAdicionar = "/images/icons/applybw.png";
        }
        return imagemAdicionar;
    }


    public String getImagemDeletar() {
        if (isCriarEditarExcluirPapel()) {
            imagemDeletar = "/images/icons/editdelete.png";
        } else {
            imagemDeletar = "/images/icons/editdeletebw.png";
        }
        return imagemDeletar;
    }


    public String getImagemEditar() {
        if (isCriarEditarExcluirPapel()) {
            imagemEditar = "/images/icons/edit_user.png";
        } else {
            imagemEditar = "/images/icons/edit_userbw.png";
        }
        return imagemEditar;
    }

    public DualListModel<ObjetoProtegidoBean> getObjetosProtegidosDisponiveis() {
        return objetosProtegidosDisponiveis;
    }

    public void setObjetosProtegidosDisponiveis(DualListModel<ObjetoProtegidoBean> objetosProtegidosDisponiveis) {
        this.setObjetosProtegidosDisponiveis(objetosProtegidosDisponiveis);
    }

    public void prepareAdicionar() {
        this.setCurrentState(PapelManagedBean.getADICIONAR_STATE());
        this.setPapel(new PapelBean());
        List<ObjetoProtegidoBean> todosObjetosProtegidos = SecurityService.getInstance().listarObjetosProtegidos();
        setObjetosProtegidosDisponiveis(new DualListModel<ObjetoProtegidoBean>(todosObjetosProtegidos, getPapel().getObjetosProtegidos()));
    }

    public void prepareEditar() {
        this.setCurrentState(PapelManagedBean.getEDITAR_STATE());
        List<ObjetoProtegidoBean> todosObjetosProtegidos = SecurityService.getInstance().listarObjetosProtegidos();
        todosObjetosProtegidos.removeAll(getPapel().getObjetosProtegidos());
        setObjetosProtegidosDisponiveis(new DualListModel<ObjetoProtegidoBean>(todosObjetosProtegidos, getPapel().getObjetosProtegidos()));
    }
 
    public void pesquisarPapeis() {
        setListaPapeis(SecurityService.getInstance().pesquisarPapel(getPesquisa()));
        //listaPapeis = SecurityService.getInstance().pesquisarPapel(pesquisa);
        
    }

    public void gravar() {
        if (getADICIONAR_STATE().equals(this.getCurrentState())) {
            getLogger().debug("ADICIONAR REGISTRO");
            SecurityService.getInstance().cadastraPapel(getPapel());
            getPapel().setObjetosProtegidos(getObjetosProtegidosDisponiveis().getTarget());
            SecurityService.getInstance().atualizarPapel(getPapel());
        } else if (getEDITAR_STATE().equals(this.getCurrentState())) {
            getLogger().debug("EDITAR REGISTRO");
            getPapel().setObjetosProtegidos(getObjetosProtegidosDisponiveis().getTarget());
            SecurityService.getInstance().atualizarPapel(getPapel());
        }
        setListaPapeis(SecurityService.getInstance().listarPapeis());
    }

    public void excluir() {
        getLogger().debug("EXCLUIR REGISTRO");
        SecurityService.getInstance().excluirPapel(getPapel());
        this.pesquisarPapeis();
    }

    public static String getADICIONAR_STATE() {
        return ADICIONAR_STATE;
    }

    public static void setADICIONAR_STATE(String ADICIONAR_STATE) {
        PapelManagedBean.ADICIONAR_STATE = ADICIONAR_STATE;
    }

    public static String getEDITAR_STATE() {
        return EDITAR_STATE;
    }

    public static void setEDITAR_STATE(String EDITAR_STATE) {
        PapelManagedBean.EDITAR_STATE = EDITAR_STATE;
    }

    public boolean isCriarEditarExcluirPapel() {
        return criarEditarExcluirPapel;
    }

    public void setCriarEditarExcluirPapel(boolean criarEditarExcluirPapel) {
        this.criarEditarExcluirPapel = criarEditarExcluirPapel;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public List<PapelBean> getListaPapeis() {
        return listaPapeis;
    }

    public void setListaPapeis(List<PapelBean> listaPapeis) {
        this.listaPapeis = listaPapeis;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PapelManagedBean.logger = logger;
    }

    public PapelBean getPapel() {
        return papel;
    }

    public void setPapel(PapelBean papel) {
        this.papel = papel;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public UserSessionManagedBean getUserSessionMB() {
        return userSessionMB;
    }

    public void setUserSessionMB(UserSessionManagedBean userSessionMB) {
        this.userSessionMB = userSessionMB;
    }

  
}
