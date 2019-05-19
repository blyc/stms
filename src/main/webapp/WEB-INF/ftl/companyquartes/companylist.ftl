<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>讲师校区评估</title>
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

<@_top.top 6/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left6 6/>
        <div class="col-md-10">
            <h2>讲师校区评估</h2>
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
                    <@shiro.hasPermission name="/quarter/addReviewCompany.shtml">
                        <a id="addBtn" class="btn btn-success" onclick="javascript:_showAddDailog();"">校内评估</a>
                    </@shiro.hasPermission>
                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>月份</th>
                        <th>校区</th>
                        <th>讲师</th>
                        <th>工作行为</th>
                        <th>工作态度</th>
                        <th>协助与配合度</th>
                        <th>校区评估结果</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.summaryTime?default('')}</td>
                            <td>${it.uCompany.name?default('')}</td>
                            <td>${it.uEmployee.name?default('')}</td>
                            <td>${(it.score1)?string("")}</td>
                            <td>${(it.score2)?string("")}</td>
                            <td>${(it.score3)?string("")}</td>
                            <td>${(it.reviewCompany*100)?string("#.##")}%</td>
                            <td>
                                <@shiro.hasAnyRoles name='888888,100001,100002'>
                                <#--<#if it.reviewCompanyFlg  == 0 >-->
                                <#--<a href="javascript:_showAddDailog(${it.cid},${it.jseid});">新增</a>-->
                                <#--<#else>-->
                                    <#if it.headflg  == 0 >
                                        <a href="javascript:_showUpdateDailog('${it.summaryTime}',${it.uEmployee.eid});">更新</a>
                                    </#if>
                                <#--</#if>-->
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


<#--添加校区月度考核指标-->
<div class="modal fade" id="addCompanyReview" tabindex="-1" role="dialog" aria-labelledby="addCompanyReviewLabel">
    <div class="modal-dialog" role="document" style="width:800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addCompanyReviewLabel">添加校区评估结果</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">讲师:</label>
                                    <select class="form-control" id="eid" name="eid"></select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">工作行为:</label>
                                    <input type="text" class="form-control" name="score1" id="score1" required
                                           maxlength="10"/>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">工作态度</label>
                                    <input type="text" class="form-control" name="score2" id="score2" required
                                           maxlength="10"/>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <label for="recipient-name" class="control-label">协助与配合</label>
                                    <input type="text" class="form-control" name="score3" id="score3" required
                                           maxlength="10"/>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <label for="recipient-name" class="control-label">校区评估结果</label>
                                    <input type="text" class="form-control" id="qualified" name="qualified"
                                           maxlength="5" disabled>
                                    <span class="input-group-addon" id="basic-addon3">%</span>
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
                <button type="button" id="saveCompanyBtn2" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>