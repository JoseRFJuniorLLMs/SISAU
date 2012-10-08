package br.app.sisau.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "especialidades", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EspecialidadesBean.findAll", query = "SELECT e FROM EspecialidadesBean e"),
    @NamedQuery(name = "EspecialidadesBean.findByPkEspecialidade", query = "SELECT e FROM EspecialidadesBean e WHERE e.pkEspecialidade = :pkEspecialidade"),
    @NamedQuery(name = "EspecialidadesBean.findByEspecialidade", query = "SELECT e FROM EspecialidadesBean e WHERE e.especialidade = :especialidade")})
public class EspecialidadesBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_especialidade", nullable = false)
    private int pkEspecialidade;
    
    @Size(max = 50)
    @Column(name = "especialidade", length = 50)
    private String especialidade;
    
    @OneToMany(mappedBy = "fkEspecialidade")
    private List<MedicosBean> medicosList;
    
    @OneToMany(mappedBy = "especialidade")
    private List<AgendaMedicoBean> agendaMedicoList;
    
    @OneToMany(mappedBy = "fkEspecialidades")
    private List<EspecialidadesMedicosBean> especialidadesMedicosList;

    public EspecialidadesBean() {
    }

    public EspecialidadesBean(int pkEspecialidade) {
        this.pkEspecialidade = pkEspecialidade;
    }

    public int getPkEspecialidade() {
        return pkEspecialidade;
    }

    public void setPkEspecialidade(int pkEspecialidade) {
        this.pkEspecialidade = pkEspecialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @XmlTransient
    public List<MedicosBean> getMedicosList() {
        return medicosList;
    }

    public void setMedicosList(List<MedicosBean> medicosList) {
        this.medicosList = medicosList;
    }

    @XmlTransient
    public List<AgendaMedicoBean> getAgendaMedicoList() {
        return agendaMedicoList;
    }

    public void setAgendaMedicoList(List<AgendaMedicoBean> agendaMedicoList) {
        this.agendaMedicoList = agendaMedicoList;
    }

    @XmlTransient
    public List<EspecialidadesMedicosBean> getEspecialidadesMedicosList() {
        return especialidadesMedicosList;
    }

    public void setEspecialidadesMedicosList(List<EspecialidadesMedicosBean> especialidadesMedicosList) {
        this.especialidadesMedicosList = especialidadesMedicosList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.pkEspecialidade;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EspecialidadesBean other = (EspecialidadesBean) obj;
        if (this.pkEspecialidade != other.pkEspecialidade) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + pkEspecialidade+"<--->"+"Descrição.:"+especialidade;
    }
    
}
