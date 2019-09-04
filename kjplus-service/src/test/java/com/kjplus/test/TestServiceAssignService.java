package com.kjplus.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.constant.Constant;
import com.kjplus.dto.IDNameDto;
import com.kjplus.dto.OrgServHeadDto;
import com.kjplus.dto.ServAsgnDto;
import com.kjplus.dto.ServAsgnPackageDto;
import com.kjplus.eto.SrvAsgnEto;
import com.kjplus.eto.ServHeadEto;
import com.kjplus.model.ServAsgnEbo;
import com.kjplus.service.IServiceAssignService;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

/**
 * 
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestServiceAssignService {

	@Autowired
	private IServiceAssignService srvAssignService;

	// 添加签约记录
	@Test
	public void testAddServAsgn() {
		try {
			SrvAsgnEto servAsgn = new SrvAsgnEto();
			servAsgn.setPrsnId(26);
			servAsgn.setPrsnCode("708125203349");
			List<String> listPackageCode = new ArrayList<String>();
			listPackageCode.add("87925998");
			listPackageCode.add("63639824");
			listPackageCode.add("60799358");
			servAsgn.setListPackageCode(listPackageCode);
			servAsgn.setDeptCode("67040130");
			servAsgn.setStafCode("62375326");
			servAsgn.setBeginDay(DateUtil.newTime());
			servAsgn.setStatus("Y");
			servAsgn.setMemo("12121");
			srvAssignService.addServAsgn(servAsgn);
			System.out.println("签约记录添加成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 修改签约记录
	@Test
	public void testUpdateServAsgn() {
		try {
			ServAsgnEbo servAsgn = new ServAsgnEbo();
			servAsgn.setId(16);
			servAsgn.setCode("257855276024");
			//servAsgn.setPrsnId(21);
			//servAsgn.setStafId(19);
			//servAsgn.setDeptId(14);
			servAsgn.setSrvId(2);
			//servAsgn.setReqTime(1212121212);
			servAsgn.setOperTime(121212111);
			servAsgn.setMemo("修改备注");
			srvAssignService.updateServAsgn(servAsgn);
			System.out.println("签约记录修改成功：");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	// 修改签约状态
	@Test
	public void testUpdateServAsgnStatus() {
		try {
			List<IDNameDto> idCodes = new ArrayList<IDNameDto>();
			IDNameDto id1 = new IDNameDto();
			//id1.setId(29);
			id1.setCode("388572734801");
			idCodes.add(id1);
			IDNameDto id2 = new IDNameDto();
			//id2.setId(30);
			id2.setCode("388572734823");
			idCodes.add(id2);
			String status = Constant.SRV_ASSIGN_STATUS_REFUSE;
			String memo = "申请通过,恭喜发财";
			srvAssignService.updateServAsgnStatus(idCodes, status, memo);
			System.out.println("签约记录修改成功：");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 签约服务列表
	@Test
	public void testListAsgnDto() {
		try {
			int staffId = 0;
			int personId = 0;
			String personName = "";
			String staffName = "";
			String isDefault =  "";
			int orgId = 1;
			String status = "";
			boolean isEffect = false;
			boolean isPackages = false;
			List<ServAsgnDto> srvAsgns = srvAssignService.listAsgnDto(personName, personId, staffName,staffId,
					isDefault, orgId,status,isEffect,isPackages, 0,10);
			for (ServAsgnDto srvAsgn : srvAsgns) {
				System.out.println(srvAsgn.getPersonId());
				for(ServAsgnPackageDto s :srvAsgn.getListPackage()){
					System.out.println("[package:]"+s.getId());
				}
			}
			int total = srvAssignService.getTotalServAss(personName, personId, staffName, staffId, isDefault, orgId, status,isEffect,isPackages);
			System.out.println("总数："+total);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 添加协议
	//@Test
	public void testAddServHead() {
		try {
			ServHeadEto ServHead = new ServHeadEto();
			ServHead.setOrgCode("CZ-VAR");
			ServHead.setSeq(2);
			ServHead.setTitle("协议标题3");
			ServHead.setContentBody("协议内容3");
			ServHead.setFlag("Y");
			srvAssignService.addServHead(ServHead);
			System.out.println("协议添加成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 修改协议状态
	//@Test
	public void testUpdateServHead() {
		try {
			srvAssignService.updateServHeadByidOrOrgId(1, 1, "N");
			System.out.println("修改协议状态成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 查询协议列表
	@Test
	public void testListServHead() {
		try {
			List<OrgServHeadDto> servHeads = srvAssignService.listServHead(0, null, null);
			for (OrgServHeadDto s : servHeads) {
				System.out.println(s);
			}
			System.out.println("获取协议内容，成功!");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 通过ID查询签约信息
	@Test
	public void testGetServAssByIdOrCode() {
		int id = 2;
		String code = "";
		try {
			ServAsgnEbo ser = srvAssignService.getServAssByIdOrCode(id, code);
			System.out.println(ser);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// 通过ID查询签约信息
	@Test
	public void testListServAssByPrsnId() {
		int prsnId = 22;
		try {
			List<ServAsgnEbo> ser = srvAssignService.listServAssByPrsnId(prsnId,false);
			for(ServAsgnEbo s : ser){
				System.out.println(s);
			}
			
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
}
