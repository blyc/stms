<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>部门列表</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/shiro.demo.js"></script>
    <script>

        //根据ID数组，删除
        function _delete(ids) {
            var index = layer.confirm("确定这" + ids.length + "个部门？", function () {
                var load = layer.load();
                $.post('${basePath}/department/deleteDepartMentById.shtml', {ids: ids.join(',')}, function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message, so.default), !0;
                    } else {
                        layer.msg('删除成功');
                        setTimeout(function () {
                            $('#formId').submit();
                        }, 1000);
                    }
                }, 'json');
                layer.close(index);
            });
        }

        function _openDlg() {
            $('#addDepartMent').modal();
        }

        function _addDepartment() {
            var name = $('#name').val(),
                    describtion = $('#describtion').val();

            if ($.trim(name) == '') {
                return layer.msg('部门名称不能为空。', so.default), !1;
            }

        <#--loding-->
            var load = layer.load();
            $.post('${basePath}/department/addDepartment.shtml', {name: name, describtion: describtion},
                    function (result) {
                        layer.close(load);
                        if (result && result.status != 200) {
                            return layer.msg(result.message, so.default), !1;
                        }
                        layer.msg('添加成功。');
                        setTimeout(function () {
                            $('#formId').submit();
                        }, 1000);
                    }, 'json');
        }

        $(function () {
            $('input[required]').before('<span style="color:red">*</span>');
        })
    </script>
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
    <@_left.left3 2/>
        <div class="col-md-10">
            <h2>部门列表</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入部门名称" maxlength="10">
                    </div>
                    <span class=""> <#--pull-right -->
                        <button type="submit" class="btn btn-primary">查询</button>
                    <@shiro.hasPermission name="/department/addDepartment.shtml">
                        <a id="addBtn" class="btn btn-success" onclick="javascript:_openDlg();">添加</a>
                    </@shiro.hasPermission>
				         </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>部门名称</th>
                        <#--<th>部门描述</th>-->
                        <#--<th>创建时间</th>-->
                        <th>部门人数</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td> ${it_index+1}</td>
                            <td>${it.name?default('未设置')}</td>
                            <#--<td>${it.describtion?default('未设置')}</td>-->
                            <#--<td>${(it.createtime?string('yyyy/MM/dd'))?default('未设置')}</td>-->
                            <td>${it.count?default('未设置')}</td>
                            <td>
                                <@shiro.hasPermission name="/department/deleteDepartMentById.shtml">
                                    <a href="javascript:_delete([${it.did}]);">删除</a>
                                </@shiro.hasPermission>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="5">没有找到部门信息</td>
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
<div class="modal fade" id="addDepartMent" tabindex="-1" role="dialog" aria-labelledby="addDepartMentLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addDepartMentLabel">添加部门信息</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">部门名称:</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="请输入部门名称" required
                               maxlength="10">
                    </div>
                    <#--<div class="form-group">-->
                        <#--<label for="recipient-name" class="control-label">部门描述:</label>-->
                        <#--<textarea class="form-control" name="describtion" id="describtion"-->
                                  <#--placeholder="请输入部门描述" maxlength="50"></textarea>-->
                    <#--</div>-->
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="_addDepartment();" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<#--/弹框-->
</body>
</html>