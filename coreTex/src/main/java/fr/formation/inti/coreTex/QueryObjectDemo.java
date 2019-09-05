package fr.formation.inti.coreTex;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import fr.formation.inti.entities.Employee;
import fr.formation.inti.service.HibernateUtils;

public class QueryObjectDemo {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		 
	       Session session = factory.getCurrentSession();
	 
	       try {
	            
	// Toutes les actions vers la DB doivent être exécutées en une seule requête.
	    	   //Accès à la DB.
	           session.getTransaction().begin();
	 
	           
	           // Crée une instruction SQL.
	           // Equivaut à "Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO"
	           
	           String sql = "Select e from " + Employee.class.getName() + " e "
	                   + " order by e.empName, e.empNo ";
	 
	   
	           // Création de la requête
	           Query<Employee> query = session.createQuery(sql);
	 
	    
	           // Exécution de la requête.
	           List<Employee> employees = query.getResultList();
	 
	           for (Employee emp : employees) {
	               System.out.println("Emp: " + emp.getEmpNo() + " : "
	                       + emp.getEmpName());
	           }

	           // Fermeture de l'accès à la DB
	           session.getTransaction().commit();
	           
	       } catch (Exception e) {
	           e.printStackTrace();
	           // Rollback in case of an error occurred.
	           session.getTransaction().rollback();
	       }

	}

}
