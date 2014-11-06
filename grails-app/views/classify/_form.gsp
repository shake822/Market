<%@ page import="com.comtop.mobile.market.Classify"%>



<div
	class="fieldcontain ${hasErrors(bean: classifyInstance, field: 'parent', 'error')} ">
	<label for="parent"> <g:message code="classify.parent.label"
			default="Parent" />

	</label>
	
	<select id="parent" onchange="onParentChange(this)" name="parent.id" class="many-to-one"  value="${classifyInstance?.parent?.id}">
		<option value="null">---</option>
		<g:each in="${com.comtop.mobile.market.Classify.findAllWhere(parent:null)}" var="classifyItem">
			<g:if test="${classifyInstance?.parent?.id == classifyItem.id}">
			 	<option value="${classifyItem.id}" code="${classifyItem.code }" selected="selected"  >${classifyItem.name }</option>
			</g:if>
			<g:else>
				<option value="${classifyItem.id}" code="${classifyItem.code }"  >${classifyItem.name }</option>
			</g:else>
		</g:each>
	</select>

</div>

<div
	class="fieldcontain ${hasErrors(bean: classifyInstance, field: 'code', 'error')} required">
	<label for="code"> <g:message code="classify.code.label"
			default="Code" /> <span class="required-indicator">*</span>
	</label>
	<g:textField name="code" maxlength="4"  required="" value="${classifyInstance?.code}" />

</div>

<div
	class="fieldcontain ${hasErrors(bean: classifyInstance, field: 'name', 'error')} required">
	<label for="name"> <g:message code="classify.name.label"
			default="Name" /> <span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${classifyInstance?.name}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: classifyInstance, field: 'description', 'error')}">
	<label for="name"> <g:message code="classify.description.label"
			default="Description" />
	</label>
	<g:textField name="description"
		value="${classifyInstance?.description}" />
</div>

<script type="text/javascript">
	function onParentChange(parent) {
		if (parent.value !== "null" ) {
			var parentCode = $("option[value='"+parent.value+"']").attr("code");
			var _code = $("#code");
			_code.val(parentCode + _code.val());
		}
	}
</script>