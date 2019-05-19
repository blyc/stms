<#macro top index>
<script baseUrl="${basePath}" src="${basePath}/js/user.login.js"></script>

<div class="navbar navbar-inverse navbar-fixed-top animated fadeInDown" style="z-index: 101;height: 41px;">

    <div class="container" style="padding-left: 0px; padding-right: 0px;">
        <div class="navbar-header">
            <button data-target=".navbar-collapse" data-toggle="collapse" type="button" class="navbar-toggle collapsed">
                <span class="sr-only">导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div role="navigation" class="navbar-collapse collapse">
            <a id="_logo" href="${basePath}" style="color:#fff; font-size: 24px;" class="navbar-brand hidden-sm"></a>
            <ul class="nav navbar-nav" id="topMenu">
                <li class="dropdown ${(index==1)?string('active','')}">
                    <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                       class="dropdown-toggle" href="${basePath}/user/index.shtml">
                        个人中心<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${basePath}/user/index.shtml">个人资料</a></li>
                        <li><a href="${basePath}/user/updateSelf.shtml">资料修改</a></li>
                        <li><a href="${basePath}/user/updatePswd.shtml">密码修改</a></li>
                        <li><a href="${basePath}/role/mypermission.shtml">我的权限</a></li>
                    </ul>
                </li>
                <@shiro.hasAnyRoles name='888888,100001'>
                    <li class="dropdown ${(index==2)?string('active','')}">
                        <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                           class="dropdown-toggle" href="${basePath}/member/list.shtml">
                            系统管理<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <@shiro.hasPermission name="/member/list.shtml">
                                <li><a href="${basePath}/member/list.shtml">用户列表</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/member/online.shtml">
                                <li><a href="${basePath}/member/online.shtml">在线用户</a></li>
                            </@shiro.hasPermission>
                        </ul>
                    </li>
                </@shiro.hasAnyRoles>
                <@shiro.hasAnyRoles name='888888,100001'>
                    <li class="dropdown ${(index==8)?string('active','')}">
                        <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                           class="dropdown-toggle" href="${basePath}/role/index.shtml">
                            权限管理<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <@shiro.hasPermission name="/role/index.shtml">
                                <li><a href="${basePath}/role/index.shtml">角色列表</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/role/allocation.shtml">
                                <li><a href="${basePath}/role/allocation.shtml">角色分配</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/permission/index.shtml">
                                <li><a href="${basePath}/permission/index.shtml">权限列表</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/permission/allocation.shtml">
                                <li><a href="${basePath}/permission/allocation.shtml">权限分配</a></li>
                            </@shiro.hasPermission>
                        </ul>
                    </li>
                </@shiro.hasAnyRoles>
                <@shiro.hasAnyRoles name='888888,100001,100003'>
                    <li class="dropdown ${(index==3)?string('active','')}">
                        <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                           class="dropdown-toggle" href="${basePath}/company/list.shtml">
                            组织架构<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <@shiro.hasPermission name="/company/list.shtml">
                                <li><a href="${basePath}/company/list.shtml">校区列表</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/department/list.shtml">
                                <li><a href="${basePath}/department/list.shtml">部门列表</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/position/list.shtml">
                                <li><a href="${basePath}/position/list.shtml">职位列表</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/employee/list.shtml">
                                <li><a href="${basePath}/employee/list.shtml">员工列表</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/major/list.shtml">
                                <li><a href="${basePath}/major/list.shtml">专业列表</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/rank/list.shtml">
                                <li><a href="${basePath}/rank/list.shtml">薪资体系列表</a></li>
                            </@shiro.hasPermission>
                        </ul>
                    </li>
                </@shiro.hasAnyRoles>
                <@shiro.hasAnyRoles name='888888,100001,100002,100003'>
                    <li class="dropdown ${(index==4)?string('active','')}">
                        <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                           class="dropdown-toggle" href="${basePath}/student/list.shtml">
                            学生管理<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <@shiro.hasPermission name="/student/list.shtml">
                                <li><a href="${basePath}/student/list.shtml">学生档案</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/divide/list.shtml">
                                <li><a href="${basePath}/divide/list.shtml">分班管理</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/payment/infolist.shtml">
                                <li><a href="${basePath}/payment/infolist.shtml">升学历史</a></li>
                            </@shiro.hasPermission>
                        </ul>
                    </li>
                </@shiro.hasAnyRoles>
                <@shiro.hasAnyRoles name='888888,100001,100002,100003'>
                    <li class="dropdown ${(index==5)?string('active','')}">
                        <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                           class="dropdown-toggle" href="${basePath}/attendance/list.shtml">
                            教学管理(每天)<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <@shiro.hasPermission name="/attendance/list.shtml">
                                <li><a href="${basePath}/attendance/list.shtml">出勤统计</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/check/list.shtml">
                                <li><a href="${basePath}/check/list.shtml">教务查课</a></li>
                            </@shiro.hasPermission>
                        </ul>
                    </li>
                </@shiro.hasAnyRoles>
                <@shiro.hasAnyRoles name='888888,100001,100002,100003'>
                    <li class="dropdown ${(index==6)?string('active','')}">
                        <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                           class="dropdown-toggle" href="${basePath}/task/list.shtml">
                            教学管理(每月)<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <@shiro.hasPermission name="/project/list.shtml">
                                <li><a href="${basePath}/project/list.shtml">项目统计</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/exam/list.shtml">
                                <li><a href="${basePath}/exam/list.shtml">考试统计</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/satisfaction/list.shtml">
                                <li><a href="${basePath}/satisfaction/list.shtml">讲师满意度</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/uclasshour/list.shtml">
                                <li><a href="${basePath}/uclasshour/list.shtml">月课时统计</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/quarter/companyquarteslist.shtml">
                                <li><a href="${basePath}/quarter/companyquarteslist.shtml">讲师校区评估</a></li>
                            </@shiro.hasPermission>
                        </ul>
                    </li>
                </@shiro.hasAnyRoles>
                <@shiro.hasAnyRoles name='888888,100001,100003,100004'>
                    <li class="dropdown ${(index==11)?string('active','')}">
                        <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                           class="dropdown-toggle" href="${basePath}/payment/s1list.shtml">
                            总部督查(每月)<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <@shiro.hasPermission name="/quarter/list.shtml">
                                <li><a href="${basePath}/quarter/list.shtml">考核标准</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/quarter/examlist.shtml">
                                <li><a href="${basePath}/quarter/examlist.shtml">督查数据(考试)</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/quarter/projectlist.shtml">
                                <li><a href="${basePath}/quarter/projectlist.shtml">督查数据(项目)</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/quarter/companylist.shtml">
                                <li><a href="${basePath}/quarter/companylist.shtml">督查数据(校区)</a></li>
                            </@shiro.hasPermission>
                        </ul>
                    </li>
                </@shiro.hasAnyRoles>
                <@shiro.hasAnyRoles name='888888,100001,100002,100003,100004'>
                    <li class="dropdown ${(index==9)?string('active','')}">
                        <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                           class="dropdown-toggle" href="${basePath}/payment/s1list.shtml">
                            数据汇总<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <@shiro.hasPermission name="/summary/classlist.shtml">
                                <li><a href="${basePath}/summary/classlist.shtml">数据汇总(班级)</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/summary/teacherlist.shtml">
                                <li><a href="${basePath}/summary/teacherlist.shtml">数据汇总(讲师)</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/summary/majorlist.shtml">
                                <li><a href="${basePath}/summary/majorlist.shtml">数据汇总(专业)</a></li>
                            </@shiro.hasPermission>
                            <li><a href="${basePath}/summary/studentlist.shtml">数据汇总(学生)</a></li>
                            <@shiro.hasPermission name="/summary/resultlist.shtml">
                                <li><a href="${basePath}/summary/resultlist.shtml">月教学考核结果</a></li>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/summary/subsidylist.shtml">
                                <li><a href="${basePath}/summary/subsidylist.shtml">月讲师授课津贴汇总</a></li>
                            </@shiro.hasPermission>
                        </ul>
                    </li>
                </@shiro.hasAnyRoles>
                <@shiro.hasAnyRoles name='777777'>
                    <li class="dropdown ${(index==10)?string('active','')}">
                        <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                           class="dropdown-toggle" href="${basePath}/payment/s1list.shtml">
                            年会相关功能<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${basePath}/meeting/index.shtml">抽奖功能</a></li>
                            <li><a href="${basePath}/meeting/list.shtml">抽奖数据录入</a></li>
                            <li><a href="${basePath}/meeting/show.shtml">弹幕首页</a></li>
                        </ul>
                    </li>
                </@shiro.hasAnyRoles>
            <#--------------------------------------------------------------------------------------------->
                <@shiro.hasAnyRoles name='888888,100001,100002,100003,100004'>
                    <li class="dropdown ${(index==12)?string('active','')}">
                        <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                           class="dropdown-toggle" href="${basePath}/payment/s1list.shtml">
                            作业管理<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <#--<@shiro.hasPermission name="/practice/list.shtml">-->
                                <#--<li><a href="${basePath}/practice/list.shtml">题库管理</a></li>-->
                            <#--</@shiro.hasPermission>-->
<#--                            <@shiro.hasPermission name="/practice/releaseList.shtml">-->
                                <li><a href="${basePath}/practice/releaseList.shtml">发布管理</a></li>
<#--                            </@shiro.hasPermission>-->
                        </ul>
                    </li>
                </@shiro.hasAnyRoles>
            <#--------------------------------------------------------------------------------------------->
            </ul>

            <ul class="nav navbar-nav  pull-right">
                <li class="dropdown ${(index==10)?string('active','')}" style="color:#fff;">
                <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown"
                    <@shiro.user>
                   onclick="location.href='${basePath}/user/index.shtml'" href="${basePath}/user/index.shtml"
                   class="dropdown-toggle qqlogin">
                    ${token.nickname?default('阿西吧')}<span class="caret"></span></a>
                        <ul class="dropdown-menu" userid="${token.uid}">
                            <li><a href="${basePath}/user/index.shtml">个人资料</a></li>
                            <li><a href="${basePath}/role/mypermission.shtml">我的权限</a></li>
                            <li><a href="javascript:void(0);" onclick="logout();">退出登录</a></li>
                        </ul>
                    </@shiro.user>
                    <@shiro.guest>
                        href="javascript:void(0);" onclick="location.href='${basePath}/u/login.shtml'"
                        class="dropdown-toggle qqlogin" >
                        <img src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_1.png">&nbsp;登录</a>
                    </@shiro.guest>
                </li>
            </ul>
            <style>#topMenu > li > a {
                padding: 10px 13px
            }</style>
        </div>
    </div>
</div>
</#macro>
