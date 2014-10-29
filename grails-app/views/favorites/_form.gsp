<%@ page import="com.comtop.mobile.market.Favorites" %>



<div class="fieldcontain ${hasErrors(bean: favoritesInstance, field: 'createTime', 'error')} required">
	<label for="createTime">
		<g:message code="favorites.createTime.label" default="Create Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="createTime" precision="day"  value="${favoritesInstance?.createTime}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: favoritesInstance, field: 'deleteFlag', 'error')} ">
	<label for="deleteFlag">
		<g:message code="favorites.deleteFlag.label" default="Delete Flag" />
		
	</label>
	<g:checkBox name="deleteFlag" value="${favoritesInstance?.deleteFlag}" />

</div>

<div class="fieldcontain ${hasErrors(bean: favoritesInstance, field: 'good', 'error')} required">
	<label for="good">
		<g:message code="favorites.good.label" default="Good" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="good" name="good.id" from="${com.comtop.mobile.market.Good.list()}" optionKey="id" required="" value="${favoritesInstance?.good?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: favoritesInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="favorites.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.comtop.mobile.market.User.list()}" optionKey="id" required="" value="${favoritesInstance?.user?.id}" class="many-to-one"/>

</div>

