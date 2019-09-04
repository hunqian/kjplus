package com.kjplus.test;


import com.kjplus.constant.Constant;
import com.kjplus.dto.SrvProjectCfgInfoDto;
import com.kjplus.eto.SrvProjectCfgEto;
import com.kjplus.eto.SrvProjectEto;
import com.kjplus.model.SrvProjectCfgEbo;
import com.kjplus.model.SrvProjectEbo;
import com.kjplus.service.ISrvProjectService;
import com.ybk.exception.DataException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestSrvProjectService {

    @Autowired
    private ISrvProjectService srvProjectService;

    //添加项目
    @Test
    public void testAddSrvProject() {
        SrvProjectEto srvPrj = null;
        try {
            srvPrj = new SrvProjectEto();
            srvPrj.setTypeCode("AC_001");
            srvPrj.setName("随访");
            SrvProjectEbo addSrvPrj = srvProjectService.addSrvProject(srvPrj);
            System.out.println("[addSrvPrj]:" + addSrvPrj);
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    //添加项目配置
    @Test
    public void testAddSrvProjectCfg() {
        SrvProjectCfgEto srvPrjCfg = null;
        try {
            srvPrjCfg = new SrvProjectCfgEto();
            srvPrjCfg.setOrgCode("CZ-VAR");
            srvPrjCfg.setTagId(1);
            srvPrjCfg.setPrjCode("59186602");
            SrvProjectCfgEbo addSrvPrjCfg = srvProjectService.addSrvProjectCfg(srvPrjCfg);
            System.out.println("[addSrvPrjCfg]:" + addSrvPrjCfg);
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    //列表
    @Test
    public void testListSrvProjectCfgInfo() {

        try {
            int prjId = 0;
            int orgId = 0;
            int tagId = 0;
            List<SrvProjectCfgInfoDto> SrvPrjInfos = srvProjectService.listSrvProjectCfgInfo(prjId, orgId, tagId, Constant.FLAG_YES);
            for (SrvProjectCfgInfoDto SrvPrjInfo : SrvPrjInfos)
                System.out.println("[SrvPrjInfo]:" + SrvPrjInfo);
        } catch (DataException e) {
            e.printStackTrace();
        }
    }


    //修改服务项目表
    @Test
    public void testUpdateSrvProjectByFlag() {

        try {
            int id = 1;
            String code = "59186602";
            String flag = Constant.FLAG_NO;
            srvProjectService.updateSrvProjectByFlag(id, code, flag);
            System.out.println("[updateSrvProjectByFlag]:修改成功");
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    //修改配置表
    @Test
    public void testUpdateSrvProjectCfg() {
        try {
            int id = 1;
            int prjId = 1;
            int orgId = 1;
            int tagId = 1;
            String isNorm = Constant.FLAG_NO;
            srvProjectService.updateSrvProjectCfg(id, prjId, tagId, orgId, isNorm);
            System.out.println("[updateSrvProjectCfg]:修改成功");
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

}
