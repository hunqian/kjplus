package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ybk.basic.util.Util;

import com.kjplus.annotation.BizType;
import com.kjplus.annotation.MethodType;
import com.kjplus.annotation.PerformanceLog;
import com.kjplus.constant.Constant;
import com.kjplus.dao.ITableDao;
import com.kjplus.dto.DataLineDto;
import com.kjplus.dto.SysRefTypeDto;
import com.kjplus.dto.SysRefVlDto;
import com.kjplus.dto.TableCfgHeadDto;
import com.kjplus.dto.TableCfgLineDto;
import com.kjplus.dto.TableDataLineDto;
import com.kjplus.eto.TableCfgHeadEto;
import com.kjplus.eto.TableCfgLineEto;
import com.kjplus.eto.TableDataHeadEto;
import com.kjplus.eto.TableDataLineEto;
import com.kjplus.model.OrgEbo;
import com.kjplus.model.StaffEbo;
import com.kjplus.model.SysRefTypeEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.TableCfgHeadEbo;
import com.kjplus.model.TableCfgLineEbo;
import com.kjplus.model.TableDataHeadEbo;
import com.kjplus.model.TableDataLineEbo;
import com.kjplus.model.inner.TableCfgInnerEbo;
import com.kjplus.model.inner.TableDataLineInnerEbo;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ITableService;
import com.kjplus.service.IUserService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("tableService")
public class TableService implements ITableService {

	@Autowired
	private ITableDao tableDao;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrgService orgService;

	public TableCfgHeadEbo getCfgHeadByCode(String cfgHeadCode) throws DataException {
		if (Util.isNull(cfgHeadCode))
			return null;
		return tableDao.getCfgHeadById(0, cfgHeadCode);
	}

	public TableCfgLineEbo getCfgLineByCode(String cfgLineCode) throws DataException {
		if (Util.isNull(cfgLineCode))
			return null;
		return tableDao.getCfgLineById(0, cfgLineCode, 0, -1, -2, -2);
	}

	public TableDataHeadEbo getDataHeadByCode(String dataHeadCode) throws DataException {
		if (Util.isNull(dataHeadCode))
			return null;
		return tableDao.getDataHeadById(0, dataHeadCode);
	}

	// 通过mainId和mainType获取表格数据存储主表
	public List<TableDataHeadEbo> listDataHeadByMainId(int mainId, String mainType) throws DataException {
		List<TableDataHeadEbo> heads = new ArrayList<TableDataHeadEbo>();
		if (mainId <= 0 && Util.isNull(mainType))
			return heads;
		return tableDao.listDataHeadByMainId(mainId, mainType);
	}

	public TableCfgHeadEbo getCfgHeadById(int cfgHeadId) throws DataException {
		if (cfgHeadId <= 0)
			return null;
		return tableDao.getCfgHeadById(cfgHeadId, null);
	}

	public TableCfgLineEbo getCfgLineById(int cfgLineId) throws DataException {
		if (cfgLineId <= 0)
			return null;
		return tableDao.getCfgLineById(cfgLineId, null, 0, -1, -2, -2);
	}

	public TableDataHeadEbo getDataHeadById(int dataHeadId) throws DataException {
		if (dataHeadId <= 0)
			return null;
		return tableDao.getDataHeadById(dataHeadId, null);
	}

	public List<TableDataHeadEbo> getDataHeadSheetCode(int orgId, String sheetCode) throws DataException {
		if (orgId <= 0 && Util.isNull(sheetCode))
			return null;
		return tableDao.listDataHeadSheetCode(orgId, sheetCode);
	}

	// 查询表(加入日志记录)列返回一个树状
	public List<TableCfgLineDto> listTableLine(int cfgId, String cfgCode) throws DataException {
		boolean treeStyle = false;
		return listTableLine(cfgId, cfgCode, treeStyle);
	}

	// 查询表(加入日志记录)列返回一个树状
	// fetchRef=true,是否返回参照
	public List<TableCfgLineDto> listTableLine(String cfgCode, boolean fetchRef) throws DataException {
		boolean treeStyle = false;
		List<TableCfgLineDto> lines = listTableLine(-1, cfgCode, treeStyle);
		if (!fetchRef)
			return lines;
		List<Integer> typeIds = new ArrayList<Integer>();
		for (TableCfgLineDto line : lines) {
			if (line.getLineRefTypeId() == null || line.getLineRefTypeId().intValue() == 0)
				continue;
			typeIds.add(line.getLineRefTypeId());
		}
		List<SysRefTypeDto> types = baseService.listRefTypesWithVl(typeIds);

		for (TableCfgLineDto line : lines) {
			if (line.getLineRefTypeId() == null || line.getLineRefTypeId().intValue() == 0)
				continue;
			for (int j = 0; j < types.size(); j++) {
				if (types.get(j).getId().intValue() == line.getLineRefTypeId().intValue())
					line.setRefVls(types.get(j).getRefVls());
			}
		}
		return lines;
	}

	// 查询表(加入日志记录)
	@PerformanceLog(methodType = MethodType.SELECT, bizType = BizType.RETTLE)
	public List<TableCfgLineDto> listTableLine(int cfgId, String cfgCode, boolean treeStyle) throws DataException {

		List<TableCfgLineDto> cfgLines = new ArrayList<TableCfgLineDto>();
		List<TableCfgInnerEbo> cfgs = tableDao.listTableCfg(cfgId, cfgCode, null);
		Hashtable<String, TableCfgLineDto> lineHash = new Hashtable<String, TableCfgLineDto>();
		Integer linePid = 0;
		String k = null;
		String k2 = null;
		for (TableCfgInnerEbo cfg : cfgs) {

			linePid = cfg.getLinePid();
			k = "C." + cfg.getLineId();
			k2 = "C." + cfg.getLineCode();
			TableCfgLineDto line = new TableCfgLineDto();
			if (lineHash.containsKey(k2))
				line = lineHash.get(k2);
			else {
				BeanUtils.copyProperties(cfg, line);
			}
			if (!treeStyle) {
				if (Util.isNotNull(line.getLineCode()))
					cfgLines.add(line);
			} else {
				if (linePid == null || linePid.intValue() <= 0) {
					// 判断子类父类
					if (!lineHash.containsKey(k))
						cfgLines.add(line);
				} else {
					k = "C." + linePid;
					if (lineHash.containsKey(k)) {
						lineHash.get(k).getSubs().add(line);
					}
				}
			}

			if (cfg.getRefVlId() != null && cfg.getRefVlId() > 0) {
				SysRefVlDto rv = new SysRefVlDto();
				BeanUtils.copyProperties(cfg, rv);
				if (Util.isNotNull(cfg.getExtVlType())) {
					List<String> vts = Util.str2Array(cfg.getExtVlType());
					for (String v : vts) {
						if (Util.parseNumVl(v, 0) == 0)
							continue;
						rv.getExts().add(Util.parseNumVl(v, 0));
					}
				}
				line.getRefVls().add(rv);
			}
			// k = "C." + line.getLineId();
			lineHash.put(k, line);
			lineHash.put(k2, line);
		}

		return cfgLines;
	}

	@PerformanceLog(methodType = MethodType.SELECT, bizType = BizType.RETTLE)
	public List<TableCfgLineDto> listTableLine(int cfgId, String cfgCode, String cfgLineCode) throws DataException {

		List<TableCfgLineDto> cfgLines = new ArrayList<TableCfgLineDto>();
		List<TableCfgInnerEbo> cfgs = tableDao.listTableCfg(cfgId, cfgCode, cfgLineCode);
		Hashtable<String, TableCfgLineDto> lineHash = new Hashtable<String, TableCfgLineDto>();
		Integer linePid = 0;
		String k = null;
		for (TableCfgInnerEbo cfg : cfgs) {
			linePid = cfg.getLinePid();
			k = "C." + cfg.getLineId();
			if (cfg.getLineCode().equals("R1001-12.1")) {
				System.out.println("R1001-12.1");
			}
			TableCfgLineDto line = new TableCfgLineDto();
			if (lineHash.containsKey(k))
				line = lineHash.get(k);
			else
				BeanUtils.copyProperties(cfg, line);
			if (linePid == null || linePid.intValue() <= 0) {
				if (!lineHash.containsKey(k))
					cfgLines.add(line);
			} else {
				k = "C." + linePid;
				if (lineHash.containsKey(k)) {
					lineHash.get(k).getSubs().add(line);
				}
			}
			if (cfg.getRefVlId() != null && cfg.getRefVlId() > 0) {
				SysRefVlDto rv = new SysRefVlDto();
				BeanUtils.copyProperties(cfg, rv);
				if (Util.isNotNull(cfg.getExtVlType())) {
					List<String> vts = Util.str2Array(cfg.getExtVlType());
					for (String v : vts) {
						if (Util.parseNumVl(v, 0) == 0)
							continue;
						rv.getExts().add(Util.parseNumVl(v, 0));
					}
				}
				line.getRefVls().add(rv);
			}
			/*
			 * k = "P." + cfg.getLinePid(); lineHash.put(k, line);
			 */
			k = "C." + line.getLineId();
			lineHash.put(k, line);

		}
		return cfgLines;
	}

	// 添加报表头
	private TableCfgHeadEbo addCfgHead(TableCfgHeadEto cfgHead) throws DataException {
		// 空值验证
		DataValUtil.dataValidation(cfgHead.getClass(), cfgHead);
		String code = cfgHead.getCode();// 获取传入的code
		TableCfgHeadEbo cfgHeadEbo = null;
		if (Util.isNull(code)) {
			// 产生一个8位长
			code = Util.genDigiCodeStr(TableCfgHeadEto.CODE_LEN);
			cfgHeadEbo = getCfgHeadByCode(code);
			while (cfgHeadEbo != null) {
				code = Util.genDigiCodeStr(TableCfgHeadEto.CODE_LEN);
				cfgHeadEbo = getCfgHeadByCode(code);
			}
		} else {
			cfgHeadEbo = getCfgHeadByCode(code);
			if (cfgHeadEbo != null)
				return cfgHeadEbo;
		}

		SysRefValueEbo refVl = baseService.getRefVlById(cfgHead.getTypeId());
		if (refVl == null)
			throw new DataException("[err]无此类型");
		TableCfgHeadEbo cfgHead1 = new TableCfgHeadEbo();
		try {
			BeanUtils.copyProperties(cfgHead, cfgHead1);
			cfgHead1.setCode(code);
			cfgHead1.setTypeId(refVl.getRefId());
			cfgHead1.setFlag(Constant.FLAG_YES);
			if (Util.isNull(cfgHead.getName()))
				cfgHead1.setName(refVl.getName());
			cfgHead1.setCreateTime(Calendar.getInstance().getTime());
			tableDao.addCfgHead(cfgHead1);
		} catch (Exception e) {
			throw new DataException(e.getMessage());
		}
		return cfgHead1;
	}

	// 添加配置表行
	private List<TableCfgLineEbo> addCfgLine(List<TableCfgLineEto> cfgLines) throws DataException {
		// 对必要的参数进行空值验证
		for (TableCfgLineEto line : cfgLines)
			DataValUtil.dataValidation(line.getClass(), line);

		int cfgId = 0;// 所属表格配置id
		TableCfgHeadEbo cfgHead = null;
		Map<Integer, Object> mapCfgId = new HashMap<Integer, Object>();
		String code = null;
		int pid = 0;
		int refTypeId = 0;
		TableCfgLineEbo cfgLine1 = null;
		TableCfgLineEbo cfgLine2 = null;
		List<TableCfgLineEbo> cfgLines1 = new ArrayList<TableCfgLineEbo>();

		try {

			for (TableCfgLineEto cfgLine : cfgLines) {
				// 所属表格配置id
				cfgId = cfgLine.getCfgId();
				// 查询所属表格配置id是否存在
				cfgHead = getCfgHeadById(cfgId);
				if (cfgHead == null)
					throw new DataException("[err]所属表格的配置不能空");
				if (!mapCfgId.containsKey(cfgId)) {// 传入的cfg配置id是否包含在map中如果不包含做二次数据库查询
					mapCfgId.put(cfgId, cfgLine);
					cfgHead = getCfgHeadById(cfgId);
					if (cfgHead == null)
						throw new DataException("[err]所属表格的配置不能空");
				}
				// 行编码,保持唯一
				code = cfgLine.getCode();
				if (Util.isNull(code)) {
					code = Util.genDigiCodeStr(TableCfgLineEto.CODE_LEN);
					cfgLine1 = getCfgLineByCode(code);
					while (cfgLine1 != null) {
						code = Util.genDigiCodeStr(TableCfgLineEto.CODE_LEN);
						cfgLine1 = getCfgLineByCode(code);
					}
				} else {
					cfgLine1 = getCfgLineByCode(code);
					if (cfgLine1 != null) {
						cfgLines.remove(cfgLine);

						continue;
					}
					if (cfgLines.isEmpty())
						return null;
				}
				if (Util.isNotNull(cfgLine.getPlinePos())) {
					tableDao.addCfgLine(cfgLines1);// 在需要读取时进行一次提交
					cfgLines1.clear();// 清空当前集合所有元素
					// TableCfgLineEbo l = tableDao.getCfgLineById(0, null,
					// cfgId, cfgLine.getPlinePos(),-2);
					TableCfgLineEbo l = tableDao.getCfgLineById(0, null, cfgId, -1, -2, -2);
					if (l != null)
						cfgLine.setPid(l.getId());
				}

				// 上一级id，默认为0
				pid = cfgLine.getPid();
				if (pid > 0) {
					cfgLine2 = getCfgLineById(pid);
					if (cfgLine2 == null)
						throw new DataException("[err]指定的上级配置行不存在");
				}
				// 对应的参照id,如果没有参照，则为0
				refTypeId = cfgLine.getRefTypeId();
				if (refTypeId > 0) {
					SysRefTypeEbo refType = baseService.getRefTypeById(refTypeId);
					if (refType == null)
						throw new DataException("[err]无此参照，参照类型id为：" + cfgLine.getRefTypeId());
				}
				TableCfgLineEbo cfgLine3 = new TableCfgLineEbo();
				BeanUtils.copyProperties(cfgLine, cfgLine3);
				cfgLine3.setCode(code);
				cfgLine3.setPid(pid);
				cfgLine3.setRefTypeId(refTypeId);
				cfgLines1.add(cfgLine3);
			}
			tableDao.addCfgLine(cfgLines1);
		} catch (Exception e) {
			throw new DataException(e.getMessage());
		}
		return cfgLines1;
	}

	// 表格数据存储主表
	private TableDataHeadEbo addDataHead(TableDataHeadEto dataHead) throws DataException {
		// 做空值验证
		String sheetCode = dataHead.getSheetCode();
		String code = dataHead.getCode();
		TableDataHeadEbo dataHead1 = null;
		// 防止数据库出现重复编码
		if (Util.isNull(code)) {
			code = Util.genDigiCodeStr(TableDataHeadEto.CODE_LEN);
			dataHead1 = getDataHeadByCode(code);
			while (dataHead1 != null) {
				code = Util.genDigiCodeStr(TableDataHeadEto.CODE_LEN);
				dataHead1 = getDataHeadByCode(code);
			}
		}

		if (Util.isNull(sheetCode)) {
			sheetCode = Util.genDigiCodeStr(TableDataHeadEto.SHEET_CODE_LEN);
			dataHead1 = tableDao.getDataHeadByOrgSheetCode(dataHead.getOrgId(), sheetCode);
			while (dataHead1 != null) {
				sheetCode = Util.genDigiCodeStr(TableDataHeadEto.SHEET_CODE_LEN);
				dataHead1 = tableDao.getDataHeadByOrgSheetCode(dataHead.getOrgId(), sheetCode);
			}
		} else {
			List<TableDataHeadEbo> dataHeads = getDataHeadSheetCode(dataHead.getOrgId(), sheetCode);
			// 同组织的重复编号系统不做处理
			if (!dataHeads.isEmpty())
				throw new DataException("[err]通组织内检查单号不能重复");
		}

		TableCfgHeadEbo cfgHead = getCfgHeadById(dataHead.getCfgId());
		if (cfgHead == null)
			throw new DataException("[err]系统无此表格,传入的表格id为：" + dataHead.getCfgId());
		// 判断检查医生是否存在 当微信公众号自己建档时 默认staffId=0
		if (dataHead.getStaffId() != 0) {
			StaffEbo staff = staffService.getStaffById(dataHead.getStaffId());
			if (staff == null)
				throw new DataException("[err]系统无此检查医生,传入的staffId为：" + dataHead.getStaffId());
		}
		// 判断用户是否存在
		// TODO:考虑user或者adminuser的区分
		/*
		 * UserEbo user = userService.getUserById(dataHead.getUid()); if (user
		 * == null) throw new DataException("[err]系统无此用户,传入的uid为：" +
		 * dataHead.getUid());
		 */
		// 判断组织是否存在
		OrgEbo org = orgService.getOrgById(dataHead.getOrgId());
		if (org == null)
			throw new DataException("[err]系统无此组织,传入的组织编号为：" + dataHead.getOrgId());
		TableDataHeadEbo dataHead2 = new TableDataHeadEbo();
		BeanUtils.copyProperties(dataHead, dataHead2);
		dataHead2.setCode(code);
		dataHead2.setSheetCode(sheetCode);
		try {
			tableDao.addDataHead(dataHead2);
		} catch (Exception e) {
			throw new DataException(e.getMessage());
		}

		return dataHead2;
	}

	private List<TableDataLineEbo> addDataLine(List<TableDataLineEto> dataLines) throws DataException {
		// 做空值验证
		for (TableDataLineEto dataLine : dataLines)
			DataValUtil.dataValidation(dataLine.getClass(), dataLine);
		List<TableDataLineEbo> dataLines2 = new ArrayList<TableDataLineEbo>();
		for (TableDataLineEto dataLine : dataLines) {
			TableDataHeadEbo dh = tableDao.getDataHeadById(dataLine.getCheckId(), null);
			// 查询对应的配置行是否存在
			// TableCfgLineEbo cfgLine = tableDao.getCfgLineById(0, null,
			// dh.getCfgId(), dataLine.getLinePos());
			TableCfgLineEbo cfgLine = tableDao.getCfgLineById(0, dataLine.getLineCode(), dh.getCfgId(), -1, -2, -2);
			if (cfgLine == null)
				throw new DataException("[err]对应的配置行不存在id为：" + dataLine.getLineCode());
			// 查询检查表是否存在
			TableDataHeadEbo dataHead = getDataHeadById(dataLine.getCheckId());
			if (dataHead == null)
				throw new DataException("[err]对应的检查表不存在id为：" + dataLine.getCheckId());

			// 做类型验证
			if (Constant.DATA_LINE_REF.equals(dataLine.getVlType())) {
				if (dataLine.getRefId() <= 0)
					throw new DataException("[err]参照类型refId不能空");
				TableDataLineEbo dataLine2 = new TableDataLineEbo();
				BeanUtils.copyProperties(dataLine, dataLine2);
				dataLine2.setCfgLineId(cfgLine.getId());
				dataLines2.add(dataLine2);
			} else if (Constant.DATA_LINE_TEXT.equals(dataLine.getVlType())) {
				if (Util.isNull(dataLine.getInputVl()))
					throw new DataException("[err]输入类型inputVl不能空");
				// 将处理完成的对象直接加入到list集合中
				List<TableDataLineEbo> dl2 = createList(dataLine);
				for (TableDataLineEbo dl : dl2) {
					dl.setCfgLineId(cfgLine.getId());
				}
				dataLines2.addAll(dl2);
			}

		}

		try {
			tableDao.addDataLine(dataLines2);
		} catch (Exception e) {
			throw new DataException(e.getMessage());
		}
		return dataLines2;
	}

	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	@PerformanceLog(methodType = MethodType.ADD, bizType = BizType.RETTLE)
	public boolean addTableCfg(TableCfgHeadEto cfgHead, List<TableCfgLineEto> cfgLines) throws DataException {
		TableCfgHeadEbo addCfghead = null;
		List<TableCfgLineEbo> addCfgline = null;
		addCfghead = addCfgHead(cfgHead);
		if (addCfghead == null)
			throw new DataException("[err]添加表格配置头失败");
		for (TableCfgLineEto line : cfgLines) {
			line.setCfgId(addCfghead.getId());
		}
		if (!cfgLines.isEmpty()) {
			addCfgline = addCfgLine(cfgLines);
			if (addCfgline.isEmpty())
				throw new DataException("[err]添加配置行失败");
		}
		return true;
	}

	// 预先检查数据格式
	public boolean preCheckTableData(TableDataHeadEto dataHead, List<TableDataLineEto> dataLines) throws DataException {
		// 需要做预先判断
		List<TableCfgLineDto> cfgs = listTableLine(dataHead.getCfgId(), null);
		if (cfgs.size() == 0) {
			throw new DataException("[err]没有对应的表格配置参数!");
		}

		Hashtable<String, TableDataLineEto> tdLineHash = new Hashtable<String, TableDataLineEto>();
		for (TableDataLineEto tdl : dataLines) {
			tdLineHash.put(tdl.getLineCode(), tdl);
		}
		// 预判断内容
		StringBuffer ebuf = new StringBuffer();
		TableDataLineEto tdline = null;
		String tdlvl = null;
		for (TableCfgLineDto cfg : cfgs) {
			if (Constant.FLAG_YES.equals(cfg.getIsReq())) {
				if (!tdLineHash.containsKey(cfg.getLineCode())) {
					ebuf.append("行'" + cfg.getLineCode() + "'必须设置\n");
				}
			}
			if (!tdLineHash.containsKey(cfg.getLineCode()))
				continue;
			tdline = tdLineHash.get(cfg.getLineCode());
			// 参照
			if (cfg.getLineRefTypeId() != null && cfg.getLineRefTypeId().intValue() > 0) {
				if (tdline.getRefId() == null || tdline.getRefId().intValue() <= 0) {
					ebuf.append("行'" + cfg.getLineCode() + "'输入值应为参照值\n");
					continue;
				}
			} else {
				// 输入值
				tdlvl = tdline.getInputVl();
				if (Constant.FLAG_YES.equals(cfg.getIsReq()) && Util.isNull(tdlvl)) {
					ebuf.append("行'" + cfg.getLineCode() + "'输入值不能为空\n");
					continue;
				}
			}
		}
		if (ebuf.length() > 0) {
			throw new DataException(ebuf.toString());
		}
		return true;
	}

	// 修改
	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	@PerformanceLog(methodType = MethodType.UPDATE, bizType = BizType.RETTLE)
	public boolean modifyTableData(int mainId, String mainType, List<TableDataLineEto> dataLines) throws DataException {

		List<TableDataHeadEbo> heads = listDataHeadByMainId(mainId, mainType);
		if (heads.size() == 0)
			return false;

		// 获得配置
		List<TableCfgLineDto> cfgs = listTableLine(heads.get(0).getCfgId(), null);
		Hashtable<String, TableCfgLineDto> tdCfgHash = new Hashtable<String, TableCfgLineDto>();
		for (TableCfgLineDto c : cfgs) {
			tdCfgHash.put(c.getLineCode(), c);
		}

		List<TableDataLineEbo> addDataline = null;
		for (TableDataLineEto line : dataLines) {
			line.setCheckId(heads.get(0).getId());
			if (!tdCfgHash.containsKey(line.getLineCode()))
				continue;
			TableCfgLineDto c = tdCfgHash.get(line.getLineCode());
			if (c.getLineRefTypeId() != null && c.getLineRefTypeId().intValue() > 0)
				line.setVlType(Constant.DATA_LINE_REF);
			else
				line.setVlType(Constant.DATA_LINE_TEXT);
		}

		if (!dataLines.isEmpty()) {
			// 删除原来的
			tableDao.delDataLine(heads.get(0).getId());

			addDataline = addDataLine(dataLines);
			if (addDataline.isEmpty())
				throw new DataException("[err]添加数据dataline失败");
		}
		return true;
	}

	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	@PerformanceLog(methodType = MethodType.ADD, bizType = BizType.RETTLE)
	public boolean addTableData(TableDataHeadEto dataHead, List<TableDataLineEto> dataLines) throws DataException {

		TableDataHeadEbo addDatahead = null;
		List<TableDataLineEbo> addDataline = null;
		if (dataHead != null) {
			addDatahead = addDataHead(dataHead);
			if (addDatahead == null)
				throw new DataException("[err]添加数据储存主表失败");
		}

		// 获得配置
		List<TableCfgLineDto> cfgs = listTableLine(dataHead.getCfgId(), null);
		Hashtable<String, TableCfgLineDto> tdCfgHash = new Hashtable<String, TableCfgLineDto>();
		for (TableCfgLineDto c : cfgs) {
			tdCfgHash.put(c.getLineCode(), c);
		}
		// ckln_seq计数器
		Hashtable<String, Integer> tdCheckLineHash = new Hashtable<String, Integer>();
		int cklnSeq = 0;
		for (TableDataLineEto line : dataLines) {
			line.setCheckId(addDatahead.getId());
			if (!tdCfgHash.containsKey(line.getLineCode()))
				continue;
			TableCfgLineDto c = tdCfgHash.get(line.getLineCode());

			if (c.getLineRefTypeId() != null && c.getLineRefTypeId().intValue() > 0)
				line.setVlType(Constant.DATA_LINE_REF);
			else
				line.setVlType(Constant.DATA_LINE_TEXT);
			if (Constant.FLAG_YES.equals(c.getLineMultiRef())) {
				if (tdCheckLineHash.containsKey(line.getLineCode())) {
					cklnSeq = tdCheckLineHash.get(line.getLineCode());
					line.setCklnSeq(cklnSeq + 1);
					tdCheckLineHash.put(line.getLineCode(), cklnSeq + 1);
				} else {
					tdCheckLineHash.put(line.getLineCode(), 1);
				}
			}
		}

		// 判断是否需要计算列总分
		TableCfgHeadEbo cfgHead = getCfgHeadById(dataHead.getCfgId());
		if (cfgHead.getDataType().equals(Constant.TBL_DATA_COUNT))
			updateDataHead(addDatahead.getId(), dataLines);
		
		if (!dataLines.isEmpty()) {
			addDataline = addDataLine(dataLines);
			if (addDataline.isEmpty())
				throw new DataException("[err]添加数据dataline失败");
		}

		return true;
	}

	// 字符串长度超出算法
	public List<TableDataLineEbo> createList(TableDataLineEto dataLine) throws DataException {

		List<TableDataLineEbo> datalines = new ArrayList<TableDataLineEbo>();
		// 获取字符串
		String inputVl = dataLine.getInputVl();
		// 开始位置 对应的的ckln_seq所有从1开始
		int i = 1;
		// 获取字符串长度
		int strLen = inputVl.length();
		// 剩余长度
		int strEnd = 0;
		// 开始截取位置
		int start = 0;
		// 结束截取位置
		int end = 0;

		TableDataLineEbo line = null;
		do {
			// 第一次判断(字符串的长度是否大于40 并且是第一次进入此方法)
			if (strLen > 40 && i == 1) {
				end = 40;
				line = new TableDataLineEbo();
				BeanUtils.copyProperties(dataLine, line);
				line.setInputVl(inputVl.substring(start, end));
				line.setCklnSeq(i);
			} else if (strLen < 40 && i == 1) {
				line = new TableDataLineEbo();
				BeanUtils.copyProperties(dataLine, line);
				line.setCklnSeq(i);
				datalines.add(line);
				return datalines;
			}
			// 剩余的长度如果大于0
			if (strEnd > 0) {
				if (strEnd > 40)// 判断剩余长度是否还大于40
					strEnd = 40;// 如果大于则直接赋值40
				// 对开始位置进行赋值
				start = end;// 开始位置转换为结束位置
				end = start + strEnd;// 结束位置进行赋值 结束位置等于开始位置加上剩余的长度
				line = new TableDataLineEbo();
				BeanUtils.copyProperties(dataLine, line);
				line.setInputVl(inputVl.substring(start, end));
				line.setCklnSeq(i);
			}
			datalines.add(line);// 添加入集合
			// 字符串总长度 减去截取的结束长度等于字符串剩余的长度
			strEnd = strLen - end;
			i++;
		} while (strEnd > 0);

		return datalines;
	}

	// 表标题列表
	public List<TableCfgHeadEbo> listCfgHeadEbo(String code, String name, int typeId) throws DataException {
		if (typeId != 0) {
			SysRefValueEbo sysRefValueEbo = baseService.getRefVlById(typeId);
			if (sysRefValueEbo == null)
				throw new DataException("该表类型不存在，typeid=" + typeId);
		}
		List<TableCfgHeadEbo> headList = tableDao.listCfgHeadEbo(code, name, typeId);
		return headList;
	};

	// 列表配置行
	public List<TableCfgHeadDto> listTableCfgHead(int id, String code) throws DataException {

		List<TableCfgHeadDto> cfgHeads = new ArrayList<TableCfgHeadDto>();
		List<TableCfgInnerEbo> cfgs = tableDao.listTableCfg(id, code, null);
		TableCfgHeadDto cfgHead = null;
		for (TableCfgInnerEbo cfg : cfgs) {
			cfgHead = new TableCfgHeadDto();
			BeanUtils.copyProperties(cfg, cfgHead);
			boolean j = true;
			for (TableCfgHeadDto head : cfgHeads) {
				if (head.getCfgId() == cfg.getCfgId()) {
					j = false;
				}
			}
			if (j)
				cfgHeads.add(cfgHead);
		}
		return cfgHeads;
	};

	public void updateCfgHead(String code, String name, String flag) throws DataException {
		TableCfgHeadEbo headEbo = getCfgHeadByCode(code);
		if (headEbo == null)
			throw new DataException("该表信息不存在，code =" + code);
		if (Util.isNotNull(flag)) {
			if (flag.equals("Y"))
				flag = Constant.FLAG_YES;
			else if (flag.equals("N"))
				flag = Constant.FLAG_NO;
			else
				throw new DataException("输入的flag格式有问题");
		}
		tableDao.updateCfgHead(code, name, flag);
	};

	public List<TableDataLineDto> listDataLine(int checkId) throws DataException {
		List<TableDataLineDto> dataLines = new ArrayList<TableDataLineDto>();
		List<TableDataLineInnerEbo> listDataLine = tableDao.listDataLine(-1, -1, checkId);
		if (listDataLine.isEmpty()) {
			return dataLines;
		}
		DataLineDto dataRef = null;
		TableDataLineDto dataLine = null;
		Hashtable<Integer, TableDataLineInnerEbo> dataHash = new Hashtable<Integer, TableDataLineInnerEbo>();
		for (TableDataLineInnerEbo line : listDataLine) {
			dataRef = new DataLineDto();
			dataLine = new TableDataLineDto();
			BeanUtils.copyProperties(line, dataLine);
			dataRef.setRefId(line.getRefId());
			dataRef.setRefVl(line.getRefVl());
			dataRef.setInputVl(line.getInputVl());
			dataRef.setCklnSeq(line.getCklnSeq());
			if (line.getMultiRef().equals("N")) {
				// 单选 输入
				dataLine.getLines().add(dataRef);
				dataLines.add(dataLine);
			} else {
				// 多选
				if (!dataHash.containsKey(line.getRefTypeId())) {
					dataLine.getLines().add(dataRef);
					dataLines.add(dataLine);
				} else {
					for (TableDataLineDto li : dataLines) {
						if (li.getRefTypeId() != 0 && li.getRefTypeId() == line.getRefTypeId())
							li.getLines().add(dataRef);
					}
				}
				dataHash.put(line.getRefTypeId(), line);
			}
		}
		return dataLines;
	}

	// 根据tableTypeId获取表格配置头
	public List<TableCfgHeadDto> listCfgHeadByTypeId(int tableTypeId, int page, int paging) throws DataException {
		List<TableCfgHeadEbo> cfgHeads = null;
		if (tableTypeId != 0) {
			cfgHeads = tableDao.listCfgHeadByTypeId(tableTypeId, page, paging);
		}
		List<TableCfgHeadDto> cfgHeadDtos = new ArrayList<TableCfgHeadDto>();
		for (TableCfgHeadEbo cfgHead : cfgHeads) {
			TableCfgHeadDto tableCfgHeadDto = new TableCfgHeadDto();
			tableCfgHeadDto.setCfgId(cfgHead.getId());
			tableCfgHeadDto.setCfgCode(cfgHead.getCode());
			tableCfgHeadDto.setCfgName(cfgHead.getName());
			cfgHeadDtos.add(tableCfgHeadDto);
		}
		return cfgHeadDtos;
	}

	// 根据tableTypeId获取表格配置头
	public int getTotalFollowupPage(int tableTypeId) throws DataException {
		int total = 0;
		if (tableTypeId != 0) {
			total = tableDao.getTotalFollowupPage(tableTypeId);
			return total;
		} else {
			throw new Error("随访表中没有表格");
		}
	}

	public void updateDataHead(int id, List<TableDataLineEto> dataLines) throws DataException {
		TableDataHeadEbo dataHead = getDataHeadById(id);
		if (dataHead == null)
			return;
		// 做空值验证
		for (TableDataLineEto dataLine : dataLines)
			DataValUtil.dataValidation(dataLine.getClass(), dataLine);
		// 保存需要计算的 表格 <数据表头id,总分>
		 Map<Integer, Double> headCfgMap = new HashMap<Integer, Double>();
		for (TableDataLineEto dataLine : dataLines) {
			TableCfgLineEbo cfgLine = tableDao.getCfgLineById(0, dataLine.getLineCode(), dataHead.getCfgId(), -1, -2, -2);
			if (cfgLine == null)
				throw new DataException("[err]对应的配置行不存在id为：" + dataLine.getLineCode());
			if (Constant.DATA_LINE_REF.equals(dataLine.getVlType())) { // 该列是参照
				// key为表头数据id 唯一性
				if (headCfgMap.containsKey(dataHead.getId())) { // 并非该表首列
					SysRefValueEbo refValue = baseService.getRefVlById(dataLine.getRefId());
					double value = Util.parseDoubleVl(refValue.getRefVl(), 0.0);
					double total = headCfgMap.get(dataHead.getId()) + value;
					headCfgMap.put(dataHead.getId(), total);
				} else {
					SysRefValueEbo refValue = baseService.getRefVlById(dataLine.getRefId());
					double value = Util.parseDoubleVl(refValue.getRefVl(), 0);
					headCfgMap.put(dataHead.getId(), value);
				}
			}
		}
		double total = headCfgMap.get(dataHead.getId());
		tableDao.updateDataHead(id, total);
	}

}
