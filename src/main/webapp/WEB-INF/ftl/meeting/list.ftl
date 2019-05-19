<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>年会</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/shiro.demo.js"></script>
    <script>
        $(function () {
            $("#btnImport").click(function () {
                var f = $("#importFile").val();
                if (f == "") {
                    return layer.msg('导入文件不能为空。'), !1;
                }
                return layer.confirm("确定保存", {
                    icon: 3,
                    title: "提示",
                    btn: ['确定', '取消'],
                }, function () {
                    var load = layer.load();
                    $("#importForm").submit();
                    $("#btnImport").attr('disabled', true);
                    return true;
                });
            });
        });

    </script>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 10/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">

        <div class="col-md-10">
            <h2>年会员工列表</h2>
            <hr>
            <div clss="well">
                <a id="btn3" class="btn btn-success" onclick='$("#importMeeting").modal();'>导入员工信息</a>
                <a id="btn4" class="btn btn-success"
                   href="${basePath}/meeting/import/template.shtml">下载Excel模板</a>
                </span>
            </div>
            <hr>
            <img src="${basePath}/screen/bg/tel2wm.jpg" style="width: 40%;height: 40%">
        </div>
    </div><#--/row-->
</div>


<#--导入-->
<div class="modal fade" id="importMeeting" tabindex="-1" role="dialog" aria-labelledby="importMeetingLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <form id="importForm" action="${basePath}/meeting/import.shtml" method="post"
                  enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="importMeetingLabel">导入年会员工信息</h4>
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