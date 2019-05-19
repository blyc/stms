<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>班级课程体系</title>
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
            vertical-align: middle!important;
        }
        #table1{
            font-size:12px
        }
    </style>
    <script>
        $(function () {
            $.get('${basePath}/course/selectall.shtml',
                function (result) {
                    if (result && result.status != 200) {
                        return layer.msg(result.message, so.default), !1;
                    }
                    var obj = result.list;
                    var str = "";
                    for(var i = 0;i<obj.length;i++){
                        var coid = obj[i].coid;
                        var coname = obj[i].coname;
                        str = str + "<option value='"+coid+"'>"+coname+"</option>"
                    }
                    $("#coname").html(str);
                })
       })

        function _openAddDlg(cid) {
            var coid = $("#coname").val()
            $.post('/course/insertucurricula.shtml', {ccid: cid,coid:coid}
                    , function (result) {
                        if (result && result.status != 200) {
                            return layer.msg(result.message, so.default), !1;
                        }
                    }, 'json');
            $('#addAttendance').modal();
        }
    </script>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 5/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.education 1/>
        <div class="col-md-10">
            <h3>班级课程体系</h3>
            <br>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well" id="div1">
                    <div class="form-group">
                        <input type="date" class="form-control" style="width: 150px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入日期">
                    </div>
                    <div class="form-group">
                        <select id="coname" class="form-control" style="width: 150px;">

                        </select>
                    </div>
                    <span class="">
                    <#--pull-right -->
                        <a id="addBtn" class="btn btn-success" onclick="javascript:_openAddDlg(${cid});">生成课程体系</a>
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
                        <th>周一</th><th id="1"></th><th id="2"></th><th id="3"></th><th id="4"></th><th id="5"></th><th id="6"></th><th id="7"></th><th id="8"></th><th id="9"></th><th id="10"></th>
                    </tr>
                    <tr>
                        <th>周二</th><th id="11"></th><th id="12"></th><th id="13"></th><th id="14"></th><th id="15"></th><th id="16"></th><th id="17"></th><th id="18"></th><th id="19"></th><th id="20"></th>
                    </tr>
                    <tr>
                        <th>周三</th><th id="21"></th><th id="22"></th><th id="23"></th><th id="24"></th><th id="25"></th><th id="26"></th><th id="27"></th><th id="28"></th><th id="29"></th><th id="30"></th>
                    </tr>
                    <tr>
                        <th>周四</th><th id="31"></th><th id="32"></th><th id="33"></th><th id="34"></th><th id="35"></th><th id="36"></th><th id="31"></th><th id="31"></th><th id="31"></th><th id="31"></th>
                    </tr>
                    <tr>
                        <th>周五</th><th id="41"></th><th id="42"></th><th id="43"></th><th id="44"></th><th id="45"></th><th id="46"></th><th id="47"></th><th id="48"></th><th id="49"></th><th id="50"></th>
                    </tr>
                    <tr>
                        <th>周六</th><th id="51"></th><th id="52"></th><th id="53"></th><th id="54"></th><th id="55"></th><th id="56"></th><th id="57"></th><th id="58"></th><th id="59"></th><th id="60"></th>
                    </tr>
                    <tr>
                        <th>周日</th><th id="61"></th><th id="62"></th><th id="63"></th><th id="64"></th><th id="65"></th><th id="66"></th><th id="67"></th><th id="68"></th><th id="69"></th><th id="70"></th>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

</body>
</html>