package com.kjplus.test;

import java.util.List;

import com.kjplus.dao.IFileRepoDao;
import com.kjplus.eto.FileRepoEto;
import com.kjplus.model.FileRepoEbo;
import com.kjplus.model.inner.FileRepoInnerEbo;
import com.kjplus.service.IFileRepoService;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestFileRepo {

	@Autowired
	private IFileRepoDao  fileRepoDao;
	@Autowired
	private IFileRepoService  fileRepoService;
	
	@Test
	public void testAddFileRepoEbo() {
		FileRepoEto file = new FileRepoEto();
		int srvId = 1;
		file.setSrvId(srvId);
		int mainId = 1;
		file.setMainId(mainId);
		String mainType = "FPLE";
		file.setMainType(mainType);
		String fileType = "PIC";
		file.setFileType(fileType);
		String filePath = "asas/asdas/11/11/1.jpg"; 
		file.setFilePath(filePath);
		try {
			FileRepoEbo fileRepo = fileRepoService.addFileRepoEbo(file);
			System.out.println("添文件成功：ID= "+fileRepo.getId());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	@Test
	public void testGetFileRepoEboByIdOrCode() {
		int id = 1;
		String code = "";
		try {
			FileRepoEbo file = fileRepoService.getFileRepoByIdOrCode(id, code);
			System.out.println(file);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testListFileRepo() {
		int srvId = 1;
		int mainId = 1;
		String mainType = "FPLE";
		String fileType = "PIC";
		int page = 0;
		int paging = 2;
		try {
			List<FileRepoInnerEbo> listFile = fileRepoService.listFileRepoInner(srvId, mainId, mainType, fileType,page,paging);
			for (FileRepoInnerEbo file : listFile) {
				System.out.println(file);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
}
