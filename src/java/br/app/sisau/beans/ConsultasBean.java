package br.app.sisau.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "consultas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultasBean.findAll", query = "SELECT c FROM ConsultasBean c"),
    @NamedQuery(name = "ConsultasBean.findByPkConsultas", query = "SELECT c FROM ConsultasBean c WHERE c.pkConsultas = :pkConsultas"),
    @NamedQuery(name = "ConsultasBean.findByDataMarcacao", query = "SELECT c FROM ConsultasBean c WHERE c.dataMarcacao = :dataMarcacao"),
    @NamedQuery(name = "ConsultasBean.findByDataConsulta", query = "SELECT c FROM ConsultasBean c WHERE c.dataConsulta = :dataConsulta")})
public class ConsultasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_consultas", nullable = false)
    private Long pkConsultas;
    @Column(name = "data_marcacao")
    @Temporal(TemporalType.DATE)
    private Date dataMarcacao;
    @Column(name = "data_consulta")
    @Temporal(TemporalType.DATE)
    private Date dataConsulta;
    @OneToMany(mappedBy = "fkConsultas")
    private List<PessoasConsultasBean> pessoasConsultasList;
    @JoinColumn(name = "fk_posto_de_saude", referencedColumnName = "pk_posto")
    @ManyToOne
    private PostoBean fkPostoDeSaude;
    @JoinColumn(name = "fk_pessoas", referencedColumnName = "id_pessoa")
    @ManyToOne
    private PessoaBean fkPessoas;

    public ConsultasBean() {
    }

    public ConsultasBean(Long pkConsultas) {
        this.pkConsultas = pkConsultas;
    }

    public Long getPkConsultas() {
        return pkConsultas;
    }

    public void setPkConsultas(Long pkConsultas) {
        this.pkConsultas = pkConsultas;
    }

    public Date getDataMarcacao() {
        return dataMarcacao;
    }

    public void setDataMarcacao(Date dataMarcacao) {
        this.dataMarcacao = dataMarcacao;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    @XmlTransient
    public List<PessoasConsultasBean> getPessoasConsultasList() {
        return pessoasConsultasList;
    }

    public void setPessoasConsultasList(List<PessoasConsultasBean> pessoasConsultasList) {
        this.pessoasConsultasList = pessoasConsultasList;
    }

    public PostoBean getFkPostoDeSaude() {
        return fkPostoDeSaude;
    }

    public void setFkPostoDeSaude(PostoBean fkPostoDeSaude) {
        this.fkPostoDeSaude = fkPostoDeSaude;
    }

    public PessoaBean getFkPessoas() {
        return fkPessoas;
    }

    public void setFkPessoas(PessoaBean fkPessoas) {
        this.fkPessoas = fkPessoas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkConsultas != null ? pkConsultas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultasBean)) {
            return false;
        }
        ConsultasBean other = (ConsultasBean) object;
        if ((this.pkConsultas == null && other.pkConsultas != null) || (this.pkConsultas != null && !this.pkConsultas.equals(other.pkConsultas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkConsultas;
    }
    
}
