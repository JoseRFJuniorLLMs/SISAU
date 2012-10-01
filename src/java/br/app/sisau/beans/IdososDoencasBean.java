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
@Table(name = "idosos_doencas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdososDoencasBean.findAll", query = "SELECT i FROM IdososDoencasBean i"),
    @NamedQuery(name = "IdososDoencasBean.findByPkIdososDoencas", query = "SELECT i FROM IdososDoencasBean i WHERE i.pkIdososDoencas = :pkIdososDoencas")})
public class IdososDoencasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_idosos_doencas", nullable = false)
    private Long pkIdososDoencas;
    @JoinColumn(name = "fk_idosos", referencedColumnName = "pk_idosos")
    @ManyToOne
    private IdososBean fkIdosos;
    @JoinColumn(name = "fk_doencas", referencedColumnName = "pk_doencas")
    @ManyToOne
    private DoencasBean fkDoencas;

    public IdososDoencasBean() {
    }

    public IdososDoencasBean(Long pkIdososDoencas) {
        this.pkIdososDoencas = pkIdososDoencas;
    }

    public Long getPkIdososDoencas() {
        return pkIdososDoencas;
    }

    public void setPkIdososDoencas(Long pkIdososDoencas) {
        this.pkIdososDoencas = pkIdososDoencas;
    }

    public IdososBean getFkIdosos() {
        return fkIdosos;
    }

    public void setFkIdosos(IdososBean fkIdosos) {
        this.fkIdosos = fkIdosos;
    }

    public DoencasBean getFkDoencas() {
        return fkDoencas;
    }

    public void setFkDoencas(DoencasBean fkDoencas) {
        this.fkDoencas = fkDoencas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdososDoencas != null ? pkIdososDoencas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdososDoencasBean)) {
            return false;
        }
        IdososDoencasBean other = (IdososDoencasBean) object;
        if ((this.pkIdososDoencas == null && other.pkIdososDoencas != null) || (this.pkIdososDoencas != null && !this.pkIdososDoencas.equals(other.pkIdososDoencas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkIdososDoencas;
    }
    
}
