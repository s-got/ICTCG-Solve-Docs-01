package ictcg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ictcg.entity.Inbox;


public interface InboxRepository extends JpaRepository<Inbox, Inbox.PK>, JpaSpecificationExecutor<Inbox>  {
	List<Inbox> findAllByDocument(String documentId);
}