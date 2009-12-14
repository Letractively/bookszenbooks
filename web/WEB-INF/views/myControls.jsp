<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<p>${lexicon.myControlsDesc}</p>

<ul>
    <li><a href="myControls?action=profile">${lexicon.editProfile}</a></li>
    <li><a href="myControls?action=listings">${lexicon.myListings}</a></li>
</ul>

<c:import url="footer.jsp" />