package util;

import javax.servlet.http.Cookie;

/**
 *
 * @author Rick
 */
public class CookieCutter {
    public static String getCookie( Cookie[] cookies, String cookieName ) {
        String cookieValue = null;

        if( cookies != null && cookies.length > 0 ) {
            for( int i = 0; i < cookies.length; i++ ) {
                if( cookies[ i ].getName().equals( cookieName ) ) {
                    cookieValue = cookies[ i ].getValue();
                }
            }
        }

        return cookieValue;
    }
}
