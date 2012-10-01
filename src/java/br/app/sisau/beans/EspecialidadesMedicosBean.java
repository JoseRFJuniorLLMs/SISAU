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
@Table(name = "especialidades_medicos", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EspecialidadesMedicosBean.findAll", query = "SELECT e FROM EspecialidadesMedicosBean e"),
    @NamedQuery(name = "EspecialidadesMedicosBean.findByPkEspecialidadeMedicos", query = "SELECT e FROM EspecialidadesMedicosBean e WHERE e.pkEspecialidadeMedicos = :pkEspecialidadeMedicos")})
public class EspecialidadesMedicosBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_especialidade_medicos", nullable = false)
    private Long pkEspecialidadeMedicos;
    @JoinColumn(name = "fk_medicos", referencedColumnName = "pk_medico")
    @ManyToOne
    private MedicosBean fkMedicos;
    @JoinColumn(name = "fk_especialidades", referencedColumnName = "pk_especialidade")
    @ManyToOne
    private EspecialidadesBean fkEspecialidades;

    public EspecialidadesMedicosBean() {
    }

    public EspecialidadesMedicosBean(Long pkEspecialidadeMedicos) {
        this.pkEspecialidadeMedicos = pkEspecialidadeMedicos;
    }

    public Long getPkEspecialidadeMedicos() {
        return pkEspecialidadeMedicos;
    }

    public void setPkEspecialidadeMedicos(Long pkEspecialidadeMedicos) {
        this.pkEspecialidadeMedicos = pkEspecialidadeMedicos;
    }

    public MedicosBean getFkMedicos() {
        return fkMedicos;
    }

    public void setFkMedicos(MedicosBean fkMedicos) {
        this.fkMedicos = fkMedicos;
    }

    public EspecialidadesBean getFkEspecialidades() {
        return fkEspecialidades;
    }

    public void setFkEspecialidades(EspecialidadesBean fkEspecialidades) {
        this.fkEspecialidades = fkEspecialidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkEspecialidadeMedicos != null ? pkEspecialidadeMedicos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspecialidadesMedicosBean)) {
            return false;
        }
        EspecialidadesMedicosBean other = (EspecialidadesMedicosBean) object;
        if ((this.pkEspecialidadeMedicos == null && other.pkEspecialidadeMedicos != null) || (this.pkEspecialidadeMedicos != null && !this.pkEspecialidadeMedicos.equals(other.pkEspecialidadeMedicos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + pkEspecialidadeMedicos;
    }
    
}
