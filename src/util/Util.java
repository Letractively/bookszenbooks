package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Rick
 */
public class Util {
    public static String joinArray( String[] array, String delimiter ) {
        StringBuilder buffer = new StringBuilder();

        if( array == null || array.length < 1 ) {
            return null;
        }

        for( int i = 0; i < array.length - 1; i++ ) {
            buffer.append( array[ i ] ).append( delimiter );
        }

        buffer.append( array[ array.length - 1 ] );

        return buffer.toString();
    }

    public static String joinArray( Collection<String> array, String delimiter ) {
        return joinArray( ( String[] ) array.toArray( new String[0] ), delimiter );
    }

    public static String toUpperCaseFirst( String string ) {
        return string.substring( 0, 1 ).toUpperCase() + string.substring( 1 );
    }

    /**
     * Attempts to parse a date in standard dd/MM/yyyy format.
     *
     * @param date The date string to parse.
     * @return The parsed Date object on success, null if the date is invalid.
     */
    public static Date parseDate( String date ) {
        SimpleDateFormat format = new SimpleDateFormat( "dd/MM/yyyy" );

        format.setLenient( false );

        try {
            return format.parse( date );
        } catch( ParseException e ) {
            return null;
        }
    }
}
