<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>教学数据统计</title>
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
            var url = "${basePath}/summary/exportSubsidy.shtml?findContent=" + $("#findContent").val() + "&queryDate=" + $("#dtp_input2").val();
            window.location.href = encodeURI(url);
        }
    </script>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 9/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left9 6/>
        <div class="col-md-10">
            <h2>月讲师授课津贴汇总</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/讲师" maxlength="10">
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
                <table class="table table-bordered" style="font-size: smaller">
                    <tr>
                    <#--<th>序号</th>-->
                        <th>月份</th>
                        <th>校区</th>
                        <th>专业</th>
                        <th>讲师</th>
                        <th style="width: 40px">出勤率</th>
                        <th style="width: 40px">考试合格率</th>
                        <th style="width: 40px">项目提交率</th>
                        <th style="width: 40px">满意度</th>
                        <th style="width: 40px">总部检查</th>
                        <th style="width: 40px">校区评估</th>
                        <th style="width: 40px">绩效结果</th>
                        <th style="width: 50px">考核基数</th>
                        <th style="width: 50px">授课津贴</th>
                        <th style="width: 50px">授课天数</th>
                        <th style="width: 50px">应发</th>
                        <th style="width: 50px">应扣</th>
                        <th style="width: 50px">应奖</th>
                        <th style="width: 50px">实发</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                        <#--<td>${((page.pageNo-1)*page.pageSize)+it_index+1}</td>-->
                            <td>${it.summaryDate?default('')}</td>
                            <td>${it.companyname?default('')}</td>
                            <td>${it.majortype?default('')}</td>
                            <td>${it.jsname?default('')}</td>
                            <td>${(it.summaryAttendance*100)?string("#.##")}%</td>
                            <td>${(it.reviewExam*100)?string("#.##")}%</td>
                            <td>${(it.reviewProject*100)?string("#.##")}%</td>
                            <td>${(it.summarySatisfaction*100)?string("#.##")}%</td>
                            <td>${(it.reviewHead*100)?string("#.##")}%</td>
                            <td>${(it.reviewCompany*100)?string("#.##")}%</td>
                            <td>${(it.avgBase)?string("##.##")}</td>
                            <td>${(it.base)?string("##.##")}</td>
                            <td>${(it.subsidy)?string("##.##")}</td>
                            <td>${(it.dayCount)?string("##.##")}</td>
                            <td>${(it.courseSubsidy)?string("##.##")}</td>
                            <#if it.diffBase gt 0>
                                <td>0</td>
                                <td>${(it.base)?string("##.##")}</td>
                            <#else>
                                <td>${(it.base)?string("##.##")}</td>
                                <td>0</td>
                            </#if>
                            <#if (it.courseSubsidy-it.base) gt 0>
                                <td>${(it.courseSubsidy-it.base)?string("##.##")}</td>
                            <#else>
                                <td>0</td>
                            </#if>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="9">没有授课津贴数据</td>
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