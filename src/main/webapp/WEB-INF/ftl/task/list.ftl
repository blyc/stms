<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>作业统计</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/util.js"></script>
    <script baseUrl="${basePath}" src="${basePath}/js/hp/task.list.js"></script>
    <style>
        .popover {
            max-width: 500px;
        }
        .table th {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 6/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left6 1/>
        <div class="col-md-10">
            <h2>作业统计</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/班级/讲师/班主任" maxlength="10">
                    </div>
                    <span class=""> <#--pull-right -->

                        <button type="submit" class="btn btn-primary">查询</button>

                    <@shiro.hasPermission name="/task.shtml">
                        <a id="addBtn" class="btn btn-success">布置作业</a>
                    </@shiro.hasPermission>
                    </span>

                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>校区</th>
                        <th>班级</th>
                        <th>专业</th>
                        <th>人数</th>
                    <#--<th>类型</th>-->
                        <th>布置时间</th>
                        <th>科目</th>
                        <th>作业</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>${it_index+1}</td>
                            <td>${it.vClass.companyname?default('未设置')}</td>
                            <td>${it.vClass.classname?default('未设置')}</td>
                            <td>${it.vClass.majorname?default('未设置')}</td>
                            <td>${it.tasknum?default('未设置')}</td>
                        <#--<td>${it.tasktype?default('未设置')}</td>-->
                            <td>${(it.arrangementtime?string("yyyy/MM/dd"))?default('未设置')}</td>
                            <td>${it.taskproject?default('未设置')}</td>
                            <td>${it.homework?default('未设置')}</td>
                            <td style="width: 200px;">

                                <#if it.mark !=1>
                                    <@shiro.hasPermission name="/task.shtml">
                                        <a href="javascript:_openAddDlg(${it.tid})">记录成绩</a>&nbsp;
                                    </@shiro.hasPermission>
                                </#if>
                                <#if it.mark ==1>

                                    <a href="javascript:_getdetail(${it.tid})">查看详情</a>&nbsp;

                                </#if>

                            </td>

                        </tr>

                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="8">该班级还没有布置过作业</td>
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
<#--添加申请作业下拉框显示-->
<div class="modal fade" id="addtask" tabindex="-1" role="dialog" aria-labelledby="addtaskLabel">
    <div class="modal-dialog" role="document" style="width:700px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="updateStudentLabel">添加作业申请</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">班级:</label>
                                    <select class="form-control" id="ccid" name="ccid"></select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">章节:</label>
                                    <input type="text" class="form-control" name="taskproject" id="taskproject"
                                           required/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">作业:</label>
                                    <textarea class="form-control" id="homework" name="homework" maxlength="1000"
                                              required></textarea>
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="taskDetails" tabindex="-1" role="dialog" aria-labelledby="showAttendanceLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addAttendanceLabel">班级学生作业情况</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="showAttendanceForm">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<#--登记学生作业情况-->
<div class="modal fade" id="addtaskaccomplish" tabindex="-1" role="dialog" aria-labelledby="addAttendanceLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addAttendanceLabel">登记作业</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm1">
                        <div class="row" id="written">
                    </form>
                </div>
            <#--<input type="text" id="tid" hidden="hidden">-->
                <input type="text" id="writtenid" hidden="hidden">
                <input type="text" id="writtentime" hidden="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn2" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>