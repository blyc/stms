<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>督查数据(校区)</title>
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
    <script baseUrl="${basePath}" src="${basePath}/js/hp/headquarters.company.list.js"></script>

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

<@_top.top 11/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left11 4/>
        <div class="col-md-10">
            <h2>督查数据(校区)</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 200px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/讲师" maxlength="10">
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
                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th rowspan="2">序号</th>
                        <th rowspan="2">月份</th>
                        <th rowspan="2">校区</th>
                        <th rowspan="2">专业</th>
                        <th rowspan="2">讲师</th>
                        <th colspan="4">校区数据</th>
                        <th colspan="3">总部数据</th>
                        <th colspan="2">校区评估</th>
                    </tr>
                    <tr>
                        <th style="width: 50px">出勤率</th>
                        <th style="width: 80px">考试合格率</th>
                        <th style="width: 80px">项目完成率</th>
                        <th style="width: 80px">讲师满意度</th>
                        <th style="width: 80px">考试合格率</th>
                        <th style="width: 80px">项目完成率</th>
                        <th style="width: 50px">总部考核</th>
                        <th style="width: 50px">校区考核</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.summaryDate?default('')}</td>
                            <td>${it.companyname?default('')}</td>
                            <td>${it.majortype?default('')}</td>
                            <td>${it.jsname?default('')}</td>
                            <td>${(it.summaryAttendance*100)?string("#.##")}%</td>
                            <td>${(it.summaryExam*100)?string("#.##")}%</td>
                            <td>${(it.summaryProject*100)?string("#.##")}%</td>
                            <td>${(it.summarySatisfaction*100)?string("#.##")}%</td>
                            <td>${(it.reviewProject*100)?string("#.##")}%</td>
                            <td>${(it.reviewExam*100)?string("#.##")}%</td>
                            <td>${(it.reviewHead*100)?string("#.##")}%</td>
                            <td>${(it.reviewCompany*100)?string("#.##")}%</td>
                            <td>
                                <@shiro.hasAnyRoles name='888888,100001'>
                                    <#if it.reviewCompanyFlg  == 0 >
                                    <#--<a href="javascript:_showAddDailog(${it.cid},${it.jseid});">新增</a>-->
                                    <#else>
                                        <a href="javascript:_showUpdateDailog('${it.summaryDate}',${it.jseid});">更新</a>
                                    </#if>
                                </@shiro.hasAnyRoles>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="9">没有教学数据</td>
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

<#--更新校区月度考核指标-->
<div class="modal fade" id="updateCompanyReview" tabindex="-1" role="dialog" aria-labelledby="updateCompanyReviewLabel">
    <div class="modal-dialog" role="document" style="width:800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="updateCompanyReviewLabel">更新校区评估结果</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">工作行为:</label>
                                    <input type="text" class="form-control" name="uscore1" id="uscore1" required
                                           maxlength="10"/>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">工作态度</label>
                                    <input type="text" class="form-control" name="uscore2" id="uscore2" required
                                           maxlength="10"/>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <label for="recipient-name" class="control-label">协助与配合</label>
                                    <input type="text" class="form-control" name="uscore3" id="uscore3" required
                                           maxlength="10"/>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <label for="recipient-name" class="control-label">校区评估结果</label>
                                    <input type="text" class="form-control" id="uqualified" name="uqualified"
                                           maxlength="5" disabled>
                                    <span class="input-group-addon" id="basic-addon3">%</span>
                                    <input type="hidden" id="ucid">
                                    <input type="hidden" id="ueid">
                                    <input type="hidden" id="urcid">
                                </div>
                            </div>

                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveHeadBtn2" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>