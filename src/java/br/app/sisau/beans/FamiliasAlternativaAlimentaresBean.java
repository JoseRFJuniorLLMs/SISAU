package br.app.sisau.beans;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "familias_alternativa_alimentares", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FamiliasAlternativaAlimentaresBean.findAll", query = "SELECT f FROM FamiliasAlternativaAlimentaresBean f"),
    @NamedQuery(name = "FamiliasAlternativaAlimentaresBean.findByPkFamiliaAlternativasAlimentares", query = "SELECT f FROM FamiliasAlternativaAlimentaresBean f WHERE f.pkFamiliaAlternativasAlimentares = :pkFamiliaAlternativasAlimentares")})
public class FamiliasAlternativaAlimentaresBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_familia_alternativas_alimentares", nullable = false)
    private Long pkFamiliaAlternativasAlimentares;
    @JoinColumn(name = "fk_familias", referencedColumnName = "pk_familia")
    @ManyToOne
    private FamiliasBean fkFamilias;
    @JoinColumn(name = "fk_alternativas_alimentares", referencedColumnName = "pk_parceiros")
    @ManyToOne
    private AlternativasAlimentaresBean fkAlternativasAlimentares;

    public FamiliasAlternativaAlimentaresBean() {
    }

    public FamiliasAlternativaAlimentaresBean(Long pkFamiliaAlternativasAlimentares) {
        this.pkFamiliaAlternativasAlimentares = pkFamiliaAlternativasAlimentares;
    }

    public Long getPkFamiliaAlternativasAlimentares() {
        return pkFamiliaAlternativasAlimentares;
    }

    public void setPkFamiliaAlternativasAlimentares(Long pkFamiliaAlternativasAlimentares) {
        this.pkFamiliaAlternativasAlimentares = pkFamiliaAlternativasAlimentares;
    }

    public FamiliasBean getFkFamilias() {
        return fkFamilias;
    }

    public void setFkFamilias(FamiliasBean fkFamilias) {
        this.fkFamilias = fkFamilias;
    }

    public AlternativasAlimentaresBean getFkAlternativasAlimentares() {
        return fkAlternativasAlimentares;
    }

    public void setFkAlternativasAlimentares(AlternativasAlimentaresBean fkAlternativasAlimentares) {
        this.fkAlternativasAlimentares = fkAlternativasAlimentares;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkFamiliaAlternativasAlimentares != null ? pkFamiliaAlternativasAlimentares.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamiliasAlternativaAlimentaresBean)) {
            return false;
        }
        FamiliasAlternativaAlimentaresBean other = (FamiliasAlternativaAlimentaresBean) object;
        if ((this.pkFamiliaAlternativasAlimentares == null && other.pkFamiliaAlternativasAlimentares != null) || (this.pkFamiliaAlternativasAlimentares != null && !this.pkFamiliaAlternativasAlimentares.equals(other.pkFamiliaAlternativasAlimentares))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkFamiliaAlternativasAlimentares;
    }
    
}
