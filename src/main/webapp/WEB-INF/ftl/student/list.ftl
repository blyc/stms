<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>学生档案</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/util.js"></script>
    <script src="${basePath}/js/jQuery.regex.js"></script>
    <script src="${basePath}/js/postcode.js"></script>
    <script baseUrl="${basePath}" src="${basePath}/js/hp/student.list.js"></script>
    <style>
        .popover {
            max-width: 600px;
        }

        .col-md-6 {
            width: 33%;
        }

        .table th {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
    <script>
        function exportfunc() {
            var url = "${basePath}/student/export.shtml?cid=" + $("#rearchcid").val() + "&grade=" + $("#rearchgrade").val() + "&mid=" + $("#rearchmid").val() + "&findContent=" + $("#findContent").val()
                    + "&cname=" + $("#rearchcname").val() + "&jsname=" + $("#rearchjsid").val() + "&bzrname=" + $("#rearchbzrid").val();
            window.location.href = encodeURI(url);
        }
    </script>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 4/>
<div class="container" style="">
    <div class="row">
    <@_left.left4 1/>
        <div class="col-md-10">
            <h2>学生档案</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <select class="form-control" id="rearchcid" name="cid"
                                                style="width:110px"></select>
                                        <input type="hidden" id="hidden_cid" value="${reachStudentBo.cid?default('')}">
                                        <select class="form-control" id="rearchgrade" name="grade"
                                                style="width:110px"></select>
                                        <input type="hidden" id="hidden_grade"
                                               value="${reachStudentBo.grade?default('')}">
                                        <select class="form-control" id="rearchmid" name="mid"
                                                style="width:110px"></select>
                                        <input type="hidden" id="hidden_mid" value="${reachStudentBo.mid?default('')}">
                                        <input type="text" class="form-control" value="${findContent?default('')}"
                                               name="findContent" id="findContent" style="width:100px" placeholder="姓名">
                                        <input type="text" class="form-control"
                                               value="${reachStudentBo.cname?default('')}" id="rearchcname" name="cname"
                                               style="width:100px" maxlength="11" placeholder="班级名称">
                                        <input type="text" class="form-control"
                                               value="${reachStudentBo.jsname?default('')}" id="rearchjsid"
                                               name="jsname" style="width:100px" maxlength="11" placeholder="讲师姓名">
                                        <input type="text" class="form-control"
                                               value="${reachStudentBo.bzrname?default('')}" id="rearchbzrid"
                                               name="bzrname" style="width:100px" maxlength="11" placeholder="班主任姓名">
                                        <button type="submit" class="btn btn-primary">查询</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <span class=""> <#--pull-right -->

                    <@shiro.hasPermission name="/student.shtml">
                        <a id="addBtn" class="btn btn-success">添加学生</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/student/export.shtml">
                        <a id="btn2" class="btn btn-success" href="javascript:exportfunc();">导出学生信息</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/student/import.shtml">
                        <a id="btn3" class="btn btn-success">导入学生信息</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/student/import/template.shtml">
                        <a id="btn1" class="btn btn-success"
                           href="${basePath}/student/import/template.shtml">下载Excel模板</a>
                    </@shiro.hasPermission>
                    </span>

                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>校区</th>
                        <th>专业</th>
                        <th>年级</th>
                        <th>班级</th>
                        <th>讲师</th>
                        <th>班主任</th>
                        <th>阶段</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td><input type="hidden" id="H${it.sid}"
                                       value="${it.name?default('')},${it.sex?default('')},${it.tel?default('')},${it.qq?default('')},${it.roomcode?default('')},${it.email?default('')},${it.fatherTel?default('')},${it.motherTel?default('')},${it.otherName?default('')},${it.otherTel?default('')},${it.mid?default('')},${it.state?default('')},${it.vClass.classname?default('')}">
                                ${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.name?default('')}</td>
                            <td>${it.sex?default('')}</td>
                            <td>
                                <#if it.uCompany?exists >
                                    ${it.uCompany.name?default('')}
                                </#if>
                            </td>
                            <td>
                                <#if it.uMajor?exists >
                                    ${it.uMajor.name?default('')}
                                </#if>
                            </td>
                            <td>${it.grade?default('')}级</td>
                            <td>
                                <#if it.vClass?exists >
                                    ${it.vClass.classname?default('')}
                                </#if>
                            </td>
                            <td>
                                <#if it.vClass?exists >
                                    <#if it.vClass.jsname?exists>
                                        ${it.vClass.jsname?default('')}
                                    </#if>
                                    <#if it.vClass.dsname?exists>
                                        |${it.vClass.dsname?default('')}
                                    </#if>
                                </#if>
                            </td>
                            <td>
                                <#if it.vClass?exists >
                                    ${it.vClass.bzrname?default('')}
                                </#if>
                            </td>

                            <td>${it.stage?default('')}</td>
                            <td>${it.state?default('')}</td>
                            <td>
                                <div class="container-fluid">
                                    <div class="row">

                                        <a class="showdesc col-md-6"
                                           title="${it.name?default('')}"
                                           data-container="body" data-toggle="popover" data-placement="left"
                                           data-content="<table class='table table-striped'>
                                                    <tr><td>民族</td><td>${it.nation?default('')}</td></tr>
                                                    <tr><td>出生日期</td><td>${(it.birthday?string("yyyy/MM/dd"))?default('')}</td></tr>
                                                    <tr><td>学历</td><td>${it.education?default('')}</td></tr>
                                                    <tr><td>身份证号</td> <td>${it.idcard?default('')}</td></tr>
                                                    <tr><td>电话</td><td>${it.tel?default('')}</td></tr>
                                                    <tr><td>邮箱</td><td>${it.email?default('')}</td></tr>
                                                    <tr><td>QQ</td><td>${it.qq?default('')}</td></tr>
                                                    <tr><td>生源省</td><td>${it.province?default('')}</td></tr>
                                                    <tr><td>生源市</td><td>${it.city?default('')}</td></tr>
                                                    <tr><td>通讯地址</td> <td>${it.address?default('')}</td></tr>
                                                    <tr><td>邮编</td><td>${it.postcode?default('')}</td></tr>
                                                    <tr><td>父亲姓名|电话</td><td>${it.fatherName?default('')}|${it.fatherTel?default('')}</td></tr>
                                                    <tr><td>母亲姓名|电话</td><td>${it.motherName?default('')}|${it.motherTel?default('')}</td></tr>
                                                    <tr><td>其他亲属姓名|电话</td><td>${it.otherName?default('')}|${it.otherTel?default('')}</td></tr>
                                                    <tr><td>寝室号</td><td>${it.roomcode?default('')}</td></tr>
                                                    </table>">
                                            详情
                                        </a>

                                        <@shiro.hasPermission name="/student/updateStudent.shtml">
                                            <a class="col-md-6" onclick="javascript:_openUpdateDlg(${it.sid});">更新</a>
                                        </@shiro.hasPermission>
                                        <a class="col-md-6"
                                           onclick="javascript:_openDeleteDlg(${it.sid},'${it.vClass.classname?default('')}');">删除</a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="12">没有找到学生信息</td>
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
<#--新增弹框-->
<div class="modal fade" id="addStudent" tabindex="-1" role="dialog" aria-labelledby="addStudentLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addStudentLabel">添加学生信息</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">姓名:</label>
                                    <input type="text" class="form-control" name="name" id="name" required
                                           maxlength="10"/>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">性别:</label>
                                    <div class="form-control">
                                        <label class="radio-inline">
                                            <input type="radio" id="sex1" name="sex" value="男" checked="checked">男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" id="sex2" name="sex" value="女">女
                                        </label>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">民族:</label>
                                    <select class="form-control" id="nation" name="nation"></select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">身份证号:</label>
                                    <input type="text" class="form-control" name="idcard" id="idcard" required
                                           maxlength="18"/>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">出生日期:</label>
                                    <input type="date" class="form-control" id="birthday" name="birthday" required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">学历:</label>
                                    <select class="form-control" id="education" name="education"></select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">政治面貌:</label>
                                    <select class="form-control" id="politics" name="politics">
                                        <option value="群众">群众</option>
                                        <option value="团员">团员</option>
                                        <option value="党员">党员</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">电话:</label>
                                    <input type="text" class="form-control" id="tel" name="tel" required maxlength="11">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">邮箱:</label>
                                    <input type="email" class="form-control" id="email" name="email" maxlength="64">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">qq:</label>
                                    <input type="email" class="form-control" id="qq" name="qq" required maxlength="15">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">生源省:</label>
                                    <select class="form-control" id="province" name="province"
                                            onclick="javascript:_selectprovince('province');">
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">生源市:</label>
                                    <select class="form-control" id="city" name="city">
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">邮编:</label>
                                    <input type="text" class="fom-control" id="postcode" name="postcode" disabled
                                           maxlength="5">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">通讯地址:</label>
                                    <input type="text" class="form-control" id="address" name="address" required
                                           maxlength="100">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">年级:</label>
                                    <select class="form-control" id="grade" name="grade" required></select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">专业:</label>
                                    <select class="form-control" id="mid" name="mid" required></select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">班级:</label>
                                    <select class="form-control" id="cid" name="cid"></select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">寝室号:</label>
                                    <input type="text" class="form-control" id="roomcode" name="roomcode"
                                           maxlength="10">
                                </div>
                            </div>
                            <div class="col-md-2">

                            </div>
                            <div class="col-md-2">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">父亲姓名:</label>
                                    <input type="text" class="form-control" id="fatherName" name="fatherName"
                                           maxlength="10">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">父亲电话:</label>
                                    <input type="text" class="form-control" id="fatherTel" name="fatherTel"
                                           maxlength="11">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">母亲姓名:</label>
                                    <input type="text" class="form-control" id="motherName" name="motherName"
                                           maxlength="10">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">母亲电话:</label>
                                    <input type="text" class="form-control" id="motherTel" name="motherTel"
                                           maxlength="11">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">其他亲属姓名:</label>
                                    <input type="text" class="form-control" id="otherName" name="otherName"
                                           maxlength="10">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">其他亲属电话:</label>
                                    <input type="text" class="form-control" id="otherTel" name="otherTel"
                                           maxlength="11">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="savebtn" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<#--/新增弹框-->
<#--更新弹框-->
<div class="modal fade" id="updateStudent" tabindex="-1" role="dialog" aria-labelledby="updateStudentLabel">
    <div class="modal-dialog" role="document" style="width:900px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="updateStudentLabel">更新学生信息</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                        <#-- * 姓名-->
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">姓名:</label>
                                    <input type="text" class="form-control" id="update_name" name="update_name" required
                                           maxlength="11">
                                    <input type="hidden" id="update_sid">
                                </div>
                            </div>
                        <#-- * 性别-->
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">性别:</label>
                                    <div class="form-control">
                                        <label class="radio-inline">
                                            <input type="radio" id="update_sex1" name="update_sex" value="男"
                                                   checked="checked">男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" id="update_sex2" name="update_sex" value="女">女
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">电话:</label>
                                    <input type="text" class="form-control" id="update_tel" name="update_tel" required
                                           maxlength="11">
                                    <input type="hidden" id="update_sid">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">qq:</label>
                                    <input type="text" class="form-control" id="update_qq" name="update_qq" required
                                           maxlength="15">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">寝室号:</label>
                                    <input type="text" class="form-control" id="update_roomcode" name="update_roomcode"
                                           maxlength="10">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">邮箱:</label>
                                    <input type="email" class="form-control" id="update_email" name="update_email"
                                           maxlength="64">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">父亲电话:</label>
                                    <input type="text" class="form-control" id="update_fatherTel"
                                           name="update_fatherTel" maxlength="11">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">母亲电话:</label>
                                    <input type="text" class="form-control" id="update_motherTel"
                                           name="update_motherTel" maxlength="11">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">其他亲属姓名:</label>
                                    <input type="text" class="form-control" id="update_otherName"
                                           name="update_otherName" maxlength="10">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">其他亲属电话:</label>
                                    <input type="text" class="form-control" id="update_otherTel" name="update_otherTel"
                                           maxlength="11">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">专业:</label>
                                    <select class="form-control" id="update_mid" name="update_mid" required></select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">状态:</label>
                                    <select class="form-control" id="update_state" name="update_state" required>
                                        <option value="" selected>请选择</option>
                                        <option value="在读">在读</option>
                                        <option value="退学">退学</option>
                                        <option value="就业">就业</option>
                                        <option value="自主择业">自主择业</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="savebtn2" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<#--/更新弹框-->

<#--高级查询-->
<#--高级查询-->

<#--导入-->
<div class="modal fade" id="importStudent" tabindex="-1" role="dialog" aria-labelledby="importStudentLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <form id="importForm" action="${basePath}/student/import.shtml" method="post"
                  enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="addStudentLabel">导入学生</h4>
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