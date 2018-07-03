package guillaume.spyWeb.security;

public class SecurityConstants {

    public static final String SECRET = "3213518325615";

    public static final String SIGN_UP_URL = "/session/register";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String COOKIE_TOKEN_NAME = "token";

    public static final String COOKIE_TOKEN_PATH = "/";

  //  public static final String HEADER_STRING = "Authorization";

    //En secondes
    public static final int COOKIE_LIFE_DURATION = 86400;
}
