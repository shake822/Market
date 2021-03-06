
<%@ page import="com.comtop.mobile.market.Ad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ad.label', default: 'Ad')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-ad" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list ad">
			
				
				
				<g:if test="${adInstance?.imgName}">
				<li class="fieldcontain">
					<span id="imgName-label" class="property-label"><g:message code="ad.imgName.label" default="Img Path" /></span>
						
						<g:img uri="${request.contextPath}/image/index?uuid=${adInstance?.id}" height="200"  />
						<span class="property-value" aria-labelledby="imgName-label"><g:fieldValue bean="${adInstance}" field="imgName"/></span>
					
				</li>
				</g:if>


				<g:if test="${adInstance?.imgAction}">
					<li class="fieldcontain">
						<span id="imgAction-label" class="property-label"><g:message code="ad.imgAction.label" default="ImgAction" /></span>

						<span class="property-value" aria-labelledby="imgAction-label"><g:fieldValue bean="${adInstance}" field="imgAction"/></span>

					</li>
				</g:if>


				<g:if test="${adInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="ad.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${adInstance}" field="description"/></span>
					
				</li>
				</g:if>
				
				
				<g:if test="${adInstance?.indexOrder}">
				<li class="fieldcontain">
					<span id="indexOrder-label" class="property-label"><g:message code="ad.indexOrder.label" default="Index Order" /></span>
					
						<span class="property-value" aria-labelledby="indexOrder-label"><g:fieldValue bean="${adInstance}" field="indexOrder"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:adInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${adInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
