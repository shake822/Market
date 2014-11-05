<%@ page import="com.comtop.mobile.market.Ad"%>



<div
	class="fieldcontain ${hasErrors(bean: adInstance, field: 'imgName', 'error')} required">
	<label for="imgName"> <g:message code="ad.imgName.label"
			default="Img Path" /> <span class="required-indicator">*</span>
	</label>
	<input type="file" style="display: none;" name="imgFile" id="imgFile"
		accept=".png,.jpg" value="${adInstance?.imgName}" />
	
	<g:img onclick="clickImg()"  id="showImg"
		uri="${request.contextPath}/image/index?uuid=${adInstance?.id}"
		height="200" />
	<br/>
</div>


<div
	class="fieldcontain">
	<label for="imgNameSpan">  
	</label>
	<span id="imgNameSpan">
		${adInstance?.imgName}
	</span>
	<g:hiddenField  type="string" name="imgName" value="${adInstance?.imgName}" />
</div>


<div
	class="fieldcontain ${hasErrors(bean: adInstance, field: 'description', 'error')} ">
	<label for="description"> <g:message
			code="ad.description.label" default="Description" />
	</label>
	<g:textArea escapeHtml="true" row="3" name="description"
		value="${adInstance?.description}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: adInstance, field: 'indexOrder', 'error')} required">
	<label for="indexOrder"> <g:message code="ad.indexOrder.label"
			default="Index Order" /> <span class="required-indicator">*</span>
	</label>
	<g:field name="indexOrder" type="number"
		value="${adInstance.indexOrder}" required="" />

</div>

<script type="text/javascript">
	$(function() {
		$("#imgFile").change(function(e) {
			var file = e.target.files || e.dataTransfer.files;
			if (file && file.length >0) {
				var reader = new FileReader();
				reader.onload = function() {
					$("#showImg").attr("src",this.result);
				}

				reader.readAsDataURL(file[0]);
				$("#imgName").val(file[0].name);
				$("#imgNameSpan").html(file[0].name);
			}
		});
	})
	function clickImg() {
		$("#imgFile").click()
	}
</script>