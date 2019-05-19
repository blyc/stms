<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>升学</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
<link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
<link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<#--<script src="${basePath}/js/shiro.demo.js"></script>-->
    <script baseUrl="${basePath}" src="${basePath}/js/hp/payment.list.js"></script>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 7/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left7 1/>
        <div class="col-md-10">
            <h2>升学</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/班级/讲师/班主任" maxlength="10">
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
                        <th>专业</th>
                        <th>班级人数</th>
                        <th>年级</th>
                        <th>讲师</th>
                        <th>导师</th>
                        <th>班主任</th>
                        <th>阶段</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>${it_index+1}</td>
                            <td>${it.companyname?default('未设置')}</td>
                            <td>${it.classname?default('未设置')}</td>
                            <td>${it.majorname?default('未设置')}</td>
                            <td>${it.num?default('未设置')}</td>
                            <td>${it.grade?default('未设置')}级</td>
                            <td>${it.jsname?default('未设置')}</td>
                            <td>${it.dsname?default('未设置')}</td>
                            <td>${it.bzrname?default('未设置')}</td>
                            <td>${it.stage?default('未设置')}</td>
                            <td>

                                <@shiro.hasPermission name="/payment.shtml">
                                    <a href="javascript:_open2('${it.ccid}')">登记升学情况</a>
                                </@shiro.hasPermission>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan=11>没有找到班级</td>
                    </tr>
                </#if>
                </table>
                <input type="hidden" id="hccid">
                <input type="hidden" id="hcsid">
            <#if page?exists>
                <div class="pagination pull-right">
                ${page.pageHtml}
                </div>
            </#if>
            </form>
        </div>
    </div><#--/row-->
</div>

<div class="modal fade" id="addPayment" tabindex="-1" role="dialog" aria-labelledby="addPaymentLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addPaymentLabel">登记升学信息</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxPaymentForm">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="hideccid">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="savepaybtn"  class="btn btn-primary">保存
                </button>
            </div>
        </div>
    </div>
</div>

</body>
</html>