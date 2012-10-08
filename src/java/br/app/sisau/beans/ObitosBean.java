package br.app.sisau.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "obitos", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObitosBean.findAll", query = "SELECT o FROM ObitosBean o"),
    @NamedQuery(name = "ObitosBean.findByData", query = "SELECT o FROM ObitosBean o WHERE o.data = :data")})
public class ObitosBean implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "pkObitos", sequenceName = "obitos_pk_obitos_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pkObitos")
    @Column(name = "pk_obitos", nullable = false)
    private Long pkObitos;
     
    @Size(max = 50)
    @Column(name = "causa", length = 50)
    private String causa;
    
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @OneToMany(mappedBy = "fkObito")
    private List<PessoaBean> pessoaList;

    public ObitosBean() {
    }

    public ObitosBean(Long pkObitos) {
        this.pkObitos = pkObitos;
    }

    public Long getPkObitos() {
        return pkObitos;
    }

    public void setPkObitos(Long pkObitos) {
        this.pkObitos = pkObitos;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @XmlTransient
    public List<PessoaBean> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<PessoaBean> pessoaList) {
        this.pessoaList = pessoaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkObitos != null ? pkObitos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObitosBean)) {
            return false;
        }
        ObitosBean other = (ObitosBean) object;
        if ((this.pkObitos == null && other.pkObitos != null) || (this.pkObitos != null && !this.pkObitos.equals(other.pkObitos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
      return "Código " + pkObitos +"Causa "+causa;
     }
    
}
