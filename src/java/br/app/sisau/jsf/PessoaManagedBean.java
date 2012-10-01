package br.app.sisau.jsf;

import br.app.sisau.beans.NascimentosBean;
import br.app.sisau.beans.PessoaBean;
import br.app.sisau.security.beans.PapelBean;
import br.app.sisau.security.service.SecurityService;
import br.app.sisau.service.AuditoriaService;
import br.app.sisau.service.ReportService;
import br.app.sisau.service.Service;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Herick
 */
@ManagedBean(name = "pessoaMB")
@ViewScoped
public class PessoaManagedBean implements Serializable {
    
    private static Logger logger = Logger.getLogger(PessoaManagedBean.class);
    private String pesquisa;
    
    private static String ADICIONAR_STATE = "adicionar";
    private static String EDITAR_STATE = "editar";
    private String currentState = ADICIONAR_STATE;

    //COMBO
    private DualListModel<PapelBean> listaGrupos = new DualListModel<PapelBean>();
    private List<PessoaBean> listaPessoas = new ArrayList<PessoaBean>();
    private PessoaBean pessoa = new PessoaBean();
    private List<SelectItem> listaPessoasItems = new ArrayList<SelectItem>();
    private int idPessoaSelecionada;
    
    //COMBO
    //private DualListModel<NascimentosBean> listaNascimentos = new DualListModel<NascimentosBean>();
    private List<NascimentosBean> listaNascimentos = new ArrayList<NascimentosBean>();
    private NascimentosBean nascimentos = new NascimentosBean();
    private List<SelectItem> listaNascimentosItems = new ArrayList<SelectItem>();
    private int idNascimentoSelecionada;
   
    //COMBO
    private List<PapelBean> listaPapeis = new ArrayList<PapelBean>();
    private PapelBean papel = new PapelBean();
    private List<SelectItem> listaPapeisItems = new ArrayList<SelectItem>();
    private int idPapelSelecionada;
    
    private boolean excluirRegistrosTerceiros;
    private boolean editarRegistrosTerceiros;
    private boolean criarRegistro;
    private boolean exibirRegistrosTerceiros=true;
    
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
          listaPessoas = Service.getInstance().listarPessoas();
        //COMBO
        setListaPapeis(SecurityService.getInstance().listarPapeis());
        for (PapelBean sec : getListaPapeis()) {
            this.getListaPapeisItems().add(new SelectItem(sec.getId(), sec.getNome()));
        }
    }
        //COMBO
    public void PessoaSelecionada(){
        PessoaBean secSelecionado = new PessoaBean();
        secSelecionado.setIdPessoa(getIdPessoaSelecionada());
        setPessoa(null);
        if (getListaPessoas().contains(secSelecionado)) {           
            int idx = getListaPessoas().indexOf(secSelecionado);
            setPessoa(getListaPessoas().get(idx));
        }
    }

    
    public void pesquisarPessoas() {
        listaPessoas = Service.getInstance().pesquisarPessoaCriteria(pesquisa);
    }
    public void prepareAdicionar() {
        this.setCurrentState(this.ADICIONAR_STATE);
        this.pessoa = new PessoaBean();
        List<PapelBean> listaTodosPapeis = new ArrayList<PapelBean>();
        listaTodosPapeis = SecurityService.getInstance().listarPapeis();
        listaGrupos = new DualListModel<PapelBean>(listaTodosPapeis, new ArrayList<PapelBean>());
     
    }

    public void prepareEditar() {
        this.setCurrentState(this.EDITAR_STATE);
        List<PapelBean> listaTodosPapeis = new ArrayList<PapelBean>();
        listaTodosPapeis = SecurityService.getInstance().listarPapeis();
        listaTodosPapeis.removeAll(pessoa.getPapeis());
        List<PapelBean> listaPapeisPessoa = new ArrayList<PapelBean>();
        listaPapeisPessoa.addAll(pessoa.getPapeis());
        listaGrupos = new DualListModel<PapelBean>(listaTodosPapeis, listaPapeisPessoa);
    }

    public String detalharPessoa(PessoaBean pessoa) {
        userSessionMB.setInterPageParameter(pessoa);
        return "infoPessoa?faces-redirect=true";
    }

     public void gravar() {
        if (ADICIONAR_STATE.equals(this.currentState)) {
            logger.debug("ADICIONAR REGISTRO");

            String senha = Service.getInstance().getRandomPassword(8);
            pessoa.setPassword(Service.getInstance().encryptPassword(senha));
            Service.getInstance().cadastrarPessoa(pessoa);
            pessoa.getPapeis().addAll(listaGrupos.getTarget());
            Service.getInstance().atualizarPessoa(pessoa);
            AuditoriaService.getInstance().gravarAcaoUsuario(userSessionMB.getLoggedUser(), "teste1", "teste2", "teste3");
        } else if (EDITAR_STATE.equals(this.currentState)) {
            logger.debug("EDITAR REGISTRO");
            pessoa.getPapeis().removeAll(listaGrupos.getSource());
            listaGrupos.getTarget().removeAll(pessoa.getPapeis());
            pessoa.getPapeis().addAll(listaGrupos.getTarget());
            Service.getInstance().atualizarPessoa(pessoa);
        }
        this.pesquisarPessoas();
    }

    public void excluir() {
        getLogger().debug("EXCLUIR REGISTRO");
        Service.getInstance().excluirPessoa(getPessoa());
        this.pesquisarPessoas();
    }

    public StreamedContent downloadReportPdf() {
        getLogger().debug("GERAR RELATORIO PDF");
        InputStream stream = ReportService.getInstance().emiteRelatorioPessoas(getListaPessoas(), ReportService.FORMATO_PDF);
        StreamedContent file = new DefaultStreamedContent(stream, "application/pdf", "report.pdf");
        return file;
    }

    public StreamedContent downloadReportXls() {
        getLogger().debug("GERAR RELATORIO XLS");
        InputStream stream = ReportService.getInstance().emiteRelatorioPessoas(getListaPessoas(), ReportService.FORMATO_XLS);
        StreamedContent file = new DefaultStreamedContent(stream, "application/vnd.ms-excel", "report.xls");
        return file;
    }
 
    private void pesquisarUsuarios() {
        getLogger().debug("PESQUISAR");
        this.setPessoa(new PessoaBean());
        if (isExibirRegistrosTerceiros()){
            this.setListaPessoas(Service.getInstance().pesquisarPessoas(getPesquisa()));
        }else{
            this.getListaPessoas().clear();
            this.getListaPessoas().add(Service.getInstance().pesquisarPessoa(getUserSessionMB().getLoggedUser()));
        }
     }

    public static String getADICIONAR_STATE() {
        return ADICIONAR_STATE;
    }

    public static void setADICIONAR_STATE(String ADICIONAR_STATE) {
        PessoaManagedBean.ADICIONAR_STATE = ADICIONAR_STATE;
    }

    public static String getEDITAR_STATE() {
        return EDITAR_STATE;
    }

    public static void setEDITAR_STATE(String EDITAR_STATE) {
        PessoaManagedBean.EDITAR_STATE = EDITAR_STATE;
    }

    public boolean isCriarRegistro() {
        return criarRegistro;
    }

    public void setCriarRegistro(boolean criarRegistro) {
        this.criarRegistro = criarRegistro;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public boolean isEditarRegistrosTerceiros() {
        return editarRegistrosTerceiros;
    }

    public void setEditarRegistrosTerceiros(boolean editarRegistrosTerceiros) {
        this.editarRegistrosTerceiros = editarRegistrosTerceiros;
    }

    public boolean isExcluirRegistrosTerceiros() {
        return excluirRegistrosTerceiros;
    }

    public void setExcluirRegistrosTerceiros(boolean excluirRegistrosTerceiros) {
        this.excluirRegistrosTerceiros = excluirRegistrosTerceiros;
    }

    public boolean isExibirRegistrosTerceiros() {
        return exibirRegistrosTerceiros;
    }

    public void setExibirRegistrosTerceiros(boolean exibirRegistrosTerceiros) {
        this.exibirRegistrosTerceiros = exibirRegistrosTerceiros;
    }

    public int getIdPapelSelecionada() {
        return idPapelSelecionada;
    }

    public void setIdPapelSelecionada(int idPapelSelecionada) {
        this.idPapelSelecionada = idPapelSelecionada;
    }

    public int getIdPessoaSelecionada() {
        return idPessoaSelecionada;
    }

    public void setIdPessoaSelecionada(int idPessoaSelecionada) {
        this.idPessoaSelecionada = idPessoaSelecionada;
    }

    public List<PapelBean> getListaPapeis() {
        return listaPapeis;
    }

    public void setListaPapeis(List<PapelBean> listaPapeis) {
        this.listaPapeis = listaPapeis;
    }

    public List<SelectItem> getListaPapeisItems() {
        return listaPapeisItems;
    }

    public void setListaPapeisItems(List<SelectItem> listaPapeisItems) {
        this.listaPapeisItems = listaPapeisItems;
    }

    public List<SelectItem> getListaPessoasItems() {
        return listaPessoasItems;
    }

    public void setListaPessoasItems(List<SelectItem> listaPessoasItems) {
        this.listaPessoasItems = listaPessoasItems;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PessoaManagedBean.logger = logger;
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

    public PessoaBean getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaBean pessoa) {
        this.pessoa = pessoa;
    }
    public List<PessoaBean> getListaPessoas() {
        return listaPessoas;
}
    public DualListModel<PapelBean> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(DualListModel<PapelBean> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }
    
    public int getIdNascimentoSelecionada() {
        return idNascimentoSelecionada;
    }

    public void setIdNascimentoSelecionada(int idNascimentoSelecionada) {
        this.idNascimentoSelecionada = idNascimentoSelecionada;
    }

    public List<NascimentosBean> getListaNascimentos() {
        return listaNascimentos;
    }

    public void setListaNascimentos(List<NascimentosBean> listaNascimentos) {
        this.listaNascimentos = listaNascimentos;
    }

    public List<SelectItem> getListaNascimentosItems() {
        return listaNascimentosItems;
    }

    public void setListaNascimentosItems(List<SelectItem> listaNascimentosItems) {
        this.listaNascimentosItems = listaNascimentosItems;
    }

    public NascimentosBean getNascimentos() {
        return nascimentos;
    }

    public void setNascimentos(NascimentosBean nascimentos) {
        this.nascimentos = nascimentos;
    }

    public void setListaPessoas(List<PessoaBean> listaPessoas) {
        this.setListaPessoas(listaPessoas);
    }
}       
    
 
