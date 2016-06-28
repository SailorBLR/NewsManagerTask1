package by.epam.hubarevich.domain;

/**
 * Created by Anton_Hubarevich on 6/20/2016.
 */
public class Role extends Domain {

    private Integer roleId;
    private String roleName;

    public Role() {
    }

    public Role(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
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

        Role role = (Role) o;

        if (!roleId.equals(role.roleId)) {
            return false;
        }
        return roleName.equals(role.roleName);

    }

    @Override
    public int hashCode() {
        int result = roleId.hashCode();
        result = 31 * result + roleName.hashCode();
        return result;
    }
}
