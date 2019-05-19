<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>员工列表</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/shiro.demo.js"></script>
    <script src="${basePath}/js/util.js"></script>
    <script src="${basePath}/js/jQuery.Hz2Py-min.js"></script>
    <script src="${basePath}/js/jQuery.regex.js"></script>
    <script baseUrl="${basePath}" src="${basePath}/js/hp/employee.list.js"></script>
    <style>
        .popover {
            max-width: 600px;
        }

        .col-md-6 {
            width: 33%;
        }

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
    <@_left.left3 4/>
        <div class="col-md-10">
            <h2>员工列表</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入昵称 / 帐号" maxlength="5">
                    </div>
                    <span class=""> <#--pull-right -->

                        <button type="submit" class="btn btn-primary">查询</button>

                    <@shiro.hasPermission name="/employee.shtml">
                        <a id="addBtn" class="btn btn-success">添加</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/employee/export.shtml">
                        <a id="btn2" class="btn btn-success" href="${basePath}/employee/export.shtml">导出员工信息</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/employee/import.shtml">
                        <a id="btn3" class="btn btn-success">导入员工信息</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/employee/import/template.shtml">
                        <a id="btn4" class="btn btn-success"
                           href="${basePath}/employee/import/template.shtml">下载Excel模板</a>
                    </@shiro.hasPermission>
                         </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>员工姓名(姓名拼音)</th>
                        <th>性别</th>
                        <th>校区</th>
                        <th>部门</th>
                        <th>职位</th>
                        <th>级别</th>
                    <@shiro.hasAnyRoles name='888888,100001,10002'>
                        <th>操作</th>
                    </@shiro.hasAnyRoles>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td> ${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.name?default('未设置')}(${it.pinyin?default('未设置')})</td>
                            <td>${it.sex?default('未设置')}</td>
                            <td>${it.uCompany.name?default('未设置')}</td>
                            <td>${it.uDepartment.name?default('未设置')}</td>
                            <td>${it.uPosition.name?default('未设置')}</td>
                            <#if  it.uJsRank?exists >
                                <td>${it.uJsRank.type?default('未设置')}-${it.uJsRank.level?default('未设置')}</td>
                            <#else>
                                <td>-</td>
                            </#if>
                            <@shiro.hasAnyRoles name='888888,100001,10002'>
                                <td>
                                    <a class="showdesc col-md-6"
                                       title="${it.name?default('未设置')}|${it.uPosition.name?default('未设置')}"
                                       data-container="body" data-toggle="popover" data-placement="left"
                                       data-content="<table class='table table-striped'>
                                        <tr><td>电话</td><td>${it.tel?default('—')}</td></tr>
                                        <tr><td>电子邮箱</td><td>${it.email?default('—')}</td></tr>
                                        <tr><td>毕业学校</td><td>${it.shool?default('—')}</td></tr>
                                        <tr><td>专业</td><td>${it.major?default('—')}</td></tr>
                                        <tr><td>最高学历</td><td>${it.education?default('—')}</td></tr>
                                        <tr><td>入职时间</td><td>${(it.entryday?string('yyyy/MM/dd'))?default('')}</td></tr>
                                        </table>">
                                        详情</a>
                                    <a class="col-md-6" onclick="javascript:_openUpdateDlg(${it.eid});">更新</a>
                                    <a class="col-md-6" onclick="javascript:_delete([${it.eid}]);">删除</a>
                                </td>
                            </@shiro.hasAnyRoles>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="7">没有找到员工</td>
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
<div class="modal fade" id="addEmployee" tabindex="-1" role="dialog" aria-labelledby="addEmployeeLabel">
    <div class="modal-dialog" role="document" style="width: 1024px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addEmployeeLabel">添加员工信息</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">员工姓名:</label>
                                    <input type="text" class="form-control" name="name" id="name"
                                           placeholder="请输入员工姓名" required maxlength="5">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">姓名拼音:</label>
                                    <input type="text" class="form-control" name="pinyin" id="pinyin"
                                           placeholder="请输入姓名拼音" required maxlength="20">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">性别:</label>
                                    <div class="form-control">
                                        <label class="radio-inline">
                                            <input type="radio" id="sex1" name="sex" value="男" checked="checked">男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" id="sex2" name="sex" value="女">女
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">电话:</label>
                                    <input type="text" class="form-control" id="tel" name="tel" placeholder="请输入电话"
                                           required maxlength="11">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">电子邮箱:</label>
                                    <input type="email" class="form-control" id="email" name="email"
                                           placeholder="请输入电子邮箱" maxlength="20">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">毕业学校:</label>
                                    <input type="text" class="form-control" id="shool" name="shool"
                                           placeholder="请输入毕业学校" maxlength="10">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">专业:</label>
                                    <input type="text" class="form-control" id="major" name="major"
                                           placeholder="请输入专业" maxlength="10">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">最高学历:</label>
                                    <select class="form-control" id="education" name="education">

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">入职时间:</label>
                                    <input type="date" class="form-control" id="entryday" name="entryday"
                                           placeholder="请输入职时间">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">校区:</label>
                                    <select class="form-control" id="cid">

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">部门:</label>
                                    <select class="form-control" id="did">

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">职位:</label>
                                    <select class="form-control" id="pid">

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">薪资级别:</label>
                                    <select class="form-control" id="lid">

                                    </select>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" id="userflg" name="userflg" value="1"> 创建登陆账号
                                    </label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="savebtn" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<#--/弹框-->

<#--更新弹框-->
<div class="modal fade" id="updateEmployeeLabel" tabindex="-1" role="dialog" aria-labelledby="updateEmployeeLabel">
    <div class="modal-dialog" role="document" style="width:900px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="updateEmployeeLabel">更新员工信息</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <input type="hidden" id="update_eid">
                                    <label for="recipient-name" class="control-label">薪资级别:</label>
                                    <select class="form-control" id="update_lid">

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">电话:</label>
                                    <input type="text" class="form-control" id="update_tel" name="update_tel"
                                           placeholder="请输入电话" required maxlength="11">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">邮箱:</label>
                                    <input type="email" class="form-control" id="update_email" name="update_email"
                                           placeholder="请输入电子邮箱" maxlength="20">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" id="update_userflg" name="update_userflg" value="1"> 创建登陆账号
                                    </label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="savebtn2" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<#--/更新弹框-->

<#--导入-->
<div class="modal fade" id="importEmployee" tabindex="-1" role="dialog" aria-labelledby="importEmployeeLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <form id="importForm" action="${basePath}/employee/import.shtml" method="post"
                  enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="addStudentLabel">导入员工</h4>
                </div>
                <div class="modal-body">.
                    <div class="container-fluid">
                        <input id="importFile" name="file" type="file" style="width:330px"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
                </div>
            </form>
        </div>
    </div>
</div>
<#--导入-->
</body>
</html>