package org.bissis.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Markus Ullrich
 */
@MappedSuperclass
public abstract class AbstractDomainClass implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private
    Integer id;

    @Version
    private Integer version;

    //@CreationTimestamp
    private Date dateCreated;

    //@UpdateTimestamp
    private Date lastUpdated;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    //Probably not necessary, but it is nice to have this as an example

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastUpdated = new Date();
        if (dateCreated == null) {
            dateCreated = lastUpdated;
        }
    }

}
