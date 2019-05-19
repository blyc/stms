<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>我的班级课程表</title>
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
    <style>
        .table th, .table td {
            text-align: center;
            vertical-align: middle !important;
        }

        #table1 {
            font-size: 12px
        }
    </style>
    <script>
        $(function () {
            var ccid = ${cid};
            $.ajax({
                url: '${basePath}/course/schedulelistall.shtml',
                type: 'post',
                data: 'id=' + ccid,
                success: function (msg) {
                    var obj = eval(msg);
                    if (obj.list != null) {
                        for (var i = 0; i < obj.list.length; i++) {
                            var course = obj.list[i].course;
                            var tname = obj.list[i].tname;
                            var place = obj.list[i].place;
                            var pin = course + "<br>" + tname + "<br>" + place
                            $("#" + obj.list[i].subscript + "").append(pin);
                        }
                    }
                }
            })
        })

        function _openAddDlg(cid) {
            $.post('/course/getClasstname.shtml', {cid: cid}
                    , function (result) {
                        if (result && result.status != 200) {
                            return layer.msg(result.message, so.default), !1;
                        }
                        var obj = result.vClass;
                        if (obj.jsname != "" || obj.dsname != "") {
                            var trtnames = "<th>请选择教师：</th>"
                            if (obj.jsname != "") {
                                trtnames = trtnames + "<th><input type='radio' name='tname' value='" + obj.jsname + "' checked='checked'> " + obj.jsname + "</th>"
                            }
                            if (obj.dsname != "") {
                                trtnames = trtnames + "<th><input type='radio' name='tname'  value='" + obj.dsname + "'checked='checked'> " + obj.dsname + "</th>"
                            }
                            $("#trtname").html(trtnames);
                        }
                    }, 'json');
            $('#addAttendance').modal();
        }

        function _addSChedule(cid) {
            var load = layer.load();
            var weeks = $("input[name='weeks']:checked").val()
            var section = $("input[name='section']:checked").val()
            var course = $("input[name='course']:checked").val()
            var tname = $("input[name='tname']:checked").val()
            var place = $("#place").val()
            $.ajax({
                url: '${basePath}/course/addschedule.shtml',
                type: 'post',
                data: 'weeks=' + weeks + '&section=' + section + '&course=' + course + '&tname=' + tname + '&place=' + place + '&ccid=' + cid,
                success: function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message, so.default), !1;
                    }
                    layer.msg('添加成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }
            })
        }
    </script>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 4/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left4 5/>
        <div class="col-md-10">
            <h3>我的班级课程表</h3>
            <br>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <span class="">
                    <#--pull-right -->
                        <a id="addBtn" class="btn btn-success" onclick="javascript:_openAddDlg(${cid});">添加课表</a>
                        <a id="addBtn" class="btn btn-success" href="/course/exportschedule_${cid}.shtml">导出</a>
                    </span>
                </div>
                <br>
                <table class="table table-bordered table-condensed" id="table1">
                    <tr>
                        <th>星期/课时</th>
                        <th>第一节</th>
                        <th>第二节</th>
                        <th>第三节</th>
                        <th>第四节</th>
                        <th>第五节</th>
                        <th>第六节</th>
                        <th>第七节</th>
                        <th>第八节</th>
                        <th>第九节</th>
                        <th>第十节</th>
                    </tr>
                    <tr>
                        <th>周一</th>
                        <th id="1"></th>
                        <th id="2"></th>
                        <th id="3"></th>
                        <th id="4"></th>
                        <th id="5"></th>
                        <th id="6"></th>
                        <th id="7"></th>
                        <th id="8"></th>
                        <th id="9"></th>
                        <th id="10"></th>
                    </tr>
                    <tr>
                        <th>周二</th>
                        <th id="11"></th>
                        <th id="12"></th>
                        <th id="13"></th>
                        <th id="14"></th>
                        <th id="15"></th>
                        <th id="16"></th>
                        <th id="17"></th>
                        <th id="18"></th>
                        <th id="19"></th>
                        <th id="20"></th>
                    </tr>
                    <tr>
                        <th>周三</th>
                        <th id="21"></th>
                        <th id="22"></th>
                        <th id="23"></th>
                        <th id="24"></th>
                        <th id="25"></th>
                        <th id="26"></th>
                        <th id="27"></th>
                        <th id="28"></th>
                        <th id="29"></th>
                        <th id="30"></th>
                    </tr>
                    <tr>
                        <th>周四</th>
                        <th id="31"></th>
                        <th id="32"></th>
                        <th id="33"></th>
                        <th id="34"></th>
                        <th id="35"></th>
                        <th id="36"></th>
                        <th id="31"></th>
                        <th id="31"></th>
                        <th id="31"></th>
                        <th id="31"></th>
                    </tr>
                    <tr>
                        <th>周五</th>
                        <th id="41"></th>
                        <th id="42"></th>
                        <th id="43"></th>
                        <th id="44"></th>
                        <th id="45"></th>
                        <th id="46"></th>
                        <th id="47"></th>
                        <th id="48"></th>
                        <th id="49"></th>
                        <th id="50"></th>
                    </tr>
                    <tr>
                        <th>周六</th>
                        <th id="51"></th>
                        <th id="52"></th>
                        <th id="53"></th>
                        <th id="54"></th>
                        <th id="55"></th>
                        <th id="56"></th>
                        <th id="57"></th>
                        <th id="58"></th>
                        <th id="59"></th>
                        <th id="60"></th>
                    </tr>
                    <tr>
                        <th>周日</th>
                        <th id="61"></th>
                        <th id="62"></th>
                        <th id="63"></th>
                        <th id="64"></th>
                        <th id="65"></th>
                        <th id="66"></th>
                        <th id="67"></th>
                        <th id="68"></th>
                        <th id="69"></th>
                        <th id="70"></th>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<#--新增弹框-->
<div class="modal fade" id="addAttendance" tabindex="-1" role="dialog" aria-labelledby="addAttendanceLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addAttendanceLabel">添加课表</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid" class="col-md-2">
                    <form id="boxRoleForm">
                        <table class="table tab-content table-condensed">
                            <tr>
                                <th>请选择星期：</th>
                                <th><input type="radio" name="weeks" value="周一" checked="checked"> 周 &nbsp;&nbsp;一</th>
                                <th><input type="radio" name="weeks" value="周二"> 周 &nbsp;&nbsp;二</th>
                                <th><input type="radio" name="weeks" value="周三"> 周 &nbsp;&nbsp;三</th>
                                <th><input type="radio" name="weeks" value="周四"> 周 &nbsp;&nbsp;四</th>
                                <th><input type="radio" name="weeks" value="周五"> 周 &nbsp;&nbsp;五</th>
                                <th><input type="radio" name="weeks" value="周六"> 周 &nbsp;&nbsp;六</th>
                                <th><input type="radio" name="weeks" value="周日"> 周 &nbsp;&nbsp;日</th>
                            </tr>
                            <tr>
                                <th>请选择时间：</th>
                                <th><input type="radio" name="section" value="第一节" checked="checked"> 第一节</th>
                                <th><input type="radio" name="section" value="第二节"> 第二节</th>
                                <th><input type="radio" name="section" value="第三节"> 第三节</th>
                                <th><input type="radio" name="section" value="第四节"> 第四节</th>
                                <th><input type="radio" name="section" value="第五节"> 第五节</th>
                                <th><input type="radio" name="section" value="第六节"> 第六节</th>
                                <th><input type="radio" name="section" value="第七节"> 第七节</th>
                                <th><input type="radio" name="section" value="第八节"> 第八节</th>
                                <th><input type="radio" name="section" value="第九节"> 第九节</th>
                                <th><input type="radio" name="section" value="第十节"> 第十节</th>
                            </tr>
                            <tr>
                                <th>请选择课程：</th>
                                <th><input type="radio" name="course" value="上机课" checked="checked"> 上机课</th>
                                <th><input type="radio" name="course" value="理论课"> 理论课</th>
                            </tr>
                            <tr id="trtname">
                            </tr>
                            <tr>
                                <th>请输入地点地点：</th>
                                <th colspan="2"><input type="text" id="place" name="place" placeholder="机房几/教室几"></
                            </tr>
                        </table>
                        <hr>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="_addSChedule(${cid});" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>