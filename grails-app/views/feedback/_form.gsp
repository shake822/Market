<%@ page import="com.comtop.mobile.market.Feedback" %>



%{--<div class="fieldcontain ${hasErrors(bean: feedbackInstance, field: 'repeats', 'error')} ">
	<label for="repeats">
		<g:message code="feedback.repeats.label" default="Repeats" />
		
	</label>
	<g:select name="repeats" from="${com.comtop.mobile.market.Feedback.list()}" multiple="multiple" optionKey="id" size="5" value="${feedbackInstance?.repeats*.id}" class="many-to-many"/>

</div>--}%

<div class="fieldcontain ${hasErrors(bean: feedbackInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="feedback.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="content" required="" value="${feedbackInstance?.content}"/>

</div>

%{--<div class="fieldcontain ${hasErrors(bean: feedbackInstance, field: 'createTime', 'error')} required">
	<label for="createTime">
		<g:message code="feedback.createTime.label" default="Create Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="createTime" precision="day"  value="${feedbackInstance?.createTime}"  />

</div>--}%

<div class="fieldcontain ${hasErrors(bean: feedbackInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="feedback.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.comtop.mobile.market.User.list()}" optionKey="id" optionValue="username" required="" value="${feedbackInstance?.user?.id}" class="many-to-one"/>

</div>

