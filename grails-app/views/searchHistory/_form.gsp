<%@ page import="com.comtop.mobile.market.SearchHistory" %>



<div class="fieldcontain ${hasErrors(bean: searchHistoryInstance, field: 'keyWord', 'error')} required">
	<label for="keyWord">
		<g:message code="searchHistory.keyWord.label" default="Key Word" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="keyWord" required="" value="${searchHistoryInstance?.keyWord}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: searchHistoryInstance, field: 'searchTime', 'error')} required">
	<label for="searchTime">
		<g:message code="searchHistory.searchTime.label" default="Search Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="searchTime" precision="day"  value="${searchHistoryInstance?.searchTime}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: searchHistoryInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="searchHistory.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.comtop.mobile.market.User.list()}" optionKey="id" required="" value="${searchHistoryInstance?.user?.id}" class="many-to-one"/>

</div>

