package dataaccess;

import domainmodel.Company;
import domainmodel.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CompanyDB {

    public int insert(Company company) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(company);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, "Cannot insert " + company.toString(), ex);
            throw new NotesDBException("Error inserting company");
        } finally {
            em.close();
        }
    }
    
    public int getCompanyId(String companyname) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
        int id = em.createNamedQuery("Company.findByCompanyName").setParameter("companyName", companyname).getFirstResult();
            return id;
        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, "Cannot read companies", ex);
            throw new NotesDBException("Error getting Company");
        } finally {
            em.close();
        }
    }
    
    public Company getCompany(int id) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Company company = em.find(Company.class, id);
            return company;
        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, "Cannot read companies", ex);
            throw new NotesDBException("Error getting Company");
        } finally {
            em.close();
        }
    }

    public int update(Company company) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(company);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, "Cannot update " + company.toString(), ex);
            throw new NotesDBException("Error updating company");
        } finally {
            em.close();
        }
    }

    public List<Company> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            List<Company> companies = em.createNamedQuery("Company.findAll", Company.class).getResultList();
            return companies;
        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, "Cannot read companies", ex);
            throw new NotesDBException("Error getting Users");
        } finally {
            em.close();
        }
    }


    public int delete(Company company) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.remove(em.merge(company));
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, "Cannot delete " + company.toString(), ex);
            throw new NotesDBException("Error deleting company");
        } finally {
            em.close();
        }
    }
}
