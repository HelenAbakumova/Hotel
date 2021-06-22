package entity;

public class RegistrationUser {
    private String name;
    private String login;
    private String email;
    private String password;
    private String rePassword;
    private String roleName;

    public RegistrationUser(String name, String login, String email, String password, String rePassword, String roleName) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.roleName = roleName;
    }

    public RegistrationUser() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
