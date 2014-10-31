<%@ page import="com.comtop.mobile.market.Classify" %>

<div class="fieldcontain ${hasErrors(bean: classifyInstance, field: 'parent', 'error')} required">
	<label for="parent">
		<g:message code="classify.parent.label" default="Parent" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="parent" name="parent.id" from="${com.comtop.mobile.market.Classify.findAllWhere(parent:com.comtop.mobile.market.Classify.findWhere(code:'-1'))}" optionKey="id" required="" value="${classifyInstance?.parent?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: classifyInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="classify.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${classifyInstance?.code}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: classifyInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="classify.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${classifyInstance?.name}"/>

</div>



