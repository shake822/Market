
<%@ page import="com.comtop.mobile.market.Classify"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'classify.label', default: 'Classify')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#list-classify" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="list-classify" class="content scaffold-list" role="main">
		<h1>
			<g:message code="default.list.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<table>
			<thead>
				<tr>
					

					<g:sortableColumn property="name"
						title="${message(code: 'classify.name.label', default: 'Name')}" />
					
					<g:sortableColumn property="code"
						title="${message(code: 'classify.code.label', default: 'Code')}" />
						
					<th><g:message code="classify.description.label"
							default="Description" /></th>

					<th><g:message code="classify.parent.label" default="Parent" /></th>

				</tr>
			</thead>
			<tbody>
				<g:each in="${classifyInstanceList}" status="i"
					var="classifyInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
						
						
						<g:if test="${classifyInstance?.parent}">
							<td><g:link action="show" id="${classifyInstance.id}">
									${fieldValue(bean: classifyInstance, field: "name")}
								</g:link></td>
						</g:if>
						<g:else>
							<td>${fieldValue(bean: classifyInstance, field: "name")}</td>
						</g:else>
						
						
						<td>
							${fieldValue(bean: classifyInstance, field: "code")}
						</td>
						
						
						
						<td>
							${fieldValue(bean: classifyInstance, field: "description")}
						</td>

						<td>
							${fieldValue(bean: classifyInstance, field: "parent.name")}
						</td>

						
					</tr>
				</g:each>
			</tbody>
		</table>
		<div class="pagination">
			<g:paginate total="${classifyInstanceCount ?: 0}" />
		</div>
	</div>
</body>
</html>
