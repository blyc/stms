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
            $('#applicationdetaildlg').modal();
            $('#stage option:first').attr("selected", true);
        }

        $(function () {
            $('input[required]').before('<span style="color:red">*</span>');

            $("#salesprice").bind('input propertychange', function () {
                var a = $("#salesprice").val();
                var b = $("#number").val();
                $("#price").val(a * b);
            });

            $("#number").bind('input propertychange', function () {
                var a = $("#salesprice").val();
                var b = $("#number").val();
                $("#price").val(a * b);
            });

            //专业
            $("#mid").empty();
            $.get('${basePath}/application/getmajor.shtml', function (result) {
                var obj = eval(result);
                $("#mid").append($("<option>").val("").text("请选择"));
                if (obj != undefined) {
                    for (var i = 0; i < obj.length; i++) {
                        var option = $("<option>").val(result[i].key).text(result[i].value);
                        $("#mid").append(option);
                    }
                }
                $('#mid option:first').attr("selected", true);
            });

            //年级
            $("#grade").empty();
            $.get('${basePath}/application/getgrade.shtml', function (result) {
                var obj = eval(result);
                var option = $("<option>").val("").text("");
                $("#grade").append(option);

                if (obj != undefined) {
                    for (var i = 0; i < obj.length; i++) {
                        var option = $("<option>").val(result[i].key).text(result[i].value);
                        $("#grade").append(option);
                    }
                }
                $('#grade option:first').attr("selected", true);
            });


            $("#stage").bind("change", function () {

                var mid = $("#mid  option:selected").val();
                var grade = $("#grade  option:selected").val();
                var stage = $("#stage  option:selected").val();
                var mname = $("#mid  option:selected").text();
                var nextstage = stage;


                if ($.trim(mid) == '') {
                    return layer.msg('专业为空。', so.default), !1;
                }

                if ($.trim(grade) == '') {
                    return layer.msg('年级为空。', so.default), !1;

                }
                if ($.trim(stage) == '') {
                    return layer.msg('阶段为空。', so.default), !1;
                }

                $.get('${basePath}/application/classinfo.shtml', {
                    mid: mid,
                    grade: grade,
                    stage: stage
                }, function (result) {
                    var obj = eval(result);
                    if (obj != undefined) {
                        $('#number').val(obj.totalsum);
                         $('#totalclass').val(obj.totalclass);
                        $('#totalclasshiden').val(obj.totalclass);
                        $("#edition").val(nextstage + "阶段");
                        $("#mname").val(mname);
                    }
                });
            });

            $("#savebtn").click(function () {
                var mid = $("#mid").val();
                var number = $("#number").val();
                var remarks = $("#remarks").val();
                var edition = $("#edition").val();
                var salesprice = $("#salesprice").val();
                var price = $("#price").val();
                var aid = $("#aid").val();
                var stage = $("#stage").val();
                var grade = $("#grade").val();
                var mname = $("#mname").val();

                var load = layer.load();
                alert("1243");
                $.post('${basePath}/application/detail.shtml',
                        {
                            mid: mid,
                            number: number,
                            remarks: remarks,
                            edition: edition,
                            mname: mname,
                            salesprice: salesprice,
                            price: price,
                            stage: stage,
                            grade: grade,
                            aid: aid,
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
                        <th>编号</th>
                        <th>教学资料名称</th>
                        <th>版本</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>总价格</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>${it_index+1}</td>
                            <td>${it.mname?default('未设置')}</td>
                            <td>${it.edition?default('未设置')}</td>
                            <td>${it.salesprice?default('未设置')}</td>
                            <td>${it.number?default('未设置')}</td>
                            <td>${it.salesprice*it.number?default('未设置')}</td>
                            <td>${it.remarks?default('未设置')}</td>
                            <td>



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


<div class="modal fade" id="applicationdetaildlg" tabindex="-1" role="dialog"
     aria-labelledby="applicationdetaildlgLabel" aria-hidden="true">
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
                                <label for="recipient-name" class="control-label">专业</label>
                                <select class="form-control" id="mid" name="mid">
                                    <option value="">请选择</option>
                                </select>
                                <input type="hidden"  name="aid" id="aid" value="${aid}">
                            </div>
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">年级</label>
                                <select class="form-control" id="grade" name="grade">
                                    <option value="">请选择</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">阶段</label>
                                <select class="form-control" id="stage" name="stage">
                                    <option value="">请选择</option>
                                    <option value="S1">S1阶段</option>
                                    <option value="S2">S2阶段</option>
                                    <option value="S3">S3阶段</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="recipient-name" class="control-label">教学资料名称:</label>
                                <input type="text" class="form-control" name="mname" id="mname" disabled/>
                            <#--<input type="hidden" class="form-control" name="mid" id="mid"/>-->
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">班级数量:</label>
                                <input type="text" class="form-control" name="totalclass" id="totalclass" disabled>
                                <input type="hidden" class="form-control" name="totalclasshide" id="totalclasshide">
                            </div>
                        </div>

                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">版本:</label>
                                <input type="text" class="form-control" name="edition" id="edition" disabled/>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">单价:</label>
                                <input type="text" class="form-control" name="salesprice" id="salesprice" maxlength="11"
                                       required/>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">数量:</label>
                                <input type="text" class="form-control" name="number" id="number" maxlength="11"
                                       required/>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">总金额:</label>
                                <input type="text" class="form-control" name="price" id="price" disabled required/>
                            </div>
                        </div>


                    </div>
                    <div class="row">

                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="recipient-name" class="control-label">备注: </label>
                                <input type="text" class="form-control" id="remarks" name="remarks" maxlength="20">
                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="savebtn" >确定</button>
            </div>
        </div><!-- /.modal-content &ndash;&gt;
    </div><!-- /.modal -->
    </div>
</div>
</body>
</html>