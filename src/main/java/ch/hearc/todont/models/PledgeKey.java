package ch.hearc.todont.models;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PledgeKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "toDontId", nullable = false, columnDefinition = "BINARY(16)")
    private UUID toDontId;
 
    public PledgeKey() {}

    public Long getUserId() {
        return userId;
    }

    public UUID getToDontId() {
        return toDontId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setToDontId(UUID toDontId) {
        this.toDontId = toDontId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((toDontId == null) ? 0 : toDontId.hashCode());
        result = prime * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PledgeKey other = (PledgeKey) obj;
        if (toDontId == null) {
            if (other.toDontId != null) {
                return false;
            }
        } else if (!toDontId.equals(other.toDontId)) {
            return false;
        }
        if (userId != other.userId) {
            return false;
        }
        return true;
    }
}