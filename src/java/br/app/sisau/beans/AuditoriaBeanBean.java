package br.app.sisau.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author herick
 */
@Entity
@Table(name = "auditoria")
@NamedQueries({
    @NamedQuery(name = "AuditoriaBean.findAll", query = "SELECT a FROM AuditoriaBean a ORDER BY a.dataAcao desc"),
    @NamedQuery(name = "AuditoriaBean.findByPessoa", query = "SELECT a FROM AuditoriaBean a WHERE a.username = :username")})
public class AuditoriaBeanBean implements Serializable {

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "idAudit", sequenceName = "auditoria_id_auditoria_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idAudit")
    @Column(name = "id_auditoria", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "acao", nullable = false, length = 100)
    private String acao;

    @Basic(optional = false)
    @Column(name = "data_acao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAcao;

    @Basic(optional = false)
    @Column(name = "modulo", nullable = false, length = 200)
    private String modulo;

    @Basic(optional = false)
    @Column(name = "id_pessoa", nullable = false)
    private int idPessoa;

    @Basic(optional = false)
    @Column(name = "nome_pessoa", nullable = false, length = 200)
    private String nomePessoa;

    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 200)
    private String username;

    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 1024)
    private String descricao;

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Date getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(Date dataAcao) {
        this.dataAcao = dataAcao;
    }

    public Integer getId() {
        return id;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
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
    public String toString() {
        return "Código.:" + id ;
    }
}
