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
import com.kjplus.dao.ICalendarMainDao;
import com.kjplus.dto.CalendarEntryDto;
import com.kjplus.eto.CalendarEntryEto;
import com.kjplus.eto.CalendarInfoEto;
import com.kjplus.model.CalendarEntryEbo;
import com.kjplus.model.CalendarEventTypeEbo;
import com.kjplus.model.CalendarInfoEbo;
import com.kjplus.qto.CalEntryInfoQto;
import com.kjplus.service.ICalendarMainService;
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
public class TestCalendarMainService {

	@Autowired
	private ICalendarMainService calMainService;
	@Autowired
	private ICalendarMainDao calMainDao;

	@Test
	public void testGetCalEntry() {
		try {
			int id = 1;
			String code = "12345678";
			CalendarEntryEbo  entry= calMainService.getCalEntryByIdOrCode(id, code);
			System.out.println(entry);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetCalInfo() {
		try {
			int id = 121;
			String code = "64040311287781028069386646541616";
			CalendarInfoEbo  entry= calMainService.getCalInfoByIdOrCode(id, code);
			System.out.println(entry);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	// 添加日历主表
	//@Test
	public void testAddCalEntry() {
		try {
			CalendarEntryEto calEntry = new CalendarEntryEto();
			calEntry.setCalTypeId(370);
			calEntry.setMainId(2);
			calEntry.setMainType(Constant.CREATE_TYPE_ORG);
			calEntry.setName("修改默认上门服务");
			calEntry.setCreateId(16);
			calEntry.setEntryType(Constant.ENTRY_TYPE_WORK);
			calEntry.setIsDefault(Constant.FLAG_YES);
			calEntry.setShowClass("lable-success");
			calEntry.setCreateId(16);
			calEntry.setOrgId(2);
			CalendarEntryEbo  entry= calMainService.addCalEntry(calEntry,false);
			System.out.println(entry);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void testUpdateCalEntryIsDefault() {
		try {
			int mainId = 9;
			String mainType = "STAFF";
			String entryType = "W";
			String isDefault = "N";
			calMainService.updateCalEntryIsdefault(mainId, mainType, entryType, isDefault);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}	
	// 添加日历信息表
	@Test
	public void testAddCalInfo() {
		try {
			CalendarInfoEto calInfo = new CalendarInfoEto();
			calInfo.setCalEntryId(2);;
			calInfo.setCalMemo("11测试添加活动");
			calInfo.setCalTitle("11预约接种，乙肝疫苗");
			calInfo.setStartTime(DateUtil.getCurTimeInSec());
			calInfo.setEndTime(DateUtil.getCurTimeInSec() + 2 * 60 * 60);
			calInfo.setMaxPerson(10);
			CalendarInfoEbo ci = calMainService.addCalInfo(calInfo);
			System.out.println("info:" + ci.getId());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 列表日历信息
	@Test
	public void testListEntryInfoDay() {
		try {
			CalEntryInfoQto cal = new CalEntryInfoQto();
			/*cal.setMainId(9);
			cal.setMainType("STAFF");
			cal.setOrgId(1);
			cal.setEntryId(1);
			cal.setInfoId(144);
			cal.setInfoTitle("预");
			cal.setEntryName("");
			cal.setInfoSourceType("E");*/
			cal.setStartTime(1519833601);
			cal.setEndTime(0);
			cal.setOrgId(1);
			cal.setInfoTypeId(0);//日历信息的类型	
			int page = 0;
			int paging =100;
			List<CalendarEntryDto> cals = calMainService.listEntryInfo(cal, page, paging);
			for (CalendarEntryDto calendarEntryDto : cals) {
				System.out.println(calendarEntryDto);
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 列表日历行
	@Test
	public void testListCalEntryInfo() {
		try {
			CalEntryInfoQto cal = new CalEntryInfoQto(); 
			cal.setDayStr("");
			cal.setYear(0);
			cal.setMonth(0);
			cal.setEndTime(1516705674);
			cal.setEntryFlag(Constant.FLAG_YES);
			cal.setEntryId(1);
			cal.setEntryName("预防");
			cal.setInfoFlag(Constant.FLAG_YES);
			cal.setInfoId(147);
			cal.setInfoSourceType("A");
			cal.setInfoTitle("主动");
			cal.setMainId(9);
			cal.setMainType("STAFF");
			cal.setOrgId(1);
			cal.setStartTime(1516703860);
			
			int page = 0;
			int paging = 10;
			List<CalendarEntryDto> listEntryInfo = calMainService.listEntryInfo(cal, page, paging);
			for (CalendarEntryDto calendarEntryDto : listEntryInfo) {
				System.out.println(calendarEntryDto);
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 日历事件类型
	//@Test
	public void testListCalEventType() {
		try {
			List<Integer> defIds = new ArrayList<Integer>();
			defIds.add(1);
			List<CalendarEventTypeEbo> listCalEventType = calMainService.listCalEventType(defIds);
			for (CalendarEventTypeEbo ci : listCalEventType) {
				System.out.println(ci);
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	//添加eventType
	//@Test
	public void testAddCalEventType() {
		try {
			Integer defId = 1;
			String title = "测试添加";
			Integer timeInterval = 100;
			Integer maxPerson = 20;
			String showClass = "red";
			String memo = "测试添加";
			CalendarEventTypeEbo calEventType = new CalendarEventTypeEbo();
			calEventType.setDefId(defId);
			calEventType.setTitle(title);
			calEventType.setTimeInterval(timeInterval);
			calEventType.setMaxPerson(maxPerson);
			calEventType.setShowClass(showClass);
			calEventType.setMemo(memo);
			calMainDao.addCalEventType(calEventType);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListEntry() {
		try {
			int mainId = 0;
			String mainType = "";
			int createId = 0;
			int orgId = 0;
			String flag = "Y";
			List<CalendarEntryEbo> listEntry =  calMainService.listCalEntry(mainId, mainType,createId, orgId, flag);
			for (CalendarEntryEbo calendarEntryEbo : listEntry) {
				System.out.println(calendarEntryEbo);
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateCalInfo() {
		try {
			int id = 0;
			String code = "64040311287781028069386646541616";
			int maxPerson = 0;
			int joinPerson = -1;
			String flag = "";
			int startTime = 0;
			int endTime = 0;
			String title = "";
			String memo = "";
			calMainService.updateCalInfo(id, code,title,memo, maxPerson, joinPerson, flag, startTime ,endTime);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	
}
