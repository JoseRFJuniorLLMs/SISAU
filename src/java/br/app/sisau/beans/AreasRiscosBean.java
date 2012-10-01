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
@Table(name = "areas_riscos", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreasRiscosBean.findAll", query = "SELECT a FROM AreasRiscosBean a"),
    @NamedQuery(name = "AreasRiscosBean.findByPkArea", query = "SELECT a FROM AreasRiscosBean a WHERE a.pkArea = :pkArea"),
    @NamedQuery(name = "AreasRiscosBean.findByArea", query = "SELECT a FROM AreasRiscosBean a WHERE a.area = :area")})
public class AreasRiscosBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_area", nullable = false)
    private Long pkArea;
    @Size(max = 50)
    @Column(name = "area", length = 50)
    private String area;
    @OneToMany(mappedBy = "fkArea")
    private List<PessoaBean> pessoaList;
    @OneToMany(mappedBy = "fkAreasRiscos")
    private List<FamiliasBean> familiasList;

    public AreasRiscosBean() {
    }

    public AreasRiscosBean(Long pkArea) {
        this.pkArea = pkArea;
    }

    public Long getPkArea() {
        return pkArea;
    }

    public void setPkArea(Long pkArea) {
        this.pkArea = pkArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @XmlTransient
    public List<PessoaBean> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<PessoaBean> pessoaList) {
        this.pessoaList = pessoaList;
    }

    @XmlTransient
    public List<FamiliasBean> getFamiliasList() {
        return familiasList;
    }

    public void setFamiliasList(List<FamiliasBean> familiasList) {
        this.familiasList = familiasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkArea != null ? pkArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreasRiscosBean)) {
            return false;
        }
        AreasRiscosBean other = (AreasRiscosBean) object;
        if ((this.pkArea == null && other.pkArea != null) || (this.pkArea != null && !this.pkArea.equals(other.pkArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkArea;
    }
    
}
