<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>专业列表</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
<link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
<link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/shiro.demo.js"></script>
    <script baseUrl="${basePath}" src="${basePath}/js/hp/major.list.js"></script>
    <style>
        .table th {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 3/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left3 5/>
        <div class="col-md-10">
            <h2>专业列表</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入专业名称">
                    </div>
                    <span class=""> <#--pull-right -->

                        <button type="submit" class="btn btn-primary">查询</button>

                    <@shiro.hasPermission name="/major.shtml">
                        <a id="addBtn" class="btn btn-success">添加</a>
                    </@shiro.hasPermission>
               	         </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>专业名称</th>
                        <th>学生人数</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td> ${it_index+1}</td>
                            <td>${it.name?default('未设置')}</td>
                            <td>${it.num?default('未设置')}</td>
                            <td>
                                <@shiro.hasPermission name="/major/deleteMajorById.shtml">
                                    <a href="javascript:_delete([${it.mid}]);">删除</a>
                                </@shiro.hasPermission>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="3">没有找到专业信息</td>
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
<#--添加弹框-->
<div class="modal fade" id="addMajor" tabindex="-1" role="dialog" aria-labelledby="addMajorLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addMajorLabel">添加专业信息</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">专业名称:</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="请输入专业名称"
                               maxlength="50"/>
                    </div>
                <#--<div class="form-group">-->
                <#--<label for="recipient-name" class="control-label">专业编号:</label>-->
                <#--<input type="text" class="form-control" id="id" name="id" placeholder="请输入专业编号">-->
                <#--</div>-->
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="savebtn" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<#--/添加弹框-->

</body>
</html>