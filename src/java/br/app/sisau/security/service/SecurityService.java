package br.app.sisau.security.service;

import br.app.sisau.beans.EspecialidadesBean;
import br.app.sisau.beans.PessoaBean;
import br.app.sisau.daos.EspecialidadeJpaDao;
import br.app.sisau.security.beans.ObjetoProtegidoBean;
import br.app.sisau.security.beans.PapelBean;
import br.app.sisau.security.daos.ObjetoProtegidoJpaDao;
import br.app.sisau.security.daos.PapelJpaDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Artur
 */
public class SecurityService {

    private static SecurityService instance = new SecurityService();
    Logger logger = Logger.getLogger(this.getClass());

    private SecurityService() {
    }

    public static SecurityService getInstance() {
        return instance;
    }

    public List<PapelBean> listarPapeis() {
        List<PapelBean> listaPapeis = new ArrayList<PapelBean>();
        listaPapeis = new PapelJpaDao().findEntities();

        return listaPapeis;
    }

    public List<PapelBean> pesquisarPapel(String pesquisa) {
        List<PapelBean> listaPapeis = new ArrayList<PapelBean>();
        listaPapeis = new PapelJpaDao().pesquisar(pesquisa);

        return listaPapeis;
    }

    public PapelBean buscarPapel(Integer id) {
        return new PapelJpaDao().findEntity(id);
    }

    public List<ObjetoProtegidoBean> listarObjetosProtegidos() {
        List<ObjetoProtegidoBean> listaObjetosProtegidos = new ArrayList<ObjetoProtegidoBean>();
        listaObjetosProtegidos = new ObjetoProtegidoJpaDao().pesquisar();

        return listaObjetosProtegidos;
    }

    public List<ObjetoProtegidoBean> pesquisarObjetosProtegidos(String pesquisa) {
        List<ObjetoProtegidoBean> listaObjetosProtegidos = new ArrayList<ObjetoProtegidoBean>();
        listaObjetosProtegidos = new ObjetoProtegidoJpaDao().pesquisar(pesquisa);

        return listaObjetosProtegidos;
    }

    public boolean verificarPermissao(PessoaBean usuario, String nomeRecurso) {
        List<PapelBean> gruposUsuario = usuario.getPapeis();
        List<ObjetoProtegidoBean> objetosProtegidos = new ArrayList<ObjetoProtegidoBean>();
        PapelBean roleTemp;
        boolean temAcesso = false;
        for (int i = 0; i < gruposUsuario.size() && !temAcesso; i++) {
            roleTemp = gruposUsuario.get(i);
            objetosProtegidos = roleTemp.getObjetosProtegidos();
            for (int j = 0; j < objetosProtegidos.size() && !temAcesso; j++) {
                temAcesso = temAcesso || objetosProtegidos.get(j).getNome().equals(nomeRecurso);
            }
        }
        return temAcesso;
    }

    public void cadastraObjetoProtegido(ObjetoProtegidoBean objetoProtegido) {
        new ObjetoProtegidoJpaDao().create(objetoProtegido);
    }

    public void atualizarObjetoProtegido(ObjetoProtegidoBean objetoProtegido) {
        new ObjetoProtegidoJpaDao().update(objetoProtegido);
    }

    public void excluirObjetoProtegido(ObjetoProtegidoBean objetoProtegido) {
        new ObjetoProtegidoJpaDao().delete(objetoProtegido);
    }

    public void cadastraPapel(PapelBean papel) {
        new PapelJpaDao().create(papel);
    }

    public void atualizarPapel(PapelBean papel) {
        new PapelJpaDao().update(papel);
    }

    public void excluirPapel(PapelBean papel) {
        new PapelJpaDao().delete(papel);
    }

    public ObjetoProtegidoBean getObjetoProtegido(Integer id) {
        return new ObjetoProtegidoJpaDao().findEntity(id);
    }

   
}
