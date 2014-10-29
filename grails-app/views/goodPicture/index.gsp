
<%@ page import="com.comtop.mobile.market.GoodPicture" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'goodPicture.label', default: 'GoodPicture')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-goodPicture" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-goodPicture" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="imgName" title="${message(code: 'goodPicture.imgName.label', default: 'Img Name')}" />
					
						<g:sortableColumn property="indexOrder" title="${message(code: 'goodPicture.indexOrder.label', default: 'Index Order')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${goodPictureInstanceList}" status="i" var="goodPictureInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${goodPictureInstance.id}">${fieldValue(bean: goodPictureInstance, field: "imgName")}</g:link></td>
					
						<td>${fieldValue(bean: goodPictureInstance, field: "indexOrder")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${goodPictureInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
