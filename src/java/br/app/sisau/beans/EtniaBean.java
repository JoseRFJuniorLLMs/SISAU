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
@Table(name = "etnia", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EtniaBean.findAll", query = "SELECT e FROM EtniaBean e"),
    @NamedQuery(name = "EtniaBean.findByDescriacao", query = "SELECT e FROM EtniaBean e WHERE e.descriacao = :descriacao")})

public class EtniaBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_etnia", nullable = false)
    private Long pkEtnia;
    @Size(max = 50)
    @Column(name = "descriacao", length = 50)
    private String descriacao;
    @OneToMany(mappedBy = "fkEtnia")
    private List<PessoaBean> pessoaList;

    public EtniaBean() {
    }

    public EtniaBean(Long pkEtnia) {
        this.pkEtnia = pkEtnia;
    }

    public Long getPkEtnia() {
        return pkEtnia;
    }

    public void setPkEtnia(Long pkEtnia) {
        this.pkEtnia = pkEtnia;
    }

    public String getDescriacao() {
        return descriacao;
    }

    public void setDescriacao(String descriacao) {
        this.descriacao = descriacao;
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
        hash += (pkEtnia != null ? pkEtnia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtniaBean)) {
            return false;
        }
        EtniaBean other = (EtniaBean) object;
        if ((this.pkEtnia == null && other.pkEtnia != null) || (this.pkEtnia != null && !this.pkEtnia.equals(other.pkEtnia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkEtnia;
    }
    
}
