package br.app.sisau.jsf;

import br.app.sisau.beans.PessoaBean;
import br.app.sisau.service.AuditoriaService;
import br.app.sisau.service.I18nService;
import br.app.sisau.service.MailService;
import br.app.sisau.service.Service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
@ManagedBean(name = "loginMB")
@ViewScoped
public class LoginManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(LoginManagedBean.class);
    private String username;
    private String password;
    private String email;
    private boolean firstTimeLogin = false;
    private PessoaBean pessoa;

    public LoginManagedBean() {
        List<PessoaBean> listaPessoas = Service.getInstance().listarPessoas();
        if (listaPessoas.isEmpty()) {
            firstTimeLogin = true;
            pessoa = new PessoaBean();
        } else {
            firstTimeLogin = false;
        }
    }

    public PessoaBean getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaBean pessoa) {
        this.pessoa = pessoa;
    }

    public boolean isFirstTimeLogin() {
        return firstTimeLogin;
    }

    public void setFirstTimeLogin(boolean firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String doLogin() {
        if (isCamposValidos()) {
            try {
                PessoaBean pessoa = Service.getInstance().realizaLogin(this.username, this.password);

                FacesContext ctx = FacesContext.getCurrentInstance();
                Map sessionMap = ctx.getExternalContext().getSessionMap();
                UserSessionManagedBean userSessionMB = (UserSessionManagedBean) sessionMap.get("userSessionMB");
                if (userSessionMB == null) {
                    userSessionMB = new UserSessionManagedBean();
                    sessionMap.put("userSessionMB", userSessionMB);
                }
                userSessionMB.setLoggedUser(pessoa);
                
                AuditoriaService.getInstance().gravarAcaoUsuario(pessoa, "Login no Sistema", "Login", "Efetuou login no sistema.");
                
                return "pessoas?faces-redirect=true";

            } catch (Exception e) {
                this.username = "";
                this.password = "";
                FacesContext.getCurrentInstance().addMessage("invalido", new FacesMessage(e.getMessage()));
                return "login";
            }
        } else {
            return "login";
        }
    }

    private boolean isCamposValidos() {
        boolean camposValidos = true;
        if ("".equals(username)) {
            FacesContext.getCurrentInstance().addMessage("invalido", new FacesMessage(I18nService.getInstance().getKey("username_required")));
            camposValidos = false;
        }
        if ("".equals(password)) {
            FacesContext.getCurrentInstance().addMessage("invalido", new FacesMessage(I18nService.getInstance().getKey("password_required")));
            camposValidos = false;
        }
        return camposValidos;
    }

    public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userSessionMB");
        return "login?faces-redirect=true";
    }

    public void recuperarSenha() {
        try {
            PessoaBean pessoa = Service.getInstance().pesquisarPessoaEmail(email);

            if (pessoa == null) {
                FacesContext.getCurrentInstance().addMessage("invalido", new FacesMessage(I18nService.getInstance().getKey("mail_not_found")));
            } else {

                FacesContext.getCurrentInstance().addMessage("invalido", new FacesMessage(I18nService.getInstance().getKey("mail_sent")));
                String password = Service.getInstance().getRandomPassword(8);
                pessoa.setPassword(Service.getInstance().encryptPassword(password));

                Service.getInstance().atualizarPessoa(pessoa);

                String subject = "Recuperação de Senha";
                String setTo = pessoa.getEmail();
                //Para envio de e-mail com HTML: Aspas duplas devem ser precedidas de barra.
                String message = "Sua senha foi alterada pelo sistema. \n"
                        + "Sua nova senha encontra-se abaixo: \n"
                        + "Nome de Usuário - " + pessoa.getUsername() + " \n"
                        + "Senha - " + password;
                MailService.getInstance().sendMail(subject, setTo, message);
            }

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("invalido", new FacesMessage(ex.getMessage()));
        }
    }

    public void cadastrar() {
        pessoa.setPassword(Service.getInstance().encryptPassword(password));
        Service.getInstance().cadastrarPessoa(pessoa);
        firstTimeLogin = false;
        FacesContext.getCurrentInstance().addMessage("invalido", new FacesMessage(I18nService.getInstance().getKey("add_success")));

        String subject = "Cadastro no Sistema";
        String setTo = pessoa.getEmail();
        //Para envio de e-mail com HTML: Aspas duplas devem ser precedidas de barra.
        String message = "Seu cadastro foi efetuado no sistema. \n"
                + "Seus dados: \n"
                + "Nome de Usuário - " + pessoa.getUsername() + " \n"
                + "Senha - " + password;
        MailService.getInstance().sendMail(subject, setTo, message);

        pessoa = new PessoaBean();
    }
}
