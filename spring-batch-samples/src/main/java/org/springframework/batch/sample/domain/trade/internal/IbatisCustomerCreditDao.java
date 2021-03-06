/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.batch.sample.domain.trade.internal;

import java.sql.SQLException;

import org.springframework.batch.sample.domain.trade.CustomerCredit;
import org.springframework.batch.sample.domain.trade.CustomerCreditDao;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @author Lucas Ward
 *
 */
public class IbatisCustomerCreditDao implements CustomerCreditDao {

	SqlMapClient sqlMapClient;

	String statementId;

	/* (non-Javadoc)
	 * @see org.springframework.batch.sample.domain.trade.internal.CustomerCreditWriter#write(org.springframework.batch.sample.domain.CustomerCredit)
	 */
	@Override
	public void writeCredit(CustomerCredit customerCredit) {

		try {
			sqlMapClient.update(statementId, customerCredit);
		} catch (SQLException e) {
			throw new SQLStateSQLExceptionTranslator().translate("SqlMapClient operation", null, e);
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ResourceLifecycle#close()
	 */
	public void close() {
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ResourceLifecycle#open()
	 */
	public void open() {
	}


	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

}
