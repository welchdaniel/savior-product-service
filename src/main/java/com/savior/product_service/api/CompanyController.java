package com.savior.product_service.api;

import com.savior.product_service.model.Product;
import com.savior.product_service.service.ProductService;
import com.savior.product_service.utils.exception.service.CompanyDoesNotExistException;

import com.savior.product_service.model.Company;
import com.savior.product_service.service.CompanyService;
import com.savior.product_service.utils.exception.http.NotFoundException;
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

  @Autowired
  private ProductService productService;

  @PostMapping
  public ResponseEntity createCompany(@RequestBody Company company) {
    Company storedCompany = companyService.insertCompany(company);
    return new ResponseEntity(storedCompany, HttpStatus.CREATED);
  }

  @PostMapping(path = "/{id}/product")
  public ResponseEntity createProduct(@PathVariable("id") String companyId, @RequestBody Product product) {
    // TODO: store product with company id
    Product storedProduct = productService.insertProduct(product);
    return new ResponseEntity(storedProduct, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity getAllCompanies() {
    List<Company> storedCompanies = companyService.getAllCompanies();
    return new ResponseEntity(storedCompanies, HttpStatus.OK);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity getCompanyById(@PathVariable("id") String id) {
    try {
      Company storedCompany = companyService.getCompanyById(id);
      return new ResponseEntity(storedCompany, HttpStatus.OK);
    } catch (CompanyDoesNotExistException cdne) {
      throw new NotFoundException(cdne.getMessage());
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteCompanyById(@PathVariable("id") String id) {
    try {
      companyService.deleteCompany(id);
      return new ResponseEntity(HttpStatus.OK);
    } catch (CompanyDoesNotExistException cdne) {
      throw new NotFoundException(cdne.getMessage());
    }
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity updateCompany(@PathVariable("id") String id, @RequestBody Company company) {
    try {
      Company storedCompany = companyService.updateCompany(id, company);
      return new ResponseEntity(storedCompany, HttpStatus.OK);
    } catch (CompanyDoesNotExistException cdne) {
      throw new NotFoundException(cdne.getMessage());
    }
  }
}
