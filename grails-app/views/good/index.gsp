
<%@ page import="com.comtop.mobile.market.Good" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'good.label', default: 'Good')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-good" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-good" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'good.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="createTime" title="${message(code: 'good.createTime.label', default: 'Create Time')}" />
					
						<g:sortableColumn property="deleteFlag" title="${message(code: 'good.deleteFlag.label', default: 'Delete Flag')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'good.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'good.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="price" title="${message(code: 'good.price.label', default: 'Price')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${goodInstanceList}" status="i" var="goodInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${goodInstance.id}">${fieldValue(bean: goodInstance, field: "code")}</g:link></td>
					
						<td><g:formatDate date="${goodInstance.createTime}" /></td>
					
						<td><g:formatBoolean boolean="${goodInstance.deleteFlag}" /></td>
					
						<td>${fieldValue(bean: goodInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: goodInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: goodInstance, field: "price")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${goodInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
