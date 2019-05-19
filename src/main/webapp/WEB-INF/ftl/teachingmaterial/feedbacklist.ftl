<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>校区教务教材管理</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/shiro.demo.js"></script>
    <script src="${basePath}/js/ajaxfileupload.js"></script>
    <script>
        $(function () {
            $("#pirc").bind('input propertychange', function() {
                var a = $("#pirc").val();
                var b = $("#num").val();
                $("#zong").val(a*b)
                $("#h").val(a*b)
            });
            $("#num").bind('input propertychange', function() {
                $("#g").val($("#num").val()+"（套）");
            });
            //查询全部校区
            $.post('${basePath}/feedback/selectUcompanyALLName.shtml', function (resultclassinfo) {
                obj1 = eval(resultclassinfo);
                for (var i = 0; i < obj1.length; i++) {
                    var option = $("<option>").val(resultclassinfo[i].key).text(resultclassinfo[i].value);
                    $("#campus").append(option);
                }
                $('#campus option:first').attr("selected", true);
            });

            //查询专业
            $.post('${basePath}/feedback/selectALLUmajor.shtml', function (resultclassinfo) {
                obj1 = eval(resultclassinfo);
                for (var i = 0; i < obj1.length; i++) {
                    var option = $("<option>").val(resultclassinfo[i].key).text(resultclassinfo[i].value);
                    $("#tname").append(option);
                }
                $('#tname option:first').attr("selected", true);
            });



            /*查询班级*/
            $.post('${basePath}/feedback/selectALLUClassname.shtml', function (resultclassinfo) {
                obj1 = eval(resultclassinfo);
                for (var i = 0; i < obj1.length; i++) {
                    var option = $("<option>").val(resultclassinfo[i].key).text(resultclassinfo[i].value);
                    $("#classs").append(option);
                }
                $('#classs option:first').attr("selected", true);
            });

            $("#classs").bind("change",function () {
                    var  a = $("#classs").val();
                    alert(a)
             $.post('${basePath}/feedback/selectALLUClassnum.shtml',{name:a},
                        function (resultclassinfo) {
                    obj1 = eval(resultclassinfo.uClasses);
                    for (var i = 0; i < obj1.length; i++) {

                        $('#num').val(obj1[i].num)
                        $('#g').val(obj1[i].num+"（套）")
                    }

                });

            });


            /*根据校区查询信息*/
            $("#campus").bind("change", function () {
                    var a = $("#campus").val()
             $.post('${basePath}/feedback/selectUApplicationBycampus.shtml',
                        {campus:a},
                function (msg) {
                    var obj = eval(msg.uApplications)

                    if(obj == "" || obj == undefined || obj == null){
                        $("#addresss").val("")
                        $("#zipcode").val("")
                        $("#addressee1").val("")
                        $("#telephone1").val("")
                        $("#tel1").val("")
                        $("#addressee2").val("")
                        $("#telephone2").val("")
                        $("#tel2").val("")
                    }
                    for (var i=0;i<obj.length;i++){
                       $("#addresss").val(obj[i].address)
                       $("#zipcode").val(obj[i].zipcode)
                       $("#addressee1").val(obj[i].addressee1)
                        $("#telephone1").val(obj[i].telephone1)
                        $("#tel1").val(obj[i].tel1)
                        $("#addressee2").val(obj[i].addressee2)
                        $("#telephone2").val(obj[i].telephone2)
                        $("#tel2").val(obj[i].tel2)
                    }
});
    });
});
        function add() {
            $('#myModal').modal();

        }



    </script>

</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 4/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left4 3/>
        <div class="col-md-10">
            <h2>教材反馈列表</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/申请人">
                    </div>
                    <span class=""> <#--pull-right -->
                        <button type="submit" class="btn btn-primary">查询</button>
            	       </span>
                </div>
                <hr>
                <table class="table table-bordered" >
                    <tr>
                        <th>反馈编号</th>
                        <th>教材名称</th>
                        <th>教材版本</th>
                        <th>教材数量</th>
                        <th>申请时间</th>
                        <th>发放时间</th>

                        <#--<th>操作</th>-->
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>${it.fid?default('未设置')}</td>
                            <td>${it.fname?default('未设置')}</td>
                            <td>${it.edition?default('未设置')}</td>
                            <td>${it.fnumber?default('未设置')}</td>
                            <td>${it.fdate?string("yyyy/MM/dd")?default('未设置')}</td>
                            <td>${it.grantdate?string("yyyy/MM/dd")?default('未设置')}</td>
                        <#--    <td>
                               &lt;#&ndash; <a href="javascript:_openoutStudentDlg(${it.ccid});">移出学生</a>

                               &ndash;&gt;
                                   <a href="javascript:Displayinterface(${it.aid});">查看详情</a>
                                   <a href="javascript:Displayinterface(${it.aid});">查看回执单</a>
                                   <a href="javascript:addUUFeedback(${it.aid});">反馈</a>
                           </td>-->

                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="9">没有申请</td>
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

</div>
</body>
</html>