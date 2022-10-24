package ictcg;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import ictcg.entity.Company;
import ictcg.entity.Document;
import ictcg.entity.Inbox;
import ictcg.entity.Invoice;
import ictcg.entity.ThirdParty;
import ictcg.repositories.CompanyRepository;
import ictcg.repositories.DocumentRepository;
import ictcg.repositories.InboxRepository;
import ictcg.repositories.InvoiceRepository;
import ictcg.repositories.ThirdPartyRepository;

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
		 * Next line generates Exception : java.lang.IllegalStateException: Cannot
		 * convert value of type 'ictcg.entity.Inbox$PK' to required type
		 * 'ictcg.entity.Inbox' for property 'inbox': no matching editors or conversion
		 * strategy found
		 */
//		invr.save(new Invoice(inbox));

//		System.err.println("Test" + ir.findAll());
		test2();
	}

	void test2() {
		ThirdParty tp1 = context.getBean(ThirdPartyRepository.class).save(new ThirdParty(new Company("A"), "TP1"));
		InboxRepository ir = context.getBean(InboxRepository.class);
		Inbox inbox = ir.findById(new Inbox.PK(new Company("A"), new Document("docHash1"))).get();
		inbox.setThirdParty(tp1);
		ir.save(inbox);

		List<Inbox> ibx = ir.findAll((r, q, c) -> c.equal(r.get("thirdParty"), tp1));
		System.err.println("" + ibx);

		InvoiceRepository ivr = context.getBean(InvoiceRepository.class);
		/**
		 * Next list generated this in production (with data) : Caused by:
		 * org.hibernate.TypeMismatchException: Provided id of the wrong type for class
		 * com.ictcg.erp.inbox.entities.Invoice. Expected: class
		 * com.ictcg.erp.inbox.entities.Invoice$PK, got class
		 * com.ictcg.erp.inbox.entities.Inbox$PK
		 */
		List<Invoice> ivx = ivr.findAll(new FetchSpecification());
		System.err.println("" + ivx);

	}

	public class FetchSpecification implements Specification<Invoice> {

		public FetchSpecification() {
		}

		@Override
		public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			Predicate r = cb.conjunction();
//			if (company.getValue() != null)
//				r = cb.and(r, cb.equal(root.get("companyId"), company.getValue().getId()));
//			if (search.getValue().trim().length() > 0) {
//				r = cb.and(r, cb.or(cb.like(root.get("inbox").get("subject"), "%" + search.getValue().trim() + "%"), cb
//						.like(root.get("inbox").get("thirdParty").get("name"), "%" + search.getValue().trim() + "%")));
//			}
			Class clazz = query.getResultType();
			if (clazz.equals(Long.class) || clazz.equals(long.class))
				return r;
			@SuppressWarnings({ "rawtypes", "unchecked" })
			Join<Invoice, Inbox> jInbox = (Join) root.fetch("inbox");
//			Join<Inbox, ThirdParty> jThirdParty = (Join) jInbox.fetch("thirdParty", JoinType.LEFT);
			return r;
		}
	}

}
