package businesslogic;

import dataaccess.CompanyDB;
import domainmodel.Company;
import java.util.List;

public class CompanyService {

    private CompanyDB companyDB;

    public CompanyService() {
        companyDB = new CompanyDB();
    }

    public Company get(String company) throws Exception {
        return companyDB.getCompany(company);
    }

    public List<Company> getAll() throws Exception {
        return companyDB.getAll();
    }

    public int update(String companyName) throws Exception {
        Company company = companyDB.getCompany(companyName);
        company.setCompanyName(companyName);
        return companyDB.update(company);
    }

    public int delete(String companyName) throws Exception {
        Company deletedCompany = companyDB.getCompany(companyName);
        return companyDB.delete(deletedCompany);
    }

    public int insert(String companyName) throws Exception {
        Company company = new Company(companyName);
        return companyDB.insert(company);
    }
}
