package util;

import java.util.Collection;

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
}
