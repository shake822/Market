
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
						
						<th>${message(code: 'good.name.label', default: 'Name')}</th>
						
						<th>${message(code: 'good.code.label', default: 'Code')}</th>
						
						<g:sortableColumn property="price" title="${message(code: 'good.price.label', default: 'Price')}" />
						
						
						<th>${message(code: 'good.recency.label', default: 'Recency')}</th>
						
						
						<g:sortableColumn property="state" title="${message(code: 'good.state.label', default: 'State')}" />
						
						<g:sortableColumn property="createTime" title="${message(code: 'good.createTime.label', default: 'Create Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${goodInstanceList}" status="i" var="goodInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${goodInstance.id}">${fieldValue(bean: goodInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: goodInstance, field: "code")}</td>
						
						
						<td>${fieldValue(bean: goodInstance, field: "price")}</td>
						
						<td>${fieldValue(bean: goodInstance, field: "recency")}</td>
						
						
						<td>${fieldValue(bean: goodInstance, field: "state")}</td>
						
						<td><g:formatDate date="${goodInstance.createTime}" /></td>
					
					
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
