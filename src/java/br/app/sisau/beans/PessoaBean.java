package br.app.sisau.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Herick
 */
@Entity
@Table(name = "pessoa")
@NamedQueries({
    @NamedQuery(name = "PessoaBean.findAll", query = "SELECT p FROM PessoaBean p ORDER BY p.nome, p.username"),
    @NamedQuery(name = "PessoaBean.findByNome", query = "SELECT p FROM PessoaBean p WHERE p.nome = :nome"),
    @NamedQuery(name = "PessoaBean.findByUsername", query = "SELECT p FROM PessoaBean p WHERE p.username = :username"),
    @NamedQuery(name = "PessoaBean.findByPassword", query = "SELECT p FROM PessoaBean p WHERE p.password = :password"),
    @NamedQuery(name = "PessoaBean.findByEmail", query = "SELECT p FROM PessoaBean p WHERE p.email = :email")
    })
   public class PessoaBean implements Serializable {
    private static final long serialVersionUID = 1L;
  
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username", nullable = false, length = 20)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "id_pessoa", nullable = false)
//    private Integer idPessoa;
//    
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "idPessoa", sequenceName = "pessoa_id_pessoa_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idPessoa")
    @Column(name = "id_pessoa", nullable = false)
    private Integer idPessoa;
    
    
    @Size(max = 2147483647)
    @Column(name = "rg", length = 2147483647)
    private String rg;
    @Size(max = 2147483647)
    @Column(name = "cpf", length = 2147483647)
    private String cpf;
    @Size(max = 100)
    @Column(name = "endereco", length = 100)
    private String endereco;
    @Size(max = 2147483647)
    @Column(name = "sexo", length = 2147483647)
    private String sexo;
    @Size(max = 100)
    @Column(name = "bairro", length = 100)
    private String bairro;
    @Column(name = "numero")
    private Integer numero;
    @Size(max = 50)
    @Column(name = "complemento", length = 50)
    private String complemento;
    @Size(max = 50)
    @Column(name = "cep", length = 50)
    private String cep;
    @Size(max = 50)
    @Column(name = "telefone", length = 50)
    private String telefone;
    @Size(max = 50)
    @Column(name = "mae", length = 50)
    private String mae;
    @Size(max = 50)
    @Column(name = "pai", length = 50)
    private String pai;
    @Size(max = 50)
    @Column(name = "celular", length = 50)
    private String celular;
    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;
    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;
    @Size(max = 50)
    @Column(name = "ponto_referencia", length = 50)
    private String pontoReferencia;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;
    @OneToMany(mappedBy = "fkPessoaLogada")
    private List<PessoasConsultasBean> pessoasConsultasList;
    @OneToMany(mappedBy = "fkPessoa")
    private List<CriancasVacinasBean> criancasVascinasList;
    
    @OneToMany(mappedBy = "fkPessoas")
    private List<ConsultasBean> consultasList;
    @JoinColumn(name = "fk_raca", referencedColumnName = "pk_raca")
    @ManyToOne
    private RacaBean fkRaca;
    @JoinColumn(name = "fk_pessoa_tipo", referencedColumnName = "pk_pessoa_tipo")
    @ManyToOne
    private PessoaTipoBean fkPessoaTipo;
    @JoinColumn(name = "fk_obito", referencedColumnName = "pk_obitos")
    @ManyToOne
    private ObitosBean fkObito;
    @JoinColumn(name = "fk_familia", referencedColumnName = "pk_familia")
    @ManyToOne
    private FamiliasBean fkFamilia;
    @JoinColumn(name = "fk_etnia", referencedColumnName = "pk_etnia")
    @ManyToOne
    private EtniaBean fkEtnia;
    @JoinColumn(name = "fk_estado", referencedColumnName = "pk_estados")
    @ManyToOne
    private EstadoBean fkEstado;
    @JoinColumn(name = "fk_cor", referencedColumnName = "pk_cor")
    @ManyToOne
    private CorBean fkCor;
    @JoinColumn(name = "fk_cidade", referencedColumnName = "pk_cidade")
    @ManyToOne
    private CidadeBean fkCidade;
    @JoinColumn(name = "fk_area", referencedColumnName = "pk_area")
    @ManyToOne
    private AreasRiscosBean fkArea;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "pessoa_papel",
    joinColumns =

    @JoinColumn(name = "fk_pessoa", referencedColumnName = "id_pessoa"),
    inverseJoinColumns =
    @JoinColumn(name = "fk_papel", referencedColumnName = "id_papel"))
    private List<br.app.sisau.security.beans.PapelBean> papeis;

    public PessoaBean() {
    }

    public PessoaBean(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public PessoaBean(Integer idPessoa, String nome, String username, String password) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.username = username;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<PessoasConsultasBean> getPessoasConsultasList() {
        return pessoasConsultasList;
    }

    public void setPessoasConsultasList(List<PessoasConsultasBean> pessoasConsultasList) {
        this.pessoasConsultasList = pessoasConsultasList;
    }

    @XmlTransient
    public List<CriancasVacinasBean> getCriancasVascinasList() {
        return criancasVascinasList;
    }

    public void setCriancasVascinasList(List<CriancasVacinasBean> criancasVascinasList) {
        this.criancasVascinasList = criancasVascinasList;
    }
    
    @XmlTransient
    public List<ConsultasBean> getConsultasList() {
        return consultasList;
    }

    public void setConsultasList(List<ConsultasBean> consultasList) {
        this.consultasList = consultasList;
    }

    public RacaBean getFkRaca() {
        return fkRaca;
    }

    public void setFkRaca(RacaBean fkRaca) {
        this.fkRaca = fkRaca;
    }

    public PessoaTipoBean getFkPessoaTipo() {
        return fkPessoaTipo;
    }

    public void setFkPessoaTipo(PessoaTipoBean fkPessoaTipo) {
        this.fkPessoaTipo = fkPessoaTipo;
    }

    public ObitosBean getFkObito() {
        return fkObito;
    }

    public void setFkObito(ObitosBean fkObito) {
        this.fkObito = fkObito;
    }

    public FamiliasBean getFkFamilia() {
        return fkFamilia;
    }

    public void setFkFamilia(FamiliasBean fkFamilia) {
        this.fkFamilia = fkFamilia;
    }

    public EtniaBean getFkEtnia() {
        return fkEtnia;
    }

    public void setFkEtnia(EtniaBean fkEtnia) {
        this.fkEtnia = fkEtnia;
    }

    public EstadoBean getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(EstadoBean fkEstado) {
        this.fkEstado = fkEstado;
    }

    public CorBean getFkCor() {
        return fkCor;
    }

    public void setFkCor(CorBean fkCor) {
        this.fkCor = fkCor;
    }

    public CidadeBean getFkCidade() {
        return fkCidade;
    }

    public void setFkCidade(CidadeBean fkCidade) {
        this.fkCidade = fkCidade;
    }

    public AreasRiscosBean getFkArea() {
        return fkArea;
    }

    public void setFkArea(AreasRiscosBean fkArea) {
        this.fkArea = fkArea;
    }
        public List<br.app.sisau.security.beans.PapelBean> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<br.app.sisau.security.beans.PapelBean> papeis) {
        this.papeis = papeis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPessoa != null ? idPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaBean)) {
            return false;
        }
        PessoaBean other = (PessoaBean) object;
        if ((this.idPessoa == null && other.idPessoa != null) || (this.idPessoa != null && !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + idPessoa;
    }

}
