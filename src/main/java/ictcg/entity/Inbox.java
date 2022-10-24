package ictcg.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import ictcg.tools.NPE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@IdClass(Inbox.PK.class)
public class Inbox implements Serializable {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private Document document;

	private LocalDate documentDate;
	private String subject;// free text

	@ManyToOne(fetch = FetchType.LAZY)
	private ThirdParty thirdParty;

//	@OneToOne(fetch = FetchType.LAZY, mappedBy = "inbox")
//	private Invoice invoice;

	private boolean locked;

	@ToString.Include
	public String getCompanyId() {
		return NPE.of(company).last(c -> c.getId());
	}

	@ToString.Include
	public String getDocumentId() {
		return NPE.of(document).last(d -> d.getId());
	}

	public String getThirdPartyDisplayName() {
		if (thirdParty != null)
			return thirdParty.getName();
		return "";
	}

	public Inbox(Company company, Document document) {
		setCompany(company);
		setDocument(document);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCompanyId(), getDocumentId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inbox other = (Inbox) obj;
		return Objects.equals(getCompanyId(), other.getCompanyId())
				&& Objects.equals(getDocumentId(), other.getDocumentId());
	}

	@Getter
	@Setter
//	@ToString(onlyExplicitlyIncluded = true)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PK implements Serializable {
		private Company company;
		private Document document;
		
		@ToString.Include
		public String getCompanyId() {
			return NPE.of(company).last(c -> c.getId());
		}

		@ToString.Include
		public String getDocumentId() {
			return NPE.of(document).last(d -> d.getId());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getCompanyId(),getDocumentId());
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Inbox.PK other = (Inbox.PK) obj;
			return Objects.equals(getCompanyId(), other.getCompanyId())
					&& Objects.equals(getDocumentId(), other.getDocumentId());
		}
	}
}
