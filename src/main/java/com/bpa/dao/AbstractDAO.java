package com.bpa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpa.model.User;

public abstract class AbstractDAO {
	    
		@Autowired
		private SessionFactory sessionFactory;	
		
		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}
		
		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}
		
	    public User getLoginVerificationByEmail(User user)
		{
			Session session = sessionFactory.openSession();
			List list = null;
			try {
				
				Criteria criteria = session.createCriteria(User.class);
				
				if ((user.getUserName() != null) && (user.getPassword() != null)){
					criteria.add(Restrictions.eq("userName", user.getUserName()).ignoreCase());
					criteria.add(Restrictions.eq("userPassword", user.getPassword()));
					
				}
				
				list = criteria.list();
				

			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				if (session != null) {
					try {
						session.close();
					} catch (HibernateException e) {
						e.printStackTrace();
					}
				}
			}

			if (list.size() != 0)
				return (User)list.get(0);
			else
				return null;
		}
}
