<%@ page import="com.comtop.mobile.market.Comment" %>



<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="comment.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="content" required="" value="${commentInstance?.content}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'createTime', 'error')} required">
	<label for="createTime">
		<g:message code="comment.createTime.label" default="Create Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="createTime" precision="day"  value="${commentInstance?.createTime}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'fromUser', 'error')} required">
	<label for="fromUser">
		<g:message code="comment.fromUser.label" default="From User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fromUser" name="fromUser.id" from="${com.comtop.mobile.market.User.list()}" optionKey="id" required="" value="${commentInstance?.fromUser?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'good', 'error')} required">
	<label for="good">
		<g:message code="comment.good.label" default="Good" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="good" name="good.id" from="${com.comtop.mobile.market.Good.list()}" optionKey="id" required="" value="${commentInstance?.good?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'indexOrder', 'error')} required">
	<label for="indexOrder">
		<g:message code="comment.indexOrder.label" default="Index Order" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="indexOrder" type="number" value="${commentInstance.indexOrder}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'toUser', 'error')} required">
	<label for="toUser">
		<g:message code="comment.toUser.label" default="To User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="toUser" name="toUser.id" from="${com.comtop.mobile.market.User.list()}" optionKey="id" required="" value="${commentInstance?.toUser?.id}" class="many-to-one"/>

</div>

