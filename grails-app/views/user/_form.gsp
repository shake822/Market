<%@ page import="com.comtop.mobile.market.domain.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'account', 'error')} required">
	<label for="account">
		<g:message code="user.account.label" default="Account" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="account" required="" value="${userInstance?.account}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="user.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="address" required="" value="${userInstance?.address}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'department', 'error')} required">
	<label for="department">
		<g:message code="user.department.label" default="Department" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="department" required="" value="${userInstance?.department}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'headImg', 'error')} required">
	<label for="headImg">
		<g:message code="user.headImg.label" default="Head Img" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="headImg" required="" value="${userInstance?.headImg}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${userInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'phone', 'error')} required">
	<label for="phone">
		<g:message code="user.phone.label" default="Phone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="phone" required="" value="${userInstance?.phone}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>

</div>

