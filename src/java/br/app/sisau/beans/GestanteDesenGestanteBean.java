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
@Table(name = "gestante_desen_gestante", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GestanteDesenGestanteBean.findAll", query = "SELECT g FROM GestanteDesenGestanteBean g"),
    @NamedQuery(name = "GestanteDesenGestanteBean.findByPkGestanteDesenGestante", query = "SELECT g FROM GestanteDesenGestanteBean g WHERE g.pkGestanteDesenGestante = :pkGestanteDesenGestante")})
public class GestanteDesenGestanteBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_gestante_desen_gestante", nullable = false)
    private Long pkGestanteDesenGestante;
    @JoinColumn(name = "fk_gestante", referencedColumnName = "pk_gestante")
    @ManyToOne
    private GestantesBean fkGestante;
    @JoinColumn(name = "fk_desesn_gestantes", referencedColumnName = "pk_desen_gestantes")
    @ManyToOne
    private DesenGestantesBean fkDesesnGestantes;

    public GestanteDesenGestanteBean() {
    }

    public GestanteDesenGestanteBean(Long pkGestanteDesenGestante) {
        this.pkGestanteDesenGestante = pkGestanteDesenGestante;
    }

    public Long getPkGestanteDesenGestante() {
        return pkGestanteDesenGestante;
    }

    public void setPkGestanteDesenGestante(Long pkGestanteDesenGestante) {
        this.pkGestanteDesenGestante = pkGestanteDesenGestante;
    }

    public GestantesBean getFkGestante() {
        return fkGestante;
    }

    public void setFkGestante(GestantesBean fkGestante) {
        this.fkGestante = fkGestante;
    }

    public DesenGestantesBean getFkDesesnGestantes() {
        return fkDesesnGestantes;
    }

    public void setFkDesesnGestantes(DesenGestantesBean fkDesesnGestantes) {
        this.fkDesesnGestantes = fkDesesnGestantes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGestanteDesenGestante != null ? pkGestanteDesenGestante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GestanteDesenGestanteBean)) {
            return false;
        }
        GestanteDesenGestanteBean other = (GestanteDesenGestanteBean) object;
        if ((this.pkGestanteDesenGestante == null && other.pkGestanteDesenGestante != null) || (this.pkGestanteDesenGestante != null && !this.pkGestanteDesenGestante.equals(other.pkGestanteDesenGestante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkGestanteDesenGestante;
    }
    
}
