package com.example.first.entity;

import com.example.first.dal.bettlsql.ActiveRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.beetl.sql.core.TailBean;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@lombok.experimental.Accessors(chain = true)
public abstract class BaseEntity extends TailBean implements ActiveRecord {

	private static final long serialVersionUID = -7161704055180374913L;

	private Integer status;

	private Date createdAt;

	private Date updatedAt;

	@Override
	public void updateStamp() {
		this.updatedAt = new Date();
	}

	@Override
	public void createStamp() {
		this.updatedAt = new Date();
		this.createdAt = new Date();
	}
}
