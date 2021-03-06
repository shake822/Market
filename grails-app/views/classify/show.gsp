
<%@ page import="com.comtop.mobile.market.Classify"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'classify.label', default: 'Classify')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-classify" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="list" action="index">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="show-classify" class="content scaffold-show" role="main">
		<h1>
			<g:message code="default.show.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<ol class="property-list classify">

			<g:if test="${classifyInstance?.parent}">
				<li class="fieldcontain"><span id="parent-label"
					class="property-label"><g:message
							code="classify.parent.label" default="Parent" /></span> <span
					class="property-value" aria-labelledby="parent-label"><g:link
							controller="classify" action="show"
							id="${classifyInstance?.parent?.id}">
							${classifyInstance?.parent?.name}
						</g:link></span></li>
			</g:if>
			
			<g:if test="${classifyInstance?.name}">
				<li class="fieldcontain"><span id="name-label"
					class="property-label"><g:message code="classify.name.label"
							default="Name" /></span> <span class="property-value"
					aria-labelledby="name-label"><g:fieldValue
							bean="${classifyInstance}" field="name" /></span></li>
			</g:if>
			
			<g:if test="${classifyInstance?.code}">
				<li class="fieldcontain"><span id="code-label"
					class="property-label"><g:message code="classify.code.label"
							default="Code" /></span> <span class="property-value"
					aria-labelledby="code-label"><g:fieldValue
							bean="${classifyInstance}" field="code" /></span></li>
			</g:if>

			
			
			<g:if test="${classifyInstance?.description}">
				<li class="fieldcontain"><span id="name-label"
					class="property-label"><g:message code="classify.description.label"
							default="Description" /></span> <span class="property-value"
					aria-labelledby="name-label"><g:fieldValue
							bean="${classifyInstance}" field="description" /></span></li>
			</g:if>
			
		</ol>
		<g:if test="${classifyInstance?.code.length() > 2}">
			<g:form url="[resource:classifyInstance, action:'delete']"
				method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${classifyInstance}">
						<g:message code="default.button.edit.label" default="Edit" />
					</g:link>
					<g:actionSubmit class="delete" action="delete"
						value="${message(code: 'default.button.delete.label', default: 'Delete')}"
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</g:if>
	</div>
</body>
</html>
