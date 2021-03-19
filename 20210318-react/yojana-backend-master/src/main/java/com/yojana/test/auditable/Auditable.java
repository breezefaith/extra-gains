package com.yojana.test.auditable;

public interface Auditable {
	public Audit getAudit();
	public void setAudit(Audit audit);
}
