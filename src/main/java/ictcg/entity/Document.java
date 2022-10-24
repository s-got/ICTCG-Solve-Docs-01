package ictcg.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Document implements Serializable {
	@Id
	private String id;
	@EqualsAndHashCode.Exclude
	private String mimeType;

	public Document(String id) {
		super();
		this.id = id;
	}
}
