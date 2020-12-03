package com.savior.company_service.api;

import com.savior.company_service.utils.exception.CompanyDoesNotExistException;

import com.savior.company_service.model.Company;
import com.savior.company_service.service.CompanyService;
import com.savior.company_service.utils.exception.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RequestMapping("api/v1/company")
@RestController
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @PostMapping
  public ResponseEntity insertCompany(@RequestBody Company company) {
    companyService.insertCompany(company);
    // TODO: return created company as JsonExchange
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity getAllCompanies() {
    var storedCompanies = companyService.getAllCompanies();
    return new ResponseEntity(storedCompanies, HttpStatus.OK);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity getCompanyById(@PathVariable("id") String id) {
    try {
      var storedCompany = companyService.getCompanyById(id);
      return new ResponseEntity(storedCompany, HttpStatus.OK);
    } catch (CompanyDoesNotExistException cdne) {
      throw new NotFoundException(cdne.getMessage());
    }

  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteCompanyById(@PathVariable("id") String id) {
    companyService.deleteCompany(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PutMapping(path = "{id}")
  public ResponseEntity updateCompany(@PathVariable("id") String id, @RequestBody Company company) {
    companyService.updateCompany(id, company);
    return new ResponseEntity(HttpStatus.OK);
  }
}
