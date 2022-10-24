package ictcg.entity;

import java.io.Serializable;
import java.util.Objects;

import ictcg.tools.NPE;
import lombok.ToString;

public abstract class AbstractInboxEntity implements Serializable {

	public abstract Inbox getInbox();
	
	@ToString.Include
	public String getCompanyId() {
		return NPE.of(getInbox()).next(i -> i.getCompany()).last(c -> c.getId());
	}

	@ToString.Include
	public String getDocumentId() {
		return NPE.of(getInbox()).next(i -> i.getDocument()).last(d -> d.getId());
	}
	@Override
    public int hashCode() {
        return Objects.hash(getDocumentId(),getCompanyId());
    }
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractInboxEntity other = (AbstractInboxEntity) obj;
        return Objects.equals(getCompanyId(), other.getCompanyId())
                && Objects.equals(getDocumentId(), other.getDocumentId());
    }
}
