package com.wky.spring_boot.mybaits.service.admin.impl;

import com.wky.spring_boot.mybaits.mapper.admindb.AdminMapper;
import com.wky.spring_boot.mybaits.service.admin.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String create(String adminName, String adminPassword) {
        try {
            adminMapper.create(adminName, adminPassword);
            return "通过";
        }catch (Exception e){
            e.printStackTrace();
            return "未通过";
        }
    }
}
