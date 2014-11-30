<%@ page import="com.comtop.mobile.market.Good" %>
<%@ page import="com.comtop.mobile.market.Classify" %>

<div
        class="fieldcontain ${hasErrors(bean: goodInstance, field: 'name', 'error')} required">
    <label for="name"><g:message code="good.name.label"
                                 default="Name"/> <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${goodInstance?.name}"/>

</div>

<div
        class="fieldcontain ${hasErrors(bean: goodInstance, field: 'description', 'error')} required">
    <label for="description"><g:message
            code="good.description.label" default="Description"/> <span
            class="required-indicator">*</span>
    </label>
    <g:textArea name="description" required="" rows="3"
                value="${goodInstance?.description}"/>

</div>


<div
        class="fieldcontain ${hasErrors(bean: goodInstance, field: 'price', 'error')} required">
    <label for="price"><g:message code="good.price.label"
                                  default="Price"/> <span class="required-indicator">*</span>
    </label>
    <g:field min="0" name="price" type="number"
             value="${fieldValue(bean: goodInstance, field: 'price')}" required=""/>
</div>

<div
        class="fieldcontain ${hasErrors(bean: goodInstance, field: 'recency', 'error')} required">
    <label for="recency"><g:message code="good.recency.label"
                                    default="Recency"/> <span class="required-indicator">*</span>
    </label>
    <g:select name="recency" required="" optionKey="key" optionValue="value" value="${goodInstance?.recency}"
              from="${flash.goodRecency}"/>
</div>

<div
        class="fieldcontain ${hasErrors(bean: goodInstance, field: 'status', 'error')} required">
    <label for="status"><g:message code="good.status.label"
                                   default="Status"/> <span class="required-indicator">*</span>
    </label>
    <g:select name="status" optionKey="key" value="${goodInstance?.status}" optionValue="value"
              from="${flash.goodStatus}"/>
</div>


<div
        class="fieldcontain ${hasErrors(bean: goodInstance, field: 'transStatus', 'error')} required">
    <label for="transStatus"><g:message code="good.transStatus.label"
                                   default="transStatus"/> <span class="required-indicator">*</span>
    </label>
    <g:select name="transStatus" optionKey="key" value="${goodInstance?.transStatus}" optionValue="value"
              from="${flash.goodTransStatus}"/>
</div>

<div
        class="fieldcontain ${hasErrors(bean: goodInstance, field: 'classify', 'error')} required">
    <label for="classify"><g:message code="good.classify.label"
                                     default="Classify"/> <span class="required-indicator">*</span>
    </label>
    <g:select name="classify" required="" optionKey="name" optionValue="name"
              value="${goodInstance?.classify}" from="${Classify.list()}"/>
</div>


<div
        class="fieldcontain ${hasErrors(bean: goodInstance, field: 'user', 'error')} required">
    <label for="user"><g:message code="good.user.label"
                                 default="User"/> <span class="required-indicator">*</span>
    </label>
    <g:select id="user" name="user.id"
              from="${com.comtop.mobile.market.User.list()}" optionKey="id"
              optionValue="username" required="" value="${goodInstance?.user?.id}"
              class="many-to-one"/>

</div>

<div
        class="fieldcontain ${hasErrors(bean: goodInstance, field: 'pictures', 'error')}">
    <label for="user"><g:message code="good.pictures.label"
                                 default="Pictures"/>
    </label>

    <g:each in="${[0, 1, 2, 3]}" var="index">
        <g:img onclick="clickImg(${index})" id="showImg${index}"
               uri="${request.contextPath}/image/index?uuid=null"
               height="100"/>
        <input type="file" style="display: none;" name="imgFile${index}"
               id="imgFile${index}" accept=".png,.jpg"
               value="${pictureItem?.imgName}"/>
    </g:each>
    <g:each in="${goodInstance?.pictures}" var="pic">
        <div type="hasPic" imgName="${pic.imgName}" style="visibility: hidden"
             id="${request.contextPath}/image/index?uuid=${pic.id}" indexOrder="${pic.indexOrder}"/>
    </g:each>
</div>

<script type="text/javascript">

    $(function () {

        $("div[type='hasPic']").each(function (index) {
            $("#showImg" + $(this).attr("indexOrder")).attr("src", $(this).attr("id"));
        });

        $("input[type='file']").each(function (index) {
            $(this).change(function onChange(e) {
                var file = e.target.files || e.dataTransfer.files;
                if (file && file.length > 0) {
                    var reader = new FileReader();
                    reader.onload = function () {
                        $("#showImg" + index).attr("src", this.result);
                    }
                    reader.readAsDataURL(file[0]);
                }
            });
        });
    });

    function clickImg(index) {
        $("#imgFile" + index).click()
    }
</script>