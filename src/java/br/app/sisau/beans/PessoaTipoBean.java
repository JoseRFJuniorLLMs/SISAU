package br.app.sisau.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "pessoa_tipo", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PessoaTipoBean.findAll", query = "SELECT p FROM PessoaTipoBean p"),
    @NamedQuery(name = "PessoaTipoBean.findByPkPessoaTipo", query = "SELECT p FROM PessoaTipoBean p WHERE p.pkPessoaTipo = :pkPessoaTipo"),
    @NamedQuery(name = "PessoaTipoBean.findByTipo", query = "SELECT p FROM PessoaTipoBean p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PessoaTipoBean.findByFkPessoa", query = "SELECT p FROM PessoaTipoBean p WHERE p.fkPessoa = :fkPessoa")})
public class PessoaTipoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_pessoa_tipo", nullable = false)
    private Long pkPessoaTipo;
    @Size(max = 50)
    @Column(name = "tipo", length = 50)
    private String tipo;
    @Column(name = "fk_pessoa")
    private BigInteger fkPessoa;
    @JoinColumn(name = "fk_parceiros", referencedColumnName = "pk_parceiros")
    @ManyToOne
    private ParceirosBean fkParceiros;
    @JoinColumn(name = "fk_medicos", referencedColumnName = "pk_medico")
    @ManyToOne
    private MedicosBean fkMedicos;
    @JoinColumn(name = "fk_idosos", referencedColumnName = "pk_idosos")
    @ManyToOne
    private IdososBean fkIdosos;
    @JoinColumn(name = "fk_gestante", referencedColumnName = "pk_gestante")
    @ManyToOne
    private GestantesBean fkGestante;
    @JoinColumn(name = "fk_criancas", referencedColumnName = "pk_criancas")
    @ManyToOne
    private CriancasBean fkCriancas;
    @JoinColumn(name = "fk_agentes", referencedColumnName = "pk_agentes")
    @ManyToOne
    private AgentesBean fkAgentes;
    @OneToMany(mappedBy = "fkPessoaTipo")
    private List<PessoaBean> pessoaList;

    public PessoaTipoBean() {
    }

    public PessoaTipoBean(Long pkPessoaTipo) {
        this.pkPessoaTipo = pkPessoaTipo;
    }

    public Long getPkPessoaTipo() {
        return pkPessoaTipo;
    }

    public void setPkPessoaTipo(Long pkPessoaTipo) {
        this.pkPessoaTipo = pkPessoaTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigInteger getFkPessoa() {
        return fkPessoa;
    }

    public void setFkPessoa(BigInteger fkPessoa) {
        this.fkPessoa = fkPessoa;
    }

    public ParceirosBean getFkParceiros() {
        return fkParceiros;
    }

    public void setFkParceiros(ParceirosBean fkParceiros) {
        this.fkParceiros = fkParceiros;
    }

    public MedicosBean getFkMedicos() {
        return fkMedicos;
    }

    public void setFkMedicos(MedicosBean fkMedicos) {
        this.fkMedicos = fkMedicos;
    }

    public IdososBean getFkIdosos() {
        return fkIdosos;
    }

    public void setFkIdosos(IdososBean fkIdosos) {
        this.fkIdosos = fkIdosos;
    }

    public GestantesBean getFkGestante() {
        return fkGestante;
    }

    public void setFkGestante(GestantesBean fkGestante) {
        this.fkGestante = fkGestante;
    }

    public CriancasBean getFkCriancas() {
        return fkCriancas;
    }

    public void setFkCriancas(CriancasBean fkCriancas) {
        this.fkCriancas = fkCriancas;
    }

    public AgentesBean getFkAgentes() {
        return fkAgentes;
    }

    public void setFkAgentes(AgentesBean fkAgentes) {
        this.fkAgentes = fkAgentes;
    }

    @XmlTransient
    public List<PessoaBean> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<PessoaBean> pessoaList) {
        this.pessoaList = pessoaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPessoaTipo != null ? pkPessoaTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaTipoBean)) {
            return false;
        }
        PessoaTipoBean other = (PessoaTipoBean) object;
        if ((this.pkPessoaTipo == null && other.pkPessoaTipo != null) || (this.pkPessoaTipo != null && !this.pkPessoaTipo.equals(other.pkPessoaTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkPessoaTipo;
    }
    
}
