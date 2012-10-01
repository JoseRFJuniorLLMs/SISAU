package br.app.sisau.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "nascimentos", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NascimentosBean.findAll", query = "SELECT n FROM NascimentosBean n"),
    @NamedQuery(name = "NascimentosBean.findByPkNascimentos", query = "SELECT n FROM NascimentosBean n WHERE n.pkNascimentos = :pkNascimentos")})
public class NascimentosBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "fk_familia")
    private BigInteger fkFamilia;
    @Size(max = 50)
    @Column(name = "fk_pessoas_mae", length = 50)
    private String fkPessoasMae;
    @Column(name = "fk_maternidade")
    private BigInteger fkMaternidade;
    @Column(name = "recem_nascido")
    private Boolean recemNascido;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.TIME)
    private Date dataNascimento;
    @Column(name = "duracao_gestacao")
    private BigInteger duracaoGestacao;
    @Size(max = 50)
    @Column(name = "tipo_parto", length = 50)
    private String tipoParto;
    @Column(name = "tamnho_nascer")
    private BigInteger tamnhoNascer;
    @Size(max = 50)
    @Column(name = "unidade_frequencia", length = 50)
    private String unidadeFrequencia;
    @Column(name = "n_ubs")
    private BigInteger nUbs;
    @Column(name = "n_declaracao_nascido_vivo")
    private BigInteger nDeclaracaoNascidoVivo;
    @Column(name = "n_registro_civil")
    private BigInteger nRegistroCivil;
    @Column(name = "n_cartao_sus")
    private BigInteger nCartaoSus;
    @Column(name = "peso_nascer")
    private BigInteger pesoNascer;
    @Column(name = "comprimento_nascer")
    private BigInteger comprimentoNascer;
    @Size(max = 50)
    @Column(name = "tipagem_sanguinea", length = 50)
    private String tipagemSanguinea;
    @Size(max = 50)
    @Column(name = "tipagem_sanguinea_mae", length = 50)
    private String tipagemSanguineaMae;
    @Size(max = 50)
    @Column(name = "teste_pesinho", length = 50)
    private String testePesinho;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_nascimentos", nullable = false)
    private Long pkNascimentos;

    public NascimentosBean() {
    }

    public NascimentosBean(Long pkNascimentos) {
        this.pkNascimentos = pkNascimentos;
    }

    public BigInteger getFkFamilia() {
        return fkFamilia;
    }

    public void setFkFamilia(BigInteger fkFamilia) {
        this.fkFamilia = fkFamilia;
    }

    public String getFkPessoasMae() {
        return fkPessoasMae;
    }

    public void setFkPessoasMae(String fkPessoasMae) {
        this.fkPessoasMae = fkPessoasMae;
    }

    public BigInteger getFkMaternidade() {
        return fkMaternidade;
    }

    public void setFkMaternidade(BigInteger fkMaternidade) {
        this.fkMaternidade = fkMaternidade;
    }

    public Boolean getRecemNascido() {
        return recemNascido;
    }

    public void setRecemNascido(Boolean recemNascido) {
        this.recemNascido = recemNascido;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public BigInteger getDuracaoGestacao() {
        return duracaoGestacao;
    }

    public void setDuracaoGestacao(BigInteger duracaoGestacao) {
        this.duracaoGestacao = duracaoGestacao;
    }

    public String getTipoParto() {
        return tipoParto;
    }

    public void setTipoParto(String tipoParto) {
        this.tipoParto = tipoParto;
    }

    public BigInteger getTamnhoNascer() {
        return tamnhoNascer;
    }

    public void setTamnhoNascer(BigInteger tamnhoNascer) {
        this.tamnhoNascer = tamnhoNascer;
    }

    public String getUnidadeFrequencia() {
        return unidadeFrequencia;
    }

    public void setUnidadeFrequencia(String unidadeFrequencia) {
        this.unidadeFrequencia = unidadeFrequencia;
    }

    public BigInteger getNUbs() {
        return nUbs;
    }

    public void setNUbs(BigInteger nUbs) {
        this.nUbs = nUbs;
    }

    public BigInteger getNDeclaracaoNascidoVivo() {
        return nDeclaracaoNascidoVivo;
    }

    public void setNDeclaracaoNascidoVivo(BigInteger nDeclaracaoNascidoVivo) {
        this.nDeclaracaoNascidoVivo = nDeclaracaoNascidoVivo;
    }

    public BigInteger getNRegistroCivil() {
        return nRegistroCivil;
    }

    public void setNRegistroCivil(BigInteger nRegistroCivil) {
        this.nRegistroCivil = nRegistroCivil;
    }

    public BigInteger getNCartaoSus() {
        return nCartaoSus;
    }

    public void setNCartaoSus(BigInteger nCartaoSus) {
        this.nCartaoSus = nCartaoSus;
    }

    public BigInteger getPesoNascer() {
        return pesoNascer;
    }

    public void setPesoNascer(BigInteger pesoNascer) {
        this.pesoNascer = pesoNascer;
    }

    public BigInteger getComprimentoNascer() {
        return comprimentoNascer;
    }

    public void setComprimentoNascer(BigInteger comprimentoNascer) {
        this.comprimentoNascer = comprimentoNascer;
    }

    public String getTipagemSanguinea() {
        return tipagemSanguinea;
    }

    public void setTipagemSanguinea(String tipagemSanguinea) {
        this.tipagemSanguinea = tipagemSanguinea;
    }

    public String getTipagemSanguineaMae() {
        return tipagemSanguineaMae;
    }

    public void setTipagemSanguineaMae(String tipagemSanguineaMae) {
        this.tipagemSanguineaMae = tipagemSanguineaMae;
    }

    public String getTestePesinho() {
        return testePesinho;
    }

    public void setTestePesinho(String testePesinho) {
        this.testePesinho = testePesinho;
    }

    public Long getPkNascimentos() {
        return pkNascimentos;
    }

    public void setPkNascimentos(Long pkNascimentos) {
        this.pkNascimentos = pkNascimentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkNascimentos != null ? pkNascimentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NascimentosBean)) {
            return false;
        }
        NascimentosBean other = (NascimentosBean) object;
        if ((this.pkNascimentos == null && other.pkNascimentos != null) || (this.pkNascimentos != null && !this.pkNascimentos.equals(other.pkNascimentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkNascimentos;
    }
    
}
