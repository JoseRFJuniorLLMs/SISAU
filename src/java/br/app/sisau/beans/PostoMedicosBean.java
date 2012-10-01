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
@Table(name = "posto_medicos", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostoMedicosBean.findAll", query = "SELECT p FROM PostoMedicosBean p"),
    @NamedQuery(name = "PostoMedicosBean.findByPkPostoMedicos", query = "SELECT p FROM PostoMedicosBean p WHERE p.pkPostoMedicos = :pkPostoMedicos")})
public class PostoMedicosBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_posto_medicos", nullable = false)
    private Long pkPostoMedicos;
    @JoinColumn(name = "fk_posto", referencedColumnName = "pk_posto")
    @ManyToOne
    private PostoBean fkPosto;
    @JoinColumn(name = "fk_medicos", referencedColumnName = "pk_medico")
    @ManyToOne
    private MedicosBean fkMedicos;

    public PostoMedicosBean() {
    }

    public PostoMedicosBean(Long pkPostoMedicos) {
        this.pkPostoMedicos = pkPostoMedicos;
    }

    public Long getPkPostoMedicos() {
        return pkPostoMedicos;
    }

    public void setPkPostoMedicos(Long pkPostoMedicos) {
        this.pkPostoMedicos = pkPostoMedicos;
    }

    public PostoBean getFkPosto() {
        return fkPosto;
    }

    public void setFkPosto(PostoBean fkPosto) {
        this.fkPosto = fkPosto;
    }

    public MedicosBean getFkMedicos() {
        return fkMedicos;
    }

    public void setFkMedicos(MedicosBean fkMedicos) {
        this.fkMedicos = fkMedicos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPostoMedicos != null ? pkPostoMedicos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostoMedicosBean)) {
            return false;
        }
        PostoMedicosBean other = (PostoMedicosBean) object;
        if ((this.pkPostoMedicos == null && other.pkPostoMedicos != null) || (this.pkPostoMedicos != null && !this.pkPostoMedicos.equals(other.pkPostoMedicos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkPostoMedicos;
    }
    
}
