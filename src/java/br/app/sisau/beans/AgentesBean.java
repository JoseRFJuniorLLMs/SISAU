package br.app.sisau.beans;

import java.io.Serializable;
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
@Table(name = "agentes", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AgentesBean.findAll", query = "SELECT a FROM AgentesBean a"),
    @NamedQuery(name = "AgentesBean.findByPkAgentes", query = "SELECT a FROM AgentesBean a WHERE a.pkAgentes = :pkAgentes"),
    @NamedQuery(name = "AgentesBean.findByMatricula", query = "SELECT a FROM AgentesBean a WHERE a.matricula = :matricula")})
public class AgentesBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_agentes", nullable = false)
    private Long pkAgentes;
    @Size(max = 50)
    @Column(name = "matricula", length = 50)
    private String matricula;
    @OneToMany(mappedBy = "fkAgentes")
    private List<PessoaTipoBean> pessoaTipoList;
    @OneToMany(mappedBy = "fkAgentes")
    private List<PessoasConsultasBean> pessoasConsultasList;

    public AgentesBean() {
    }

    public AgentesBean(Long pkAgentes) {
        this.pkAgentes = pkAgentes;
    }

    public Long getPkAgentes() {
        return pkAgentes;
    }

    public void setPkAgentes(Long pkAgentes) {
        this.pkAgentes = pkAgentes;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @XmlTransient
    public List<PessoaTipoBean> getPessoaTipoList() {
        return pessoaTipoList;
    }

    public void setPessoaTipoList(List<PessoaTipoBean> pessoaTipoList) {
        this.pessoaTipoList = pessoaTipoList;
    }

    @XmlTransient
    public List<PessoasConsultasBean> getPessoasConsultasList() {
        return pessoasConsultasList;
    }

    public void setPessoasConsultasList(List<PessoasConsultasBean> pessoasConsultasList) {
        this.pessoasConsultasList = pessoasConsultasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkAgentes != null ? pkAgentes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgentesBean)) {
            return false;
        }
        AgentesBean other = (AgentesBean) object;
        if ((this.pkAgentes == null && other.pkAgentes != null) || (this.pkAgentes != null && !this.pkAgentes.equals(other.pkAgentes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + pkAgentes;
    }
    
}
