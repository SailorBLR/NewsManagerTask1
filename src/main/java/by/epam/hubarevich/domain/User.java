package by.epam.hubarevich.domain;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public class User extends Domain {

    private Integer userId;
    private String userName;
    private String userLogin;
    private String userPassword;

    public User() {
    }

    public User(Integer userId, String userName, String userLogin, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!userId.equals(user.userId)) {
            return false;
        }
        if (!userName.equals(user.userName)) {
            return false;
        }
        if (!userLogin.equals(user.userLogin)) {
            return false;
        }
        return userPassword.equals(user.userPassword);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + userLogin.hashCode();
        result = 31 * result + userPassword.hashCode();
        return result;
    }
}
