<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>数据汇总</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/picker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/picker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script src="${basePath}/picker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script src="${basePath}/js/util.js"></script>

    <style>
        .popover {
            max-width: 500px;
        }

        .table th {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
    <script>
        function exportfunc() {
            var url = "${basePath}/summary/exportStudent.shtml?findContent=" + $("#findContent").val() + "&queryDate=" + $("#dtp_input2").val();
            window.location.href = encodeURI(url);
        }
        var baseUrl = $("script[baseUrl]").attr('baseUrl');
        // 获取校区
        $.get("${basePath}/student/getcompany.shtml", function (result) {
            obj = eval(result);
            if (obj != undefined) {
                for (var i = 1; i < obj.length; i++) {
                        var option = $("<option>").val(result[i].key).text(result[i].value);
                        $("#campusid").append(option);
                }
            }
            $('#campusid option:first').attr("selected", true);
            var hidden_cid = $("#hidden_cid").val();
            if (hidden_cid != "") {
                $("#campusid").val(hidden_cid);
            }
        });
    </script>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 9/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left9 4/>
        <div class="col-md-10">
            <h2>学生数据统计</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <select class="form-control" id="campusid" name="campusid" style="width:110px" ></select>
                        <input type="hidden" id="hidden_cid" value="${cid?default('')}">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入年级/专业/班级/学生" maxlength="10">
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
                    <span class=""> <#--pull-right -->

                        <button type="submit" class="btn btn-primary">查询</button>
                        <a id="btn2" class="btn btn-success" href="javascript:exportfunc();">导出信息</a>


                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th rowspan="2">序号</th>
                        <th rowspan="2">月份</th>
                        <th rowspan="2">校区</th>
                        <th rowspan="2">年级</th>
                        <th rowspan="2">专业</th>
                        <th rowspan="2">班级</th>
                        <th rowspan="2">姓名</th>
                        <th colspan="5">校区数据</th>
                    </tr>
                    <tr>
                        <th style="width: 80px">月出勤率</th>
                        <th style="width: 100px">月迟到次数</th>
                        <th style="width: 100px">月旷课次数</th>
                        <th style="width: 100px">月请假次数</th>
                        <th style="width: 100px">月早退次数</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>  ${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.recordTime?default('')}</td>
                            <td>${it.campusName?default('')}</td>
                            <td>${it.grade?default('')}</td>
                            <td>${it.majorName?default('')}</td>
                            <td>${it.className?default('')}</td>
                            <td>${it.studentName?default('')}</td>
                            <td>${(it.attendanceRate*100)?string("#.##")}%</td>
                            <td>${it.belateCount?default('')}</td>
                            <td>${it.truantCount?default('')}</td>
                            <td>${it.leaveCount?default('')}</td>
                            <td>${it.leaveearlyCount?default('')}</td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="12">没有教学数据</td>
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
<script>
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 3,
        minView: 3,
        forceParse: 0
    });
</script>
</body>
</html>