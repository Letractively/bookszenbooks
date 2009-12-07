<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<p>${lexicon.registerDesc}</p>

<form action="registerAccount" method="post">
    <input type="hidden" name="action" value="register" />
    <c:if test="${formErrors.email != null}"><p class="error">${formErrors.email}</p></c:if>
    <p><label for="email">${lexicon.email}:</label><input type="text" name="email" id="email" value="${param.email}" /></p>
    <c:if test="${formErrors.password != null}"><p class="error">${formErrors.password}</p></c:if>
    <p><label for="password">${lexicon.password}:</label><input type="password" name="password" id="password" /></p>
    <p><label for="confirmPassword">${lexicon.confirmPassword}:</label><input type="password" name="confirmPassword" id="confirmPassword" /></p>
    <c:if test="${formErrors.firstName != null}"><p class="error">${formErrors.firstName}</p></c:if>
    <p><label for="firstName">${lexicon.firstName}:</label><input type="text" name="firstName" id="firstName"  value="${param.firstName}" /></p>
    <c:if test="${formErrors.lastName != null}"><p class="error">${formErrors.lastName}</p></c:if>
    <p><label for="lastName">${lexicon.lastName}:</label><input type="text" name="lastName" id="lastName"  value="${param.lastName}" /></p>
    <c:if test="${formErrors.address != null}"><p class="error">${formErrors.address}</p></c:if>
    <p><label for="address">${lexicon.address}:</label><input type="text" name="address" id="address"  value="${param.address}" /></p>
    <c:if test="${formErrors.city != null}"><p class="error">${formErrors.city}</p></c:if>
    <p><label for="city">${lexicon.city}:</label><input type="text" name="city" id="city"  value="${param.city}" /></p>
    <c:if test="${formErrors.state != null}"><p class="error">${formErrors.state}</p></c:if>
    <p><label for="state">${lexicon.state}:</label><input type="text" name="state" id="state"  value="${param.state}" /></p>
    <c:if test="${formErrors.postalCode != null}"><p class="error">${formErrors.postalCode}</p></c:if>
    <p><label for="postalcode">${lexicon.postalCode}:</label><input type="text" name="postalCode" id="postalCode"  value="${param.postalCode}" /></p>
    <c:if test="${formErrors.country != null}"><p class="error">${formErrors.country}</p></c:if>
    <p><label for="country">${lexicon.country}:</label>
        <select name="country" id="country">
            <c:forEach var="country" items="${countries}">
                <c:choose>
                    <c:when test="${country[0] == pageContext.request.locale.country}">
                        <option value="${country[0]}" selected="selected">${country[1]}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${country[0]}">${country[1]}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select></p>
    <c:if test="${formErrors.phone != null}"><p class="error">${formErrors.phone}</p></c:if>
    <p><label for="phone">${lexicon.phone}:</label><input type="text" name="phone" id="phone"  value="${param.phone}" /></p>
    <c:if test="${formErrors.birthDate != null}"><p class="error">${formErrors.birthDate}</p></c:if>
    <p><label for="birthDate">${lexicon.birthDate}:</label><input type="text" name="birthDate" id="birthDate" value="${param.birthDate}" /></p>
    <c:if test="${formErrors.agreeTerms != null}"><p class="error">${formErrors.agreeTerms}</p></c:if>
    <p><label for="agreeTerms">${lexicon.agreeTerms}</label><input type="checkbox" name="agreeTerms" id="agreeTerms" /></p>
    <p class="submit"><input type="submit" value="${lexicon.registerAccount}" /></p>
</form>

<c:import url="footer.jsp" />