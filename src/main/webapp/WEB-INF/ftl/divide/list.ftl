<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>分班管理</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script baseUrl="${basePath}" src="${basePath}/js/hp/divide.list.js"></script>
    <script baseUrl="${basePath}" src="${basePath}/js/hp/payment.list.js"></script>
    <style>
        .table th {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 4/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left4 2/>
        <div class="col-md-10">
            <h2>分班管理</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/班级/讲师/班主任" maxlength="10">
                    </div>
                    <span class=""> <#--pull-right -->

                        <button type="submit" class="btn btn-primary">查询</button>

                    <@shiro.hasPermission name="/divide.shtml">
                        <a id="addBtn" class="btn btn-success">添加</a>
                    </@shiro.hasPermission>
                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>校区</th>
                        <th>专业</th>
                        <th>年级</th>
                        <th>班级</th>
                        <th>阶段</th>
                        <th>班级人数</th>
                        <th>讲师</th>
                        <th>班主任</th>
                        <th>班级详情</th>
                        <th>操作</th>
                        <th>升学</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.companyname?default('')}</td>
                            <td>${it.majorname?default('')}</td>
                            <td>${it.grade?default('')}级</td>
                            <td>${it.classname?default('')}</td>
                            <td>${it.stage?default('')}阶段</td>
                            <td>${it.num?default('0')}</td>
                            <td>${it.jsname?default('')}</td>
                            <td>${it.bzrname?default('')}</td>
                            <td>
                                <a href="javascript:_showStudentDlg(${it.ccid},'${it.classname}');">班级学生信息</a>
                            </td>
                            <td>
                                <@shiro.hasPermission name="/divide/shiftoutStudent.shtml">
                                    <a href="javascript:_openoutStudentDlg(${it.ccid},'${it.classname}');">移出学生</a>
                                </@shiro.hasPermission>
                                <@shiro.hasPermission name="/divide/shiftinStudent.shtml">
                                    <a href="javascript:_openinStudentDlg(${it.ccid},${it.mid});">移入学生</a>
                                </@shiro.hasPermission>
                                <br>
                                <@shiro.hasPermission name="/divide/addTeacherClass.shtml">
                                    <a href="javascript:_openTeacherDlg(${it.ccid});">选择老师</a>
                                </@shiro.hasPermission>
                                <@shiro.hasPermission name="/divide/delUClass.shtml">
                                    <a onclick="_deleteUClass(${it.ccid});" href="#">删除班级</a>
                                </@shiro.hasPermission>
                            </td>
                            <td>
                                <@shiro.hasPermission name="/payment.shtml">
                                    <#if it.stage != "S3" >
                                        <a href="javascript:_open2('${it.ccid}')">登记升学情况</a>
                                    </#if>
                                </@shiro.hasPermission>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="11">没有找到班级</td>
                    </tr>
                </#if>
                </table>
                <input type="hidden" id="hccid">
                <input type="hidden" id="hcsid">
                <input type="hidden" id="mid">
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
<div class="modal fade" id="addUClass" tabindex="-1" role="dialog" aria-labelledby="addUClassLabel">
    <div class="modal-dialog" role="document" style="width: 1024px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addUClassLabel">添加班级信息</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxRoleForm">
                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="form-group">
                                        <label for="recipient-name" class="control-label">年级:</label>
                                        <select name="grade" id="grade" class="form-control">
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>现有班级数量</th>
                                                <th>新增班级数量</th>
                                                <th>预计班级总数量</th>
                                            </tr>
                                        </thead>
                                        <tbody id="majorList">
                                           <#-- <tr>
                                                <td>高软</td>
                                                <td><span id="t0"></span></td>
                                                <td><span id="t00"></span></td>
                                                <td><span id="t000"></span></td>
                                            </tr>
                                            <tr>
                                                <td>3G4G</td>
                                                <td><span id="t1"></span></td>
                                                <td><span id="t11"></span></td>
                                                <td><span id="t111"></span></td>
                                            </tr>
                                            <tr>
                                                <td>UI</td>
                                                <td><span id="t2"></span></td>
                                                <td><span id="t22"></span></td>
                                                <td><span id="t222"></span></td>
                                            </tr>
                                            <tr>
                                                <td>网销</td>
                                                <td><span id="t3"></span></td>
                                                <td><span id="t33"></span></td>
                                                <td><span id="t333"></span></td>
                                            </tr>
                                            <tr>
                                                <td>WEB前端</td>
                                                <td><span id="t4"></span></td>
                                                <td><span id="t44"></span></td>
                                                <td><span id="t444"></span></td>
                                            </tr>-->
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-md-7" id="major_classNum">
                               <#-- <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-5">
                                        <div class="form-group">
                                            <div class="form-group">
                                                <label class="control-label">高软:</label>
                                                <input type="text" maxlength="2" class="form-control" name="grnum"
                                                       id="grnum" placeholder="请输入班级数量"
                                                       onblur="javascript:changnum(0)"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="form-group">
                                            <label for="recipient-name" class="control-label">3G4G:</label>
                                            <input type="text" maxlength="2" class="form-control" name="ydnum"
                                                   id="ydnum" placeholder="请输入班级数量" onblur="javascript:changnum(1)"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-5">
                                        <div class="form-group">
                                            <label for="recipient-name" class="control-label">UI:</label>
                                            <input type="text" maxlength="2" class="form-control" name="uinum"
                                                   id="uinum" placeholder="请输入班级数量" onblur="javascript:changnum(2)"/>
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="form-group">
                                            <label for="recipient-name" class="control-label">网销:</label>
                                            <input type="text" maxlength="2" class="form-control" name="wxnum"
                                                   id="wxnum" placeholder="请输入班级数量" onblur="javascript:changnum(3)"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <div class="col-md-5">
                                        <div class="form-group">
                                            <label for="recipient-name" class="control-label">WEB前端:</label>
                                            <input type="text" maxlength="2" class="form-control" name="webnum"
                                                   id="webnum" placeholder="请输入班级数量" min="0"
                                                   onblur="javascript:changnum(4)"/>
                                        </div>
                                    </div>
                                </div>-->
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<#--/弹框-->
<#--弹框 选择老师-->
<div class="modal fade" id="addTeacherClass" tabindex="-1" role="dialog" aria-labelledby="addTeacherClassLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addTeacherClassLabel">选择老师</h4>
            </div>
            <div class="modal-body">
                <form id="boxRoleForm">
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">讲师:</label>
                        <select class="form-control techerClass" id="js">
                            <option></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">导师:</label>
                        <select class="form-control techerClass" id="ds">
                            <option></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">班主任:</label>
                        <select class="form-control techerClass" id="bzr">
                            <option></option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn1" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="studentClass" tabindex="-1" role="dialog" aria-labelledby="studentClassLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="studentClassLabel"><span id="classname"></span>的学生</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="classStudnetname">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<#--/弹框  选择学生-->
<div class="modal fade" id="addStudentClass" tabindex="-1" role="dialog" aria-labelledby="addStudentClassLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addStudentClassLabel"><span id="classname"></span>已分班学生</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="haveClassStudnetname">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <input type="text" id="select_LikeName" placeholder="姓名" maxlength="5">
                <input type="text" id="startout_id" placeholder="开始编号" maxlength="5">
                <input type="text" id="endout_id" placeholder="结束编号" maxlength="5">
                <input type="checkbox" id="shiftoutckb_add" value="1">选中
                <input type="checkbox" id="shiftoutckb" value="1">全选
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <@shiro.hasPermission name="/divide/shiftoutStudent.shtml">
                <button type="button" id="shiftoutBtn" class="btn btn-primary">移出</button>
            </@shiro.hasPermission>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="withoutStudentClass" tabindex="-1" role="dialog" aria-labelledby="withoutStudentClassLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="withoutStudentClassLabel">待分班学生</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="withoutClassStudnetname">

                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <input type="text" id="select_LikeName2" placeholder="姓名" maxlength="5">
                <input type="text" id="startin_id" placeholder="开始编号" maxlength="5">
                <input type="text" id="endin_id" placeholder="结束编号" maxlength="5">
                <input type="checkbox" id="shiftinckb_add">选中
                <input type="checkbox" id="shiftinckb">全选
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <@shiro.hasPermission name="/divide/shiftinStudent.shtml">
                <button type="button" id="shiftinBtn" class="btn btn-primary">移入</button>
            </@shiro.hasPermission>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="addPayment" tabindex="-1" role="dialog" aria-labelledby="addPaymentLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addPaymentLabel">登记升学信息</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="boxPaymentForm">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="hideccid">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="savepaybtn" class="btn btn-primary">保存
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>