<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
    <style type="text/css" media="screen">
    #status {
        background-color: #eee;
        border: .2em solid #fff;
        margin: 2em 2em 1em;
        padding: 1em;
        width: 12em;
        float: left;
        -moz-box-shadow: 0px 0px 1.25em #ccc;
        -webkit-box-shadow: 0px 0px 1.25em #ccc;
        box-shadow: 0px 0px 1.25em #ccc;
        -moz-border-radius: 0.6em;
        -webkit-border-radius: 0.6em;
        border-radius: 0.6em;
    }

    .ie6 #status {
        display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
    }

    #status ul {
        font-size: 0.9em;
        list-style-type: none;
        margin-bottom: 0.6em;
        padding: 0;
    }

    #status li {
        line-height: 1.3;
    }

    #status h1 {
        text-transform: uppercase;
        font-size: 1.1em;
        margin: 0 0 0.3em;
    }

    #page-body {
        margin: 2em 1em 1.25em 18em;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    #controller-list ul {
        list-style-position: inside;
    }

    #controller-list li {
        line-height: 1.3;
        list-style-position: inside;
        margin: 0.25em 0;
    }

    @media screen and (max-width: 480px) {
        #status {
            display: none;
        }

        #page-body {
            margin: 0 1em 1em;
        }

        #page-body h1 {
            margin-top: 0;
        }
    }

    .doc-table {
        border-collapse: collapse;
        border-spacing: 0;
        margin-left: 9px;
        width: 99%;
        word-break: break-all;
    }

    .doc-table th, .doc-table td {
        border: 1px solid #ccc;
        padding: 0.3em 0.7em;
    }

    .doc-table th {
        background: none repeat scroll 0 0 #EEEEEE;
    }

    .doc-table pre {
        background: none repeat scroll 0 0 #FAFAFA;
        color: #006600;
        font-family: Verdana;
        font-size: 12px;
        padding: 5px;
    }

    </style>
</head>

<body>
<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="status" role="complementary">
    <h1>Application Status</h1>
    <ul>
        <li>App version: <g:meta name="app.version"/></li>
        <li>Grails version: <g:meta name="app.grails.version"/></li>
        <li>Groovy version: ${GroovySystem.getVersion()}</li>
        <li>JVM version: ${System.getProperty('java.version')}</li>
        <li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
        <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
        <li>Domains: ${grailsApplication.domainClasses.size()}</li>
        <li>Services: ${grailsApplication.serviceClasses.size()}</li>
        <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
    </ul>

    <h1>Installed Plugins</h1>
    <ul>
        <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
            <li>${plugin.name} - ${plugin.version}</li>
        </g:each>
    </ul>
</div>

<div id="page-body" role="main">
    <h1>Welcome to Grails</h1>

    <p>Congratulations, you have successfully started your first Grails application! At the moment
    this is the default page, feel free to modify it to either redirect to a controller or display whatever
    content you may choose. Below is a list of controllers that are currently deployed in this application,
    click on each to execute its default action:</p>

    <div id="controller-list" role="navigation">
        <table class="doc-table">
            <tbody>
            <tr>
                <th><strong>JSON通用格式:</strong></th>
            </tr>
            <tr>
                <td>
                    code：1表示成功；-1表示失败<br>
                    msg: 成功或失败的消息<br>
                    body:code为1时额外的业务信息
                </td>
            </tr>
            </tbody>
        </table>
        <table class="doc-table">
            <tbody>
            <tr>
                <th colspan="2"><strong>数据约定:</strong></th>
            </tr>
            <tr>
                <td>
                    商品类型
                </td>
                <td>
                    [-1:'最新', 0: '出售',1: '交换', 2: '求购']
                </td>
            </tr>
            <tr>
                <td>
                    商品新旧程度
                </td>
                <td>
                    [7: '八成以下', 8: '八成', 9: '九成', 10: '全新']
                </td>
            </tr>
            <tr>
                <td>
                    商品价格
                </td>
                <td>
                    0表示面议
                </td>
            </tr>
            </tbody>
        </table>

        <h2>修改完成的功能:</h2>
        <ul>
            <li class="controller"><g:link controller="ad">${message(code: 'ad.label', default: 'Ad')}</g:link></li>
            <ul style="margin-left: 50px">
                <li>获取广告列表url:<g:link controller="ad" action="mGet">ad/mGet</g:link></li>
                <table class="doc-table">
                    <tbody>
                    <tr>
                        <th><strong>入参</strong></th>
                        <th><strong>返回</strong></th>
                    </tr>
                    <tr>
                        <td>
                            无
                        </td>
                        <td>
                            body:<br>
                            [<br>{"id":"8a8ab2ec496065050149609ea6620e4f","url":"/image/index?uuid=8a8ab2ec496065050149609ea6620e4f","action":""},<br>{"id":"8a8ab2ec496065050149609ea6620e4f","url":"/image/index?uuid=8a8ab2ec496065050149609ea6620e4f","action":""}<br>]
                        </td>
                    </tr>
                    </tbody>
                </table>
            </ul>
            <li class="controller"><g:link
                    controller="classify">${message(code: 'classify.label', default: 'Classify')}</g:link></li>
            <ul style="margin-left: 50px">
                <li>获取分类url:<g:link controller="classify" action="mGet">classify/mGet</g:link></li>
                <table class="doc-table">
                    <tbody>
                    <tr>
                        <th><strong>入参</strong></th>
                        <th><strong>返回</strong></th>
                    </tr>
                    <tr>
                        <td>
                            无
                        </td>
                        <td>
                            body:<br>
                            [<br>{"name":"数码产品","id":"8a8ab2ec4998a169014998aa05870007"},<br>{"name":"食品饮料","id":"8a8ab2ec4998a169014998aa058c0008"}<br>]
                        </td>
                    </tr>
                    </tbody>
                </table>
            </ul>
            <li class="controller"><g:link
                    controller="good">${message(code: 'good.label', default: 'Good')}</g:link></li>
            <ul style="margin-left: 50px">
                <li>获取物品列表:<g:link controller="good" action="mFind">good/mFind</g:link></li>
                <table class="doc-table">
                    <tbody>
                    <tr>
                        <th><strong>入参</strong></th>
                        <th><strong>返回</strong></th>
                    </tr>
                    <tr>
                        <td>
                            pageSize:分页大小<br>
                            currentPage:当前页码<br>
                            status:物品状态([1: '交换', 0: '出售', 2: '求购',null:'最新'])
                        </td>
                        <td>
                            body:<br>
                            {<br>"hasMore":false,<br> "currentPage":1,<br>"data":<br>[{"classify":"数码产品","userId":"1","description":"流浪","deleteFlag":false,"name":"流浪","price":0,"recency":"10","createTime":"2014-11-23 15:12:54","status":"0","code":"xxx","picture":"/image/index?uuid=ff80818149daaf3d0149db7f22f0000d"}<br>]<br>}
                        </td>
                    </tr>
                    </tbody>
                </table>
                <li>获取物品详情:<g:link controller="good" action="mGet">good/mGet</g:link></li>
                <table class="doc-table">
                    <tbody>
                    <tr>
                        <th><strong>入参</strong></th>
                        <th><strong>返回</strong></th>
                    </tr>
                    <tr>
                        <td>
                            id:物品的ID
                        </td>
                        <td>
                            body:<br>
                            {"id":"8a8ab2ec49eb6b8a0149eb73eed30001","name":"123","classify":"数码产品","createTime":"2014-11-26 17:34:43","updateTime":"2014-11-26 17:34:43","deleteFlag":false,"userId":"8a8ab2ec496065050149606f1754098a","status":"1","recency":"7","price":0,"code":"xxx","description":"123","pictures":[{"indexOrder":2,"url":"/image/index?uuid=8a8ab2ec49eb6b8a0149eb73ecd10000"},{"indexOrder":3,"url":"/image/index?uuid=8a8ab2ec49eb6b8a0149eb7470160004"},{"indexOrder":1,"url":"/image/index?uuid=8a8ab2ec49eb6b8a0149eb746ff80003"},{"indexOrder":0,"url":"/image/index?uuid=8a8ab2ec49eb6b8a0149eb746fe60002"}]}}
                        </td>
                    </tr>
                    </tbody>
                </table>
                <li>新增物品:<g:link controller="good" action="mSave">good/mSave</g:link></li>
                <table class="doc-table">
                    <tbody>
                    <tr>
                        <th><strong>入参</strong></th>
                        <th><strong>返回</strong></th>
                    </tr>
                    <tr>
                        <td>
                            id:ID<br>
                            name:名称<br>
                            description:描述<br>
                            classify:分类<br>
                            status:状态<br>
                            price:价格(默认为0)<br>
                            code:编码<br>
                            recency:新旧程度<br>
                            deleteFlag:是否删除(默认为false)<br>
                        </td>
                        <td>
                            msg:
                        </td>
                    </tr>
                    </tbody>
                </table>
            </ul>
            <li class="controller"><g:link controller="login">登录</g:link></li>
            <ul style="margin-left: 50px">
                <li>登录:<g:link controller="login" action="mLogin">login/mLogin</g:link></li>
                <table class="doc-table">
                    <tbody>
                    <tr>
                        <th><strong>入参</strong></th>
                        <th><strong>返回</strong></th>
                    </tr>
                    <tr>
                        <td>
                            account :账号 <br>
                            password:密码(32位MD5加密后的大写)
                        </td>
                        <td>
                            body：{<br>"id":"8a8ab2ec496065050149606f1790099a",<br>"account":"zhaoqunqi",<br>"address":null,<br>
                            "department":null,<br>"headImg":null,<br>"password":"C33367701511B4F6020EC61DED352059",<br>"phone":null,<br>"username":"赵群齐"<br>}
                        </td>
                    </tr>
                    </tbody>
                </table>
                <li>登出:<g:link controller="login" action="mLogout">login/mLogout</g:link></li>
                <table class="doc-table">
                    <tbody>
                    <tr>
                        <th><strong>入参</strong></th>
                        <th><strong>返回</strong></th>
                    </tr>
                    <tr>
                        <td>
                            无
                        </td>
                        <td>
                            msg:"登出成功"
                        </td>
                    </tr>
                    </tbody>
                </table>
                <li>获取登录用户信息:<g:link controller="login" action="mGetUserInfo">login/mGetUserInfo</g:link></li>
                <table class="doc-table">
                    <tbody>
                    <tr>
                        <th><strong>入参</strong></th>
                        <th><strong>返回</strong></th>
                    </tr>
                    <tr>
                        <td>
                            无
                        </td>
                        <td>
                            同登录返回结果
                        </td>
                    </tr>
                    </tbody>
                </table>
            </ul>
            <li class="controller"><g:link controller="user">用户</g:link></li>
            <ul style="margin-left: 50px">
                <li>获取用户信息:<g:link controller="user" action="mGet">user/mGet</g:link></li>
                <table class="doc-table">
                    <tbody>
                    <tr>
                        <th><strong>入参</strong></th>
                        <th><strong>返回</strong></th>
                    </tr>
                    <tr>
                        <td>
                            id
                        </td>
                        <td>
                            body：{<br>"id":"8a8ab2ec496065050149606f1790099a",<br>"account":"zhaoqunqi",<br>"address":null,<br>
                            "department":null,<br>"headImg":null,<br>"password":"C33367701511B4F6020EC61DED352059",<br>"phone":null,<br>"username":"赵群齐"<br>}
                        </td>
                    </tr>
                    </tbody>
                </table>

            </ul>
        </ul>
    </div>

    <div id="controller-list" role="navigation">
        <h2>Available Controllers:</h2>
        <ul>
            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
                <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
            </g:each>
        </ul>
    </div>
</div>
</body>
</html>
