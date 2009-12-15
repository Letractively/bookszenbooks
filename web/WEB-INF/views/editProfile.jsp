<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp" />

<p>${lexicon.editProfileDesc}</p>

<form action="editProfile" method="post">
    <fieldset>
        <legend>${lexicon.profileGeneral}</legend>
        <input type="hidden" name="action" value="save" />
        <c:if test="${not empty formErrors.firstName}"><p class="error">${formErrors.firstName}</p></c:if>
        <label for="firstName">${lexicon.firstName}:</label><input type="text" name="firstName" id="firstName" value="<c:out value="${param.firstName}" default="${user.firstName}"></c:out>" /><br />
        <c:if test="${not empty formErrors.lastName}"><p class="error">${formErrors.lastName}</p></c:if>
        <label for="lastName">${lexicon.lastName}:</label><input type="text" name="lastName" id="lastName" value="<c:out value="${param.lastName}" default="${user.lastName}"></c:out>" /><br />
        <c:if test="${not empty formErrors.address}"><p class="error">${formErrors.address}</p></c:if>
        <label for="address">${lexicon.address}:</label><input type="text" name="address" id="address" value="<c:out value="${param.address}" default="${user.address}"></c:out>" /><br />
        <c:if test="${not empty formErrors.city}"><p class="error">${formErrors.city}</p></c:if>
        <label for="city">${lexicon.city}:</label><input type="text" name="city" id="city" value="<c:out value="${param.city}" default="${user.city}"></c:out>" /><br />
        <c:if test="${not empty formErrors.state}"><p class="error">${formErrors.state}</p></c:if>
        <label for="state">${lexicon.state}:</label><input type="text" name="state" id="state" value="<c:out value="${param.state}" default="${user.state}"></c:out>" /><br />
        <c:if test="${not empty formErrors.postalCode}"><p class="error">${formErrors.postalCode}</p></c:if>
        <label for="postalcode">${lexicon.postalCode}:</label><input type="text" name="postalCode" id="postalCode" value="<c:out value="${param.postalCode}" default="${user.postalCode}"></c:out>" /><br />
        <c:if test="${not empty formErrors.country}"><p class="error">${formErrors.country}</p></c:if>
        <label for="country">${lexicon.country}:</label>
            <select name="country" id="country">
                <c:forEach var="country" items="${countries}">
                    <c:choose>
                        <c:when test="${(not empty param.country and param.country eq country[0]) or (empty param.country and country[0] eq user.country)}">
                            <option value="${country[0]}" selected="selected">${country[1]}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${country[0]}">${country[1]}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select><br />
        <c:if test="${not empty formErrors.phone}"><p class="error">${formErrors.phone}</p></c:if>
        <label for="phone">${lexicon.phone}:</label><input type="text" name="phone" id="phone" value="<c:out value="${param.phone}" default="${user.phone}"></c:out>" /><br />
        <c:if test="${not empty formErrors.birthDate}"><p class="error">${formErrors.birthDate}</p></c:if>
        <label for="birthDate">${lexicon.birthDate}:</label><input type="text" name="birthDate" id="birthDate" value="<c:out value="${param.birthDate}" default="${user.birthDate}"></c:out>" /><br />
    </fieldset>
    <fieldset>
        <legend>${lexicon.changeEmail}</legend>
        <label>${lexicon.currentEmail}:</label><span class="label">${authUser.email}</span><br />
        <c:if test="${not empty formErrors.newEmail}"><p class="error">${formErrors.newEmail}</p></c:if>
        <label for="newEmail">${lexicon.newEmail}:</label><input type="text" name="newEmail" id="newEmail" value="<c:out value="${param.newEmail}"></c:out>" /><br />
    </fieldset>
    <fieldset>
        <legend>${lexicon.changePassword}</legend>
        <c:if test="${not empty formErrors.currentPassword}"><p class="error">${formErrors.currentPassword}</p></c:if>
        <label for="currentPassword">${lexicon.currentPassword}:</label><input type="password" name="currentPassword" id="currentPassword" /><br />
        <c:if test="${not empty formErrors.newPassword}"><p class="error">${formErrors.newPassword}</p></c:if>
        <label for="newPassword">${lexicon.newPassword}:</label><input type="password" name="newPassword" id="newPassword" /><br />
        <label for="newPasswordConfirm">${lexicon.confirmPassword}:</label><input type="password" name="newPasswordConfirm" id="newPasswordConfirm" /><br />
    </fieldset>
    
    <p class="submit"><input type="submit" value="${lexicon.saveProfile}" class="button submit" /><br />
</form>

<c:import url="footer.jsp" />