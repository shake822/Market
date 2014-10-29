
<%@ page import="com.comtop.mobile.market.domain.HotSearch" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'hotSearch.label', default: 'HotSearch')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-hotSearch" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-hotSearch" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="keyWord" title="${message(code: 'hotSearch.keyWord.label', default: 'Key Word')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${hotSearchInstanceList}" status="i" var="hotSearchInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${hotSearchInstance.id}">${fieldValue(bean: hotSearchInstance, field: "keyWord")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${hotSearchInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
