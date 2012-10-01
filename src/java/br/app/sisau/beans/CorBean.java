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
@Table(name = "cor", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CorBean.findAll", query = "SELECT c FROM CorBean c"),
    @NamedQuery(name = "CorBean.findByPkCor", query = "SELECT c FROM CorBean c WHERE c.pkCor = :pkCor"),
    @NamedQuery(name = "CorBean.findByDescricao", query = "SELECT c FROM CorBean c WHERE c.descricao = :descricao")})
public class CorBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_cor", nullable = false)
    private Long pkCor;
    @Size(max = 50)
    @Column(name = "descricao", length = 50)
    private String descricao;
    @OneToMany(mappedBy = "fkCor")
    private List<PessoaBean> pessoaList;

    public CorBean() {
    }

    public CorBean(Long pkCor) {
        this.pkCor = pkCor;
    }

    public Long getPkCor() {
        return pkCor;
    }

    public void setPkCor(Long pkCor) {
        this.pkCor = pkCor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        hash += (pkCor != null ? pkCor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorBean)) {
            return false;
        }
        CorBean other = (CorBean) object;
        if ((this.pkCor == null && other.pkCor != null) || (this.pkCor != null && !this.pkCor.equals(other.pkCor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkCor;
    }
    
}
