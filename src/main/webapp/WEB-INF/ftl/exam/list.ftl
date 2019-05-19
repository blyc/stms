<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>考试统计</title>
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
    <script baseUrl="${basePath}" src="${basePath}/js/hp/exam.list.js"></script>

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
    <@_left.left6 3/>
        <div class="col-md-10">
            <h2>考试统计</h2>
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

                    <@shiro.hasPermission name="/exam.shtml">
                        <a id="addBtn" class="btn btn-success">申请考试</a>
                    </@shiro.hasPermission>

                     <a id="btn2" class="btn btn-success" href="${basePath}/exam/export.shtml">导出信息</a>

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
                        <th>科目</th>
                        <th style="width: 50px">监考</th>
                        <th style="width: 50px">地点</th>
                        <th style="width: 50px">时间</th>
                        <th style="width: 50px">合格率</th>
                        <th style="width: 50px">合格分数</th>
                        <th>操作</th>
                        <th>导入</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>  ${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.vClass.companyname?default('')}</td>
                            <td>${it.vClass.classname?default('')}</td>
                            <td>${it.vClass.majorname?default('')}</td>
                            <td>${it.examnum?default('')}</td>
                            <td>${it.examproject?default('')}</td>
                            <td>${it.invigilator?default('')}</td>
                            <td>${it.examsite?default('')}</td>
                            <td>${(it.examtime?string("yyyy/MM/dd"))?default('')}</td>
                            <td>${(it.qualifiedrate*100)?string("#.##")}%</td>
                            <td>${(it.rateStandard)?string("#")}</td>
                            <td>
                                <#if it.mark?exists && it.mark !=1>
                                    <#if it.ceid?exists>
                                        <@shiro.hasPermission name="/exam.shtml">
                                        <#--<a href="javascript:_getgrade(${it.ceid})">记录成绩</a>&nbsp;-->
                                        </@shiro.hasPermission>
                                    </#if>
                                <#else>
                                    <#if it.ceid?exists>

                                        <a href="javascript:_getdetail(${it.ceid})">查看成绩</a>&nbsp;
                                    </#if>
                                </#if>

                                <#if it.examevent? exists  && it.examevent == '0'>
                                    <#if it.ceid?exists>
                                        <@shiro.hasPermission name="/exam/addExamEvent.shtml">
                                            <a href="javascript:_getevent(${it.ceid})">记录异常</a>&nbsp;
                                        </@shiro.hasPermission>
                                    </#if>
                                <#else>
                                    <#if it.examevent?exists>
                                        <a class="showdesc" data-container="body" data-toggle="popover"
                                           data-placement="left"
                                           data-content="${it.examevent}">查看异常</a>
                                    </#if>
                                </#if>
                                <@shiro.hasPermission name="/exam/deleteExam.shtml">
                                    <#if it.deleteFlg !=0 >
                                        <a href="javascript:_delete([${it.ceid}]);">删除</a>
                                    </#if>
                                </@shiro.hasPermission>
                            </td>
                            <td>
                                <a id="btn2"
                                   href="${basePath}/studentexamlist/import/template.shtml?ceid=${it.ceid}&classname=${it.vClass.classname}">导出模板</a>&nbsp;
                                <#if it.mark?exists && it.mark !=1>
                                    <#if it.importFlg !=0 >
                                        <@shiro.hasPermission name="/studentexamlist/import.shtml">
                                            <a href="javascript:_show_import_dlg(${it.ceid})">导入数据</a>
                                        </@shiro.hasPermission>
                                    </#if>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="12">没有考试成绩</td>
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

<#--记录笔试成绩-->

<div class="modal fade" id="writtenGrade" tabindex="-1" role="dialog" aria-labelledby="writtenGradeLabel">
    <div class="modal-dialog" role="document" style="width:800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addAttendanceLabel">考试统计</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row" id="written">
                        </div>
                    </form>
                </div>
                <input type="text" id="writtenid" hidden="hidden">
                <input type="text" id="writtentime" hidden="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn1" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<#--查看成绩详情-->
<div class="modal fade" id="examDetails" tabindex="-1" role="dialog" aria-labelledby="examDetailsLabel">
    <div class="modal-dialog" role="document" style="width:800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addAttendanceLabel">学生考试详情</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th colspan="2">姓名</th>
                        <th colspan="2">考试成绩</th>
                        <th colspan="4">试卷提交地址</th>
                        <th colspan="2">考试时间</th>
                    </tr>
                    </thead>
                    <tbody id="showCount">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <input type="text" id="idtext" hidden>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<#--添加申请考试列表-->
<div class="modal fade" id="addClassExam" tabindex="-1" role="dialog" aria-labelledby="addClassExamLabel">
    <div class="modal-dialog" role="document" style="width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="updateStudentLabel">添加考试申请</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">班级:</label>
                                    <select class="form-control" id="ccid" name="ccid"
                                            onchange="javascript:_show_class_num();"></select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">考试人数:</label>
                                    <input type="text" class="form-control" id="examnum" name="examnum" disabled>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">专业:</label>
                                    <input type="text" class="form-control" id="umajorname" name="umajorname" disabled>

                                </div>
                            </div>
                        </div>
                    <#--<div class="row">-->
                    <#--<div class="form-group" style="position:relative;left: 15px;">-->
                    <#--<label for="recipient-name" class="control-label">考试类型:</label>-->
                    <#--<select class="form-control techerClass" id="examtype" style="width: 540px" hidden>-->
                    <#--<option value="笔试题">笔试题</option>-->
                    <#--<option value="机试题">机试题</option>-->
                    <#--</select>-->
                    <#--</div>-->
                    <#--</div>-->
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">考试时间:</label>
                                    <input type="date" class="form-control" id="examtime" name="examtime" required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">考试合格分数:</label>
                                    <input type="text" class="form-control" id="rateStandard" name="rateStandard"
                                           required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">考试科目:</label>
                                    <input type="text" class="form-control" id="examproject" name="examproject"
                                           required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">监考人:</label>
                                    <input type="text" class="form-control" id="invigilator" name="invigilator"
                                           required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">考试地点:</label>
                                    <input type="text" class="form-control" id="examsite" name="examsite" required>
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


<div class="modal fade" id="writeEvent" tabindex="-1" role="dialog" aria-labelledby="writeEventLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="_getclose();" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addAttendanceLabel">班级学生考试异常情况</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form role="form">
                        <div class="form-group">
                            <textarea class="form-control" id="examevent" rows="3"></textarea>
                        </div>
                    </form>
                </div>
                <input type="text" id="ceid" hidden="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" onclick="_getclose();" class="btn btn-default" data-dismiss="modal"
                        aria-label="Close">关闭
                </button>
                <button type="button" id="saveBtn2" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<#--导入-->
<div class="modal fade" id="importExam" tabindex="-1" role="dialog" aria-labelledby="importExamLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <form id="importForm" action="${basePath}/studentexamlist/import.shtml" method="post"
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