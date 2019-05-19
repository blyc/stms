<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>总部督查</title>
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
    <script baseUrl="${basePath}" src="${basePath}/js/hp/headquarters.list.js"></script>

    <style>
        .popover {
            max-width: 500px;
        }

        .table th, .table td {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 11/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left11 1/>
        <div class="col-md-10">
            <h2>考核标准</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div class="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 200px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入年级/专业" maxlength="10">
                    </div>
                    <div class="form-group">
                        <div class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm"
                             data-link-field="dtp_input2" data-link-format="yyyy-mm">
                            <input class="form-control" size="16" type="text" value="${queryDate?default('')}"
                                   placeholder="输入月份" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        <input type="hidden" name="queryDate" id="dtp_input2" value="${queryDate?default('')}"/><br/>
                    </div>
                    <button type="submit" class="btn btn-primary">查询</button>
                </div>
                <span class=""> <#--pull-right -->
                    <@shiro.hasAnyRoles name='888888,100001'>
                        <a id="addBtn" class="btn btn-success">添加月度考核指标</a>
                    </@shiro.hasAnyRoles>
                        <a id="btn3" class="btn btn-success">导入月度考核</a>
                        <a id="btn1" class="btn btn-success"
                           href="${basePath}/quarter/import/template.shtml">下载Excel模板</a>

                <#--<a id="btn2" class="btn btn-success" href="${basePath}/exam/export.shtml">导出信息</a>-->

                    </span>
                <hr>
                <table class="table table-bordered">
                    <tr>
                    <#--<th rowspan="2">序号</th>-->
                        <th rowspan="2">月份</th>
                        <th rowspan="2">年级</th>
                        <th rowspan="2">专业</th>
                        <th rowspan="2">适用校区</th>
                        <th colspan="2">考试</th>
                        <th colspan="2">项目</th>
                        <th colspan="2">满意度</th>
                        <th colspan="2">出勤</th>
                        <th colspan="2">校区</th>
                        <th colspan="2">总部</th>
                        <th rowspan="2" style="width: 50px">计算结果</th>
                        <th rowspan="2" style="width: 50px">考核基数</th>
                        <th rowspan="2">操作</th>
                    </tr>
                    <tr>
                        <th>权重</th>
                        <th>标准</th>
                        <th>权重</th>
                        <th>标准</th>
                        <th>权重</th>
                        <th>标准</th>
                        <th>权重</th>
                        <th>标准</th>
                        <th>权重</th>
                        <th>标准</th>
                        <th>权重</th>
                        <th>标准</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                        <#--<td>  ${((page.pageNo-1)*page.pageSize)+it_index+1}</td>-->
                            <td>${it.standardTime?default('')}</td>
                            <td>${it.grade?default('')}级</td>
                            <td>${it.uMajor.name?default('')}</td>
                        <#if it.uCompany?exists >
                            <td>${it.uCompany.name?default('')}</td>
                        <#else>
                            <td>全校区</td>
                        </#if>
                            <td>${it.weightExam?default('')}</td>
                            <td>${(it.standardExam*100)?string("#.##")}%</td>
                            <td>${it.weightProject?default('')}</td>
                            <td>${(it.standardProject*100)?string("#.##")}%</td>
                            <td>${it.weightSatisfaction?default('')}</td>
                            <td>${(it.standardSatisfaction*100)?string("#.##")}%</td>
                            <td>${it.weightAttendance?default('')}</td>
                            <td>${(it.standardAttendance*100)?string("#.##")}%</td>
                            <td>${it.weightSchool?default('')}</td>
                            <td>${(it.standardSchool*100)?string("#.##")}%</td>
                            <td>${it.weightHead?default('')}</td>
                            <td>${(it.standardHead*100)?string("#.##")}%</td>
                            <td>${(it.result)?string("#.##")}</td>
                            <td>${(it.base)?string("#.##")}</td>
                        <#--<td>${it.scope?default('')}</td>-->
                            <td>
                                <@shiro.hasAnyRoles name='888888,100001'>
                                    <a href="javascript:update(${it.sid})">修改</a>
                                </@shiro.hasAnyRoles>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="22">没有月度教学考核指标</td>
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

<#--添加月度考核指标-->
<div class="modal fade" id="addStandard" tabindex="-1" role="dialog" aria-labelledby="addStandardLabel">
    <div class="modal-dialog" role="document" style="width:800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addStandardLabel">添加月度考核指标</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">月份:</label>
                                    <input type="text" class="form-control" id="standardTime" name="standardTime"
                                           >
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">年级:</label>
                                    <select class="form-control" id="grade" name="grade"></select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">专业:</label>
                                    <select class="form-control" id="mid" name="mid"></select>
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">考试权重:</label>
                                    <input type="text" class="form-control" id="weightExam" name="weightExam">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">考试考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="standardExam" name="standardExam">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">项目权重:</label>
                                    <input type="text" class="form-control" id="weightProject" name="weightProject">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">项目考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="standardProject"
                                               name="standardProject">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">出勤权重:</label>
                                    <input type="text" class="form-control" id="weightAttendance"
                                           name="weightAttendance">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">出勤考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="standardAttendance"
                                               name="standardAttendance">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">满意度权重:</label>
                                    <input type="text" class="form-control" id="weightSatisfaction"
                                           name="weightSatisfaction">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">满意度考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="standardSatisfaction"
                                               name="standardSatisfaction">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">校区权重:</label>
                                    <input type="text" class="form-control" id="weightSchool" name="weightSchool">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">校区考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="standardSchool"
                                               name="standardSchool">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">总部权重:</label>
                                    <input type="text" class="form-control" id="weightHead" name="weightHead">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">总部考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="standardHead" name="standardHead">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">计算结果:</label>
                                    <input type="text" class="form-control" id="result" name="result" disabled>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">考核基数:</label>
                                    <input type="text" class="form-control" id="base" name="base">
                                </div>
                            </div>
                            <#--<div class="col-md-6">-->
                                <#--<div class="form-group">-->
                                    <#--<label for="recipient-name" class="control-label">使用范围:</label>-->
                                    <#--<input type="text" class="form-control" id="scope" name="scope">-->
                                <#--</div>-->
                            <#--</div>-->
                            <div class="col-md-6">
                            <#--<div class="form-group">-->
                            <#--<label for="recipient-name" class="control-label">校区:</label>-->
                            <#--<select class="form-control" id="ccid" name="ccid"></select>-->
                            <#--</div>-->
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">适用校区:</label>
                                   <div id="cid"></div>
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

<#--修改月度考核指标-->
<div class="modal fade" id="updateStandard" tabindex="-1" role="dialog" aria-labelledby="updateStandardLabel">
    <div class="modal-dialog" role="document" style="width:800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="updateStandardLabel">修改月度考核指标</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">月份:</label>
                                    <input type="hidden" id="usid">
                                    <input type="text" class="form-control" id="ustandardTime" name="ustandardTime"
                                           >
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">年级:</label>
                                    <select class="form-control" id="ugrade" name="ugrade"></select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">专业:</label>
                                    <select class="form-control" id="umid" name="umid"></select>
                                    <#--<input type="hidden" id="umajorType" name="umajorType"></input>-->
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">考试权重:</label>
                                    <input type="text" class="form-control" id="uweightExam" name="uweightExam">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">考试考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="ustandardExam" name="ustandardExam">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">项目权重:</label>
                                    <input type="text" class="form-control" id="uweightProject" name="uweightProject">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">项目考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="ustandardProject"
                                               name="ustandardProject">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">出勤权重:</label>
                                    <input type="text" class="form-control" id="uweightAttendance"
                                           name="uweightAttendance">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">出勤考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="ustandardAttendance"
                                               name="ustandardAttendance">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">满意度权重:</label>
                                    <input type="text" class="form-control" id="uweightSatisfaction"
                                           name="uweightSatisfaction">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">满意度考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="ustandardSatisfaction"
                                               name="ustandardSatisfaction">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">校区权重:</label>
                                    <input type="text" class="form-control" id="uweightSchool" name="uweightSchool">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">校区考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="ustandardSchool"
                                               name="ustandardSchool">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">总部权重:</label>
                                    <input type="text" class="form-control" id="uweightHead" name="uweightHead">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">总部考核标准:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="ustandardHead" name="ustandardHead">
                                        <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">计算结果:</label>
                                    <input type="text" class="form-control" id="uresult" name="uresult" disabled>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">考核基数:</label>
                                    <input type="text" class="form-control" id="ubase" name="ubase">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">使用范围:</label>
                                    <input type="text" class="form-control" id="uscope" name="uscope">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn1" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


<#--导入-->
<div class="modal fade" id="importExam" tabindex="-1" role="dialog" aria-labelledby="importExamLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <form id="importForm" action="${basePath}/quarter/import.shtml" method="post"
                  enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="importExamLabel">导入考试成绩</h4>
                </div>
                <div class="modal-body">.
                    <div class="container-fluid">
                        <input id="importFile" name="file" type="file" style="width:330px"/>
                        <input type="hidden" id="importceid" name="ceid">
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