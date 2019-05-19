<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>教材管理</title>
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
        $.post('${basePath}/stock/findAllUdetails.shtml', function (resultclassinfo) {
            obj1 = eval(resultclassinfo);
            for (var i = 0; i < obj1.length; i++) {
                var option = $("<option>").val(resultclassinfo[i].key).text(resultclassinfo[i].value);
                $("#mid").append(option);
            }
            $('#mid option:first').attr("selected", true);
        });
    });
        function addUkucun() {
            $('#addUkucun').modal();
        }

        function setruku() {
            var mid =$("#mid").val();
            alert(mid);
            var snumber =$("#number").val();
            var sname =$("#sname").val();
            var edition =$("#edition").val();
            var price =$("#price").val();
            var load = layer.load();
            $.post('${basePath}/stock/insertUStorage.shtml',
                    {snumber:snumber,sname:sname,mid:mid,price:price,edition:edition},
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

    </script>

</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 7/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left7 7/>
        <div class="col-md-10">
            <h2>库存管理</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/申请人">
                    </div>
                    <span class=""> <#--pull-right -->
                        <button type="submit" class="btn btn-primary">查询</button>

                         <a id="addBtn" class="btn btn-success" onclick="javascript:addUkucun();">添加库存</a>

                    <#--
                                                 <a id="addBtn" class="btn btn-success" onclick="javascript:_openaddDlg();">添加班级</a>
                    -->
                    </span>
                </div>
                <hr>
                <table class="table table-bordered" >
                    <tr>

                        <th>编号</th>
                        <th>教学资料</th>
                        <th>剩余数量</th>
                        <th>单价</th>


                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                          <td>${it.did?default('未设置')}</td>
                            <td>${it.uMajor.name?default('未设置')}</td>
                            <td>${it.total?default('未设置')}</td>
                            <td>${it.price?default('未设置')}</td>
                         <td>
                                   <a href="${basePath}/thelibrary/list${it.did}.shtml">入库记录</a>
                                   <a href="${basePath}/record/list${it.did}.shtml">出库记录</a>
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
<div class="modal fade" id="addUkucun" tabindex="-1" role="dialog" aria-labelledby="addUClassLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addUClassLabel">添加教材</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="form-group">
                        <input type="text" style="display: none" id="aid">
                        <label for="recipient-name" class="control-label">教材名称:</label>
                        <select class="form-control" id="mid" name="mid"  required>
                            <option value="">请选择</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" style="display: none" id="aid">
                        <label for="recipient-name" class="control-label">阶段:</label>
                        <select class="form-control" id="edition" name="edition"  required>
                            <option value="第一阶段">第一阶段</option>
                            <option value="第二阶段">第二阶段</option>
                            <option value="第三阶段">第三阶段</option>
                        </select>
                    </div>

                    <div class="form-group">
                    <label for="recipient-name" class="control-label">入库数量</label>
                    <input type="text" class="form-control" name="number" id="number"
                           placeholder="请输入数量" />
                </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">单价</label>
                        <input type="text" class="form-control" name="price" id="price"
                               placeholder="请输入单价" />
                    </div>

                    <div class="form-group">
                        <label for="recipient-name" class="control-label">登记人</label><span id="t1"></span>
                        <input type="text" class="form-control" name="sname" id="sname"
                               placeholder="请输入登记人" />
                         </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="setruku();" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


</div>
</body>
</html>