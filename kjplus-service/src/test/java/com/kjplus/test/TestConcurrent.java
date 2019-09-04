package com.kjplus.test;


//import java.util.concurrent.CountDownLatch;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
// (并发测试类)
public class TestConcurrent {

	// 线程数
	//private static final int THREAD_NUM = 10;

	// java工具类并发包
	//private CountDownLatch cdl = new CountDownLatch(THREAD_NUM);

	// 对命令工厂进行并发测试
	/*@Test
	public void testAddPerson() {

		try {

			for (int i = 0; i < 10; i++) {
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
				// 实例成功线程 在此等待
				cdl.await();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}*/
}
