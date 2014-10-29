<%@ page import="com.comtop.mobile.market.domain.Good" %>



<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="good.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${goodInstance?.code}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'createTime', 'error')} required">
	<label for="createTime">
		<g:message code="good.createTime.label" default="Create Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="createTime" precision="day"  value="${goodInstance?.createTime}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'deleteFlag', 'error')} ">
	<label for="deleteFlag">
		<g:message code="good.deleteFlag.label" default="Delete Flag" />
		
	</label>
	<g:checkBox name="deleteFlag" value="${goodInstance?.deleteFlag}" />

</div>

<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="good.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${goodInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="good.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${goodInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'pictures', 'error')} ">
	<label for="pictures">
		<g:message code="good.pictures.label" default="Pictures" />
		
	</label>
	<g:select name="pictures" from="${com.comtop.mobile.market.domain.GoodPicture.list()}" multiple="multiple" optionKey="id" size="5" value="${goodInstance?.pictures*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="good.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="price" value="${fieldValue(bean: goodInstance, field: 'price')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'recency', 'error')} required">
	<label for="recency">
		<g:message code="good.recency.label" default="Recency" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="recency" required="" value="${goodInstance?.recency}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'state', 'error')} required">
	<label for="state">
		<g:message code="good.state.label" default="State" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="state" required="" value="${goodInstance?.state}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="good.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="type" required="" value="${goodInstance?.type}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'updateTime', 'error')} required">
	<label for="updateTime">
		<g:message code="good.updateTime.label" default="Update Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="updateTime" precision="day"  value="${goodInstance?.updateTime}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: goodInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="good.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.comtop.mobile.market.domain.User.list()}" optionKey="id" required="" value="${goodInstance?.user?.id}" class="many-to-one"/>

</div>

