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
@Table(name = "parceiros", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParceirosBean.findAll", query = "SELECT p FROM ParceirosBean p"),
    @NamedQuery(name = "ParceirosBean.findByPkParceiros", query = "SELECT p FROM ParceirosBean p WHERE p.pkParceiros = :pkParceiros"),
    @NamedQuery(name = "ParceirosBean.findByParceiro", query = "SELECT p FROM ParceirosBean p WHERE p.parceiro = :parceiro"),
    @NamedQuery(name = "ParceirosBean.findByTipoParceiro", query = "SELECT p FROM ParceirosBean p WHERE p.tipoParceiro = :tipoParceiro")})
public class ParceirosBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "pkParceiros", sequenceName = "parceiros_pk_parceiros_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pkParceiros")
    @Column(name = "pk_parceiros", nullable = false)
    private Long pkParceiros;
    
    
    @Size(max = 50)
    @Column(name = "parceiro", length = 50)
    private String parceiro;
    @Size(max = 50)
    @Column(name = "tipo_parceiro", length = 50)
    private String tipoParceiro;
    @OneToMany(mappedBy = "fkParceiros")
    private List<PessoaTipoBean> pessoaTipoList;
    @OneToMany(mappedBy = "fkParceiros")
    private List<PessoasConsultasBean> pessoasConsultasList;

    public ParceirosBean() {
    }

    public ParceirosBean(Long pkParceiros) {
        this.pkParceiros = pkParceiros;
    }

    public Long getPkParceiros() {
        return pkParceiros;
    }

    public void setPkParceiros(Long pkParceiros) {
        this.pkParceiros = pkParceiros;
    }

    public String getParceiro() {
        return parceiro;
    }

    public void setParceiro(String parceiro) {
        this.parceiro = parceiro;
    }

    public String getTipoParceiro() {
        return tipoParceiro;
    }

    public void setTipoParceiro(String tipoParceiro) {
        this.tipoParceiro = tipoParceiro;
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
        hash += (pkParceiros != null ? pkParceiros.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParceirosBean)) {
            return false;
        }
        ParceirosBean other = (ParceirosBean) object;
        if ((this.pkParceiros == null && other.pkParceiros != null) || (this.pkParceiros != null && !this.pkParceiros.equals(other.pkParceiros))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkParceiros;
    }
    
}
