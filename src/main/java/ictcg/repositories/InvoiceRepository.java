package ictcg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ictcg.entity.Invoice;


public interface InvoiceRepository extends JpaRepository<Invoice, Invoice.PK>, JpaSpecificationExecutor<Invoice>  {
//	Invoice findByDocument(String document);
}