package ictcg.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }),
		@UniqueConstraint(columnNames = { "company_id", "name" }),
		@UniqueConstraint(columnNames = { "company_id", "bookId" }) })
@IdClass(ThirdParty.PK.class)
public class ThirdParty extends AbstractCompanyEntity {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	Company company;
	@Id
	String id = UUID.randomUUID().toString();

	String name;
	String bookId;// Bob ID, rename accountingId (check SyncFromBobButton)
	String vat;

	public ThirdParty(Company company) {
		this.company = company;
	}
	public ThirdParty(Company company, String name) {
		this.company = company;
		this.name=name;
	}
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PK implements Serializable{
		private Company company;
		private String id;
	}

}
