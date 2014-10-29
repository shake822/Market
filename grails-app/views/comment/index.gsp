
<%@ page import="com.comtop.mobile.market.domain.Comment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comment.label', default: 'Comment')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-comment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-comment" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="content" title="${message(code: 'comment.content.label', default: 'Content')}" />
					
						<g:sortableColumn property="createTime" title="${message(code: 'comment.createTime.label', default: 'Create Time')}" />
					
						<th><g:message code="comment.fromUser.label" default="From User" /></th>
					
						<th><g:message code="comment.good.label" default="Good" /></th>
					
						<g:sortableColumn property="indexOrder" title="${message(code: 'comment.indexOrder.label', default: 'Index Order')}" />
					
						<th><g:message code="comment.toUser.label" default="To User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${commentInstanceList}" status="i" var="commentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${commentInstance.id}">${fieldValue(bean: commentInstance, field: "content")}</g:link></td>
					
						<td><g:formatDate date="${commentInstance.createTime}" /></td>
					
						<td>${fieldValue(bean: commentInstance, field: "fromUser")}</td>
					
						<td>${fieldValue(bean: commentInstance, field: "good")}</td>
					
						<td>${fieldValue(bean: commentInstance, field: "indexOrder")}</td>
					
						<td>${fieldValue(bean: commentInstance, field: "toUser")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${commentInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
