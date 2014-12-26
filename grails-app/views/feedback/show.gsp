
<%@ page import="com.comtop.mobile.market.Feedback" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'feedback.label', default: 'Feedback')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-feedback" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-feedback" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list feedback">
			
				<g:if test="${feedbackInstance?.repeats}">
				<li class="fieldcontain">
					<span id="repeats-label" class="property-label"><g:message code="feedback.repeats.label" default="Repeats" /></span>
					
						<g:each in="${feedbackInstance.repeats}" var="r">
						<span class="property-value" aria-labelledby="repeats-label"><g:link controller="feedback" action="show" id="${r.id}">${r?.user.username}@${r?.content}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${feedbackInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="feedback.content.label" default="Content" /></span>
					
						<span class="property-value" aria-labelledby="content-label"><g:fieldValue bean="${feedbackInstance}" field="content"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${feedbackInstance?.createTime}">
				<li class="fieldcontain">
					<span id="createTime-label" class="property-label"><g:message code="feedback.createTime.label" default="Create Time" /></span>
					
						<span class="property-value" aria-labelledby="createTime-label"><g:formatDate date="${feedbackInstance?.createTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${feedbackInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="feedback.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${feedbackInstance?.user?.id}">${feedbackInstance?.user?.username}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:feedbackInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${feedbackInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:if  test="${feedbackInstance.isRepeat == false}"> <g:link class="edit" action="saveRepeat" resource="${feedbackInstance}">添加评论</g:link> </g:if>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
