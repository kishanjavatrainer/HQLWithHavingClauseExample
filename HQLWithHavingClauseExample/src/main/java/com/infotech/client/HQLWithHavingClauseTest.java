package com.infotech.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.infotech.util.HibernateUtil;

public class HQLWithHavingClauseTest {

	public static void main(String[] args) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String HQL = "SELECT dept.departmentName,SUM(emp.salary) FROM Department dept"
					+ " LEFT JOIN dept.employees emp GROUP BY dept"
					+ " HAVING SUM(emp.salary)>200000";
			
			Query<Object[]> query = session.createQuery(HQL, Object[].class);
			List<Object[]> list = query.list();
			for (Object[] objects : list) {
				String departmentName=(String)objects[0];
				Double totalSalByDept = (Double)objects[1];
				System.out.println("Department Name:"+departmentName);
				System.out.println("Total Sal By Dept:"+totalSalByDept);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null)
				session.close();
		}
	}

}
