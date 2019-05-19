<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>讲师满意度</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/picker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/jQuery.regex.js"></script>
    <script src="${basePath}/picker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script src="${basePath}/picker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script src="${basePath}/js/util.js"></script>
    <script baseUrl="${basePath}" src="${basePath}/js/hp/satisfaction.list.js"></script>
    <style>
        .popover {
            max-width: 600px;
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
    <@_left.left6 4/>
        <div class="col-md-10">
            <h2>讲师满意度</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 200px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/班级/讲师" maxlength="10">
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

                    <@shiro.hasPermission name="/satisfaction.shtml">
                        <a id="addBtn" class="btn btn-success">添加满意度</a>
                    </@shiro.hasPermission>
                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>讲师</th>
                        <th>校区</th>
                        <th>年级</th>
                        <th>班级</th>
                        <th>时间</th>
                        <th>满意度</th>
                        <th>备注</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>  ${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.uEmployee.name?default('')}</td>
                            <td>${(it.vClass.companyname)?default('')}</td>
                            <td>${it.vClass.grade?default('')}</td>
                            <td>${it.vClass.classname?default('')}</td>
                            <td>${it.surveyTime?string("yyyy/MM/dd")}</td>
                            <td>${(it.score*100)?string("#.##")}%</td>
                            <td>
                                <a class="showdesc" title="备注"
                                   data-container="body" data-toggle="popover" data-placement="left"
                                   data-content="${(it.remark)?default('—')}">
                                    查看</a>
                                <@shiro.hasPermission name="/satisfaction/deleteUSatisfaction.shtml">
                                    <#if it.deleteFlg !=0 >
                                    <a href="javascript:_delete([${it.usid}]);">删除</a>
                                    </#if>
                                </@shiro.hasPermission>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="8">没有满意度数据</td>
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


<!-- 模态框（Modal） -->
<div class="modal fade" id="satisfactiondlg" tabindex="-1" role="dialog" aria-labelledby="satisfactiondlgLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">录入满意度分数</h4>
            </div>
            <div class="modal-body" id="modeljiemian">
                <form id="boxRoleForm">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">讲师:</label>
                                <select class="form-control" id="eid" name="eid"></select>
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">班级</label>
                                <select class="form-control" id="ccid" name="ccid"></select>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="input-group">
                                <label for="recipient-name" class="control-label">满意度</label>
                                <input type="text" class="form-control" name="score" id="score" required
                                       maxlength="10"/><span class="input-group-addon" style="height: 30px;">%</span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-9">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">备注: </label>
                                <textarea class="form-control" id="remark" name="remark" maxlength="1000"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="savebtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
</body>
</html>