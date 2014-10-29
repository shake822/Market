
<%@ page import="com.comtop.mobile.market.domain.Favorites" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'favorites.label', default: 'Favorites')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-favorites" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-favorites" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="createTime" title="${message(code: 'favorites.createTime.label', default: 'Create Time')}" />
					
						<g:sortableColumn property="deleteFlag" title="${message(code: 'favorites.deleteFlag.label', default: 'Delete Flag')}" />
					
						<th><g:message code="favorites.good.label" default="Good" /></th>
					
						<th><g:message code="favorites.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${favoritesInstanceList}" status="i" var="favoritesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${favoritesInstance.id}">${fieldValue(bean: favoritesInstance, field: "createTime")}</g:link></td>
					
						<td><g:formatBoolean boolean="${favoritesInstance.deleteFlag}" /></td>
					
						<td>${fieldValue(bean: favoritesInstance, field: "good")}</td>
					
						<td>${fieldValue(bean: favoritesInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${favoritesInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
