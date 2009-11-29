<form action="bookSearch" method="get">
    <p><label for="searchKeywords">${lexicon.search}</label>
        <input type="text" name="keywords" id="searchKeywords" />
        <input type="submit" value="${lexicon.go}" /> [<a href="bookSearch?action=advancedSearch">${lexicon.advancedSearch}</a>]</p>
</form>