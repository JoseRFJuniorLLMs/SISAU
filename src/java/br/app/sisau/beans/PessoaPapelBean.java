package br.app.sisau.beans;

import br.app.sisau.security.beans.PapelBean;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "pessoa_papel", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PessoaPapelBean.findAll", query = "SELECT p FROM PessoaPapelBean p"),
    @NamedQuery(name = "PessoaPapelBean.findByIdPessoaPapel", query = "SELECT p FROM PessoaPapelBean p WHERE p.idPessoaPapel = :idPessoaPapel")})
public class PessoaPapelBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pessoa_papel", nullable = false)
    private Integer idPessoaPapel;
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "id_pessoa", nullable = false)
    @ManyToOne(optional = false)
    private PessoaBean fkPessoa;
    @JoinColumn(name = "fk_papel", referencedColumnName = "id_papel", nullable = false)
    @ManyToOne(optional = false)
    private PapelBean fkPapel;

    public PessoaPapelBean() {
    }

    public PessoaPapelBean(Integer idPessoaPapel) {
        this.idPessoaPapel = idPessoaPapel;
    }

    public Integer getIdPessoaPapel() {
        return idPessoaPapel;
    }

    public void setIdPessoaPapel(Integer idPessoaPapel) {
        this.idPessoaPapel = idPessoaPapel;
    }

    public PessoaBean getFkPessoa() {
        return fkPessoa;
    }

    public void setFkPessoa(PessoaBean fkPessoa) {
        this.fkPessoa = fkPessoa;
    }

    public PapelBean getFkPapel() {
        return fkPapel;
    }

    public void setFkPapel(PapelBean fkPapel) {
        this.fkPapel = fkPapel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPessoaPapel != null ? idPessoaPapel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaPapelBean)) {
            return false;
        }
        PessoaPapelBean other = (PessoaPapelBean) object;
        if ((this.idPessoaPapel == null && other.idPessoaPapel != null) || (this.idPessoaPapel != null && !this.idPessoaPapel.equals(other.idPessoaPapel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + idPessoaPapel;
    }
    
}
