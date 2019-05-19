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
    <script src="${basePath}/js/jQuery.regex.js"></script>
    <script>
        /*/!*显示弹窗*!/
        function show_detail(id) {
            $('#myModal').modal();
            $.get('${basePath}/teachingmaterial/getapplication.shtml', {aid: id}, function (result) {
                var obj = eval(result.uApplications);
                var strHtml = "";
                for (var i = 0; i < obj.length; i++) {
                    strHtml = strHtml + '<tr><th>书籍信息</th>'
                            + '<th>版本</th>'
                            + '<th>单价</th>'
                            + ' <th>购买数量</th>'
                            + ' <th>使用班级</th>'
                            + '<th>收货人</th>'
                            + ' <th>电话</th>'
                            + '<th>手机</th>'
                            + '<th>收货人</th>'
                            + '<th>电话</th>'
                            + '<th>手机</th></tr>'
                            + '<tr><th>' + obj[i].uMajor.name + ''
                            + '<th>' + obj[i].edition + '</th>'
                            + '<th>' + obj[i].price + '(元)' + '</th>'
                            + '<th>' + obj[i].number + '(套)' + '</th>'
                            + '<th>' + obj[i].classname + '</th>'
                            + '<th>' + obj[i].addressee1 + '</th>'
                            + '<th>' + obj[i].telephone1 + '</th>'
                            + '<th>' + obj[i].tel1 + '</th>'
                            + '<th>' + obj[i].addressee2 + '</th>'
                            + '<th>' + obj[i].telephone2 + '</th>'
                            + '<th>' + obj[i].tel2 + '</th></tr>'
                            + '<tr style="text-align: center;background-color:#1abc9c "><th>共计</th><th>' + obj[i].number + '(套)' + '</th><th>合计</th><th>' + obj[i].number * obj[i].price + '(元)' + '</th></tr>';
                    $("#fname").val(obj[i].uMajor.name);
                    $("#fnumber").val(obj[i].number);
                    $("#fdate").val(obj[i].adate);
                }
                $("#modeoss").html(strHtml);
            });
        }*/

        function show_application_dlg() {
            $('#applicationdlg').modal();
        }

        <#--function getimg(id) {-->
            <#--$('#myModa2').modal();-->
            <#--$.get('${basePath}/teachingmaterial/selectByUteach.shtml', {aid: id}, function (result) {-->
                <#--var obj = eval(result.uApplications);-->
                <#--for (var i = 0; i < obj.length; i++) {-->
                    <#--$('#img').attr('src', obj[i].img);-->
                <#--}-->
            <#--});-->
        <#--}-->
        <#--function addUUFeedback(id) {-->
            <#--$("#aid").val(id);-->
            <#--$('#addUUFeedback').modal();-->
            <#--var format = function (time, format) {-->
                <#--var t = new Date(time);-->
                <#--var tf = function (i) {-->
                    <#--return (i < 10 ? '0' : '') + i-->
                <#--};-->
                <#--return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {-->
                    <#--switch (a) {-->
                        <#--case 'yyyy':-->
                            <#--return tf(t.getFullYear());-->
                            <#--break;-->
                        <#--case 'MM':-->
                            <#--return tf(t.getMonth() + 1);-->
                            <#--break;-->
                        <#--case 'mm':-->
                            <#--return tf(t.getMinutes());-->
                            <#--break;-->
                        <#--case 'dd':-->
                            <#--return tf(t.getDate());-->
                            <#--break;-->
                        <#--case 'HH':-->
                            <#--return tf(t.getHours());-->
                            <#--break;-->
                        <#--case 'ss':-->
                            <#--return tf(t.getSeconds());-->
                            <#--break;-->
                    <#--}-->
                <#--})-->
            <#--};-->
            <#--$.get('${basePath}/teachingmaterial/selectByUteach.shtml', {aid: id}, function (result) {-->
                <#--var obj = eval(result.uApplications);-->
                <#--var strHtml = "";-->
                <#--for (var i = 0; i < obj.length; i++) {-->
                    <#--$("#fname").val(obj[i].uMajor.name);-->
                    <#--$("#fnumber").val(obj[i].number);-->
                    <#--$("#fdate").val(format(obj[i].adate, 'yyyy-MM-dd'));-->
                    <#--$("#edition").val(obj[i].edition);-->
                <#--}-->
            <#--});-->
            <#--$.post('${basePath}/teachingmaterial/updateByPrimaryKeySelective.shtml', {aid: id}, function (result) {-->
            <#--});-->
        <#--}-->

        function ustate(id) {
            var load = layer.load();
            $.post('${basePath}/application/ustate.shtml', {action: 1, id: id},
                    function (result) {
                        layer.close(load);
                        if (result && result.status != 200) {

                            return layer.msg(result.message, so.default), !1;
                        }
                        layer.msg('保存成功。');
                        setTimeout(function () {
                            $('#formId').submit();
                        }, 1000);
                    });

        }


        <#--function addUFeedback() {-->
            <#--var a = $("#aid").val();-->
            <#--var load = layer.load();-->
            <#--var fname = $("#fname").val();-->
            <#--var edition = $("#edition").val();-->
            <#--var fnumber = $("#fnumber").val();-->
            <#--var fdate = $("#fdate").val();-->
            <#--var grantdate = $("#grantdate").val();-->

            <#--function tab(date1, date2) {-->
                <#--var oDate1 = new Date(date1);-->
                <#--var oDate2 = new Date(date2);-->
                <#--if (oDate1.getTime() > oDate2.getTime()) {-->
                    <#--return layer.msg('反馈时间不能小于申请时间。', so.default), !1;-->
                <#--}-->
            <#--}-->

            <#--/*比较时间*/-->
            <#--tab(fdate, grantdate)-->
            <#--if ($.trim(grantdate) == '') {-->
                <#--return layer.msg('反馈时间为空。', so.default), !1;-->

            <#--}-->
            <#--if ($.trim(fnumber) == '') {-->
                <#--return layer.msg('数量不能为空。', so.default), !1;-->
            <#--}-->
            <#--$.post('${basePath}/teachingmaterial/insertSelective.shtml', {-->
                        <#--fname: fname,-->
                        <#--edition: edition,-->
                        <#--fnumber: fnumber,-->
                        <#--fdate: fdate,-->
                        <#--grantdate: grantdate,-->
                        <#--aid: a-->
                    <#--},-->
                    <#--function (result) {-->
                        <#--layer.close(load);-->
                        <#--if (result && result.status != 200) {-->

                            <#--return layer.msg(result.message, so.default), !1;-->
                        <#--}-->
                        <#--layer.msg('保存成功。');-->
                        <#--setTimeout(function () {-->
                            <#--$('#formId').submit();-->
                        <#--}, 1000);-->

                    <#--}, 'json');-->
        <#--}-->


       <#--/* function selectUFeedback(id) {-->
            <#--$("#aid").val(id);-->
            <#--$('#selectUFeedback').modal();-->
            <#--var format = function (time, format) {-->
                <#--var t = new Date(time);-->
                <#--var tf = function (i) {-->
                    <#--return (i < 10 ? '0' : '') + i-->
                <#--};-->
                <#--return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {-->
                    <#--switch (a) {-->
                        <#--case 'yyyy':-->
                            <#--return tf(t.getFullYear());-->
                            <#--break;-->
                        <#--case 'MM':-->
                            <#--return tf(t.getMonth() + 1);-->
                            <#--break;-->
                        <#--case 'mm':-->
                            <#--return tf(t.getMinutes());-->
                            <#--break;-->
                        <#--case 'dd':-->
                            <#--return tf(t.getDate());-->
                            <#--break;-->
                        <#--case 'HH':-->
                            <#--return tf(t.getHours());-->
                            <#--break;-->
                        <#--case 'ss':-->
                            <#--return tf(t.getSeconds());-->
                            <#--break;-->
                    <#--}-->
                <#--})-->
            <#--};-->
            <#--$.post('${basePath}/application/selectUFeedbackANdaid.shtml', {aid: id}, function (result) {-->
                <#--var obj = eval(result.uFeedbacks);-->
                <#--for (var i = 0; i < obj.length; i++) {-->
                    <#--$("#fname1").val(obj[i].fname);-->
                    <#--$("#fnumber1").val(obj[i].fnumber);-->
                    <#--$("#fdate1").val(format(obj[i].fdate, 'yyyy-MM-dd'));-->
                    <#--$("#fa1").val(format(obj[i].grantdate, 'yyyy-MM-dd'));-->
                    <#--$("#edition1").val(obj[i].edition);-->
                <#--}-->
            <#--});-->

        <#--}*/-->
        function uploadjpg() {
            $.ajaxFileUpload({
                url: '${basePath}/application/uploada.shtml',//后台请求地址
                type: 'post',//请求方式 当要提交自定义参数时，这个参数要设置成post
                secureuri: false,//是否启用安全提交，默认为false。
                fileElementId: 'file',// 需要上传的文件域的ID，即<input type="file">的ID。
                dataType: 'json',//服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。如果json返回的带pre,这里修改为json即可解决。
                success: function (msg) {//提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                    var a = msg.fileName
                    var b = msg.file
                    /*alert("/"+a+"/"+b)*/
                    $("#filetext").val("/" + a + "/" + b);
                    var ig = $("#filetext").val();
                    alert(ig)
                    /*$('#imggg').attr('src',"/"+a+"/"+b);*/
                    var a = '<img src=' + ig + ' style="width: 400px;height:200px;" id="imggg">';
                    $("#loadimg1").html(a)

                },

            });
        }


        $(function () {
            $('input[required]').before('<span style="color:red">*</span>');

            $("#pirc").bind('input propertychange', function () {
                var a = $("#pirc").val();
                var b = $("#num").val();
                $("#zong").val(a * b)
                $("#h").val(a * b)
            });
            $("#num").bind('input propertychange', function () {
                var a = $("#pirc").val();
                var b = $("#num").val();
                $("#zong").val(a * b)
                $("#h").val(a * b)
                $("#g").val($("#num").val() + "（套）");
            });

            $.get('${basePath}/application/getcompany.shtml', function (result) {
                var obj = result.entity;
                $("#campus").val(obj.name);
            });


            $("#savebtn").click(function () {
                var campus = $("#campus").val();
                var zipcode = $("#zipcode").val();
                var address = $("#addresss").val();
                var addressee1 = $("#addressee1").val();
                var telephone1 = $("#telephone1").val();
                var tel1 = $("#tel1").val();
                var remarks = $("#remarks").val();
                var addressee2 = $("#addressee2").val();
                var telephone2 = $("#telephone2").val();
                var tel2 = $("#tel2").val();
                var img = $("#filetext").val();

                if ($.trim(campus) == '') {
                    return layer.msg('校区为空。', so.default), !1;
                }

                if ($.trim(address) == '') {
                    return layer.msg('订购地址为空。', so.default), !1;

                }
                if ($.trim(addressee1) == '') {
                    return layer.msg('收件人为空。', so.default), !1;

                }
                if ($.trim(telephone1) == '') {
                    return layer.msg('固定电话为空。', so.default), !1;
                }

                if ($.trim(tel1) == '') {
                    return layer.msg('手机号为空。', so.default), !1;
                }

                var load = layer.load();
                $.post('${basePath}/application.shtml',
                        {
                            campus: campus,
                            zipcode: zipcode,
                            address: address,
                            addressee1: addressee1,
                            telephone1: telephone1,
                            tel1: tel1,
                            remarks: remarks,
                            addressee2: addressee2,
                            telephone2: telephone2,
                            tel2: tel2,
                            img: img,
                        },
                        function (result) {
                            layer.close(load);
                            if (result && result.status != 200) {

                                return layer.msg(result.message, so.default), !1;
                            }
                            layer.msg('保存成功。');
                            setTimeout(function () {
                                $('#formId').submit();
                            }, 1000);
                        });
            });
        });
    </script>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 4/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left4 3/>
        <div class="col-md-10">
            <h2>教材申请列表</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/申请人">
                    </div>
                    <span class=""> <#--pull-right -->
                        <button type="submit" class="btn btn-primary">查询</button>
                         <a id="addBtn" class="btn btn-success" onclick="javascript:show_application_dlg();">申请教材</a>
                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>订购校区</th>
                        <th>申请时间</th>
                        <th>校区地址</th>
                        <th>申请人</th>
                        <th>备注</th>
                        <th>总数量</th>
                        <th>总金额</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>${it.campus?default('未设置')}</td>
                            <td>${(it.adate?string("yyyy/MM/dd"))?default('未设置')}</td>
                            <td>${it.address?default('未设置')}</td>
                            <td>${it.applicant?default('未设置')}</td>
                            <td>${it.remarks?default('未设置')}</td>
                            <td>${it.number?default('未设置')}</td>
                            <td>${it.price?default('未设置')}</td>
                            <td>
                                <#if it.state ==0 >
                                    未确定
                                </#if>
                                <#if it.state ==1 >
                                    待发货
                                </#if>
                                <#if it.state ==2 >
                                    已发货
                                </#if>
                                <#if it.state ==3 >
                                    已收货
                                </#if>

                            </td>
                            <td>
                                <a href="${basePath}/application/detaillist/${it.aid}.shtml">查看详情</a>
                                <#if it.state ==0 >
                                    <a href="javascript:ustate(${it.aid});">确认下单</a>
                                </#if>
                                <#if it.state ==1 >
                                    <a href="javascript:addUUFeedback(${it.aid});">确认发货</a>
                                </#if>
                                <#if it.state ==2 >
                                    <a href="javascript:ustate(${it.aid});">确认收货</a>
                                </#if>
                            <#-- <a href="javascript:displayinterface(${it.aid});">查看详情</a>-->

                            <#-- <a href="javascript:displayinterface(${it.aid});">查看详情</a>
                             <a href="javascript:getimg(${it.aid});">查看回执单</a>-->
                            <#--<#if it.state !=1 >
                                <a href="javascript:addUUFeedback(${it.aid});">添加反馈</a>
                            </#if>
                            <#if it.state ==1 >
                                <a href="javascript:selectUFeedback(${it.aid});">查看反馈</a>
                            </#if>-->

                            </td>

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

<#--弹框 添加班级-->
<div class="modal fade" id="addUUFeedback" tabindex="-1" role="dialog" aria-labelledby="addUClassLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addUClassLabel">添加反馈单</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="form-group">
                        <input type="text" style="display: none" id="aid">
                        <label for="recipient-name" class="control-label">教材名称</label>
                        <input type="text" class="form-control" name="fname" id="fname"
                               placeholder="请输入教材名称" disabled/>
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">教材版本</label>
                        <input type="text" class="form-control" name="edition" id="edition"
                               placeholder="请输入教材版本" disabled/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">教材数量</label><span id="t0"></span>
                        <input type="number" max="20" class="form-control" name="fnumber" id="fnumber"
                               placeholder="请输入班级数量"/>
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">教材申请日期</label><span id="t1"></span>
                        <input type="text" class="form-control" name="fdate" id="fdate"
                               placeholder="请输入班级数量" disabled/>

                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">发放日期</label><span id="t2"></span>
                        <input type="date" class="form-control" id="grantdate" name="grantdate" required>

                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="addUFeedback();" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


<#--弹框 添加班级-->
<div class="modal fade" id="selectUFeedback" tabindex="-1" role="dialog" aria-labelledby="addUClassLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addUClassLabel">反馈单</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">教材名称</label>
                        <input type="text" class="form-control" name="fname1" id="fname1"
                               placeholder="请输入教材名称" disabled/>
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">教材版本</label>
                        <input type="text" class="form-control" name="edition1" id="edition1"
                               placeholder="请输入教材版本" disabled/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">教材数量</label><span id="t0"></span>
                        <input type="number" max="20" class="form-control" name="fnumber1" id="fnumber1"
                               placeholder="请输入班级数量" disabled/>
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">教材申请日期</label><span id="t1"></span>
                        <input type="text" class="form-control" name="fdate1" id="fdate1"
                               placeholder="请输入班级数量" disabled/>
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">教材发放日期</label><span id="t1"></span>
                        <input type="text" class="form-control" name="fa1" id="fa1"
                               placeholder="请输入班级数量" disabled/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="addUFeedback();" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="applicationdlg" tabindex="-1" role="dialog" aria-labelledby="applicationdlgLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:1000px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">申请单详情</h4>
            </div>
            <div class="modal-body" id="modeljiemian">
                <form id="boxRoleForm">
                    <div class="row">
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">订购校区:</label>
                                <input type="text" class="form-control" id="campus" name="campus" disabled/>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">邮编:</label>
                                <input type="text" class="form-control" name="zipcode" id="zipcode" maxlength="6"/>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">送货地址:</label>
                                <input type="text" class="form-control" id="addresss" name="addresss" maxlength="20"
                                       required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">收件人1:</label>
                                <input type="text" class="form-control" name="addressee1" id="addressee1" required
                                       maxlength="10"/>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">固定电话:</label>
                                <input type="text" class="form-control" name="telephone1" id="telephone1" maxlength="11"
                                       required/>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">手机:</label>
                                <input type="text" class="form-control" name="tel1" id="tel1" maxlength="11" required/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">备注: </label>
                                <input type="text" class="form-control" id="remarks" name="remarks" maxlength="20">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">收件人2:</label>
                                <input type="text" class="form-control" name="addressee2" id="addressee2"
                                       maxlength="11"/>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">固定电话2:</label>
                                <input type="text" class="form-control" name="telephone2" id="telephone2"
                                       maxlength="11"/>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">手机2:</label>
                                <input type="text" class="form-control" name="tel2" id="tel2" maxlength="11"/>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <input id="lefile" type="file" style="display:none">
                            <div class="input-append">
                                <input type="text" id="filetext" style="display:none">
                                <input id="file" name="file" class="input-large" type="file" style="height:30px;">
                                <button type="button" class="btn btn-primary" onclick="uploadjpg();"
                                        style="position: absolute;top: 1px;left: 200px">上传
                                </button>
                            </div>
                        </div>
                        <div style="position: absolute;z-index: 30;width:400px;height: 200px;left: 500px; top: 200px;"
                             id="loadimg1">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="savebtn">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
</body>
</html>