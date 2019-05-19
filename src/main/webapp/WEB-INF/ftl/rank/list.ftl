<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>薪资体系</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/jQuery.regex.js"></script>
    <script src="${basePath}/js/util.js"></script>
    <script baseUrl="${basePath}" src="${basePath}/js/hp/rank.list.js"></script>
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

<@_top.top 3/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left3 6/>
        <div class="col-md-10">
            <h2>薪资体系</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="岗位/级别" maxlength="10">
                    </div>
                    <span class=""> <#--pull-right -->

                        <button type="submit" class="btn btn-primary">查询</button>
                    <@shiro.hasPermission name="/rank.shtml">
                        <a id="addBtn" class="btn btn-success">添加薪资体系</a>
                    </@shiro.hasPermission>
                        <a id="btn3" class="btn btn-success">导入薪酬信息</a>
                        <a id="btn1" class="btn btn-success"
                           href="${basePath}/rank/import/template.shtml">下载Excel模板</a>
                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>岗位代码</th>
                        <th>级别</th>
                        <th>薪资</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${(it.type)?default('')}</td>
                            <td> ${(it.level)?default('')}</td>
                            <td>${it.subsidy?default('')}</td>
                            <td><a class="col-md-6" onclick="javascript:_openUpdateDlg(${it.lid});">更新</a></td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="8">没有薪酬数据</td>
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
<div class="modal fade" id="rankdlg" tabindex="-1" role="dialog" aria-labelledby="rankdlgLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">录入薪酬体系</h4>
            </div>
            <div class="modal-body" id="modeljiemian">
                <form id="boxRoleForm">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">岗位代码:</label>
                                <input type="text" class="form-control" name="type" id="type" required
                                       maxlength="10"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">级别</label>
                                <input type="text" class="form-control" name="level" id="level" required
                                       maxlength="10"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="input-group">
                                <label for="recipient-name" class="control-label">薪资</label>
                                <input type="text" class="form-control" name="subsidy" id="subsidy" required
                                       maxlength="10"/>
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


<!-- 模态框（Modal） -->
<div class="modal fade" id="updaterankdlg" tabindex="-1" role="dialog" aria-labelledby="updaterankdlgLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">录入薪酬体系</h4>
            </div>
            <div class="modal-body" id="modeljiemian">
                <form id="boxRoleForm">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">岗位代码:</label>
                                <input type="hidden" class="form-control" name="ulid" id="ulid"/>
                                <input type="text" class="form-control" name="utype" id="utype" required
                                       maxlength="10"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">级别</label>
                                <input type="text" class="form-control" name="ulevel" id="ulevel" required
                                       maxlength="10"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="input-group">
                                <label for="recipient-name" class="control-label">薪资</label>
                                <input type="text" class="form-control" name="usubsidy" id="usubsidy" required
                                       maxlength="10"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="savebtn1">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<#--导入-->
<div class="modal fade" id="importRank" tabindex="-1" role="dialog" aria-labelledby="importRankLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <form id="importForm" action="${basePath}/rank/import.shtml" method="post"
                  enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="addRankLabel">导入学生</h4>
                </div>
                <div class="modal-body">.
                    <div class="container-fluid">
                        <input id="importFile" name="file" type="file" style="width:330px"/>
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