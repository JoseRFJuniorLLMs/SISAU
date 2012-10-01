package br.app.sisau.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author herick
 */
@Entity
@Table(name = "auditoria")
@NamedQueries({
    @NamedQuery(name = "AuditoriaBean.findAll", query = "SELECT a FROM AuditoriaBean a ORDER BY a.dataAcao desc"),
    @NamedQuery(name = "AuditoriaBean.findByPessoa", query = "SELECT a FROM AuditoriaBean a WHERE a.username = :username")})
public class AuditoriaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_auditoria", nullable = false)
    private Integer idAuditoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "acao", nullable = false, length = 2147483647)
    private String acao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "modulo", nullable = false, length = 2147483647)
    private String modulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_acao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAcao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pessoa", nullable = false)
    private int idPessoa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome_pessoa", nullable = false, length = 2147483647)
    private String nomePessoa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "username", nullable = false, length = 2147483647)
    private String username;
    @Size(max = 2147483647)
    @Column(name = "descricao", length = 2147483647)
    private String descricao;

    public AuditoriaBean() {
    }

    public AuditoriaBean(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public AuditoriaBean(Integer idAuditoria, String acao, String modulo, Date dataAcao, int idPessoa, String nomePessoa, String username) {
        this.idAuditoria = idAuditoria;
        this.acao = acao;
        this.modulo = modulo;
        this.dataAcao = dataAcao;
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        this.username = username;
    }

    public Integer getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public Date getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(Date dataAcao) {
        this.dataAcao = dataAcao;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuditoria != null ? idAuditoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditoriaBean)) {
            return false;
        }
        AuditoriaBean other = (AuditoriaBean) object;
        if ((this.idAuditoria == null && other.idAuditoria != null) || (this.idAuditoria != null && !this.idAuditoria.equals(other.idAuditoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + idAuditoria;
    }
}
