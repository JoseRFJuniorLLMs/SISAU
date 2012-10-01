package br.app.sisau.beans;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "criancas_vascinas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CriancasVacinasBean.findAll", query = "SELECT c FROM CriancasVacinasBean c"),
    @NamedQuery(name = "CriancasVacinasBean.findByPkCriancasVacinas", query = "SELECT c FROM CriancasVacinasBean c WHERE c.pkCriancasVacinas = :pkCriancasVacinas")})
public class CriancasVacinasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_criancas_vacinas", nullable = false)
    private Long pkCriancasVacinas;
    @JoinColumn(name = "fk_vacinas", referencedColumnName = "pk_vacinas")
    @ManyToOne
    private VacinasBean fkVacinas;
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "id_pessoa")
    @ManyToOne
    private PessoaBean fkPessoa;
    @JoinColumn(name = "fk_criancas", referencedColumnName = "pk_criancas")
    @ManyToOne
    private CriancasBean fkCriancas;

    public CriancasVacinasBean() {
    }

    public CriancasVacinasBean(Long pkCriancasVacinas) {
        this.pkCriancasVacinas = pkCriancasVacinas;
    }

    public Long getPkCriancasVacinas() {
        return pkCriancasVacinas;
    }

    public void setPkCriancasVacinas(Long pkCriancasVacinas) {
        this.pkCriancasVacinas = pkCriancasVacinas;
    }

    public VacinasBean getFkVacinas() {
        return fkVacinas;
    }

    public void setFkVacinas(VacinasBean fkVacinas) {
        this.fkVacinas = fkVacinas;
    }

    public PessoaBean getFkPessoa() {
        return fkPessoa;
    }

    public void setFkPessoa(PessoaBean fkPessoa) {
        this.fkPessoa = fkPessoa;
    }

    public CriancasBean getFkCriancas() {
        return fkCriancas;
    }

    public void setFkCriancas(CriancasBean fkCriancas) {
        this.fkCriancas = fkCriancas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCriancasVacinas != null ? pkCriancasVacinas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CriancasVacinasBean)) {
            return false;
        }
        CriancasVacinasBean other = (CriancasVacinasBean) object;
        if ((this.pkCriancasVacinas == null && other.pkCriancasVacinas != null) || (this.pkCriancasVacinas != null && !this.pkCriancasVacinas.equals(other.pkCriancasVacinas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + pkCriancasVacinas;
    }
    
}
