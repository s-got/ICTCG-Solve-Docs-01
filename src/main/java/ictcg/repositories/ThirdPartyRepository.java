package ictcg.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ictcg.entity.Company;
import ictcg.entity.ThirdParty;


public interface ThirdPartyRepository extends JpaRepository<ThirdParty, ThirdParty.PK>, JpaSpecificationExecutor<ThirdParty>  {
	List<ThirdParty>findByNameLikeIgnoreCase(String name);
	Optional<ThirdParty> findOneByCompanyAndBookId(Company company,String bookId);
}