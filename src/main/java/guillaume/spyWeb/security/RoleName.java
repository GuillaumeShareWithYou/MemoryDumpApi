package guillaume.spyWeb.security;

public enum RoleName {
    ADMIN,
    USER,
    TEACHER;

    public String getName() {
        return "ROLE_".concat(this.name());
    }
}
