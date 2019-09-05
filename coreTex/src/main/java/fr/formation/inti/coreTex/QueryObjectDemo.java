package fr.formation.inti.coreTex;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import fr.formation.inti.beans.ShortEmpInfo;
import fr.formation.inti.entities.Department;
import fr.formation.inti.entities.Employee;
import fr.formation.inti.service.HibernateUtils;
import fr.formation.inti.utils.DataUtils;

public class QueryObjectDemo {
	
	public static void main(String[] args) {
		  SessionFactory factory = HibernateUtils.getSessionFactory();
		  
	       Session session = factory.getCurrentSession();
	       Department department = null;
	 
	       try {
	           session.getTransaction().begin();
	 
	           System.out.println("- Finding Department deptNo = D10...");
	       
	           // Récupératiion d'un objet persistant.
	           department = DataUtils.findDepartment(session, "D10");
	 
	           System.out.println("- First change Location");
	      
	            
	           // Modifier un objet persistant.
	           department.setLocation("Chicago " + System.currentTimeMillis());
	            
	           System.out.println("- Location = " + department.getLocation());
	 
	           System.out.println("- Calling flush...");
	    
	           // session.flush() permet d'insérer les changement dans la DB.
	           session.flush();
	 
	           System.out.println("- Flush OK");
	 
	           System.out.println("- Second change Location");
	            
	          
	        // -----------------------------------------------------------------\\
	           
	           
	           department.setLocation("Chicago " + System.currentTimeMillis());
	  
	           // Print out location
	           System.out.println("- Location = " + department.getLocation());
	 
	           System.out.println("- Calling commit...");
	  
	           // Commit
	           session.getTransaction().commit();
	 
	           System.out.println("- Commit OK");
	           
	       } catch (Exception e) {
	           e.printStackTrace();
	           session.getTransaction().rollback();
	       }
	  
	       // Create the session after it had been closed earlier
	       // (Cause by commit or update)
	       session = factory.getCurrentSession();
	       try {
	           session.getTransaction().begin();
	 
	           System.out.println("- Finding Department deptNo = D10...");
	            
	           // Recherche du département
	           department = DataUtils.findDepartment(session, "D10");
	  

	           System.out.println("- D10 Location = " + department.getLocation());
	 
	           session.getTransaction().commit();
	       } catch (Exception e) {
	           e.printStackTrace();
	           session.getTransaction().rollback();
	       }
	   }
	    
	}
