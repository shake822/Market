<%@ page import="com.comtop.mobile.market.GoodPicture" %>



<div class="fieldcontain ${hasErrors(bean: goodPictureInstance, field: 'imgName', 'error')} required">
	<label for="imgName">
		<g:message code="goodPicture.imgName.label" default="Img Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="imgName" required="" value="${goodPictureInstance?.imgName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: goodPictureInstance, field: 'indexOrder', 'error')} required">
	<label for="indexOrder">
		<g:message code="goodPicture.indexOrder.label" default="Index Order" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="indexOrder" type="number" value="${goodPictureInstance.indexOrder}" required=""/>

</div>

