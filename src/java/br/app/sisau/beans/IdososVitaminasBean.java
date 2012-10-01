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
@Table(name = "idosos_vitaminas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdososVitaminasBean.findAll", query = "SELECT i FROM IdososVitaminasBean i"),
    @NamedQuery(name = "IdososVitaminasBean.findByPkIdososVitaminas", query = "SELECT i FROM IdososVitaminasBean i WHERE i.pkIdososVitaminas = :pkIdososVitaminas")})
public class IdososVitaminasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_idosos_vitaminas", nullable = false)
    private Long pkIdososVitaminas;
    @JoinColumn(name = "fk_vitaminas", referencedColumnName = "pk_vitaminas")
    @ManyToOne
    private VitaminasBean fkVitaminas;
    @JoinColumn(name = "fk_idosos", referencedColumnName = "pk_idosos")
    @ManyToOne
    private IdososBean fkIdosos;

    public IdososVitaminasBean() {
    }

    public IdososVitaminasBean(Long pkIdososVitaminas) {
        this.pkIdososVitaminas = pkIdososVitaminas;
    }

    public Long getPkIdososVitaminas() {
        return pkIdososVitaminas;
    }

    public void setPkIdososVitaminas(Long pkIdososVitaminas) {
        this.pkIdososVitaminas = pkIdososVitaminas;
    }

    public VitaminasBean getFkVitaminas() {
        return fkVitaminas;
    }

    public void setFkVitaminas(VitaminasBean fkVitaminas) {
        this.fkVitaminas = fkVitaminas;
    }

    public IdososBean getFkIdosos() {
        return fkIdosos;
    }

    public void setFkIdosos(IdososBean fkIdosos) {
        this.fkIdosos = fkIdosos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdososVitaminas != null ? pkIdososVitaminas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdososVitaminasBean)) {
            return false;
        }
        IdososVitaminasBean other = (IdososVitaminasBean) object;
        if ((this.pkIdososVitaminas == null && other.pkIdososVitaminas != null) || (this.pkIdososVitaminas != null && !this.pkIdososVitaminas.equals(other.pkIdososVitaminas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkIdososVitaminas;
    }
    
}
