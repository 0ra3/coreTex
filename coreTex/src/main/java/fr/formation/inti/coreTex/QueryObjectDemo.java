package fr.formation.inti.coreTex;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.formation.inti.entities.Etudiant;

public class QueryObjectDemo {
	
	public static void main(String[] args) {
		
		// Création de la session\\
		
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml")
							.addAnnotatedClass(Etudiant.class).buildSessionFactory();
		
		
		//Création d'un étudiant\\
		
		Etudiant e = new Etudiant();
		e.setNom("Liddle");
		e.setPrenom("Alice");
		e.setEmail("Londerland@gmail.com");
		
		//Création de la session\\
		Session session = sf.getCurrentSession();
		save(session,e);
		
		Etudiant e1= findById(session, 2);
		System.out.println(e1);
		
	}	
	
	

}
