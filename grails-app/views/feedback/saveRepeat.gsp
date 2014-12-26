<!DOCTYPE html>
<%@ page import="com.comtop.mobile.market.Feedback" %>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'feedback.label', default: 'Feedback')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-feedback" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-feedback" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${feedbackInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${feedbackInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:feedbackInstance, action:'mSave']" >
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: feedbackInstance, field: 'content', 'error')} required">

						<label >
							<g:message code="feedback.content.label" default="Content" />
						</label>
						${feedbackInstance?.content}

					</div>
					<div class="fieldcontain ${hasErrors(bean: feedbackInstance, field: 'content', 'error')} required">
						<label for="content">
							 回复
							<span class="required-indicator">*</span>
						</label>
						<g:textField name="content" required="" value=""/>

					</div>
					<g:hiddenField name="feedbackId" required="" value="${feedbackInstance?.id}"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
