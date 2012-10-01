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
@Table(name = "gestantes_doencas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GestantesDoencasBean.findAll", query = "SELECT g FROM GestantesDoencasBean g"),
    @NamedQuery(name = "GestantesDoencasBean.findByPkGestantesDoencas", query = "SELECT g FROM GestantesDoencasBean g WHERE g.pkGestantesDoencas = :pkGestantesDoencas")})
public class GestantesDoencasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_gestantes_doencas", nullable = false)
    private Long pkGestantesDoencas;
    @JoinColumn(name = "fk_gestantes", referencedColumnName = "pk_gestante")
    @ManyToOne
    private GestantesBean fkGestantes;
    @JoinColumn(name = "fk_doencas", referencedColumnName = "pk_doencas")
    @ManyToOne
    private DoencasBean fkDoencas;

    public GestantesDoencasBean() {
    }

    public GestantesDoencasBean(Long pkGestantesDoencas) {
        this.pkGestantesDoencas = pkGestantesDoencas;
    }

    public Long getPkGestantesDoencas() {
        return pkGestantesDoencas;
    }

    public void setPkGestantesDoencas(Long pkGestantesDoencas) {
        this.pkGestantesDoencas = pkGestantesDoencas;
    }

    public GestantesBean getFkGestantes() {
        return fkGestantes;
    }

    public void setFkGestantes(GestantesBean fkGestantes) {
        this.fkGestantes = fkGestantes;
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
        hash += (pkGestantesDoencas != null ? pkGestantesDoencas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GestantesDoencasBean)) {
            return false;
        }
        GestantesDoencasBean other = (GestantesDoencasBean) object;
        if ((this.pkGestantesDoencas == null && other.pkGestantesDoencas != null) || (this.pkGestantesDoencas != null && !this.pkGestantesDoencas.equals(other.pkGestantesDoencas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + pkGestantesDoencas;
    }
    
}
