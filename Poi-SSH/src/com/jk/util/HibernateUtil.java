package com.jk.util;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateUtil extends HibernateDaoSupport{
	public Session getSessionBy() {
		return (Session) this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				return arg0;
			}
		});
	}
}
