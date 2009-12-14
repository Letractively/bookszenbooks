<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<p>${lexicon.editProfileDesc}</p>

<form action="editProfile" method="post">
    <input type="hidden" name="action" value="register" />
    <c:if test="${formErrors.firstName != null}"><p class="error">${formErrors.firstName}</p></c:if>
    <label for="firstName">${lexicon.firstName}:</label><input type="text" name="firstName" id="firstName"  value="${user.firstName}" /><br />
    <c:if test="${formErrors.lastName != null}"><p class="error">${formErrors.lastName}</p></c:if>
    <label for="lastName">${lexicon.lastName}:</label><input type="text" name="lastName" id="lastName"  value="${user.lastName}" /><br />
    <c:if test="${formErrors.address != null}"><p class="error">${formErrors.address}</p></c:if>
    <label for="address">${lexicon.address}:</label><input type="text" name="address" id="address"  value="${user.address}" /><br />
    <c:if test="${formErrors.city != null}"><p class="error">${formErrors.city}</p></c:if>
    <label for="city">${lexicon.city}:</label><input type="text" name="city" id="city"  value="${user.city}" /><br />
    <c:if test="${formErrors.state != null}"><p class="error">${formErrors.state}</p></c:if>
    <label for="state">${lexicon.state}:</label><input type="text" name="state" id="state"  value="${user.state}" /><br />
    <c:if test="${formErrors.postalCode != null}"><p class="error">${formErrors.postalCode}</p></c:if>
    <label for="postalcode">${lexicon.postalCode}:</label><input type="text" name="postalCode" id="postalCode"  value="${user.postalCode}" /><br />
    <c:if test="${formErrors.country != null}"><p class="error">${formErrors.country}</p></c:if>
    <label for="country">${lexicon.country}:</label>
        <select name="country" id="country">
            <c:forEach var="country" items="${countries}">
                <c:choose>
                    <c:when test="${country[0] == user.country}">
                        <option value="${country[0]}" selected="selected">${country[1]}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${country[0]}">${country[1]}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select><br />
    <c:if test="${formErrors.phone != null}"><p class="error">${formErrors.phone}</p></c:if>
    <label for="phone">${lexicon.phone}:</label><input type="text" name="phone" id="phone"  value="${user.phone}" /><br />
    <c:if test="${formErrors.birthDate != null}"><p class="error">${formErrors.birthDate}</p></c:if>
    <label for="birthDate">${lexicon.birthDate}:</label><input type="text" name="birthDate" id="birthDate" value="${user.birthDate}" /><br />
    <p class="submit"><input type="submit" value="${lexicon.saveProfile}" class="button submit" /><br />
</form>

<c:import url="footer.jsp" />