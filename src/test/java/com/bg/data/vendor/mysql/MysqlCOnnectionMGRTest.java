package com.bg.data.vendor.mysql;

import org.junit.Test;
import com.bg.data.vendor.mysql.MysqlConnectMGR;

public class MysqlCOnnectionMGRTest {

	@Test
	public void testInstance()
	{
		MysqlConnectMGR.getInstance();
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
