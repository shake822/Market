
<%@ page import="com.comtop.mobile.market.Good"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'good.label', default: 'Good')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-good" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="list" action="index">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="show-good" class="content scaffold-show" role="main">
		<h1>
			<g:message code="default.show.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<ol class="property-list good">

			<g:if test="${goodInstance?.name}">
				<li class="fieldcontain"><span id="name-label"
					class="property-label"><g:message code="good.name.label"
							default="Name" /></span> <span class="property-value"
					aria-labelledby="name-label"><g:fieldValue
							bean="${goodInstance}" field="name" /></span></li>
			</g:if>


			<g:if test="${goodInstance?.code}">
				<li class="fieldcontain"><span id="code-label"
					class="property-label"><g:message code="good.code.label"
							default="Code" /></span> <span class="property-value"
					aria-labelledby="code-label"><g:fieldValue
							bean="${goodInstance}" field="code" /></span></li>
			</g:if>

			<g:if test="${goodInstance?.state}">
				<li class="fieldcontain"><span id="state-label"
					class="property-label"><g:message code="good.state.label"
							default="State" /></span> <span class="property-value"
					aria-labelledby="state-label"><g:fieldValue
							bean="${goodInstance}" field="state" /></span></li>
			</g:if>


			<g:if test="${goodInstance?.description}">
				<li class="fieldcontain"><span id="description-label"
					class="property-label"><g:message
							code="good.description.label" default="Description" /></span> <span
					class="property-value" aria-labelledby="description-label"><g:fieldValue
							bean="${goodInstance}" field="description" /></span></li>
			</g:if>


			<g:if test="${goodInstance?.price}">
				<li class="fieldcontain"><span id="price-label"
					class="property-label"><g:message code="good.price.label"
							default="Price" /></span> <span class="property-value"
					aria-labelledby="price-label"><g:fieldValue
							bean="${goodInstance}" field="price" /></span></li>
			</g:if>

			<g:if test="${goodInstance?.recency}">
				<li class="fieldcontain"><span id="recency-label"
					class="property-label"><g:message code="good.recency.label"
							default="Recency" /></span> <span class="property-value"
					aria-labelledby="recency-label"><g:fieldValue
							bean="${goodInstance}" field="recency" /></span></li>
			</g:if>

			<g:if test="${goodInstance?.type}">
				<li class="fieldcontain"><span id="type-label"
					class="property-label"><g:message code="good.type.label"
							default="Type" /></span> <span class="property-value"
					aria-labelledby="type-label"><g:fieldValue
							bean="${goodInstance}" field="type" /></span></li>
			</g:if>

			<g:if test="${goodInstance?.user}">
				<li class="fieldcontain"><span id="user-label"
					class="property-label"><g:message code="good.user.label"
							default="User" /></span> <span class="property-value"
					aria-labelledby="user-label"><g:link controller="user"
							action="show" id="${goodInstance?.user?.id}">
							${goodInstance?.user?.username}
						</g:link></span></li>
			</g:if>

			<g:if test="${goodInstance?.createTime}">
				<li class="fieldcontain"><span id="createTime-label"
					class="property-label"><g:message
							code="good.createTime.label" default="Create Time" /></span> <span
					class="property-value" aria-labelledby="createTime-label"><g:formatDate
							date="${goodInstance?.createTime}" /></span></li>
			</g:if>


			<g:if test="${goodInstance?.updateTime}">
				<li class="fieldcontain"><span id="updateTime-label"
					class="property-label"><g:message
							code="good.updateTime.label" default="Update Time" /></span> <span
					class="property-value" aria-labelledby="updateTime-label"><g:formatDate
							date="${goodInstance?.updateTime}" /></span></li>
			</g:if>
			<g:if test="${goodInstance?.pictures}">
				<li class="fieldcontain"><span id="pictures-label"
					class="property-label"><g:message code="good.pictures.label"
							default="Pictures" /></span> <g:each in="${goodInstance.pictures}"
						var="p">
						<g:img uri="${request.contextPath}/image/index?uuid=${p?.id}" height="100"  />
					</g:each></li>
			</g:if>










		</ol>
		<g:form url="[resource:goodInstance, action:'delete']" method="DELETE">
			<fieldset class="buttons">
				<g:link class="edit" action="edit" resource="${goodInstance}">
					<g:message code="default.button.edit.label" default="Edit" />
				</g:link>
				<g:actionSubmit class="delete" action="delete"
					value="${message(code: 'default.button.delete.label', default: 'Delete')}"
					onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
