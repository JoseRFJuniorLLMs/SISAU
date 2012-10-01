package br.app.sisau.beans;

import br.app.sisau.security.beans.ObjetoProtegidoBean;
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
@Table(name = "permissao", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermissaoBean.findAll", query = "SELECT p FROM PermissaoBean p"),
    @NamedQuery(name = "PermissaoBean.findByIdPermissao", query = "SELECT p FROM PermissaoBean p WHERE p.idPermissao = :idPermissao")})
public class PermissaoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_permissao", nullable = false)
    private Integer idPermissao;
    @JoinColumn(name = "fk_papel", referencedColumnName = "id_papel", nullable = false)
    @ManyToOne(optional = false)
    private PapelBean fkPapel;
    @JoinColumn(name = "fk_objeto_protegido", referencedColumnName = "id_objeto_protegido", nullable = false)
    @ManyToOne(optional = false)
    private ObjetoProtegidoBean fkObjetoProtegido;

    public PermissaoBean() {
    }

    public PermissaoBean(Integer idPermissao) {
        this.idPermissao = idPermissao;
    }

    public Integer getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Integer idPermissao) {
        this.idPermissao = idPermissao;
    }

    public PapelBean getFkPapel() {
        return fkPapel;
    }

    public void setFkPapel(PapelBean fkPapel) {
        this.fkPapel = fkPapel;
    }

    public ObjetoProtegidoBean getFkObjetoProtegido() {
        return fkObjetoProtegido;
    }

    public void setFkObjetoProtegido(ObjetoProtegidoBean fkObjetoProtegido) {
        this.fkObjetoProtegido = fkObjetoProtegido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermissao != null ? idPermissao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermissaoBean)) {
            return false;
        }
        PermissaoBean other = (PermissaoBean) object;
        if ((this.idPermissao == null && other.idPermissao != null) || (this.idPermissao != null && !this.idPermissao.equals(other.idPermissao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + idPermissao;
    }
    
}
