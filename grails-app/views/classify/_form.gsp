<%@ page import="com.comtop.mobile.market.Classify"%>



<div
	class="fieldcontain ${hasErrors(bean: classifyInstance, field: 'parent', 'error')} ">
	<label for="parent"> <g:message code="classify.parent.label"
			default="Parent" />

	</label>
	<g:select id="parent" onChange="onParentChange(this)" name="parent.id"
		from="${com.comtop.mobile.market.Classify.findAllWhere(parent:null)}"
		optionValue="name" value="${classifyInstance?.parent?.id}:${classifyInstance?.parent?.code}"
		class="many-to-one" noSelection="['null': '---']" />

</div>

<div
	class="fieldcontain ${hasErrors(bean: classifyInstance, field: 'code', 'error')} required">
	<label for="code"> <g:message code="classify.code.label"
			default="Code" /> <span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${classifyInstance?.code}" />

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
		if (parent.value !== "null" && parent.value.indexOf(":") > 0) {
			var parentCode = parent.value.split(":")[1];
			var _code = $("#code");
			_code.val(parentCode + _code.val());
		}
	}
	function onSubmit() {
		var _parent = $("#parent");
		if (_parent.val().indexOf(":") > 0) {
			_parent.val(_parent.val().split(":")[0]);
		}
	}
</script>