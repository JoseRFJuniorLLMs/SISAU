/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.sisau.security.jsf;

import br.app.sisau.jsf.UserSessionManagedBean;
import br.app.sisau.security.RbacConstantes;
import br.app.sisau.security.beans.ObjetoProtegidoBean;
import br.app.sisau.security.service.SecurityService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
@ManagedBean(name = "objetoProtegidoMB")
@ViewScoped
public class ObjetoProtegidoManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(ObjetoProtegidoManagedBean.class);
    private List<ObjetoProtegidoBean> listaObjetos = new ArrayList<ObjetoProtegidoBean>();
    private ObjetoProtegidoBean objetoProtegido = new ObjetoProtegidoBean();
    private String pesquisa;
    public static final String ADICIONAR_STATE = "adicionar";
    public static final String EDITAR_STATE = "editar";
    private String currentState = ADICIONAR_STATE;
    @ManagedProperty(value = "#{userSessionMB}")
    private UserSessionManagedBean userSessionMB;
    private boolean criarEditarExcluirObjeto = true;
    private String imagemEditar = "/images/icons/edit_user.png";
    private String imagemDeletar = "/images/icons/editdelete.png";
    private String imagemAdicionar = "/images/icons/apply.png";

    @PostConstruct
    public void postConstruct() {
        criarEditarExcluirObjeto = SecurityService.getInstance().verificarPermissao(userSessionMB.getLoggedUser(), RbacConstantes.CRIA_EDITA_OBJETOS_PROTEGIDOS);
        listaObjetos = SecurityService.getInstance().listarObjetosProtegidos();
    }

    public String getImagemAdicionar() {
        if (criarEditarExcluirObjeto) {
            imagemAdicionar = "/images/icons/apply.png";
        } else {
            imagemAdicionar = "/images/icons/applybw.png";
        }
        return imagemAdicionar;
    }

    public void setImagemAdicionar(String imagemAdicionar) {
        this.imagemAdicionar = imagemAdicionar;
    }

    public String getImagemDeletar() {
        if (criarEditarExcluirObjeto) {
            imagemDeletar = "/images/icons/editdelete.png";
        } else {
            imagemDeletar = "/images/icons/editdeletebw.png";
        }
        return imagemDeletar;
    }

    public void setImagemDeletar(String imagemDeletar) {
        this.imagemDeletar = imagemDeletar;
    }

    public String getImagemEditar() {
        if (criarEditarExcluirObjeto) {
            imagemEditar = "/images/icons/edit_user.png";
        } else {
            imagemEditar = "/images/icons/edit_userbw.png";
        }
        return imagemEditar;
    }

    public void setImagemEditar(String imagemEditar) {
        this.imagemEditar = imagemEditar;
    }

    public boolean isCriarEditarExcluirObjeto() {
        return criarEditarExcluirObjeto;
    }

    public void setCriarEditarExcluirObjeto(boolean criarEditarExcluirObjeto) {
        this.criarEditarExcluirObjeto = criarEditarExcluirObjeto;
    }

    public UserSessionManagedBean getUserSessionMB() {
        return userSessionMB;
    }

    public void setUserSessionMB(UserSessionManagedBean userSessionMB) {
        this.userSessionMB = userSessionMB;
    }

    public List<ObjetoProtegidoBean> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(List<ObjetoProtegidoBean> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public ObjetoProtegidoBean getObjetoProtegido() {
        return objetoProtegido;
    }

    public void setObjetoProtegido(ObjetoProtegidoBean objetoProtegido) {
        this.objetoProtegido = objetoProtegido;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public void prepareAdicionar() {
        this.setCurrentState(this.ADICIONAR_STATE);
        this.objetoProtegido = new ObjetoProtegidoBean();
    }

    public void prepareEditar() {
        this.setCurrentState(this.EDITAR_STATE);
    }

    public void pesquisarObjetosProtegidos() {
        listaObjetos = SecurityService.getInstance().pesquisarObjetosProtegidos(pesquisa);
    }

    public void gravar() {
        if (ADICIONAR_STATE.equals(this.currentState)) {
            logger.debug("ADICIONAR REGISTRO");
            SecurityService.getInstance().cadastraObjetoProtegido(objetoProtegido);
        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("EDITAR REGISTRO");
            SecurityService.getInstance().atualizarObjetoProtegido(objetoProtegido);
        }
        listaObjetos = SecurityService.getInstance().listarObjetosProtegidos();
    }

    public void excluir() {
        logger.debug("EXCLUIR REGISTRO");
        SecurityService.getInstance().excluirObjetoProtegido(objetoProtegido);
        this.pesquisarObjetosProtegidos();
    }
}
