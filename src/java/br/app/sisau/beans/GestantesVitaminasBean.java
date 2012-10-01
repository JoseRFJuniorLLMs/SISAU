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
@Table(name = "gestantes_vitaminas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GestantesVitaminasBean.findAll", query = "SELECT g FROM GestantesVitaminasBean g"),
    @NamedQuery(name = "GestantesVitaminasBean.findByPkGestantesVitaminas", query = "SELECT g FROM GestantesVitaminasBean g WHERE g.pkGestantesVitaminas = :pkGestantesVitaminas")})
public class GestantesVitaminasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_gestantes_vitaminas", nullable = false)
    private Long pkGestantesVitaminas;
    @JoinColumn(name = "fk_vitaminas", referencedColumnName = "pk_vitaminas")
    @ManyToOne
    private VitaminasBean fkVitaminas;
    @JoinColumn(name = "fk_gestantes", referencedColumnName = "pk_gestante")
    @ManyToOne
    private GestantesBean fkGestantes;

    public GestantesVitaminasBean() {
    }

    public GestantesVitaminasBean(Long pkGestantesVitaminas) {
        this.pkGestantesVitaminas = pkGestantesVitaminas;
    }

    public Long getPkGestantesVitaminas() {
        return pkGestantesVitaminas;
    }

    public void setPkGestantesVitaminas(Long pkGestantesVitaminas) {
        this.pkGestantesVitaminas = pkGestantesVitaminas;
    }

    public VitaminasBean getFkVitaminas() {
        return fkVitaminas;
    }

    public void setFkVitaminas(VitaminasBean fkVitaminas) {
        this.fkVitaminas = fkVitaminas;
    }

    public GestantesBean getFkGestantes() {
        return fkGestantes;
    }

    public void setFkGestantes(GestantesBean fkGestantes) {
        this.fkGestantes = fkGestantes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGestantesVitaminas != null ? pkGestantesVitaminas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GestantesVitaminasBean)) {
            return false;
        }
        GestantesVitaminasBean other = (GestantesVitaminasBean) object;
        if ((this.pkGestantesVitaminas == null && other.pkGestantesVitaminas != null) || (this.pkGestantesVitaminas != null && !this.pkGestantesVitaminas.equals(other.pkGestantesVitaminas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + pkGestantesVitaminas;
    }
    
}
