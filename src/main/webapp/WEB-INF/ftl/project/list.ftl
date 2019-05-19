<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>项目统计</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/picker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/picker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script src="${basePath}/picker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script src="${basePath}/js/util.js"></script>
    <script baseUrl="${basePath}" src="${basePath}/js/hp/project.list.js"></script>
    <style>
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
    <@_left.left6 2/>
        <div class="col-md-10">
            <h2>项目统计</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 200px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/班级/讲师/班主任" maxlength="10">
                    </div>
                    <div class="form-group">
                        <div class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm" data-link-field="dtp_input2" data-link-format="yyyy-mm">
                            <input class="form-control" size="16" type="text"   value="${queryDate?default('')}" placeholder="输入月份" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        <input type="hidden" name="queryDate"  id="dtp_input2" value="${queryDate?default('')}"/><br/>
                    </div>
                    <span class=""> <#--pull-right -->
                        <button type="submit" class="btn btn-primary">查询</button>
                    <@shiro.hasPermission name="/project.shtml">
                        <a id="addBtn" class="btn btn-success">申请项目</a>
                    </@shiro.hasPermission>
                         </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>校区</th>
                        <th>班级</th>
                        <th>项目名称</th>
                        <th>创建时间</th>
                        <th>完成率</th>
                        <th>操作</th>
                        <th>导入</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>  ${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.vClass.companyname?default('')}</td>
                            <td>${it.vClass.classname?default('')}</td>
                            <td>${it.name?default('')}</td>
                            <td>${(it.createTime?string("yyyy/MM/dd"))?default('')}</td>
                            <td>${(it.squalified*100)?string("#.##")}%</td>
                            <td style="width: 200px;">
                                <#if it.flg !=1>
                                    <@shiro.hasPermission name="/project.shtml">
                                    <#--<a href="javascript:_getgrade(${it.cpid})">登记成绩</a>-->
                                    </@shiro.hasPermission>
                                </#if>
                                <#if it.flg ==1>
                                    <a href="javascript:_getDetail(${it.cpid})">查看成绩</a>
                                </#if>
                                <@shiro.hasPermission name="/project/deleteProject.shtml">
                                    <#if it.deleteFlg !=0 >
                                        <a href="javascript:_delete([${it.cpid}]);">删除</a>
                                    </#if>
                                </@shiro.hasPermission>
                            </td>
                            <td>
                                <a id="btn2"
                                   href="${basePath}/project/import/template.shtml?cpid=${it.cpid}&classname=${it.vClass.classname}">导出模板</a>&nbsp;
                                <#if it.flg !=1>
                                    <#if it.importFlg !=0 >
                                        <@shiro.hasPermission name="/project/import.shtml">
                                            <a href="javascript:_show_import_dlg(${it.cpid})">导入数据</a>
                                        </@shiro.hasPermission>
                                    </#if>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="8">没有项目信息</td>
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

<#--申请考试-->
<div class="modal fade" id="addProject" tabindex="-1" role="dialog" aria-labelledby="addProjectLabel">
    <div class="modal-dialog" role="document" style="width:500px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addProjectLabel">添加月度项目申请</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">班级:</label>
                                    <select class="form-control" id="ccid" name="ccid"></select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">项目名称:</label>
                                    <input type="text" class="form-control" id="name" name="name">
                                </div>
                            </div>
                        </div>
                    <#--<div class="row">-->
                    <#--<div class="col-md-12">-->
                    <#--<div class="form-group">-->
                    <#--<label for="recipient-name" class="control-label">项目简介:</label>-->
                    <#--<textarea class="form-control" id="description" name="description"-->
                    <#--maxlength="500"></textarea>-->
                    <#--</div>-->
                    <#--</div>-->
                    <#--</div>-->

                    <#--<div class="row">-->
                    <#--<div class="col-md-12">-->
                    <#--<div class="form-group">-->
                    <#--<label for="recipient-name" class="control-label">项目开始时间:</label>-->
                    <#--<input type="date" class="form-control" id="startTime" name="startTime" required>-->
                    <#--</div>-->
                    <#--</div>-->
                    <#--</div>-->

                    <#--<div class="row">-->
                    <#--<div class="col-md-12">-->
                    <#--<div class="form-group">-->
                    <#--<label for="recipient-name" class="control-label">项目结束时间:</label>-->
                    <#--<input type="date" class="form-control" id="startEnd" name="startEnd" required>-->
                    <#--</div>-->
                    <#--</div>-->
                    <#--</div>-->
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

<#--记录考试和查看考试成绩-->
<div class="modal fade" id="writtenGrade" tabindex="-1" role="dialog" aria-labelledby="writtenGradeLabel">
    <div class="modal-dialog" role="document" style="width:1280px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="writtenGradeLabel">项目统计</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row" id="written">
                        </div>

                    </form>
                </div>
            <#--登记成绩查看成绩隐藏和显示-->
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

<#--查看项目成绩详情-->
<div class="modal fade" id="projectDetails" tabindex="-1" role="dialog" aria-labelledby="examDetailsLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addAttendanceLabel">学生项目成绩详情</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered">
                    <thead>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <form id="boxRoleForm">
                                <div class="row" id="projectwritten">
                                </div>

                            </form>
                        </div>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<#--导入-->
<div class="modal fade" id="importProject" tabindex="-1" role="dialog" aria-labelledby="importProjectLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <form id="importForm" action="${basePath}/project/import.shtml" method="post"
                  enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="importProjectLabel">导入项目成绩</h4>
                </div>
                <div class="modal-body">.
                    <div class="container-fluid">
                        <input id="importFile" name="file" type="file" style="width:330px"/>
                        <input type="hidden" id="importcpid" name="cpid">
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