<%@ page import="com.comtop.mobile.market.HotSearch" %>



<div class="fieldcontain ${hasErrors(bean: hotSearchInstance, field: 'keyWord', 'error')} required">
	<label for="keyWord">
		<g:message code="hotSearch.keyWord.label" default="Key Word" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="keyWord" required="" value="${hotSearchInstance?.keyWord}"/>

</div>

