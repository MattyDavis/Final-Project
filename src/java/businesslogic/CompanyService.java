package businesslogic;

import dataaccess.CompanyDB;
import domainmodel.Company;
import java.util.List;

public class CompanyService {

    private CompanyDB companyDB;

    public CompanyService() {
        companyDB = new CompanyDB();
    }
    

    
    public int getCompanyID(String name) throws Exception
    {
        return companyDB.getCompanyId(name);
    }
    
    public Company getCompany(int id) throws Exception
    {
        return companyDB.getCompany(id);
    }
    
        
    


    public List<Company> getAll() throws Exception {
        return companyDB.getAll();
    }

    public int update(int companyId, String companyName) throws Exception {
        
        Company company = new Company(companyId,companyName);
        return companyDB.update(company);
    }

    public int delete(int id) throws Exception {
        Company deletedCompany = companyDB.getCompany(id);
        return companyDB.delete(deletedCompany);
    }

    public int insert(String companyName) throws Exception {
        Company company = new Company(companyName);
        return companyDB.insert(company);
    }
}
