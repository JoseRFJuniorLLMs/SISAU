package br.app.sisau.jsf;

import br.app.sisau.beans.PostoBean;
import br.app.sisau.service.Service;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;
import org.apache.log4j.Logger;

/**
 *
 * @author Paulo
 */
@ManagedBean(name = "postoMB")
@ViewScoped
public class PostoManagedBean implements Serializable{
    
    private PostoBean posto;
    private PostoBean postoApagar;
    private String txtFiltroPesquisa;
    String mensagemProcessamento = "";
    private List<PostoBean> postos;
    FacesMessage.Severity tipoMensagem = FacesMessage.SEVERITY_INFO;
    private static Logger logger = Logger.getLogger(PostoManagedBean.class);

    //Construtor
    public PostoManagedBean(){
        posto = new PostoBean();
        this.postos = Service.getInstance().listaPostosTodos();
    }
    
    //Pesquisar Produto
    public void pesquisarPosto(){
        this.postos = Service.getInstance().pesquisaPosto(txtFiltroPesquisa);
    }

    //Gravação do Posto
    public void gravar(ActionListener action){
        try{
            Service.getInstance().cadastrarPosto(this.posto);
            tipoMensagem = FacesMessage.SEVERITY_INFO;
            mensagemProcessamento = "Posto Gravado Com sucesso";
            this.posto = new PostoBean();
            listarPostos();
        }catch(Exception e){
            tipoMensagem = FacesMessage.SEVERITY_ERROR;
            mensagemProcessamento = "Erro na Gravação do Posto: \n" + e.getMessage();
        }finally{
            FacesMessage message = new FacesMessage(tipoMensagem, mensagemProcessamento, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            mensagemProcessamento = "";
        }
    }

    //Lista todos os postos
    public void listarPostos(){
        this.postos = Service.getInstance().listaPostosTodos();
    }
    
    //Limpar posto(remover instancia)
    public void limparPosto(ActionListener action){
        this.posto = new PostoBean();
    }
    
    //Excluir o posto
    public void excluir(){
        try{
            Service.getInstance().excluirPosto(postoApagar);
            mensagemProcessamento = "Posto excluído com sucesso!";
            listarPostos();
            tipoMensagem = FacesMessage.SEVERITY_INFO;
        }catch(Exception e){
            mensagemProcessamento = "Erro ao excluir o posto: \n" + e.getMessage();
            tipoMensagem = FacesMessage.SEVERITY_ERROR;
        }finally{
            FacesMessage message = new FacesMessage(tipoMensagem, mensagemProcessamento, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            mensagemProcessamento = "";            
        }
    }
    
    //Get e Set
    public String getTxtFiltroPesquisa() {
        return txtFiltroPesquisa;
    }
    public void setTxtFiltroPesquisa(String txtFiltroPesquisa) {
        this.txtFiltroPesquisa = txtFiltroPesquisa;
    }
    public static Logger getLogger() {
        return logger;
    }
    public static void setLogger(Logger logger) {
        PostoManagedBean.logger = logger;
    }
    public PostoBean getPosto() {
        return posto;
    }
    public void setPosto(PostoBean posto) {
        this.posto = posto;
    }
    public PostoBean getPostoApagar() {
        return postoApagar;
    }
    public void setPostoApagar(PostoBean postoApagar) {
        this.postoApagar = postoApagar;
    }
    public List<PostoBean> getPostos() {
        return postos;
    }
    public void setPostos(List<PostoBean> postos) {
        this.postos = postos;
    }
    
}
