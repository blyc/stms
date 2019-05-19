<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>出勤统计</title>
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
    <script baseUrl="${basePath}" src="${basePath}/js/hp/attendance.list.js"></script>
    <style>
        .table th {
            text-align: center;
            vertical-align: middle!important;
        }
    </style>
</head>
<body data-target="#one" data-spy="scroll">
<@_top.top 5/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left5 1/>
        <div class="col-md-10">
            <h2>出勤统计</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 200px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/班级/讲师/班主任" maxlength="10">
                    </div>
                    <div class="form-group">
                        <div class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
                            <input class="form-control" size="16" type="text"   value="${startQueryDate?default('')}" placeholder="输入开始月份" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        <input type="hidden" name="startQueryDate"  id="dtp_input1" value="${startQueryDate?default('')}"/><br/>
                    </div>
                    <div class="form-group">
                        <div class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                            <input class="form-control" size="16" type="text"   value="${endQueryDate?default('')}" placeholder="输入结束月份" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        <input type="hidden" name="endQueryDate"  id="dtp_input2" value="${endQueryDate?default('')}"/><br/>
                    </div>
                    <span class=""> <#--pull-right -->

                        <button type="submit" class="btn btn-primary">查询</button>

                    <@shiro.hasPermission name="/attendance.shtml">
                        <a id="addBtn" class="btn btn-success">添加出勤</a>
                    </@shiro.hasPermission>
                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>校区</th>
                        <th>班级</th>
                        <th>日期</th>
                        <th>班级人数</th>
                        <th>出勤</th>
                        <th>迟到</th>
                        <th>请假</th>
                        <th>旷课</th>
                        <th>早退</th>
                        <th>出勤率</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>  ${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.vClass.companyname?default('未设置')}</td>
                            <td>${it.vClass.classname?default('未设置')}</td>
                            <td>${it.recordTime?default('未设置')}</td>
                            <td>${it.scounts?default('未设置')}</td>
                            <td>${it.normalCount?default('未设置')}</td>
                            <td>${it.lateCount?default('未设置')}</td>
                            <td>${it.leaveCount?default('未设置')}</td>
                            <td>${it.truantCount?default('未设置')}</td>
                            <td>${it.earlyCount?default('未设置')}</td>
                            <td>${(it.rate*100)?string('#.##')}%</td>
                            <td>
                                <#assign sum1=it.normalCount+it.lateCount+it.leaveCount+it.truantCount+it.earlyCount>
                                <#if sum1==0>
                                    <@shiro.hasPermission name="/attendance.shtml">
                                        <a href="javascript:_open2('${it.ccid}','${it.caid}')">添加出勤</a>
                                    </@shiro.hasPermission>
                                <#else >
                                    <@shiro.hasPermission name="/attendance/list.shtml">
                                        <a href="javascript:_get_detail('${it.caid}')">查看出勤</a>
                                        <#assign sum2=it.lateCount+it.leaveCount+it.truantCount+it.earlyCount>
                                        <#if sum2 gt 0 >
                                            <#if it.arid??>
                                            <a href="javascript:_get_remarks_detail('${it.caid}')">查看备注</a>
                                            <#else >
                                            <a href="javascript:_open3('${it.caid}')">添加备注</a>
                                            </#if>
                                        </#if>
                                    </@shiro.hasPermission>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="12">该班级还没有登记过出勤情况</td>
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
<div class="modal fade" id="addClassAttendance" tabindex="-1" role="dialog" aria-labelledby="addClassAttendanceLabel">
    <div class="modal-dialog" role="document" style="width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加班级出勤</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form>
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
                                    <label for="recipient-name" class="control-label">章节:</label>
                                    <input type="text" class="form-control" id="section" name="section" required>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveBtn1">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="addAttendance" tabindex="-1" role="dialog" aria-labelledby="addAttendanceLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addAttendanceLabel">班级学生出勤情况</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="hidecaid">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn2" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="showAttendance" tabindex="-1" role="dialog" aria-labelledby="showAttendanceLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="showAttendanceLabel">班级学生出勤情况</h4>
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
<div class="modal fade" id="addRemarks" tabindex="-1" role="dialog" aria-labelledby="addRemarksLabel">
    <div class="modal-dialog" role="document" style="width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加班级出勤备注</h4>
                <span style="color: chocolate">注意!您只有<span style="color: red">一次</span>保存的机会!</span><br>
                <span style="color: chocolate">注意!仅限<span style="color: red">请假学生</span>添加<span style="color: red">有效备注</span>后计入出勤!</span>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="addRemarksForm">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="hideremarkscaid">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveBtn3">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="showRemarks" tabindex="-1" role="dialog" aria-labelledby="showRemarksLabel">
    <div class="modal-dialog" role="document" style="width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">班级出勤备注</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="showRemarksForm">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>