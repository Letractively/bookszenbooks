<%@ page import="java.sql.ResultSet, data.*, business.User, util.BooksZenBooks" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/includes/header.jsp" %>


<jsp:useBean id="user" class="business.User" scope="request" />
<%

BooksZenBooks bzb = new BooksZenBooks();
bzb.initDatabase( "dbConfig.xml" );

ResultSet t = bzb.getDBDriver().select( "user", new String[]{ "*" } , null );

/*User u = new User();
u.setFirstName( "John" );
u.setLastName( "Johnson" );
u.setPassword( "MD5(12345)" );
u.setEmail( "john@host.com" );

u.save();*/

if( t.next() ) {
    user.populate( t );
    user.setDriver( bzb.getDBDriver() );

    user.setEmail( "rvarella@worcester.edu");
    user.setCity( "Leicester" );
    user.save();

        %>
        email: ${user.email} <br />
        <%
}

%>

<%@include file="/includes/footer.jsp" %>
