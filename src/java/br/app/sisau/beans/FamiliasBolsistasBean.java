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
@Table(name = "familias_bolcistas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FamiliasBolsistasBean.findAll", query = "SELECT f FROM FamiliasBolsistasBean f"),
    @NamedQuery(name = "FamiliasBolsistasBean.findByPkFamiliasBolcistas", query = "SELECT f FROM FamiliasBolsistasBean f WHERE f.pkFamiliasBolcistas = :pkFamiliasBolcistas")})
public class FamiliasBolsistasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_familias_bolcistas", nullable = false)
    private Long pkFamiliasBolcistas;
    @JoinColumn(name = "fk_familias", referencedColumnName = "pk_familia")
    @ManyToOne
    private FamiliasBean fkFamilias;
    @JoinColumn(name = "fk_bolsistas", referencedColumnName = "pk_bolsista")
    @ManyToOne
    private BolsistasBean fkBolcistas;

    public FamiliasBolsistasBean() {
    }

    public FamiliasBolsistasBean(Long pkFamiliasBolcistas) {
        this.pkFamiliasBolcistas = pkFamiliasBolcistas;
    }

    public Long getPkFamiliasBolcistas() {
        return pkFamiliasBolcistas;
    }

    public void setPkFamiliasBolcistas(Long pkFamiliasBolcistas) {
        this.pkFamiliasBolcistas = pkFamiliasBolcistas;
    }

    public FamiliasBean getFkFamilias() {
        return fkFamilias;
    }

    public void setFkFamilias(FamiliasBean fkFamilias) {
        this.fkFamilias = fkFamilias;
    }

    public BolsistasBean getFkBolcistas() {
        return fkBolcistas;
    }

    public void setFkBolcistas(BolsistasBean fkBolcistas) {
        this.fkBolcistas = fkBolcistas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkFamiliasBolcistas != null ? pkFamiliasBolcistas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamiliasBolsistasBean)) {
            return false;
        }
        FamiliasBolsistasBean other = (FamiliasBolsistasBean) object;
        if ((this.pkFamiliasBolcistas == null && other.pkFamiliasBolcistas != null) || (this.pkFamiliasBolcistas != null && !this.pkFamiliasBolcistas.equals(other.pkFamiliasBolcistas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkFamiliasBolcistas;
    }
    
}
