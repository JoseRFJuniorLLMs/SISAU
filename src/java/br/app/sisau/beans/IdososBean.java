package br.app.sisau.beans;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "idosos", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdososBean.findAll", query = "SELECT i FROM IdososBean i"),
    @NamedQuery(name = "IdososBean.findByPkIdosos", query = "SELECT i FROM IdososBean i WHERE i.pkIdosos = :pkIdosos"),
    @NamedQuery(name = "IdososBean.findByPeso", query = "SELECT i FROM IdososBean i WHERE i.peso = :peso"),
    @NamedQuery(name = "IdososBean.findByDataNascimento", query = "SELECT i FROM IdososBean i WHERE i.dataNascimento = :dataNascimento")})
public class IdososBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_idosos", nullable = false)
    private Long pkIdosos;
    @Size(max = 50)
    @Column(name = "peso", length = 50)
    private String peso;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @OneToMany(mappedBy = "fkIdosos")
    private List<PessoaTipoBean> pessoaTipoList;
    @OneToMany(mappedBy = "fkIdosos")
    private List<IdososDoencasBean> idososDoencasList;
    @OneToMany(mappedBy = "fkIdosos")
    private List<PessoasConsultasBean> pessoasConsultasList;
    @OneToMany(mappedBy = "fkIdosos")
    private List<IdososVitaminasBean> idososVitaminasList;
    @OneToMany(mappedBy = "fkIdosos")
    private List<IdososVacinaBean> idososVacinaList;

    public IdososBean() {
    }

    public IdososBean(Long pkIdosos) {
        this.pkIdosos = pkIdosos;
    }

    public Long getPkIdosos() {
        return pkIdosos;
    }

    public void setPkIdosos(Long pkIdosos) {
        this.pkIdosos = pkIdosos;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @XmlTransient
    public List<PessoaTipoBean> getPessoaTipoList() {
        return pessoaTipoList;
    }

    public void setPessoaTipoList(List<PessoaTipoBean> pessoaTipoList) {
        this.pessoaTipoList = pessoaTipoList;
    }

    @XmlTransient
    public List<IdososDoencasBean> getIdososDoencasList() {
        return idososDoencasList;
    }

    public void setIdososDoencasList(List<IdososDoencasBean> idososDoencasList) {
        this.idososDoencasList = idososDoencasList;
    }

    @XmlTransient
    public List<PessoasConsultasBean> getPessoasConsultasList() {
        return pessoasConsultasList;
    }

    public void setPessoasConsultasList(List<PessoasConsultasBean> pessoasConsultasList) {
        this.pessoasConsultasList = pessoasConsultasList;
    }

    @XmlTransient
    public List<IdososVitaminasBean> getIdososVitaminasList() {
        return idososVitaminasList;
    }

    public void setIdososVitaminasList(List<IdososVitaminasBean> idososVitaminasList) {
        this.idososVitaminasList = idososVitaminasList;
    }

    @XmlTransient
    public List<IdososVacinaBean> getIdososVacinaList() {
        return idososVacinaList;
    }

    public void setIdososVacinaList(List<IdososVacinaBean> idososVacinaList) {
        this.idososVacinaList = idososVacinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdosos != null ? pkIdosos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdososBean)) {
            return false;
        }
        IdososBean other = (IdososBean) object;
        if ((this.pkIdosos == null && other.pkIdosos != null) || (this.pkIdosos != null && !this.pkIdosos.equals(other.pkIdosos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + pkIdosos;
    }
    
}
