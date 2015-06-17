package com.capital.dragon.dto;

import java.util.ArrayList;
import java.util.Collection;




import java.util.jar.Attributes.Name;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



//@Entity (name="USER_DETAILS")
@Entity
@NamedQuery(name="UserDetails.byId",query="from UserDetails where userId=?")
@NamedNativeQuery(name="UserDetails.byName",query="select * from user_details where user_name=?",resultClass = UserDetails.class)
@Table(name="USER_DETAILS")
@org.hibernate.annotations.SelectBeforeUpdate
public class UserDetails {
	@Id @GeneratedValue
	private int userId;
	@Column (name="user_name")	
	private String userName;
	

	//@ManyToMany (mappedBy="users")
	@OneToMany(cascade=CascadeType.PERSIST) // all connected objects a created 	
	private Collection<Vehicle> vehicles = new ArrayList<Vehicle>();
	
	

	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	@Lob  
	private String descritpion;	
	 
	public String getDescritpion() {
		return descritpion;
	}
	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
	}
		
	@Column (name="user_id")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
