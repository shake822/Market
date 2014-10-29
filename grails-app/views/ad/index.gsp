
<%@ page import="com.comtop.mobile.market.Ad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ad.label', default: 'Ad')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ad" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<th>${message(code: 'ad.imgName.label', default: 'Img Path')}</th>
						
						<th>${message(code: 'ad.description.label', default: 'Description')}</th>
						
					
						<g:sortableColumn property="indexOrder" title="${message(code: 'ad.indexOrder.label', default: 'Index Order')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${adInstanceList}" status="i" var="adInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${adInstance.id}">${fieldValue(bean: adInstance, field: "imgName")}</g:link></td>
						<td>${fieldValue(bean: adInstance, field: "description")}</td>
						<td>${fieldValue(bean: adInstance, field: "indexOrder")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${adInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
