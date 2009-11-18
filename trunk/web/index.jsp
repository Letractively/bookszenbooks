<%@ page import="java.sql.ResultSet, data.*, business.User, util.BooksZenBooks" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/includes/header.jsp" %>


<jsp:useBean id="user" class="business.User" scope="request" />
<%

BooksZenBooks bzb = new BooksZenBooks();
bzb.initDatabase( "dbConfig.xml" );

ResultSet t = bzb.getDBDriver().select( "user", new String[]{ "*" } , null );

if( t.next() ) {
    user.populate( t );
    user.setDriver( bzb.getDBDriver() );
    System.out.println( ">>>EMAIL" + user.getEmail());

    user.setEmail( "someotheremail@domain.com");
    user.save();

        %>
        email: ${user.email} <br />
        <%
}

%>

<%@include file="/includes/footer.jsp" %>
