package org.ybk.basic.util;

import java.util.ArrayList;
import java.util.List;

import com.ybk.bean.PageTag;

public class PageUtil {
	public static void main(String[] args) {
		/*testPage(1,1);
		testPage(3,3);
		testPage(1,10);*/
		testPage(1,3);
		testPage(2,3);
		testPage(3,3);
		
		/*testPage(8,10);
		testPage(9,10);
		testPage(10,10);*/

		/*testPage(3,10);
		testPage(3,4);*/
	}
	
	public static void testPage(int curpage,int maxpage){
		System.out.print("cur：" + curpage);
		System.out.println(",max:" + maxpage);
		List<PageTag> lists = getPages(curpage, maxpage);
		for (int i=0;i<lists.size();i++) {
			System.out.println("\t"+lists.get(i));
		}
		System.out.println();
	}

	//根据获得当前页码和最大页面数量，获得可以在页面显示的页面编码
	public static List<PageTag> getPages(Integer curpage,Integer maxpage) {
		List<PageTag> pages = new ArrayList<PageTag>();
		if(curpage>maxpage)
			return pages;
		
		PageTag first = null;
		PageTag prev = null;
		PageTag next = null;
		PageTag last = null;
		PageTag curr = null;
		//判断前一页和首页
		if (curpage>2){
			first = new PageTag();
			first.setName(PageTag.FIRST_PAGE);
			first.setCaption(PageTag.FIRST_CAPTION);
			first.setFlag("Y");
			first.setPage(1);
		
			prev = new PageTag();
			prev.setName(PageTag.PREV_PAGE);
			prev.setCaption(PageTag.PREV_CAPTION);
			prev.setFlag("Y");
			prev.setPage(curpage-1);
		}else if(curpage==2){
			first = new PageTag();
			first.setName(PageTag.FIRST_PAGE);
			first.setCaption(PageTag.FIRST_CAPTION);
			first.setFlag("Y");
			first.setPage(1);

			prev = new PageTag();
			prev.setName(PageTag.PREV_PAGE);
			prev.setCaption(PageTag.PREV_CAPTION);
			prev.setFlag("N");
			prev.setPage(curpage-1);
		}else if(curpage<2){
			prev = new PageTag();
			prev.setName(PageTag.PREV_PAGE);
			prev.setCaption(PageTag.PREV_CAPTION);
			prev.setFlag("N");
			prev.setPage(curpage-1);
			
			first = new PageTag();
			first.setName(PageTag.FIRST_PAGE);
			first.setCaption(PageTag.FIRST_CAPTION);
			first.setFlag("N");
			first.setPage(1);
		}
		
		curr = new PageTag();
		curr.setName(PageTag.CURR_PAGE);
		curr.setCaption("当前页");
		curr.setFlag("Y");
		curr.setPage(curpage);
		
		//判断后一页和尾页
		if(maxpage>=curpage+2){
			next = new PageTag();
			next.setName(PageTag.NEXT_PAGE);
			next.setCaption(PageTag.NEXT_CAPTION);
			next.setFlag("Y");
			next.setPage(curpage+1);
	
			last = new PageTag();
			last.setName(PageTag.LAST_PAGE);
			last.setCaption(PageTag.LAST_CAPTION);
			last.setFlag("Y");
			last.setPage(maxpage);	
		}else if(curpage + 1 == maxpage){
			next = new PageTag();
			next.setName(PageTag.NEXT_PAGE);
			next.setCaption(PageTag.NEXT_CAPTION);
			next.setFlag("N");
			next.setPage(curpage+1);
			
			last = new PageTag();
			last.setName(PageTag.LAST_PAGE);
			last.setCaption(PageTag.LAST_CAPTION);
			last.setFlag("Y");
			last.setPage(maxpage);
		}else if(curpage == maxpage){
			next = new PageTag();
			next.setName(PageTag.NEXT_PAGE);
			next.setCaption(PageTag.NEXT_CAPTION);
			next.setFlag("N");
			next.setPage(curpage+1);
			
			last = new PageTag();
			last.setName(PageTag.LAST_PAGE);
			last.setCaption(PageTag.LAST_CAPTION);
			last.setFlag("N");
			last.setPage(maxpage);
		}
		pages.add(first);
		pages.add(prev);
		//pages.add(curr);
		pages.add(next);
		pages.add(last);
		return pages;
	}
}
