<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>教务查课</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/util.js"></script>
    <script src="${basePath}/js/jQuery.regex.js"></script>
    <script baseUrl="${basePath}" src="${basePath}/js/hp/check.list.js"></script>
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

<@_top.top 5/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left5 2/>
        <div class="col-md-10">
            <h2>教务查课</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/班级/讲师/班主任" maxlength="10">
                    </div>
                    <span class=""> <#--pull-right -->

                        <button type="submit" class="btn btn-primary">查询</button>

                    <@shiro.hasPermission name="/check.shtml">
                        <a id="addBtn" class="btn btn-success">添加查课</a>
                    </@shiro.hasPermission>
                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>校区</th>
                        <th>专业</th>
                        <th>班级</th>
                        <th>日期</th>
                        <th>课前</th>
                        <th>课中</th>
                        <th>课后</th>
                        <th>备注</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td> ${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.vClass.companyname?default('')}</td>
                            <td>${it.vClass.majorname?default('')}</td>
                            <td>${it.vClass.classname?default('')}</td>
                            <td>${it.chdate?string("yyyy-MM-dd")}</td>
                            <td>
                                <#if it.beforestate=="0">
                                    <@shiro.hasPermission name="/check/updateCheck.shtml">
                                        <a href="javascript:_openaddbeforeDlg(${it.chid},${it.vClass.ccid});">添加</a>
                                    </@shiro.hasPermission>
                                <#else>

                                    <a class="showdesc" title="课前"
                                       data-container="body" data-toggle="popover" data-placement="left"
                                       data-content="<table class='table table-striped'>
                                        <tr><td>应到人数</td><td>${(it.due)?default('—')}</td></tr>
                                        <tr><td>实到人数</td><td>${it.actual?default('—')}</td></tr>
                                        <tr><td>出勤率</td><td>${(it.attendancerate)?string.percent}</td></tr>
                                        <tr><td>导师</td><td>${it.jsname?default('—')}</td></tr>
                                        <tr><td>班主任</td><td>${(it.bzrname)?default('—')}</td></tr>
                                        <tr><td>投影</td><td>${it.projector?default('—')}</td></tr>
                                        <tr><td>课件</td><td>${it.courseware?default('—')}</td></tr>
                                        <tr><td>控屏</td><td>${(it.touchscreen)?default('—')}</td></tr>
                                        <tr><td>教学进度</td><td>${(it.teapro)?default('—')}</td></tr>
                                 </table>">
                                        查看</a>

                                </#if>
                            </td>
                            <td>
                                <#if it.middlestate=="0">
                                    <@shiro.hasPermission name="/check/updateCheck.shtml">
                                        <a href="javascript:_openaddmiddleDlg(${it.chid});">添加</a>
                                    </@shiro.hasPermission>
                                <#else>

                                    <a class="showdesc" title="课中"
                                       data-container="body" data-toggle="popover" data-placement="left"
                                       data-content="<table class='table table-striped'>
                                        <tr><td>讲师状态</td><td>${it.teastate?default('—')}</td></tr>
                                        <tr><td>学生状态</td><td>${(it.stustate)?default('—')}</td></tr>
                                        <tr><td>讲师巡班</td><td>${(it.teatourclass)?default('—')}</td></tr>
                                 </table>">
                                        查看</a>

                                </#if>
                            </td>
                            <td>
                                <#if it.laterstate=="0">
                                    <@shiro.hasPermission name="/check/updateCheck.shtml">
                                        <a href="javascript:_openaddlaterDlg(${it.chid});">添加</a>
                                    </@shiro.hasPermission>
                                <#else>

                                    <a class="showdesc" title="课末"
                                       data-container="body" data-toggle="popover" data-placement="left"
                                       data-content="<table class='table table-striped'>
                                        <tr><td>学生(人数)</td><td>${(it.stunum)?default('—')}</td></tr>
                                        <tr><td>班级纪律</td><td>${it.discipline?default('—')}</td></tr>
                                 </table>">
                                        查看</a>

                                </#if>
                            </td>
                        <td>
                            <#if it.remark=="0">
                                <@shiro.hasPermission name="/check/updateCheck.shtml">
                                    <a href="javascript:_openaddremarkDlg(${it.chid});">添加</a></td>
                                </@shiro.hasPermission>
                            <#else>

                                <a class="showdesc" title="备注"
                                   data-container="body" data-toggle="popover" data-placement="left"
                                   data-content="<table class='table table-striped'>
                                        <tr><td>备注</td><td>${(it.remark)?default('—')}</td></tr>
                                 </table>">
                                    查看</a>

                            </#if>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="9">没有找到班级</td>
                    </tr>
                </#if>
                </table>
                <input type="hidden" id="hccid">
                <input type="hidden" id="hcsid">
            <#if page?exists>
                <div class="pagination pull-right">
                ${page.pageHtml}
                </div>
            </#if>
            </form>
        </div>
    </div><#--/row-->
</div>

<div class="form-group" style="display: none">
    <label for="recipient-name" class="control-label">隐藏的界面:</label>
    <input type="text" class="form-control" id="chid" name="chid" placeholder="主键">
</div>

<#--弹框 添加数据-->
<div class="modal fade" id="addCheckClass" tabindex="-1" role="dialog" aria-labelledby="addTeacherClassLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addTeacherClassLabel">添加查课记录</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">班级:</label>
                        <select class="form-control" id="ccid">
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn1" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<#--弹框 添加课前-->
<div class="modal fade" id="addClassBefore" tabindex="-1" role="dialog" aria-labelledby="addUClassLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addUClassLabel">课前</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">应到人数:</label>
                                <input type="text" class="form-control" id="due" name="due" disabled>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">实到人数:</label>
                                <input type="text" class="form-control" id="actual" name="actual" maxlength="3">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">出勤率:</label>
                                <input type="text" class="form-control" id="attendancerate" name="attendancerate"
                                       disabled>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">讲师:</label>
                                <input type="text" class="form-control" id="js" name="jsname" disabled>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">班主任:</label>
                                <input type="text" class="form-control" id="bzr" name="bzrname" disabled>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">投影:</label>
                                <select name="projector" id="projector" class="form-control">
                                    <option value="已打开">已打开</option>
                                    <option value="未打开">未打开</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">课件:</label>
                                <select class="form-control" id="courseware">
                                    <option value="已准备">已准备</option>
                                    <option value="未准备">未准备</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">控屏:</label>
                                <select class="form-control" id="touchscreen">
                                    <option value="已控">已控</option>
                                    <option value="未控">未控</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">教学进度:</label>
                                <select name="teapro" id="teapro" class="form-control">
                                    <option value="一致">一致</option>
                                    <option value="不一致">不一致</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn2" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<#--弹框 添加课中-->
<div class="modal fade" id="addClassMiddle" tabindex="-1" role="dialog" aria-labelledby="addTeacherClassLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addTeacherClassLabel">课中</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">讲师状态:</label>
                        <select class="form-control" id="jsstate">
                            <option value="良好">良好</option>
                            <option value="一般">一般</option>
                            <option value="很差">很差</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">学生状态:</label>
                        <select class="form-control" id="stustate">
                            <option value="良好">良好</option>
                            <option value="一般">一般</option>
                            <option value="很差">很差</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">讲师巡课:</label>
                        <select class="form-control" id="teatourclass">
                            <option value="已巡">已巡</option>
                            <option value="未巡">未巡</option>
                        </select>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn3" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<#--弹框 添加课末-->
<div class="modal fade" id="addClassLater" tabindex="-1" role="dialog" aria-labelledby="addTeacherClassLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addTeacherClassLabel">课末</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">人数:</label>
                        <input type="text" class="form-control" id="stunum" name="stunum" maxlength="3">
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">班级纪律:</label>
                        <select class="form-control" id="discipline">
                            <option value="良好">良好</option>
                            <option value="一般">一般</option>
                            <option value="很差">很差</option>
                        </select>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn4" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<#--弹框 添加备注-->
<div class="modal fade" id="addClassRemark" tabindex="-1" role="dialog" aria-labelledby="addTeacherClassLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addTeacherClassLabel">备注</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <textarea class="form-control" id="remark" name="remark" maxlength="500"></textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn5" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

</div>
</body>
</html>