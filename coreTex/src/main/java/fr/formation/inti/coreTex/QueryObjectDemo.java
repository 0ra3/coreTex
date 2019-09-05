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
	       Employee emp = null;
	       try {
	           
	       session.getTransaction().begin();
	 
	       Long MaxEmpId= DataUtils.getMaxEmpId(session);
	       Long empId= MaxEmpId  +1;
	           
	       //Obtenir un objet persistant
	       
	       department = DataUtils.findDepartment(session, "D10");
	       
	       
	       // Creation d'un objet transitoire
	       
           emp = new Employee();
           emp.setEmpId(empId);
           emp.setEmpNo("E" + empId);
           emp.setEmpName("Name " + empId);
           emp.setJob("Coder");
           emp.setSalary(1000f);
           emp.setManager(null);
           emp.sethireDate(new Date());
           emp.setDepartment(department);
           
           //Persistance sur l'objet emp. Pas de lien avec la DB.
           session.persist(emp);
	            
	           // Insertion dans la DB
	           session.getTransaction().commit();
	           
	       } catch (Exception e) {
	           e.printStackTrace();
	           // Rollback in case of an error occurred.
	           session.getTransaction().rollback();
	       }
	       
	       System.out.println("Emp No: " + emp.getEmpNo());
	}

}
