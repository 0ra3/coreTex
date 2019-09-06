package fr.formation.inti.bean;

import org.hibernate.Session;

import fr.formation.inti.entities.Etudiant;

public class Repository {

	public static Etudiant save(Session s, Etudiant e){
		s.getTransaction().begin();
		s.save(e);
		s.getTransaction().commit();
		return e;
	}
	
	public static Etudiant findById( Session s, int id) {
		s.getTransaction().begin();
		Etudiant e= s.find(Etudiant.class, id);
		s.getTransaction().commit();
		return e;
		
	}
}
