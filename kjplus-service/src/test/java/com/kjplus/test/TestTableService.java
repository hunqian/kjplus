package com.kjplus.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ybk.basic.util.DateUtil;

import com.kjplus.constant.ConstantTableCfg;
import com.kjplus.dao.ITableDao;
import com.kjplus.dto.DataLineDto;
import com.kjplus.dto.SysRefVlDto;
import com.kjplus.dto.TableCfgHeadDto;
import com.kjplus.dto.TableCfgLineDto;
import com.kjplus.dto.TableDataLineDto;
import com.kjplus.eto.TableCfgHeadEto;
import com.kjplus.eto.TableCfgLineEto;
import com.kjplus.eto.TableDataHeadEto;
import com.kjplus.eto.TableDataLineEto;
import com.kjplus.model.SysRefTypeEbo;
import com.kjplus.model.TableCfgHeadEbo;
import com.kjplus.model.TableCfgLineEbo;
import com.kjplus.model.TableDataHeadEbo;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ITableService;
import com.ybk.exception.DataException;

/**
 * 
 * @author niuzhiwei
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestTableService {

	@Autowired
	private ITableService tableService;
	@Autowired
	private ITableDao tableDao;
	@Autowired
	private ISysBaseService baseService;

	// 获取表头信息
	@Test
	public void testGetCfgHeadById() {
		try {
			int cfgHeadId = 25;
			String cfgHeadcode = null;
			TableCfgHeadEbo tHead = tableDao.getCfgHeadById(cfgHeadId, cfgHeadcode);
			System.out.println(tHead.getName() + "," + tHead.getTypeId());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获取表列信息
	// @Test
	public void testGetCfgLineById() {
		try {
			TableCfgLineEbo cfgLine = tableDao.getCfgLineById(1155, null, 25, -1, -2, -2);
			System.out.println(cfgLine);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListCfgLineById() {
		try {
			String code = "";
			List<TableCfgLineDto> cfgs = tableService.listTableLine(code, true);
			for (TableCfgLineDto tableCfgLineDto : cfgs) {
				System.out.println(tableCfgLineDto);
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testAddTableCfg() {
		SysRefTypeEbo refType = null;
		try {

			String tbcode = "R1001";
			TableCfgHeadEto cfgHead = new TableCfgHeadEto();
			cfgHead.setCode(tbcode);
			cfgHead.setName("个人信息维护表");
			// 暂时为类型1
			cfgHead.setTypeId(83);

			TableCfgLineEto cfgLine = null;
			List<TableCfgLineEto> cfgLines = new ArrayList<TableCfgLineEto>();

			// 性别
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-1.1");
			// cfgLine.setLinePos("1.1");
			cfgLine.setLingSeq(1);
			cfgLine.setTitle("性别");
			refType = baseService.getRefTypeByCode("SEX_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 出生日期
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-1.2");
			// cfgLine.setLinePos("1.2");
			cfgLine.setLingSeq(1);
			cfgLine.setTitle("出生日期");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 身份证号
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-2.1");
			// cfgLine.setLinePos("2.1");
			cfgLine.setLingSeq(2);
			cfgLine.setTitle("身份证号");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 工作单位
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-2.2");
			// cfgLine.setLinePos("2.2");
			cfgLine.setLingSeq(2);
			cfgLine.setTitle("工作单位");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 本人电话
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-3.1");
			// cfgLine.setLinePos("3.1");
			cfgLine.setLingSeq(3);
			cfgLine.setTitle("本人电话");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 联系人姓名
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-3.2");
			// cfgLine.setLinePos("3.2");
			cfgLine.setLingSeq(3);
			cfgLine.setTitle("/联系人姓名");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 联系人电话
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-3.3");
			// cfgLine.setLinePos("3.3");
			cfgLine.setLingSeq(3);
			cfgLine.setTitle("联系人电话");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 常住类型
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-4.1");
			// cfgLine.setLinePos("4.1");
			cfgLine.setLingSeq(4);
			cfgLine.setTitle("常住类型");
			refType = baseService.getRefTypeByCode("RESIDE_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 民族
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-4.2");
			// cfgLine.setLinePos("4.2");
			cfgLine.setLingSeq(4);
			cfgLine.setTitle("民族");
			refType = baseService.getRefTypeByCode("NATION_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 血型
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-5");
			// cfgLine.setLinePos("5");
			cfgLine.setLingSeq(5);
			cfgLine.setTitle("血型");
			refType = baseService.getRefTypeByCode("BLOOD_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 文化程度
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-6");
			// cfgLine.setLinePos("6");
			cfgLine.setLingSeq(6);
			cfgLine.setTitle("文化程度");
			refType = baseService.getRefTypeByCode("EDU_RECORD");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 职业
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-7");
			// cfgLine.setLinePos("7");
			cfgLine.setLingSeq(7);
			cfgLine.setTitle("职业");
			refType = baseService.getRefTypeByCode("CARRER_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 婚姻状况
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-8");
			// cfgLine.setLinePos("8");
			cfgLine.setLingSeq(8);
			cfgLine.setTitle("婚姻状况");
			refType = baseService.getRefTypeByCode("MARRIAGE_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 医疗费用支付方式
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-9");
			// cfgLine.setLinePos("9");
			cfgLine.setLingSeq(9);
			cfgLine.setTitle("医疗费用支付方式");
			refType = baseService.getRefTypeByCode("FEE_PAY_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 药物过敏史
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-10");
			// cfgLine.setLinePos("10");
			cfgLine.setLingSeq(10);
			cfgLine.setTitle("药物过敏史");
			refType = baseService.getRefTypeByCode("DRUGALLERGY_HIS_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 暴露史
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-11");
			// cfgLine.setLinePos("11");
			cfgLine.setLingSeq(11);
			cfgLine.setTitle("暴露史");
			refType = baseService.getRefTypeByCode("EXPOSE_HIS_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 既往史
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-12");
			// cfgLine.setLinePos("12");
			cfgLine.setLingSeq(12);
			cfgLine.setTitle("既往史");
			refType = baseService.getRefTypeByCode("PAST_HIS_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 疾病
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-12.1");
			// cfgLine.setLinePos("12.1");
			cfgLine.setLingSeq(12);
			cfgLine.setPlinePos("12");
			cfgLine.setTitle("疾病");
			refType = baseService.getRefTypeByCode("DISEASE_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 手术
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-12.2");
			// cfgLine.setLinePos("12.2");
			cfgLine.setLingSeq(12);
			cfgLine.setPlinePos("12");
			cfgLine.setTitle("手术");
			refType = baseService.getRefTypeByCode("YESNO_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 外伤
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-12.3");
			// cfgLine.setLinePos("12.3");
			cfgLine.setLingSeq(12);
			cfgLine.setPlinePos("12");
			cfgLine.setTitle("外伤");
			refType = baseService.getRefTypeByCode("YESNO_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 输血
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-12.4");
			// cfgLine.setLinePos("12.4");
			cfgLine.setLingSeq(12);
			cfgLine.setPlinePos("12");
			cfgLine.setTitle("输血");
			refType = baseService.getRefTypeByCode("YESNO_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 家族史
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-13");
			// cfgLine.setLinePos("13");
			cfgLine.setLingSeq(13);
			cfgLine.setTitle("家族史");
			refType = baseService.getRefTypeByCode("FAMILY_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 父亲
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-13.1");
			// cfgLine.setLinePos("13.1");
			cfgLine.setLingSeq(13);
			cfgLine.setPlinePos("13");
			cfgLine.setTitle("父亲");
			refType = baseService.getRefTypeByCode("FAMILY_HIS_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 母亲
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-13.2");
			// cfgLine.setLinePos("13.2");
			cfgLine.setLingSeq(13);
			cfgLine.setPlinePos("13");
			cfgLine.setTitle("母亲");
			refType = baseService.getRefTypeByCode("FAMILY_HIS_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 兄弟姐妹
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-13.3");
			// cfgLine.setLinePos("13.3");
			cfgLine.setLingSeq(13);
			cfgLine.setPlinePos("13");
			cfgLine.setTitle("兄弟姐妹");
			refType = baseService.getRefTypeByCode("FAMILY_HIS_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 子女
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-13.4");
			// cfgLine.setLinePos("13.4");
			cfgLine.setLingSeq(13);
			cfgLine.setPlinePos("13");
			cfgLine.setTitle("子女");
			refType = baseService.getRefTypeByCode("FAMILY_HIS_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 遗传病史
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-14");
			// cfgLine.setLinePos("14");
			cfgLine.setLingSeq(14);
			cfgLine.setTitle("遗传病史");
			refType = baseService.getRefTypeByCode("YESNO_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 残疾情况
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-15");
			// cfgLine.setLinePos("15");
			cfgLine.setLingSeq(15);
			cfgLine.setTitle("残疾情况");
			refType = baseService.getRefTypeByCode("DISABLE_HIS_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 生活方式
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16");
			// cfgLine.setLinePos("16");
			cfgLine.setLingSeq(16);
			cfgLine.setTitle("生活方式");
			refType = baseService.getRefTypeByCode("LIVE_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 体育锻炼
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.1");
			// cfgLine.setLinePos("16.1");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16");
			cfgLine.setTitle("体育锻炼");
			refType = baseService.getRefTypeByCode("SPORT_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 锻炼频率
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.1.1");
			// cfgLine.setLinePos("16.1.1");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.1");
			cfgLine.setTitle("锻炼频率");
			refType = baseService.getRefTypeByCode("SPORT_RATE_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 每次锻炼时间
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.1.2.1");
			// cfgLine.setLinePos("16.1.2.1");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.1");
			cfgLine.setTitle("每次锻炼时间");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 坚持锻炼时间
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.1.2.2");
			// cfgLine.setLinePos("16.1.2.2");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.1");
			cfgLine.setTitle("坚持锻炼时间");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 锻炼方式
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.1.3");
			// cfgLine.setLinePos("16.1.3");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.1");
			cfgLine.setTitle("锻炼方式");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 饮食习惯
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.2");
			// cfgLine.setLinePos("16.2");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16");
			cfgLine.setTitle("饮食习惯");
			refType = baseService.getRefTypeByCode("EAT_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 吸烟情况
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.3");
			// cfgLine.setLinePos("16.3");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16");
			cfgLine.setTitle("吸烟情况");
			refType = baseService.getRefTypeByCode("SMOKE_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 吸烟状况
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.3.1");
			// cfgLine.setLinePos("16.3.1");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.3");
			cfgLine.setTitle("吸烟状况");
			refType = baseService.getRefTypeByCode("SMOKE_STATUS_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 日吸烟量
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.3.2");
			// cfgLine.setLinePos("16.3.2");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.3");
			cfgLine.setTitle("日吸烟量");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 开始吸烟年龄
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.3.3.1");
			// cfgLine.setLinePos("16.3.3.1");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.3");
			cfgLine.setTitle("开始吸烟年龄");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 戒烟年龄
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.3.3.2");
			// cfgLine.setLinePos("16.3.3.2");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.3");
			cfgLine.setTitle("戒烟年龄");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 饮酒情况
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.4");
			// cfgLine.setLinePos("16.4");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16");
			cfgLine.setTitle("饮酒情况");
			refType = baseService.getRefTypeByCode("DRINK_LIQUOR_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 饮酒频率
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.4.1");
			// cfgLine.setLinePos("16.4.1");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.4");
			cfgLine.setTitle("饮酒频率");
			refType = baseService.getRefTypeByCode("LIQUOR_RATE_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 日饮酒量
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.4.2");
			// cfgLine.setLinePos("16.4.2");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.4");
			cfgLine.setTitle("日饮酒量");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 是否戒酒
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.4.3");
			// cfgLine.setLinePos("16.4.3");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.4");
			cfgLine.setTitle("是否戒酒");
			refType = baseService.getRefTypeByCode("STOP_LIQUOR_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 开始饮酒年龄
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.4.4.1");
			// cfgLine.setLinePos("16.4.4.1");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.4");
			cfgLine.setTitle("开始饮酒年龄");
			cfgLine.setInputVlType("string");
			cfgLines.add(cfgLine);

			// 近一年内是否曾醉酒
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.4.4.2");
			// cfgLine.setLinePos("16.4.4.2");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.4");
			cfgLine.setTitle("近一年内是否曾醉酒");
			refType = baseService.getRefTypeByCode("IFELSE_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 饮酒种类
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-16.4.5");
			// cfgLine.setLinePos("16.4.5");
			cfgLine.setLingSeq(16);
			cfgLine.setPlinePos("16.4");
			cfgLine.setTitle("饮酒种类");
			refType = baseService.getRefTypeByCode("LIQUOR_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 生活环境
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-17");
			// cfgLine.setLinePos("17");
			cfgLine.setLingSeq(17);
			cfgLine.setTitle("生活环境");
			refType = baseService.getRefTypeByCode("LIVEENV_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 厨房排风设施
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-17.1");
			// cfgLine.setLinePos("17.1");
			cfgLine.setLingSeq(17);
			cfgLine.setPlinePos("17");
			cfgLine.setTitle("厨房排风设施");
			refType = baseService.getRefTypeByCode("KITCHENAIR_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 燃料类型
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-17.2");
			// cfgLine.setLinePos("17.2");
			cfgLine.setLingSeq(17);
			cfgLine.setPlinePos("17");
			cfgLine.setTitle("燃料类型");
			refType = baseService.getRefTypeByCode("FUEL_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 饮水
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-17.3");
			// cfgLine.setLinePos("17.3");
			cfgLine.setLingSeq(17);
			cfgLine.setPlinePos("17");
			cfgLine.setTitle("饮水");
			refType = baseService.getRefTypeByCode("WATER_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 厕所
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-17.4");
			// cfgLine.setLinePos("17.4");
			cfgLine.setLingSeq(17);
			cfgLine.setPlinePos("17");
			cfgLine.setTitle("厕所");
			refType = baseService.getRefTypeByCode("TOILET_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			// 禽畜栏
			cfgLine = new TableCfgLineEto();
			cfgLine.setCode(tbcode + "-17.5");
			// cfgLine.setLinePos("17.5");
			cfgLine.setLingSeq(17);
			cfgLine.setPlinePos("17");
			cfgLine.setTitle("禽畜栏");
			refType = baseService.getRefTypeByCode("CORRAL_TYPE");
			cfgLine.setRefTypeId(refType.getId());
			cfgLines.add(cfgLine);

			boolean addStatus = false;

			addStatus = tableService.addTableCfg(cfgHead, cfgLines);
			System.out.println("是否添加成功" + addStatus);

		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获取表列全部信息
	// @Test
	public void testListTableCfg() {
		try {
			List<TableCfgLineDto> cfgs = tableService.listTableLine(ConstantTableCfg.TB_DOCINFO_R1001, false);
			for (TableCfgLineDto cfg : cfgs) {
				System.out.println(cfg.getLineCode());
				/*
				 * System.out.println("+++"+cfg.getLineCode()+","+cfg.
				 * getLineTypeVlId()+","+cfg.getLineTypeVlName()
				 * +","+cfg.getLineRefTypeId()+","+cfg.getLineRefTypeName());
				 * for (TableCfgLineDto cfg2 : cfg.getSubs()) {
				 * System.out.println("sub="+cfg2.getLineCode()); } for
				 * (SysRefVlDto vl : cfg.getRefVls()) { System.out.println("\t"
				 * + vl.getRefVlName()); }
				 */

			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获取表中具体某列信息
	// @Test
	public void testListTableCfgByLineCode() {
		try {
			List<TableCfgLineDto> cfgs = tableService.listTableLine(25, ConstantTableCfg.TB_DOCINFO_R1001, "R1001-12");
			for (TableCfgLineDto cfg : cfgs) {
				System.out.println(cfg.getLineCode());
				for (TableCfgLineDto cfg2 : cfg.getSubs()) {
					System.out.println(cfg2.getLineCode());
				}
				for (SysRefVlDto vl : cfg.getRefVls()) {
					System.out.println("\t" + vl.getRefVlName());
				}
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获取表中具体某列信息
	// @Test
	public void testListCfgHeadByTypeId() {
		try {
			String code = "";
			String name = "测试";
			int typeId = 376;
			List<TableCfgHeadEbo> tableList = tableService.listCfgHeadEbo(code, name, typeId);
			for (TableCfgHeadEbo tableCfgHeadEbo : tableList) {
				System.out.println(tableCfgHeadEbo.getName());
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获取表中数据信息
	@Test
	public void testGetDataHeadById() {
		try {
			int dataHeadId = 25;
			String dataHeadCode = "";
			TableDataHeadEbo tableDataHeadEbo = tableDao.getDataHeadById(dataHeadId, dataHeadCode);
			System.out.println("tableDataHeadEbo=" + tableDataHeadEbo);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 通过编号获取表有数据信息
	// @Test
	public void testGetDataHeadByOrgSheetCode() {
		try {
			int orgId = 1;
			String sheetCode = "";
			TableDataHeadEbo tableDataHeadEbo = tableDao.getDataHeadByOrgSheetCode(orgId, sheetCode);
			System.out.println("tableDataHeadEbo=" + tableDataHeadEbo);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获取表列数据
	// @Test
	/*
	 * public void testListDataLineById() { try { int id = 0; int cfgLineId = 0;
	 * int checkId = 20; List<TableDataLineEbo> lineList =
	 * tableDao.listDataLine(id, cfgLineId, checkId); for(TableDataLineEbo l :
	 * lineList ){ System.out.println("l=" + l); }
	 * 
	 * } catch (DataException e) { e.printStackTrace(); } }
	 */

	// 获取表头数据 TODO sheetCode测试
	// @Test
	public void testListDataHeadSheetCode() {
		try {
			int orgId = 0;
			String sheetCode = "2017-09-25";
			List<TableDataHeadEbo> headList = tableDao.listDataHeadSheetCode(orgId, sheetCode);
			for (TableDataHeadEbo l : headList) {
				System.out.println("l=" + l);
			}

		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试添加表标题
	// @Test
	public void testAddCfgHead() {
		try {
			String name = "测试添加Head";
			int typeId = 0;
			String code = "CS001";
			TableCfgHeadEbo cfgHead = new TableCfgHeadEbo();
			cfgHead.setName(name);
			cfgHead.setTypeId(typeId);
			cfgHead.setCode(code);
			cfgHead.setFlag("Y");
			cfgHead.setCreateTime(DateUtil.newTime());
			tableDao.addCfgHead(cfgHead);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 添加报表数据
	@Test
	public void testAddTableData() {
		try {
			TableDataHeadEto dataHead = new TableDataHeadEto();
			dataHead.setCfgId(25);
			dataHead.setOrgId(1);
			dataHead.setStaffId(9);
			// dataHead.setSheetCode("2017-09-25");
			dataHead.setUid(1);
			Date date = new Date();
			dataHead.setSheetDay(date);
			dataHead.setBeginTime(DateUtil.getCurTimeInSec());
			dataHead.setEndTime(DateUtil.getCurTimeInSec());

			List<TableDataLineEto> dataLines = new ArrayList<TableDataLineEto>();

			TableDataLineEto dataLine = null;

			dataLine = new TableDataLineEto();
			dataLine.setLineCode(ConstantTableCfg.TBLN_DI_NAME_0);
			dataLine.setInputVl("姓名");
			dataLines.add(dataLine);

			dataLine = new TableDataLineEto();
			dataLine.setLineCode(ConstantTableCfg.TBLN_DI_SEX_1_1);
			dataLine.setRefId(159);
			dataLines.add(dataLine);

			dataLine = new TableDataLineEto();
			dataLine.setLineCode(ConstantTableCfg.TBLN_DI_IDCARD_2_1);
			dataLine.setInputVl("2.1身份证");
			;
			dataLines.add(dataLine);

			dataLine = new TableDataLineEto();
			dataLine.setLineCode(ConstantTableCfg.TBLN_DI_BIRTH_1_2);
			dataLine.setInputVl("2000-01-01");
			;
			dataLines.add(dataLine);

			dataLine = new TableDataLineEto();
			dataLine.setLineCode(ConstantTableCfg.TBLN_DI_MOBILR_3_1);
			dataLine.setInputVl("本人电话");
			dataLines.add(dataLine);

			dataLine = new TableDataLineEto();
			dataLine.setLineCode(ConstantTableCfg.TBLN_DI_CONTACT_3_2);
			dataLine.setInputVl("联系人姓名");
			;
			dataLines.add(dataLine);

			// 添加多选
			dataLine = new TableDataLineEto();
			dataLine.setLineCode(ConstantTableCfg.TBLN_DI_exposure_11);
			dataLine.setRefId(209);
			dataLine.setInputVl("209输入值");
			dataLines.add(dataLine);

			dataLine = new TableDataLineEto();
			dataLine.setLineCode(ConstantTableCfg.TBLN_DI_exposure_11);
			dataLine.setRefId(210);
			dataLines.add(dataLine);

			/*
			 * dataLine = new TableDataLineEto(); dataLine.setLineCode("3_1");
			 * //dataLine2.setVlType(Constant.DATA_LINE_TEXT);
			 * dataLine.setInputVl("11111就在一起谁都不许说分离" + "这段恋情 是上天给予" +
			 * "会让我们成为这个世界上" + "最美的情侣" + "你笑我陪着你笑 " + "你哭我就在你身边逗你开心" +
			 * "如果你生气不理我" + "我就会厚着脸皮向你贴近" + "在这我们共同呼吸 " + "不管未来怎样都无所畏惧" +
			 * "一辈子都说我爱你 " + "让整个城市聆听着我们幸福的声音" + "就在一起谁让我们相遇 " + "以后的日子我们一起相依" +
			 * "我会宠着你 我会纵容你 " + "谁要欺负你我就站出保护你 " + "就在一起一生相守不弃" + "就在一起谁都不许说分离" +
			 * "这段恋情 是上天给予 " + "会让我们成为这个世界上" + "最美的情侣 ");
			 * dataLines.add(dataLine);
			 */

			/*
			 * dataLine = new TableDataLineEto(); dataLine.setLineCode("3_2");
			 * dataLine.setInputVl("3.2输入值1111");; dataLines.add(dataLine);
			 */
			// 预先检查
			tableService.preCheckTableData(dataHead, dataLines);

			boolean addStatus = tableService.addTableData(dataHead, dataLines);
			System.out.println("是否添加成功" + addStatus);

		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 添加报表数据
	@Test
	public void testAddTableDataCount() {
		try {
			// 表头数据
			TableDataHeadEto dataHead = new TableDataHeadEto();
			// 老年人生活自理能力评估表 需要计算
			dataHead.setCfgId(36);
			dataHead.setOrgId(1);
			dataHead.setStaffId(9);
			dataHead.setUid(1);
			Date date = new Date();
			dataHead.setSheetDay(date);
			dataHead.setBeginTime(DateUtil.getCurTimeInSec());
			dataHead.setEndTime(DateUtil.getCurTimeInSec());

			// 表列数据
			List<TableDataLineEto> dataLines = new ArrayList<TableDataLineEto>();
			TableDataLineEto dataLine = null;

			// 进餐
			dataLine = new TableDataLineEto();
			dataLine.setLineCode("2");
			dataLine.setRefId(904); // 3
			dataLines.add(dataLine);
			// 梳洗
			dataLine = new TableDataLineEto();
			dataLine.setLineCode("3");
			dataLine.setRefId(907); // 1
			dataLines.add(dataLine);
			// 穿衣
			dataLine = new TableDataLineEto();
			dataLine.setLineCode("4");
			dataLine.setRefId(911); // 3
			dataLines.add(dataLine);
			// 如厕
			dataLine = new TableDataLineEto();
			dataLine.setLineCode("5");
			dataLine.setRefId(913); // 0
			dataLines.add(dataLine);
			// 活动
			dataLine = new TableDataLineEto();
			dataLine.setLineCode("6");
			dataLine.setRefId(917); // 0
			dataLines.add(dataLine);

			// 预先检查
			tableService.preCheckTableData(dataHead, dataLines);

			boolean addStatus = tableService.addTableData(dataHead, dataLines);
			System.out.println("是否添加成功" + addStatus);

		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获取报表数据
	@Test
	public void testGetTableData() {
		try {

			List<TableDataHeadEbo> heads = tableService.listDataHeadByMainId(20, "DOC");
			if (heads.size() == 0)
				return;
			List<TableDataLineDto> lines = tableService.listDataLine(heads.get(0).getId());
			for (TableDataLineDto line : lines) {
				System.out.println(line.getLineCode() + ",datas=" + line.getLines().size());
				for (DataLineDto l : line.getLines())
					System.out.println("[ln]" + l.getRefId() + "," + l.getRefVl() + "," + l.getInputVl());
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获取表头数据 TODO sheetCode测试
	// @Test
	public void testListTableHead() {
		try {
			int id = 0;
			String code = "R1001";
			List<TableCfgHeadDto> headList = tableService.listTableCfgHead(id, code);
			for (TableCfgHeadDto head : headList) {
				System.out.println(head.getCfgId() + "," + head.getCfgTypeId() + "," + head.getCfgTypeName());
			}

		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获取表头数据 TODO sheetCode测试
	// @Test
	public void testUpdateTableHead() {
		try {
			String code = "T1000";
			String name = "测试修改";
			String flag = "";
			tableService.updateCfgHead(code, name, flag);
			System.out.println("修改成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 获取表头数据 TODO sheetCode测试
	// @Test
	public void testListDataLine() {
		try {
			int checkId = 69;
			List<TableDataLineDto> listData = tableService.listDataLine(checkId);
			for (TableDataLineDto t : listData) {
				System.out.println(t);
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 根据tableTypeId获取表格配置头
	@Test
	public void testListCfgHeadsByTypeId() {
		try {
			int tableTypeId = 1068;
			List<TableCfgHeadDto> listCfgHeadByTypeId = tableService.listCfgHeadByTypeId(tableTypeId, 0, 10);
			for (TableCfgHeadDto t : listCfgHeadByTypeId) {
				System.out.println(t.getCfgName());
			}
			int total = tableService.getTotalFollowupPage(tableTypeId);
			System.out.println(total);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

}
