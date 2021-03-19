package com.yojana.test;

import com.yojana.test.auditable.Audit;
import com.yojana.test.auditable.AuditListener;
import com.yojana.test.auditable.Auditable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "entitya", catalog = "", schema = "")
@EntityListeners(AuditListener.class)
public class EntityA implements Auditable, Serializable {
    @Embedded
    private Audit audit;

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    public EntityA() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }
}
