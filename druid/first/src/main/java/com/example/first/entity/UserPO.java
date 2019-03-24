package com.example.first.entity;

import com.example.first.dal.bettlsql.ActiveRecord;
import com.example.first.dal.bettlsql.ActiveRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.Date;

import static com.example.first.dal.bettlsql.SqlKit.mapper;

/**
 *
 *  gen by smthit 2018-04-19
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@lombok.experimental.Accessors(chain = true)
@Table(name = "uc_users")
public class UserPO extends BaseEntity implements ActiveRecord {

	private static final long serialVersionUID = -7881210592511232526L;

	@AssignID
	private Long id;

	private Integer status;

	//登录名、默认是注册的手机号
	private String loginName;

	//账号人、机构名
	private String name;

	//昵称
	private String nickname;

	private Date lastLoginTime;

	private String password;

	private String salt;

	private Long orgId;
	private String orgName;

	public interface Dao extends BaseMapper <UserPO> {
		Dao $ = mapper(Dao.class);

	}

}
