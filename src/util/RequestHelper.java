package util;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rick
 */
public class RequestHelper {
    public static void sanitizeRequest( HttpServletRequest request ) {
        Enumeration e = request.getParameterNames();
        String paramName;
        Object value;

        while( e.hasMoreElements() ) {
            paramName = e.nextElement().toString();
            value = sanitize( request.getAttribute( paramName ) );

            request.setAttribute( paramName, value );
        }
    }

    public static Object sanitize( Object value ) {
        Object cleanValue;
        String[] temp;

        if( value.getClass().isArray() ) {
            temp = ( String[] ) value;

            for( int i = 0; i < temp.length; i++ ) {
                temp[ i ] = sanitize( temp[ i ] ).toString();
            }

            return temp;
        }

        cleanValue = value.toString(); //StringEscapeUtils.escapeSql( value.toString() );

        return cleanValue;
    }

    public static String getString( String paramName, HttpServletRequest request ) {
        return request.getParameter( paramName ) != null ? ( String ) request.getParameter( paramName ).trim() : "";
    }

    public static int getInt( String paramName, HttpServletRequest request ) {
        String value = getString( paramName, request );
        int numericValue;

        try {
            numericValue = Integer.parseInt( value );
        } catch( NumberFormatException e ) {
            numericValue = 0;
        }

        return numericValue;
    }

    public static double getDouble( String paramName, HttpServletRequest request ) {
        String value = getString( paramName, request );
        double numericValue;

        try {
            numericValue = Double.parseDouble( value );
        } catch( NumberFormatException e ) {
            numericValue = 0;
        }

        return numericValue;
    }
}
