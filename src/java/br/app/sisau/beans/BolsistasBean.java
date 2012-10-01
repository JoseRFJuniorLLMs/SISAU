package br.app.sisau.beans;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "bolsistas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BolsistasBean.findAll", query = "SELECT b FROM BolsistasBean b"),
    @NamedQuery(name = "BolsistasBean.findByPkBolcista", query = "SELECT b FROM BolsistasBean b WHERE b.pkBolsista = :pkBolsista"),
    @NamedQuery(name = "BolsistasBean.findByTipo", query = "SELECT b FROM BolsistasBean b WHERE b.tipo = :tipo"),
    @NamedQuery(name = "BolsistasBean.findByNome", query = "SELECT b FROM BolsistasBean b WHERE b.nome = :nome"),
    @NamedQuery(name = "BolsistasBean.findByValor", query = "SELECT b FROM BolsistasBean b WHERE b.valor = :valor")})
public class BolsistasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_bolsista", nullable = false)
    private Long pkBolsista;
    @Size(max = 50)
    @Column(name = "tipo", length = 50)
    private String tipo;
    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;
    @Column(name = "valor")
    private BigInteger valor;
    @OneToMany(mappedBy = "fkBolcistas")
    private List<FamiliasBolsistasBean> familiasBolcistasList;
    @OneToMany(mappedBy = "fkBolcistas")
    private List<FamiliasBean> familiasList;

    public BolsistasBean() {
    }

    public BolsistasBean(Long pkBolsista) {
        this.pkBolsista = pkBolsista;
    }

    public Long getPkBolcista() {
        return pkBolsista;
    }

    public void setPkBolcista(Long pkBolsista) {
        this.pkBolsista = pkBolsista;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    @XmlTransient
    public List<FamiliasBolsistasBean> getFamiliasBolcistasList() {
        return familiasBolcistasList;
    }

    public void setFamiliasBolcistasList(List<FamiliasBolsistasBean> familiasBolcistasList) {
        this.familiasBolcistasList = familiasBolcistasList;
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
        hash += (pkBolsista != null ? pkBolsista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BolsistasBean)) {
            return false;
        }
        BolsistasBean other = (BolsistasBean) object;
        if ((this.pkBolsista == null && other.pkBolsista != null) || (this.pkBolsista != null && !this.pkBolsista.equals(other.pkBolsista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkBolsista;
    }
    
}
