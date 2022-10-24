package ictcg.entity;

import java.io.Serializable;
import java.util.Objects;

import ictcg.tools.NPE;
import lombok.ToString;

public abstract class AbstractCompanyEntity implements Serializable {

	public abstract Company getCompany();
	public abstract void setCompany(Company company);
	public abstract String getId();
	
	@ToString.Include
	public String getCompanyId() {
		return NPE.of(getCompany()).last(c -> c.getId());
	}
	@Override
    public int hashCode() {
        return Objects.hash(getCompanyId(), getId());
    }
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractCompanyEntity other = (AbstractCompanyEntity) obj;
        return Objects.equals(getCompanyId(), other.getCompanyId())
                && Objects.equals(getId(), other.getId());
    }
}
