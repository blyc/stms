<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>班级访谈列表</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/shiro.demo.js"></script>
    <script src="${basePath}/js/util.js"></script>
    <script>

        function _openAddDlg() {
            $('#addInterView').modal();
            $("#ccid").empty();
            $.get('${basePath}/exam/class.shtml', function (result) {
                var obj = eval(result);
                var option = $("<option>").val("").text("");
                $("#ccid").append(option);

                if (obj != undefined) {
                    for (var i = 0; i < obj.length; i++) {
                        var option = $("<option>").val(result[i].key).text(result[i].value);
                        $("#ccid").append(option);
                    }
                }

            });
        }

        function _addInterView() {
            var motif = $('#motif').val(),
                    begintime = $('#begintime').val(),
                    endtime = $('#endtime').val(),
                    ccid = $('#ccid').val();

            if ($.trim(motif) == '') {
                return layer.msg('主题不能为空。', so.default), !1;
            }


            if ($.trim(begintime) == '') {
                return layer.msg('开始时间不能为空。', so.default), !1;
            }

            if ($.trim(endtime) == '') {
                return layer.msg('结束时间不能为空。', so.default), !1;
            }
            if (begintime > endtime) {
                return layer.msg('开始时间大于结束时间。', so.default), !1;
            }

        <#--loding-->
            var load = layer.load();
            $.post('${basePath}/interview.shtml', {
                        motif: motif,
                        begintime: begintime,
                        endtime: endtime,
                        ccid: ccid
                    }
                    , function (result) {
                        layer.close(load);
                        if (result && result.status != 200) {
                            return layer.msg(result.message, so.default), !1;
                        }
                        layer.msg('添加成功。');
                        setTimeout(function () {
                            $('#formId').submit();
                        }, 1000);
                    }, 'json');
        }
        $(function () {
            _initPopover();
        })
    </script>

    <style>
        .popover {
            max-width: 600px;
        }
    </style>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 5/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
<div class="row">
<@_left.left5 3/>
<div class="col-md-10">
    <h2>班级访谈列表</h2>
    <hr>
<form method="post" action="" id="formId" class="form-inline">
    <div clss="well">
        <div class="form-group">
            <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                   name="findContent" id="findContent" placeholder="yy/MM/dd">
        </div>
        <span class=""> <#--pull-right -->
            <button type="submit" class="btn btn-primary">查询</button>
                        <a id="addBtn" class="btn btn-success" onclick="javascript:_openAddDlg();">添加访谈</a>
                         </span>
    </div>
    <hr>
<table class="table table-bordered">
    <tr>
        <th>校区</th>
        <th>班级</th>
        <th>主题</th>
        <th>开始时间</th>
        <th>结束时间</th>
    <#--<th>完成率</th>-->
        <th>操作</th>

    </tr>
<#if page?exists && page.list?size gt 0 >
    <#list page.list as it>
        <tr>
            <td>${it.uCompany.name?default('未设置')}</td>
            <td>${it.uClass.name?default('未设置')}</td>
            <td>${it.motif?default('未设置')}</td>
            <td>${(it.begintime?string("yyyy/MM/dd"))?default('未设置')}</td>
            <td>${(it.endtime?string("yyyy/MM/dd"))?default('未设置')}</td>
        <#--<td>${(comrate?string.percent)?default('未设置')}</td>-->
            <td>
                   <a class="col-md-8" href="${basePath}/interview/infolist/${it.iid}.shtml">查看详情</a>
            </td>
    </div>
    </div>
        </td>
        </tr>
    </#list>
<#else>
    <tr>
        <td class="text-center danger" colspan="6">没有找到学生信息</td>
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
<#--添加访谈弹框-->
<div class="modal fade" id="addInterView" tabindex="-1" role="dialog" aria-labelledby="addTeacherClassLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addTeacherClassLabel">添加访谈</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">班级:</label>
                        <select class="form-control" id="ccid" name="ccid"></select>
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">主题:</label>
                        <input type="text" class="form-control" id="motif" name="motif" placeholder="请输入主题">
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">开始时间:</label>
                        <input type="date" class="form-control" id="begintime" name="begintime" placeholder="请输入开始时间">
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">结束时间:</label>
                        <input type="date" class="form-control" id="endtime" name="endtime" placeholder="请输入结束时间">
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="_addInterView();" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>