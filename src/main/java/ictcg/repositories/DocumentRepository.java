package ictcg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ictcg.entity.Document;


public interface DocumentRepository extends JpaRepository<Document, String>, JpaSpecificationExecutor<Document>  {
}