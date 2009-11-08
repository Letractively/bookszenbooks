<%@ page import="java.sql.ResultSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="bzb" class="org.bzb.model.BooksZenBooks" scope="request" />



<%String[] where = { "*" };
ResultSet t = bzb.getDBDriver().select( "user", where , null );

if( t == null ) {
    %>LOL, NULL<%
    return;
    }

while( t.next() ) {
    %>
    RESULT<br />
    <%
    }

%>