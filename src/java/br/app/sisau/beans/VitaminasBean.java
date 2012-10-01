package br.app.sisau.beans;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "vitaminas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VitaminasBean.findAll", query = "SELECT v FROM VitaminasBean v"),
    @NamedQuery(name = "VitaminasBean.findByPkVitaminas", query = "SELECT v FROM VitaminasBean v WHERE v.pkVitaminas = :pkVitaminas"),
    @NamedQuery(name = "VitaminasBean.findByVitamina", query = "SELECT v FROM VitaminasBean v WHERE v.vitamina = :vitamina"),
    @NamedQuery(name = "VitaminasBean.findByDataEntrega", query = "SELECT v FROM VitaminasBean v WHERE v.dataEntrega = :dataEntrega"),
    @NamedQuery(name = "VitaminasBean.findByDataValidade", query = "SELECT v FROM VitaminasBean v WHERE v.dataValidade = :dataValidade")})
public class VitaminasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_vitaminas", nullable = false)
    private Long pkVitaminas;
    @Size(max = 30)
    @Column(name = "vitamina", length = 30)
    private String vitamina;
    @Column(name = "data_entrega")
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;
    @Column(name = "data_validade")
    @Temporal(TemporalType.DATE)
    private Date dataValidade;
    @OneToMany(mappedBy = "fkVitaminas")
    private List<GestantesVitaminasBean> gestantesVitaminasList;
    @OneToMany(mappedBy = "fkVitaminas")
    private List<CriancasVitaminasBean> criancasVitaminasList;
    @OneToMany(mappedBy = "fkVitaminas")
    private List<IdososVitaminasBean> idososVitaminasList;

    public VitaminasBean() {
    }

    public VitaminasBean(Long pkVitaminas) {
        this.pkVitaminas = pkVitaminas;
    }

    public Long getPkVitaminas() {
        return pkVitaminas;
    }

    public void setPkVitaminas(Long pkVitaminas) {
        this.pkVitaminas = pkVitaminas;
    }

    public String getVitamina() {
        return vitamina;
    }

    public void setVitamina(String vitamina) {
        this.vitamina = vitamina;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    @XmlTransient
    public List<GestantesVitaminasBean> getGestantesVitaminasList() {
        return gestantesVitaminasList;
    }

    public void setGestantesVitaminasList(List<GestantesVitaminasBean> gestantesVitaminasList) {
        this.gestantesVitaminasList = gestantesVitaminasList;
    }

    @XmlTransient
    public List<CriancasVitaminasBean> getCriancasVitaminasList() {
        return criancasVitaminasList;
    }

    public void setCriancasVitaminasList(List<CriancasVitaminasBean> criancasVitaminasList) {
        this.criancasVitaminasList = criancasVitaminasList;
    }

    @XmlTransient
    public List<IdososVitaminasBean> getIdososVitaminasList() {
        return idososVitaminasList;
    }

    public void setIdososVitaminasList(List<IdososVitaminasBean> idososVitaminasList) {
        this.idososVitaminasList = idososVitaminasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkVitaminas != null ? pkVitaminas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VitaminasBean)) {
            return false;
        }
        VitaminasBean other = (VitaminasBean) object;
        if ((this.pkVitaminas == null && other.pkVitaminas != null) || (this.pkVitaminas != null && !this.pkVitaminas.equals(other.pkVitaminas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkVitaminas;
    }
    
}
