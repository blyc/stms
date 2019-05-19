package com.hp.employee.service.impl;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.dao.UEmployeeMapper;
import com.hp.common.dao.UEmployeeRankMapper;
import com.hp.common.dao.UUserMapper;
import com.hp.common.dao.UUserRoleMapper;
import com.hp.common.model.UEmployee;
import com.hp.common.model.UEmployeeRank;
import com.hp.common.model.UUser;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.LoggerUtils;
import com.hp.common.utils.StringUtils;
import com.hp.common.utils.vcode.Randoms;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.core.statics.Constant;
import com.hp.employee.service.EmployeeService;
import com.hp.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @Author: boy
 * @Date: 2018/02/10
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class EmployeeSeviceImpl extends BaseMybatisDao<UEmployeeMapper> implements EmployeeService {
    @Autowired
    private UEmployeeMapper uEmployeeMapper;
    @Autowired
    private UUserMapper uUserMapper;
    @Autowired
    private UUserRoleMapper uUserRoleMapper;
    @Autowired
    private UEmployeeRankMapper uEmployeeRankMapper;

    @Override
    public Pagination<UEmployee> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    public UEmployee findByIdCard(String idCard) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idcard", idCard);
        List<UEmployee> uEmployeeList = uEmployeeMapper.findAllEmployee(map);
        if (uEmployeeList == null || uEmployeeList.size() == 0) {
            return null;
        }
        return uEmployeeList.get(0);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(UEmployee record) {
        UEmployee uEmployee = this.findByPk(record.getEid());
        if (uEmployee != null) {
            if (record.getLid() == null) {
                if (uEmployee.getLid() != null) {
                    UEmployeeRank u1 = uEmployeeRankMapper.selectByPrimaryKey(uEmployee.getUerid());
                    u1.setEndTime(DateUtil.getCurrentDateTime());
                    uEmployeeRankMapper.updateByPrimaryKey(u1);
                    uEmployee.setUerid(null);
                }
            } else {
                if (uEmployee.getLid() != null) {
                    UEmployeeRank u1 = uEmployeeRankMapper.selectByPrimaryKey(uEmployee.getUerid());
                    if (DateUtil.dateToString(u1.getStartTime(), DateUtil.ISO_EXPANDED_DATE_FORMAT_1).
                            equals(DateUtil.getCurrentDateString(DateUtil.ISO_EXPANDED_DATE_FORMAT_1))
                            && (!uEmployee.getLid().equals(record.getLid()))) {
                        u1.setLid(record.getLid());
                        uEmployeeRankMapper.updateByPrimaryKey(u1);
                    } else {
                        u1.setEndTime(DateUtil.getCurrentDateTime());
                        uEmployeeRankMapper.updateByPrimaryKey(u1);

                        UEmployeeRank u2 = new UEmployeeRank();
                        u2.setLid(record.getLid());
                        u2.setEid(record.getEid());
                        u2.setStartTime(DateUtil.getCurrentDateTime());
                        u2.setCreateTime(DateUtil.getCurrentDateTime());
                        uEmployeeRankMapper.insertSelective(u2);
                        uEmployee.setUerid(u2.getId());
                    }
                } else {
                    UEmployeeRank u2 = new UEmployeeRank();
                    u2.setLid(record.getLid());
                    u2.setEid(record.getEid());
                    u2.setStartTime(DateUtil.getCurrentDateTime());
                    u2.setCreateTime(DateUtil.getCurrentDateTime());
                    uEmployeeRankMapper.insertSelective(u2);
                    uEmployee.setUerid(u2.getId());
                }
            }
            if (record.getEmail() != null) {
                uEmployee.setEmail(record.getEmail());
            }
            if (record.getTel() != null) {
                uEmployee.setTel(record.getTel());
            }
            uEmployee.setLid(record.getLid());
            if (record.getState() != null) {
                uEmployee.setState(record.getState());
            }
            uEmployee.setUpdatetime(DateUtil.getCurrentDateTime());
            uEmployeeMapper.updateByPrimaryKey(uEmployee);

            //更新登录用户信息
            UUser uUser =  uUserMapper.selectByPrimaryKey(uEmployee.getEid());

            if (record.getUserflg() != null && record.getUserflg() == 1) {
                if(uUser==null){
                    int rand = Randoms.num(10000);
                    DecimalFormat df = new DecimalFormat("0000");
                    String last4 = df.format(rand);
                    String uemail = uEmployee.getPinyin() + last4;

                    uUser = uUserMapper.findUserByEmail(uemail);
                    if(uUser !=null){
                        return -1;
                    }
                    uUser = new UUser();
                    uUser.setEmail(uemail);
                    uUser.setCreateTime(uEmployee.getCreatetime());
                    uUser.setNickname(uEmployee.getName());
                    uUser.setCid(uEmployee.getCid());
                    uUser.setStatus(UUser._1);
                    uUser.setPswd(last4);
                    uUser = UserManager.md5Pswd(uUser);
                    uUser.setUid(uEmployee.getEid());
                    uUserMapper.insert(uUser);
                }
                else{
                    return 1;
                }
            }
            else{
                if(uUser==null){
                    return 1;
                }
                else{
                    //删除用户
                    uUserMapper.deleteByPrimaryKey(uUser.getUid());
                    //删除用户角色
                    uUserRoleMapper.deleteByUserId(uUser.getUid());
                }
            }
        }
        return 1;
    }

    @Override
    public List<UEmployee> findByTel(String tel) {
        if (tel == null || tel.equals("")) {
            return null;
        }

        Map map = new HashMap();
        map.put("tel", tel);
        return uEmployeeMapper.findAllEmployee(map);
    }

    @Override
    @Transactional(readOnly = false)
    public UEmployee insert(UEmployee record) {
        int rand = Randoms.num(10000);
        DecimalFormat df = new DecimalFormat("0000");
        String last4 = df.format(rand);
        String uemail = record.getPinyin() + last4;

        UUser uUser = uUserMapper.findUserByEmail(uemail);

        if (null != uUser) {
            return null;
        }

        UEmployeeRank uEmployeeRank = null;
        if (record.getLid() != null) {
            uEmployeeRank = new UEmployeeRank();
            uEmployeeRank.setLid(record.getLid());
            uEmployeeRank.setStartTime(DateUtil.getCurrentDateTime());
            uEmployeeRank.setCreateTime(DateUtil.getCurrentDateTime());
            uEmployeeRankMapper.insertSelective(uEmployeeRank);
            record.setUerid(uEmployeeRank.getId());
        }


        Date date = new Date();
        record.setCreatetime(date);
        record.setUpdatetime(date);
        record.setState(Constant.EMPLOYEE_STATE_JOB);

        uEmployeeMapper.insert(record);

        if (uEmployeeRank != null) {
            uEmployeeRank.setEid(record.getEid());
            uEmployeeRankMapper.updateByPrimaryKeySelective(uEmployeeRank);
        }

        if (record.getUserflg() != null && record.getUserflg() == 1) {
            uUser = new UUser();
            uUser.setEmail(uemail);
            uUser.setCreateTime(record.getCreatetime());
            uUser.setNickname(record.getName());
            uUser.setCid(record.getCid());
            uUser.setStatus(UUser._1);
            uUser.setPswd(last4);
            uUser = UserManager.md5Pswd(uUser);
            uUser.setUid(record.getEid());

            uUserMapper.insert(uUser);
        }

        return record;
    }


    @Override
    public List<KeyAndValue> findAllEmployeeForSelect() {
        //校区id
        Long cid = TokenManager.getToken().getCid();
        Map map = new HashMap();
        map.put("cid", cid);
        List<UEmployee> uEmployees = uEmployeeMapper.findAllEmployee(map);
        if (uEmployees == null || uEmployees.size() <= 0) {
            return null;
        }
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();

        for (UEmployee u : uEmployees) {
            KeyAndValue keyAndValue = new KeyAndValue(u.getEid(), u.getName());
            list.add(keyAndValue);
        }
        return list;
    }

    @Override
    public UEmployee findByPk(Long id) {
        UEmployee uEmployee  = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("eid", id);
        List<UEmployee> uEmployeeList = uEmployeeMapper.findAllEmployee(map);
        if (uEmployeeList != null && uEmployeeList.size() > 0) {
            uEmployee = uEmployeeList.get(0);
            UUser uUser= uUserMapper.selectByPrimaryKey(uEmployee.getEid());
            if(uUser == null){
                uEmployee.setUserflg(0L);
            }
            else{
                uEmployee.setUserflg(1L);
            }
            return uEmployee;
        }

        return null;
    }

    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(Long id) {
        UEmployee uEmployee = uEmployeeMapper.selectByPrimaryKey(id);
        if (uEmployee == null) {
            return -1;
        }
        uEmployee.setState("删除");
        //删除用户
        uUserMapper.deleteByPrimaryKey(id);
        //删除用户角色
        uUserRoleMapper.deleteByUserId(id);
        return uEmployeeMapper.updateByPrimaryKey(uEmployee);
    }


    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> deleteEmployeeById(String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            int count = 0;
            String[] idArray = new String[]{};
            if (StringUtils.contains(ids, ",")) {
                idArray = ids.split(",");
            } else {
                idArray = new String[]{ids};
            }

            for (String id : idArray) {
                count += this.deleteByPrimaryKey(new Long(id));
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除员工出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }
}
