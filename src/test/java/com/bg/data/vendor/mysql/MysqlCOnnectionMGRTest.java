package com.bg.data.vendor.mysql;



import org.junit.jupiter.api.Test;

import com.bg.data.vendor.mysql.MysqlConnectMGR;

public class MysqlCOnnectionMGRTest {

	@Test
	public void testInstance()
	{
		MysqlConnectMGR.getInstance().querySelect("select * from employees as e1 inner join salaries as s1 on s1.emp_no=e1.emp_no where hire_date > \'1997-01-01\'");;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			MysqlConnectMGR.getInstance().stop();
		}
	}
}
