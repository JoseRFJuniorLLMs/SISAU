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
@Table(name = "criancas_doencas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CriancasDoencasBean.findAll", query = "SELECT c FROM CriancasDoencasBean c"),
    @NamedQuery(name = "CriancasDoencasBean.findByPkCriancasDoencas", query = "SELECT c FROM CriancasDoencasBean c WHERE c.pkCriancasDoencas = :pkCriancasDoencas")})
public class CriancasDoencasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_criancas_doencas", nullable = false)
    private Long pkCriancasDoencas;
    @JoinColumn(name = "fk_doencas", referencedColumnName = "pk_doencas")
    @ManyToOne
    private DoencasBean fkDoencas;
    @JoinColumn(name = "fk_criancas", referencedColumnName = "pk_criancas")
    @ManyToOne
    private CriancasBean fkCriancas;

    public CriancasDoencasBean() {
    }

    public CriancasDoencasBean(Long pkCriancasDoencas) {
        this.pkCriancasDoencas = pkCriancasDoencas;
    }

    public Long getPkCriancasDoencas() {
        return pkCriancasDoencas;
    }

    public void setPkCriancasDoencas(Long pkCriancasDoencas) {
        this.pkCriancasDoencas = pkCriancasDoencas;
    }

    public DoencasBean getFkDoencas() {
        return fkDoencas;
    }

    public void setFkDoencas(DoencasBean fkDoencas) {
        this.fkDoencas = fkDoencas;
    }

    public CriancasBean getFkCriancas() {
        return fkCriancas;
    }

    public void setFkCriancas(CriancasBean fkCriancas) {
        this.fkCriancas = fkCriancas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCriancasDoencas != null ? pkCriancasDoencas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CriancasDoencasBean)) {
            return false;
        }
        CriancasDoencasBean other = (CriancasDoencasBean) object;
        if ((this.pkCriancasDoencas == null && other.pkCriancasDoencas != null) || (this.pkCriancasDoencas != null && !this.pkCriancasDoencas.equals(other.pkCriancasDoencas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkCriancasDoencas;
    }
    
}
