package com.kjplus.test;

import com.kjplus.constant.Constant;
import com.kjplus.dto.DocumentOplogDto;
import com.kjplus.eto.DocumentOplogEto;
import com.kjplus.model.DocumentOplogEbo;
import com.kjplus.service.IDmtOplogService;
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
public class TestDmtOplogService {

    @Autowired
    private IDmtOplogService dmtOplogService;

    //测试添加档案操作记录
    @Test
    public void testAddDmtOplog() {

        try {
            DocumentOplogEto dmtOplog = new DocumentOplogEto();
            dmtOplog.setOpTypeCode("9");//类型其他
//            dmtOplog.setPrsnCode("12345");
            dmtOplog.setOrgCode("CZ-VAR");
            dmtOplog.setUpPrsn(18);
            dmtOplog.setMemo("这是一个测试");
            DocumentOplogEbo addDmtOplog = dmtOplogService.addDmtOplog(dmtOplog);
            System.out.println("[addDmtOplog]:" + addDmtOplog);
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    //测试列表
    @Test
    public void testListDmtOplog() {

        try {
            int prsnId = 2;
            int opType = 70;
            int orgId = 1;
            int upPrsn = 18;
            int upSeq = 1;
            String flag = Constant.FLAG_YES;
            List<DocumentOplogDto> dmtOplogs = dmtOplogService.listDmtOplog(prsnId, opType, orgId, upPrsn, upSeq, flag);
            for (DocumentOplogDto dmtOplog : dmtOplogs) {
                System.out.println("[dmtOplog]:" + dmtOplog);
            }
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    //某人某档案最近一次修改记录
    @Test
    public void testGetDmtOplog() {

        int prsnId = 2;
        int opType = 70;
        int orgId = 1;
        try {
            DocumentOplogEbo dmtOplog=dmtOplogService.getDmtOplog(prsnId, opType, orgId, Constant.FLAG_YES);
            System.out.println(dmtOplog);
        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    //测试修改档案记录状态
    @Test
    public void testUpdateDmtOplog() {

        try {
            String flag = Constant.FLAG_NO;
            dmtOplogService.updateDmtOplog(1, flag);
            System.out.println("[updateDmtOplog]状态修改成功");
        } catch (DataException e) {
            e.printStackTrace();
        }
    }
}
