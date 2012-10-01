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
@Table(name = "doencas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoencasBean.findAll", query = "SELECT d FROM DoencasBean d"),
    @NamedQuery(name = "DoencasBean.findByPkDoencas", query = "SELECT d FROM DoencasBean d WHERE d.pkDoencas = :pkDoencas"),
    @NamedQuery(name = "DoencasBean.findByDoenca", query = "SELECT d FROM DoencasBean d WHERE d.doenca = :doenca"),
    @NamedQuery(name = "DoencasBean.findByCid", query = "SELECT d FROM DoencasBean d WHERE d.cid = :cid")})
public class DoencasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_doencas", nullable = false)
    private Long pkDoencas;
    @Size(max = 50)
    @Column(name = "doenca", length = 50)
    private String doenca;
    @Size(max = 20)
    @Column(name = "cid", length = 20)
    private String cid;
    @OneToMany(mappedBy = "fkDoencas")
    private List<IdososDoencasBean> idososDoencasList;
    @OneToMany(mappedBy = "fkDoencas")
    private List<CriancasDoencasBean> criancasDoencasList;
    @OneToMany(mappedBy = "fkDoencas")
    private List<VacinasBean> vacinasList;
    @OneToMany(mappedBy = "fkDoencas")
    private List<GestantesDoencasBean> gestantesDoencasList;
    @OneToMany(mappedBy = "fkDoencas")
    private List<VacinasDoencasBean> vascinasDoencasList;

    public DoencasBean() {
    }

    public DoencasBean(Long pkDoencas) {
        this.pkDoencas = pkDoencas;
    }

    public Long getPkDoencas() {
        return pkDoencas;
    }

    public void setPkDoencas(Long pkDoencas) {
        this.pkDoencas = pkDoencas;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @XmlTransient
    public List<IdososDoencasBean> getIdososDoencasList() {
        return idososDoencasList;
    }

    public void setIdososDoencasList(List<IdososDoencasBean> idososDoencasList) {
        this.idososDoencasList = idososDoencasList;
    }

    @XmlTransient
    public List<CriancasDoencasBean> getCriancasDoencasList() {
        return criancasDoencasList;
    }

    public void setCriancasDoencasList(List<CriancasDoencasBean> criancasDoencasList) {
        this.criancasDoencasList = criancasDoencasList;
    }

    @XmlTransient
    public List<VacinasBean> getVacinasList() {
        return vacinasList;
    }

    public void setVacinasList(List<VacinasBean> vacinasList) {
        this.vacinasList = vacinasList;
    }

    @XmlTransient
    public List<GestantesDoencasBean> getGestantesDoencasList() {
        return gestantesDoencasList;
    }

    public void setGestantesDoencasList(List<GestantesDoencasBean> gestantesDoencasList) {
        this.gestantesDoencasList = gestantesDoencasList;
    }

    @XmlTransient
    public List<VacinasDoencasBean> getVascinasDoencasList() {
        return vascinasDoencasList;
    }

    public void setVascinasDoencasList(List<VacinasDoencasBean> vascinasDoencasList) {
        this.vascinasDoencasList = vascinasDoencasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkDoencas != null ? pkDoencas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoencasBean)) {
            return false;
        }
        DoencasBean other = (DoencasBean) object;
        if ((this.pkDoencas == null && other.pkDoencas != null) || (this.pkDoencas != null && !this.pkDoencas.equals(other.pkDoencas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkDoencas;
    }
    
}
