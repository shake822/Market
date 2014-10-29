<%@ page import="com.comtop.mobile.market.Ad" %>



<div class="fieldcontain ${hasErrors(bean: adInstance, field: 'imgName', 'error')} required">
	<label for="imgName">
		<g:message code="ad.imgName.label" default="Img Path" />
		<span class="required-indicator">*</span>
	</label>
    <input   type="file" name="imgFile" id="imgFile" value="${adInstance?.imgName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="ad.description.label" default="Description" />
	</label>
	<g:textArea escapeHtml="true" row="6" name="description" value="${adInstance?.description}"/>
	<g:hiddenField name="imgName" value="1"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adInstance, field: 'indexOrder', 'error')} required">
	<label for="indexOrder">
		<g:message code="ad.indexOrder.label" default="Index Order" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="indexOrder" type="number" value="${adInstance.indexOrder}" required=""/>

</div>

