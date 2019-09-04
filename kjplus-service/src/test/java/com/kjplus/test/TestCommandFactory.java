package com.kjplus.test;


import java.util.concurrent.CountDownLatch;

import com.mq.call.CommandFactory;
import com.mq.message.MsgCommand;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ybk.basic.http.HttpUtil2;

import com.kjplus.service.IChatMsgService;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
// 并发情况下测试消息命令工厂(并发测试类)
public class TestCommandFactory {

	// 线程数
	private static final int THREAD_NUM = 1000;
	// java工具类并发包
	private CountDownLatch cdl = new CountDownLatch(THREAD_NUM);

	@Autowired
	private IChatMsgService chatMsgService;

	// private static AppointEto appiont = new AppointEto("123", "CZ-VAR",
	// "12345", "69822936",Constant.APPIONT_TYPE_STAFF);

	@Test
	public void testHttp() {
		String url = "//http://localhost:8181/kjplus-console/testmsg.html";
		for (int i = 0; i < 1000; i++){
			String str=HttpUtil2.doGet(url);
			System.out.println(str);
		}

	}

	// 对命令工厂进行并发测试
	@Test
	public void testAddPerson() {

		try {

			for (int i = 0; i < 1000; i++) {
				new Thread(new GetCmd()).start();
				// 线程记数
				cdl.countDown();
			}
			// 阻塞主线程
			Thread.currentThread().join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class GetCmd implements Runnable {

		public void run() {
			try {
				// 实例还线程 在此等待
				cdl.await();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				MsgCommand comm = CommandFactory.getMsgCommand();
				System.out.println(comm);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}
}
