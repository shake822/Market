<%@ page import="com.comtop.mobile.market.Good"%>
<%@ page import="com.comtop.mobile.market.Classify"%>

<div
	class="fieldcontain ${hasErrors(bean: goodInstance, field: 'name', 'error')} required">
	<label for="name"> <g:message code="good.name.label"
			default="Name" /> <span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${goodInstance?.name}" />

</div>

<div
	class="fieldcontain ${hasErrors(bean: goodInstance, field: 'description', 'error')} required">
	<label for="description"> <g:message
			code="good.description.label" default="Description" /> <span
		class="required-indicator">*</span>
	</label>
	<g:textArea name="description" required="" rows="3"
		value="${goodInstance?.description}" />

</div>




<div
	class="fieldcontain ${hasErrors(bean: goodInstance, field: 'price', 'error')} required">
	<label for="price"> <g:message code="good.price.label"
			default="Price" /> <span class="required-indicator">*</span>
	</label>
	<g:field name="price" type="number"
		value="${fieldValue(bean: goodInstance, field: 'price')}" required="" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: goodInstance, field: 'recency', 'error')} required">
	<label for="recency"> <g:message code="good.recency.label"
			default="Recency" /> <span class="required-indicator">*</span>
	</label>
	<g:select name="recency" required="" value="${goodInstance?.recency}"
		from="${['全新', '九成', '八成', '七成', '六成']}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: goodInstance, field: 'state', 'error')} required">
	<label for="state"> <g:message code="good.state.label"
			default="State" /> <span class="required-indicator">*</span>
	</label>
	<g:textField name="state" required="" value="${goodInstance?.state}" />

</div>

<div
	class="fieldcontain ${hasErrors(bean: goodInstance, field: 'type', 'error')} required">
	<label for="type"> <g:message code="good.type.label"
			default="Type" /> <span class="required-indicator">*</span>
	</label>
	<g:select name="type" required="" optionKey="code" optionValue="name"
		value="${goodInstance?.type}" from="${Classify.list() }" />
</div>


<div
	class="fieldcontain ${hasErrors(bean: goodInstance, field: 'user', 'error')} required">
	<label for="user"> <g:message code="good.user.label"
			default="User" /> <span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id"
		from="${com.comtop.mobile.market.User.list()}" optionKey="id"
		optionValue="username" required="" value="${goodInstance?.user?.id}"
		class="many-to-one" />

</div>
 
<div
	class="fieldcontain ${hasErrors(bean: goodInstance, field: 'pictures', 'error')}">
	<label for="user"> <g:message code="good.pictures.label"
			default="Pictures" />
	</label>

	<g:each in="${[0,1,2,3] }" var="index">
		<g:set var="pictureItem"
			value="${ goodInstance?.pictures?.getAt(index)}"></g:set>
		<g:img onclick="clickImg(${index})" id="showImg${index}"
			uri="${request.contextPath}/image/index?uuid=${pictureItem?.id}"
			height="100" />
		<input type="file" style="display: none;" name="imgFile${index}"
			id="imgFile${index}" accept=".png,.jpg"
			value="${pictureItem?.imgName}" />
	</g:each>
</div>

<script type="text/javascript">

	
	$(function() {
		$("input[type='file']").each(function(index){
			$(this).change(function onChange(e){
				var file = e.target.files || e.dataTransfer.files;
				if (file && file.length >0) {
					var reader = new FileReader();
					reader.onload = function() {
						$("#showImg"+index).attr("src",this.result);
					}
					reader.readAsDataURL(file[0]);
				}
			});
			});
	});
	function clickImg(index) {
		$("#imgFile"+index).click()
	}
</script>