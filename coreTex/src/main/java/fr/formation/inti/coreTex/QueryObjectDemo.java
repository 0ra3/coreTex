package fr.formation.inti.coreTex;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import fr.formation.inti.beans.ShortEmpInfo;
import fr.formation.inti.entities.Department;
import fr.formation.inti.entities.Employee;
import fr.formation.inti.service.HibernateUtils;

public class QueryObjectDemo {
	
	 public static Department getDepartment(Session session, String deptNo) {
	        String sql = "Select d from " + Department.class.getName() + " d "
	                + " where d.deptNo= :deptNo ";
	        
	        Query<Department> query = session.createQuery(sql);
	        query.setParameter("deptNo", deptNo);
	        return (Department) query.getSingleResult();
	    }
	 
	    public static Employee getEmployee(Session session, Long empId) {
	        String sql = "Select e from " + Employee.class.getName() + " e "
	                + " where e.empId= :empId ";
	        
	        Query<Employee> query = session.createQuery(sql);
	        query.setParameter("empId", empId);
	        return (Employee) query.getSingleResult();
	    }

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		 
	       Session session = factory.getCurrentSession();
	 
	       try {
	            
	// Toutes les actions vers la DB doivent être exécutées en une seule requête.
	    	   //Accès à la DB.
	           session.getTransaction().begin();
	 
	           
	           Department dept = getDepartment(session, "D10");
	           Set<Employee> emps = dept.getEmployees();
	 
	            System.out.println("Dept Name: " + dept.getDeptName());
	            for (Employee emp : emps) {
	                System.out.println("  Emp name: " + emp.getEmpName());
	            }
	 
	            Employee emp = getEmployee(session, 7839L);
	            System.out.println("Emp Name: " + emp.getEmpName());
	            
	            
	           // Fermeture de l'accès à la DB
	           session.getTransaction().commit();
	           
	       } catch (Exception e) {
	           e.printStackTrace();
	           // Rollback in case of an error occurred.
	           session.getTransaction().rollback();
	       }

	}

}
