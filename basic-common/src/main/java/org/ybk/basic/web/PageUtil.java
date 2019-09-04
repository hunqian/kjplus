package org.ybk.basic.web;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
	
	//获得七段分页
	//curPage当前页，从1开始
	public static List<Page> calc7Paging(int totalPage,int curPage){
		List<Page> pages = new ArrayList<Page>();
		Page p = null;
		
		if(totalPage <= 7){
			//添加全部
			for(int i=1;i<=totalPage;i++){
				if(curPage == i)
					pages.add(new Page(i,"",false));
				else
					pages.add(new Page(i,"",true));
			}
		}else{
			//上一页
			if(curPage == 1){
				p = new Page(1,"上一页","",false);
			}else{
				p = new Page(curPage-1,"上一页","",true);
			}
			pages.add(p);
			
			if(curPage<5){
				for(int i=1;i<=5;i++){
					if(curPage == i)
						pages.add(new Page(i,"",false));
					else
						pages.add(new Page(i,"",true));
				}
				pages.add(new Page(-1,"...","",false));
				pages.add(new Page(totalPage,"",true));
			}else if(curPage>totalPage-4){
				pages.add(new Page(1,"",true));
				pages.add(new Page(-1,"...","",false));
				for(int i=totalPage-4;i<=totalPage;i++){
					if(curPage == i)
						pages.add(new Page(i,"",false));
					else
						pages.add(new Page(i,"",true));
				}
			}else{
				pages.add(new Page(1,"",true));
				pages.add(new Page(-1,"...","",false));
				pages.add(new Page(curPage-1,"",true));
				pages.add(new Page(curPage,"",false));
				pages.add(new Page(curPage+1,"",true));
				pages.add(new Page(-1,"...","",false));
				pages.add(new Page(totalPage,"",true));
			}
			if(curPage == totalPage)
				pages.add(new Page(curPage+1,"下一页","",false));
			else
				pages.add(new Page(curPage+1,"下一页","",true));
		}
		return pages;
	}
	
	public static void main(String[] argc){
		//System.out.println(PageUtil.calc7Paging(1,2));
		//System.out.println(PageUtil.calc7Paging(2,2));
		//System.out.println(PageUtil.calc7Paging(3,2));
		/*System.out.println(PageUtil.calc7Paging(4,2));
		System.out.println(PageUtil.calc7Paging(5,2));
		System.out.println(PageUtil.calc7Paging(6,2));
		System.out.println(PageUtil.calc7Paging(7,2));*/
		//System.out.println(PageUtil.calc7Paging(8,2));
		
		System.out.println(PageUtil.calc7Paging(36,6));
		System.out.println(PageUtil.calc7Paging(9,8));
	}
}
