<%@ page import="java.sql.ResultSet, data.*, business.User, util.BooksZenBooks,java.io.InputStream,java.net.URL" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/includes/header.jsp" %>


<jsp:useBean id="user" class="business.User" scope="request" />
<%

BooksZenBooks bzb = new BooksZenBooks();
bzb.initDatabase( "dbConfig.xml" );

ResultSet t = bzb.getDBDriver().select( "user", new String[]{ "*" } , "userId = 1" );

/*User u = new User();
u.init( bzb.getDBDriver() );
u.setFirstName( "John" );
u.setLastName( "Johnson" );
u.setPassword( "MD5(12345)" );
u.setEmail( "john@host.com" );
u.setJoinDate( new java.util.Date() );
u.setBirthDate( new java.util.Date() );

u.save();*/



if( t.next() ) {
    user.init( bzb.getDBDriver() );
    user.populate( t );

    //user.remove();
    

    /*user.setEmail( "rvarella@google.edu");
    user.setCity( "Leicestah" );
    user.save();*/

        %>
        email: ${user.email} <br />
        <%
}

%>

<%@include file="/includes/footer.jsp" %>
