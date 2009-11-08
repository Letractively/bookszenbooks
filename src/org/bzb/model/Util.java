package org.bzb.model;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Rick
 */
public class Util {
    public static String joinArray( String[] array, String delimiter ) {
        StringBuilder buffer = new StringBuilder();

        if( array.length < 1 ) {
            return null;
        }

        for( int i = 0; i < array.length - 1; i++ ) {
            buffer.append( array[ i ] ).append( delimiter );
        }

        buffer.append( array[ array.length - 1 ] );

        return buffer.toString();
    }

    public static String joinArray( Collection<String> array, String delimiter ) {
        

        return joinArray( ( String[] ) array.toArray(), delimiter );
    }
}
