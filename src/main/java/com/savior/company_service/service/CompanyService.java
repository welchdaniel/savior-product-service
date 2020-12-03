package com.savior.company_service.service;

import com.savior.company_service.dao.CompanyDao;
import com.savior.company_service.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyDao companyDao;

    @Autowired
    public CompanyService(@Qualifier("mysql") CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public void insertCompany(Company company) {
        companyDao.insertCompany(company);
    }

    public List<Company> getAllCompanies() {
        return companyDao.selectAllCompanies();
    }

    public Optional<Company> getCompanyById(String id) {
        return companyDao.selectCompanyById(id);
    }

    public void deleteCompany(String id) {
        companyDao.deleteCompanyById(id);
    }

    public void updateCompany(String id, Company newCompany) {
        companyDao.updateCompanyById(id, newCompany);
    }

}
