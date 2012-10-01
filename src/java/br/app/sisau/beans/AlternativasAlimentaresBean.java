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
@Table(name = "alternativas_alimentares", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlternativasAlimentaresBean.findAll", query = "SELECT a FROM AlternativasAlimentaresBean a"),
    @NamedQuery(name = "AlternativasAlimentaresBean.findByPkParceiros", query = "SELECT a FROM AlternativasAlimentaresBean a WHERE a.pkParceiros = :pkParceiros"),
    @NamedQuery(name = "AlternativasAlimentaresBean.findByNome", query = "SELECT a FROM AlternativasAlimentaresBean a WHERE a.nome = :nome")})
public class AlternativasAlimentaresBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_parceiros", nullable = false)
    private Long pkParceiros;
    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;
    @OneToMany(mappedBy = "fkAlternativasAlimentares")
    private List<FamiliasAlternativaAlimentaresBean> familiasAlternativaAlimentaresList;
    @OneToMany(mappedBy = "fkAlternativa")
    private List<FamiliasBean> familiasList;

    public AlternativasAlimentaresBean() {
    }

    public AlternativasAlimentaresBean(Long pkParceiros) {
        this.pkParceiros = pkParceiros;
    }

    public Long getPkParceiros() {
        return pkParceiros;
    }

    public void setPkParceiros(Long pkParceiros) {
        this.pkParceiros = pkParceiros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<FamiliasAlternativaAlimentaresBean> getFamiliasAlternativaAlimentaresList() {
        return familiasAlternativaAlimentaresList;
    }

    public void setFamiliasAlternativaAlimentaresList(List<FamiliasAlternativaAlimentaresBean> familiasAlternativaAlimentaresList) {
        this.familiasAlternativaAlimentaresList = familiasAlternativaAlimentaresList;
    }

    @XmlTransient
    public List<FamiliasBean> getFamiliasList() {
        return familiasList;
    }

    public void setFamiliasList(List<FamiliasBean> familiasList) {
        this.familiasList = familiasList;
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
        if (!(object instanceof AlternativasAlimentaresBean)) {
            return false;
        }
        AlternativasAlimentaresBean other = (AlternativasAlimentaresBean) object;
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
