package br.app.sisau.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "pessoas_consultas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PessoasConsultasBean.findAll", query = "SELECT p FROM PessoasConsultasBean p"),
    @NamedQuery(name = "PessoasConsultasBean.findByPkPessoasConsultas", query = "SELECT p FROM PessoasConsultasBean p WHERE p.pkPessoasConsultas = :pkPessoasConsultas"),
    @NamedQuery(name = "PessoasConsultasBean.findByStatus", query = "SELECT p FROM PessoasConsultasBean p WHERE p.status = :status"),
    @NamedQuery(name = "PessoasConsultasBean.findByDataMarcada", query = "SELECT p FROM PessoasConsultasBean p WHERE p.dataMarcada = :dataMarcada"),
    @NamedQuery(name = "PessoasConsultasBean.findByDataConsulta", query = "SELECT p FROM PessoasConsultasBean p WHERE p.dataConsulta = :dataConsulta")})
public class PessoasConsultasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_pessoas_consultas", nullable = false)
    private Long pkPessoasConsultas;
    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;
    @Column(name = "data_marcada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMarcada;
    @Column(name = "data_consulta")
    private BigInteger dataConsulta;
    @JoinColumn(name = "fk_pessoa_logada", referencedColumnName = "id_pessoa")
    @ManyToOne
    private PessoaBean fkPessoaLogada;
    @JoinColumn(name = "fk_parceiros", referencedColumnName = "pk_parceiros")
    @ManyToOne
    private ParceirosBean fkParceiros;
    @JoinColumn(name = "fk_idosos", referencedColumnName = "pk_idosos")
    @ManyToOne
    private IdososBean fkIdosos;
    @JoinColumn(name = "fk_gestantes", referencedColumnName = "pk_gestante")
    @ManyToOne
    private GestantesBean fkGestantes;
    @JoinColumn(name = "fk_consultas", referencedColumnName = "pk_consultas")
    @ManyToOne
    private ConsultasBean fkConsultas;
    @JoinColumn(name = "fk_agentes", referencedColumnName = "pk_agentes")
    @ManyToOne
    private AgentesBean fkAgentes;

    public PessoasConsultasBean() {
    }

    public PessoasConsultasBean(Long pkPessoasConsultas) {
        this.pkPessoasConsultas = pkPessoasConsultas;
    }

    public Long getPkPessoasConsultas() {
        return pkPessoasConsultas;
    }

    public void setPkPessoasConsultas(Long pkPessoasConsultas) {
        this.pkPessoasConsultas = pkPessoasConsultas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataMarcada() {
        return dataMarcada;
    }

    public void setDataMarcada(Date dataMarcada) {
        this.dataMarcada = dataMarcada;
    }

    public BigInteger getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(BigInteger dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public PessoaBean getFkPessoaLogada() {
        return fkPessoaLogada;
    }

    public void setFkPessoaLogada(PessoaBean fkPessoaLogada) {
        this.fkPessoaLogada = fkPessoaLogada;
    }

    public ParceirosBean getFkParceiros() {
        return fkParceiros;
    }

    public void setFkParceiros(ParceirosBean fkParceiros) {
        this.fkParceiros = fkParceiros;
    }

    public IdososBean getFkIdosos() {
        return fkIdosos;
    }

    public void setFkIdosos(IdososBean fkIdosos) {
        this.fkIdosos = fkIdosos;
    }

    public GestantesBean getFkGestantes() {
        return fkGestantes;
    }

    public void setFkGestantes(GestantesBean fkGestantes) {
        this.fkGestantes = fkGestantes;
    }

    public ConsultasBean getFkConsultas() {
        return fkConsultas;
    }

    public void setFkConsultas(ConsultasBean fkConsultas) {
        this.fkConsultas = fkConsultas;
    }

    public AgentesBean getFkAgentes() {
        return fkAgentes;
    }

    public void setFkAgentes(AgentesBean fkAgentes) {
        this.fkAgentes = fkAgentes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPessoasConsultas != null ? pkPessoasConsultas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoasConsultasBean)) {
            return false;
        }
        PessoasConsultasBean other = (PessoasConsultasBean) object;
        if ((this.pkPessoasConsultas == null && other.pkPessoasConsultas != null) || (this.pkPessoasConsultas != null && !this.pkPessoasConsultas.equals(other.pkPessoasConsultas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkPessoasConsultas;
    }
    
}
