package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.FollowupMainDto;
import com.kjplus.eto.FollowupMainEto;
import com.kjplus.eto.FollowupResEto;
import com.kjplus.model.CodeRepoEbo;
import com.kjplus.model.FollowupExamEbo;
import com.kjplus.model.FollowupMainEbo;
import com.kjplus.model.FollowupRecEbo;
import com.kjplus.qto.FollowupMainQto;
import com.ybk.exception.DataException;

public interface IFollowupService {

	// 通过id，code获取 CodeRepoEbo 对象
	public CodeRepoEbo getCodeRepoByIdOrCode(Integer id, String code) throws DataException;

	// 通过id，code获取 FollowupExamEbo 对象
	public FollowupExamEbo getFollowupExamByIdOrCode(Integer id, String code) throws DataException;

	// 通过id，code获取 FollowupRecEbo 对象
	public FollowupRecEbo getFollowupRecByIdOrCode(Integer id, String code) throws DataException;

	// 通过id，code获取 FollowupMainEbo 对象
	public FollowupMainEbo getFollowupMainByIdOrCode(Integer id, String code) throws DataException;

	// 通过orgId，selfCode获取 FollowupMainEbo 对象
	public FollowupMainEbo getFollowupMainBySelfCode(String selfCode, int orgId) throws DataException;

	// 添加 FollowupMainEbo 对象
	public FollowupMainEbo addFollowupMain(FollowupMainEto fm) throws DataException;

	// 添加随访结论记录
	public FollowupMainEbo addOrModifyFollowupRes(FollowupResEto fuResEto) throws DataException;

	// 列表随访记录 　
	public List<FollowupMainDto> listFollowupMainDto(FollowupMainQto fupQto, int page, int paging) throws DataException;

	// 随访记录总数
	public int getTotalFollowupMain(FollowupMainQto fupQto) throws DataException;

	// 修改随访记录
	public void updateFollowupMain(FollowupMainEbo fMainEbo) throws DataException;

}
