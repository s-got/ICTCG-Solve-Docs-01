package ictcg.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ictcg.tools.NPE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(Invoice.PK.class)
public class Invoice extends AbstractInboxEntity {

	@Id
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="companyId", referencedColumnName="company_id")
    @JoinColumn(name="documentId", referencedColumnName="document_id")
	private Inbox inbox;
	
	String importId;

	public Invoice(Inbox inbox) {
		setInbox(inbox);
	}

	@Data
	public static class PK implements Serializable{
		Inbox inbox;
	}
	/**
	 * Next class is very very heavy, but how to avoid LazyException if inbox is a proxy ?
	 * (@EqualsAndHash from Lombok would invoke equals and hash from inbox and throw the exception)
	 *
	 */
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PK_WithEqualsAndHash implements Serializable {
		private Inbox inbox;
		@ToString.Include
		public String getCompanyId() {
			return NPE.of(inbox).last(i->getCompanyId());
		}

		@ToString.Include
		public String getDocumentId() {
			return NPE.of(inbox).last(d -> d.getDocumentId());
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
