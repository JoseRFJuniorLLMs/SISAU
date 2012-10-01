package br.app.sisau.beans;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "criancas_vitaminas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CriancasVitaminasBean.findAll", query = "SELECT c FROM CriancasVitaminasBean c"),
    @NamedQuery(name = "CriancasVitaminasBean.findByPkClinicasVitaminas", query = "SELECT c FROM CriancasVitaminasBean c WHERE c.pkClinicasVitaminas = :pkClinicasVitaminas"),
    @NamedQuery(name = "CriancasVitaminasBean.findByFkClinicas", query = "SELECT c FROM CriancasVitaminasBean c WHERE c.fkClinicas = :fkClinicas")})
public class CriancasVitaminasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_clinicas_vitaminas", nullable = false)
    private Long pkClinicasVitaminas;
    @Column(name = "fk_clinicas")
    private BigInteger fkClinicas;
    @JoinColumn(name = "fk_vitaminas", referencedColumnName = "pk_vitaminas")
    @ManyToOne
    private VitaminasBean fkVitaminas;
    @JoinColumn(name = "fk_crianca", referencedColumnName = "pk_criancas")
    @ManyToOne
    private CriancasBean fkCrianca;

    public CriancasVitaminasBean() {
    }

    public CriancasVitaminasBean(Long pkClinicasVitaminas) {
        this.pkClinicasVitaminas = pkClinicasVitaminas;
    }

    public Long getPkClinicasVitaminas() {
        return pkClinicasVitaminas;
    }

    public void setPkClinicasVitaminas(Long pkClinicasVitaminas) {
        this.pkClinicasVitaminas = pkClinicasVitaminas;
    }

    public BigInteger getFkClinicas() {
        return fkClinicas;
    }

    public void setFkClinicas(BigInteger fkClinicas) {
        this.fkClinicas = fkClinicas;
    }

    public VitaminasBean getFkVitaminas() {
        return fkVitaminas;
    }

    public void setFkVitaminas(VitaminasBean fkVitaminas) {
        this.fkVitaminas = fkVitaminas;
    }

    public CriancasBean getFkCrianca() {
        return fkCrianca;
    }

    public void setFkCrianca(CriancasBean fkCrianca) {
        this.fkCrianca = fkCrianca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkClinicasVitaminas != null ? pkClinicasVitaminas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CriancasVitaminasBean)) {
            return false;
        }
        CriancasVitaminasBean other = (CriancasVitaminasBean) object;
        if ((this.pkClinicasVitaminas == null && other.pkClinicasVitaminas != null) || (this.pkClinicasVitaminas != null && !this.pkClinicasVitaminas.equals(other.pkClinicasVitaminas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + pkClinicasVitaminas;
    }
    
}
