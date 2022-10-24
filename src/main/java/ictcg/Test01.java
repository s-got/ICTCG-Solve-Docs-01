package ictcg;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import ictcg.entity.Company;
import ictcg.entity.Document;
import ictcg.entity.Inbox;
import ictcg.entity.Invoice;
import ictcg.repositories.CompanyRepository;
import ictcg.repositories.DocumentRepository;
import ictcg.repositories.InboxRepository;
import ictcg.repositories.InvoiceRepository;

@Component
public class Test01 {

	@Autowired
    ApplicationContext context;
	
	@PostConstruct
	void test() {
		Company a = context.getBean(CompanyRepository.class).save(new Company("A"));
		Document doc = context.getBean(DocumentRepository.class).save(new Document("docHash1"));
		InboxRepository ir = context.getBean(InboxRepository.class);
		Inbox inbox = ir.save(new Inbox(a, doc));
		InvoiceRepository invr = context.getBean(InvoiceRepository.class);

		/**
		 * Next line generates Exception :
		 * java.lang.IllegalStateException: Cannot convert value of type 'ictcg.entity.Inbox$PK' to required type 'ictcg.entity.Inbox' for property 'inbox': no matching editors or conversion strategy found
		 */
		invr.save(new Invoice(inbox));
		
		System.err.println("Test"+ir.findAll());
	}
	
}
