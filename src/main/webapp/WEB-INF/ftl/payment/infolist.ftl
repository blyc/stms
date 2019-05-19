<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>升学记录</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/shiro.demo.js"></script>
    <script src="${basePath}/js/ajaxfileupload.js"></script>
    <script src="${basePath}/js/jQuery.regex.js"></script>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 4/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left4 3/>
        <div class="col-md-10">
            <h2>升学记录</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/申请人">
                    </div>
                    <span class=""> <#--pull-right -->

                        <button type="submit" class="btn btn-primary">查询</button>

                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>校区</th>
                        <th>班级</th>
                        <th>讲师</th>
                        <th>班主任</th>
                        <th>阶段</th>
                        <th>班级人数</th>
                        <th>升学人数</th>
                        <th>升学率</th>
                        <th>时间</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${(it.vClass.companyname)?default('未设置')}</td>
                            <td>${it.vClass.classname?default('未设置')}</td>
                            <td>${it.vClass.jsname?default('未设置')}</td>
                            <td>${it.vClass.bzrname?default('未设置')}</td>
                            <td>${it.stage?default('未设置')}</td>
                            <td>${it.num?string.number}</td>
                            <td>${it.paynum?string.number}</td>
                            <td>${it.payval?string("0.00%")}</td>
                            <td>${it.createTime?string("yyyy/MM/dd")}</td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="10">没有升学记录</td>
                    </tr>
                </#if>
                </table>
            <#if page?exists>
                <div class="pagination pull-right">
                ${page.pageHtml}
                </div>
            </#if>
            </form>
        </div>
    </div>
</div>
</body>
</html>