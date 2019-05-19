<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>发布管理</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/shiro.demo.js"></script>
    <script src="${basePath}/js/hp/release.list.js"></script>

    <style>
        .table th {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 12/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
        <@_left.left12 1/>
        <div class="col-md-10">
            <h2>发布列表</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入题目名称/班级" maxlength="10">
                    </div>
                    <span class="">
                        <button type="submit" class="btn btn-primary">查询</button>
<#--                    <@shiro.hasPermission name="/position/addPosition.shtml">-->
                        <a id="addBtn" class="btn btn-success" onclick="javascript:_openDlg();">发布</a>
<#--                    </@shiro.hasPermission>-->
                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>name</th>
                        <th>校区</th>
                        <th>班级</th>
                        <th>出题人</th>
                        <th>结束时间</th>
                        <th>验证码</th>
                        <th>状态</th>
                        <th>提交人数</th>
                        <th>操作</th>
                    </tr>
                    <#if page?exists && page.list?size gt 0 >
                        <#list page.list as it>
                            <tr>
                                <td>${it_index+1}</td>
                                <td>${it.name?default('未设置')}</td>
                                <td>${it.ccname?default('未设置')}</td>
                                <td>${it.cname?default('未设置')}</td>
                                <td>${it.ename?default('未设置')}</td>
                                <td>${it.failureTime?string('yyyy-MM-dd HH:mm:ss')?default('未设置')}</td>
                                <td>${it.key?default('未设置')}</td>
                                <td>
                                    <#switch it.state>
                                        <#case "1">
                                            有效
                                            <#break>
                                        <#default>
                                            已失效
                                    </#switch>
                                </td>
                                <td>${it.sumbitted?default('未设置')}</td>
                                <td>
                                    <#--                                <@shiro.hasPermission name="/position/deletePositionById.shtml">-->
                                    <a href="javascript:_delete([${it.qbid}]);">删除</a>
                                    <#--                                </@shiro.hasPermission>-->
                                </td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td class="text-center danger" colspan="6">未找到题目发布信息</td>
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
    </div><#--/row-->
</div>
<#--弹框-->
<div class="modal fade" id="addPosition" tabindex="-1" role="dialog" aria-labelledby="addPositionLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addPositionLabel">题目发布信息添加</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="col-md-1">
                        <div class="form-group">
                            <label for="recipient-name" class="control-label">题目选择:</label>
                            <select class="form-control" id="nation" name="nation"></select>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label for="recipient-name" class="control-label">失效时间:</label>
                            <input type="text" class="form-control" name="name" id="name" placeholder="请定义失效时间" required
                                   maxlength="5">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="_addPosition();" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<#--/弹框-->
</body>
</html>