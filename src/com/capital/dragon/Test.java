package com.capital.dragon;


import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;









import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.capital.dragon.dto.FourWheeler;
import com.capital.dragon.dto.TwoWheeler;
import com.capital.dragon.dto.UserDetails;
import com.capital.dragon.dto.Vehicle;

public class Test {
	
	public static void main(String[] args){
		UserDetails user = new UserDetails();
		user.setUserName("vova");
		Configuration configuration = new Configuration().configure();		
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().		
		applySettings(configuration.getProperties());	
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		Session session = factory.openSession();		
		
		session = factory.openSession();		
		session.beginTransaction();
		user = (UserDetails)session.get(UserDetails.class, 4);		
		session.getTransaction().commit();
		session.close();
		
		//user.setUserName("Vova the Great");
		session = factory.openSession();		
		session.beginTransaction();				
		session.update(user);
		session.getTransaction().commit();
		session.close();
		
		session = factory.openSession();		
		session.beginTransaction();
		//Query query  = session.createQuery("from UserDetails where userName=:userName");
		Query query  = session.getNamedQuery("UserDetails.byName");		
		
		String userInput = "vova";
		query.setString(0, userInput);
		//query.setInteger(0, 5);
		//Query query  = session.createQuery("select userName from UserDetails where userId>1");
		//Query query  = session.createQuery("select max(userId) from UserDetails");
		//Query query  = session.createQuery("select new map(userId,userName) from UserDetails where userId>1");
		//query.setFirstResult(1);
		query.setMaxResults(5);
		
		
		//List<Map<Integer, String>> listUsers = (List<Map<Integer, String>>) query.list();
		UserDetails exampleUser = new UserDetails();
		exampleUser.setUserId(4);
		exampleUser.setUserName("vov%");
		
		Example example = Example.create(exampleUser).enableLike();
		Criteria criteria = session.createCriteria(UserDetails.class)
		//		.setProjection(Projections.property("userId"));
		//		.setProjection(Projections.max("userId"));
				//.setProjection(Projections.max("userId"));
				//.addOrder(Order.desc("userId"));
				.add(example);
		
//		criteria.add(Restrictions.eq("userName", "vova"))
//			    .add(Restrictions.gt("userId", 5));
//		
		
		List<UserDetails> listUsers = (List<UserDetails>) criteria.list();
		//List<Integer> listUsers = (List<Integer>) criteria.list();
		System.out.println(listUsers.size());
		
		
		
		session.getTransaction().commit();		
		session.close();		
		for (UserDetails u:listUsers){
			System.out.println(u.getUserName());		
			
		}
		
				
	}
	

}
