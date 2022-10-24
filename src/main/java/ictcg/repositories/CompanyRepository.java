package ictcg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ictcg.entity.Company;


public interface CompanyRepository extends JpaRepository<Company, String>, JpaSpecificationExecutor<Company>  {
}