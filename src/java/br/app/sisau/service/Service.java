package br.app.sisau.service;

import br.app.sisau.beans.*;
import br.app.sisau.daos.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
public class Service {

    private static final Logger logger = Logger.getLogger(Service.class);
    /*
     * Objeto para implementacao do singleton
     */
    private static Service instance = new Service();

    /*
     * Metodo construtor privado, para evitar
     * multipla instanciacao
     */
    private Service() {
    }
    /*
     * Metodo que retorna a unica instancia da classe
     */

    public static Service getInstance() {
        return instance;
    }

    public String encryptPassword(String input) {
        MessageDigest md = null;
        String result = input;
        if (input != null) {
            try {
                md = MessageDigest.getInstance("MD5"); //or "SHA-1"
                md.update(input.getBytes());
                BigInteger hash = new BigInteger(1, md.digest());
                result = hash.toString(16);
            } catch (NoSuchAlgorithmException ex) {
                logger.debug(ex);
            }
            while (result.length() < 32) {
                result = "0" + result;
            }
        }
        return result;
    }

    /*
     * Gera senha randômica da combinação de números e caracteres
     * maíusculas e minúsculas.
     * Parâmetro de passagem é o tamanho da senha a ser gerada
     */
    public String getRandomPassword(int length) {
        char[] ALL_CHARS = new char[62];
        Random RANDOM = new Random();

        for (int i = 48, j = 0; i < 123; i++) {
            if (Character.isLetterOrDigit(i)) {
                ALL_CHARS[j] = (char) i;
                j++;
            }
        }

        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = ALL_CHARS[RANDOM.nextInt(ALL_CHARS.length)];
        }
        return new String(result);
    }

    public PessoaBean realizaLogin(String username, String password) throws Exception {
        if ("".equals(username)) {
            throw new Exception("Username não pode ser vazio");
        }
        if ("".equals(password)) {
            throw new Exception("Senha não pode ser vazia");
        }
        PessoaBean cadastrada = new PessoaJpaDao().findByUsername(username);
        String encryptedPassword = encryptPassword(password);

        if (!encryptedPassword.equals(cadastrada.getPassword())) {
            throw new Exception("Senha inválida");
        }
        return cadastrada;
    }

    public List<PessoaBean> listarPessoas() {
        return new PessoaJpaDao().findEntities();
        //return new PessoaJpaDao().pesquisar();
    }

    public PessoaBean pesquisarPessoaEmail(String email) throws Exception {
        return new PessoaJpaDao().findByEmail(email);
    }

    public List<PessoaBean> pesquisarPessoaCriteria(String termoPesquisa) {
        return new PessoaJpaDao().pesquisarCriteria(termoPesquisa);
    }

    public void cadastrarPessoa(PessoaBean pessoa) {
        new PessoaJpaDao().create(pessoa);
    }

    public void atualizarPessoa(PessoaBean pessoa) {
        new PessoaJpaDao().update(pessoa);
    }

    public void excluirPessoa(PessoaBean pessoa) {
        new PessoaJpaDao().delete(pessoa);
    }

    public List<AuditoriaBeanBean> listarAuditorias() {
        return new AuditoriaJpaDao().findEntities();
    }

    public List<PessoaBean> pesquisarPessoas(String termoPesquisa) {
        return new PessoaJpaDao().pesquisar(termoPesquisa);
    }

    public List<PessoaBean> pesquisarPessoas() {
        return new PessoaJpaDao().pesquisar();
    }

    public PessoaBean pesquisarPessoa(PessoaBean pessoa) {
        return new PessoaJpaDao().findEntity(pessoa.getIdPessoa());
    }
    //==========================================================================================//
    
                            //MÉDICOS  - Junior 24/09/2012
    
    //=========================================================================================//

    public List<MedicosBean> listarMedicos() {
        return new MedicoJpaDao().findEntities();
    }
    
    public List<MedicosBean> pesquisarMedicosCriteria(String termoPesquisa) {
        return new MedicoJpaDao().pesquisarCriteria(termoPesquisa);
    }

    public List<MedicosBean> pesquisarMedicos(String termoPesquisa) {
        return new MedicoJpaDao().pesquisar(termoPesquisa);
    }

    public MedicosBean pesquisarMedicos(MedicosBean medicos) {
        return new MedicoJpaDao().findEntity(medicos.getPkMedico());
    }

    public void cadastrarMedicos(MedicosBean medicos) {
        new MedicoJpaDao().create(medicos);
    }

    public void atualizarMedicos(MedicosBean medicos) {
        new MedicoJpaDao().update(medicos);
    }

    public void excluirMedicos(MedicosBean medicos) {
        new MedicoJpaDao().delete(medicos);
    }

    public List<EspecialidadesBean> listaEspecialidade() {
        return new EspecialidadeJpaDao().findEntities();
    }
   //==========================================================================================//
    
                            //OBITOS  - Junior 08/10/2012
    
    //=========================================================================================//
    public List<ObitosBean> listarObitos() {
        return new ObitoJpaDao().findEntities();
    }

    public List<ObitosBean> pesquisarObitosCriteria(String termoPesquisa) {
        return new ObitoJpaDao().pesquisarCriteria(termoPesquisa);
    }

    public List<ObitosBean> pesquisarObitos(String termoPesquisa) {
        return new ObitoJpaDao().pesquisar(termoPesquisa);
    }

    public ObitosBean pesquisarObitos(ObitosBean obitos) {
        return new ObitoJpaDao().findEntity(obitos.getPkObitos());
    }

    public void cadastrarObitos(ObitosBean obitos) {
        new ObitoJpaDao().create(obitos);
    }

    public void atualizarObitos(ObitosBean obitos) {
        new ObitoJpaDao().update(obitos);
    }

    public void excluirObitos(ObitosBean obitos) {
        new ObitoJpaDao().delete(obitos);
    }

//    public List<EspecialidadesBean> listaEspecialidade() {
//        return new EspecialidadeJpaDao().findEntities();
//    }
    
    
    // Parceiros Ewerton
    public List<ParceirosBean> listarParceiros() {
        return new ParceiroJpaDao().findEntities();
    }

    public List<ParceirosBean> pesquisarParceirosCriteria(String termoPesquisa) {
        return new ParceiroJpaDao().pesquisarCriteria(termoPesquisa);
    }

    public List<ParceirosBean> pesquisarParceiros(String termoPesquisa) {
        return new ParceiroJpaDao().pesquisar(termoPesquisa);
    }

    public ParceirosBean pesquisarParceiros(ParceirosBean parceiros) {
        return new ParceiroJpaDao().findEntity(parceiros.getPkParceiros());
    }

    public void cadastrarParceiros(ParceirosBean parceiros) {
        new ParceiroJpaDao().create(parceiros);
    }

    public void atualizarParceiros(ParceirosBean parceiros) {
        new ParceiroJpaDao().update(parceiros);
    }

    public void excluirParceiros(ParceirosBean parceiros) {
        new ParceiroJpaDao().delete(parceiros);
    }

    
}
