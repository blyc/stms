<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>班级访谈详情</title>
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

        $(function () {
            _initPopover();

            $("#patriarch").change(function () {
                var patriarch = $("#patriarch option:selected").val();
                $("#patriarchtel").val(patriarch);
            });
        })

        function _openRegisterDlg(infoid, iid, n, t1, t2, t3) {
            $("#infoid").val(infoid);
            $("#name").val(n);
            $("#iid").val(iid);
            $("#patriarch").empty();
            $("#patriarch").append($("<option>").val("").text(""));
            $("#patriarch").append($("<option>").val(t1).text("父亲"));
            $("#patriarch").append($("<option>").val(t2).text("母亲"));
            $("#patriarch").append($("<option>").val(t3).text("其他"));
            $('#registerInterView').modal();
        }

        function _registerInterViewInfo() {
            var infoid = $('#infoid').val(),
                    iid = $("#iid").val(),
                    patriarch = $('#patriarch').text(),
                    patriarchtel = $('#patriarchtel').val(),
                    comdate = $('#comdate').val(),
                    comcontent = $('#comcontent').val(),
                    remark = $('#remark').val();
//                    state = "1";

            if ($.trim(patriarch) == '') {
                return layer.msg('家长不能为空。', so.default), !1;
            }
            if ($.trim(patriarchtel) == '') {
                return layer.msg('家长电话不能为空。', so.default), !1;
            }
            if ($.trim(comcontent) == '') {
                return layer.msg('沟通内容不能为空。', so.default), !1;
            }

        <#--loding-->
            var load = layer.load();
            $.post('${basePath}/interview/registerInterViewInfo.shtml', {
                        infoid: infoid,
                        iid: iid,
                        patriarch: patriarch,
                        patriarchtel: patriarchtel,
                        comdate: comdate,
                        comcontent: comcontent,
                        remark: remark,
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
            <h2>班级访谈详情</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入昵称 / 帐号">
                    </div>
                    <span class=""> <#--pull-right -->
                        <button type="submit" class="btn btn-primary">查询</button>
                         </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>学生名字</th>
                        <th>家长姓名</th>
                        <th>家长联系电话</th>
                        <th>沟通时间</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>

                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>${it.uStudent.name?default('—')}</td>
                            <td>${it.patriarch?default('—')}</td>
                            <td>${it.patriarchtel?default('—')}</td>
                            <td>${(it.comdate?string('yyyy/MM/dd'))?default('')}</td>
                            <td>${it.remark?default('—')}</td>
                            <#if it.state =="0" >
                                <td>
                                    <a class="col-md-8"
                                       href="javascript:_openRegisterDlg(${it.infoid}, ${it.iid},'${it.uStudent.name}',
                                    '${it.uStudent.fatherTel}','${it.uStudent.motherTel}','${it.uStudent.otherTel}');">登记访谈</a>
                                </td>
                            <#else>
                                <td>
                                    <a class="showdesc"
                                       title="沟通内容"
                                       data-container="body" data-toggle="popover" data-placement="left"
                                       data-content="<table class='table table-striped'>
                                        <tr><td>${it.comcontent?default('—')}</td></tr>
                                        </table>">
                                        详情</a>
                                </td>

                            </#if>
                        </tr>

                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="6">没有找到访谈详情</td>
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
<#--登记访谈弹框-->
<div class="modal fade" id="registerInterView" tabindex="-1" role="dialog" aria-labelledby="addUClassLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addUClassLabel">登记访谈</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">学生姓名:</label>
                                <input type="hidden" id="infoid">
                                <input type="hidden" id="iid">
                                <input type="text" class="form-control" id="name" name="name" disabled>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">家长:</label>
                                <select name="patriarch" id="patriarch" class="form-control">
                                    <option></option>
                                    <option>父亲</option>
                                    <option>母亲</option>
                                    <option>其他</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">家长联系方式:</label>
                                <input type="text" class="form-control" id="patriarchtel" name="patriarchtel">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">沟通内容:</label>
                                <textarea class="form-control" id="comcontent" name="comcontent" style="height: 150px;"
                                          maxlength="100"></textarea>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">备注:</label>
                                <textarea class="form-control" id="remark" name="remark" style="height: 80px;"
                                          maxlength="50"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="_registerInterViewInfo();" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>