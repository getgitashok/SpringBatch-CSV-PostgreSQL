package com.javasampleapproach.batchcsvpostgresql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.batchcsvpostgresql.dao.CustomerDao;
import com.javasampleapproach.batchcsvpostgresql.model.Customer;

@Repository
public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public void insert(List<? extends Customer> Customers) {
		String sql = "INSERT INTO customer " + "(proj_id, opprtunity_name, asso_proj, descri, owner) VALUES (?, ?, ?, ?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Customer customer = Customers.get(i);
				ps.setInt(1, customer.getProj_id());
				ps.setString(2, customer.getOpprtunity_name());
				ps.setString(3, customer.getAsso_proj());
				ps.setString(4, customer.getDescri());
				ps.setString(5, customer.getOwner());
			}

			public int getBatchSize() {
				return Customers.size();
			}
		});

	}
	
	@Override
	public void update(List<? extends Customer> Customers) {
		String sql = "UPDATE customer SET opprtunity_name=?, asso_proj=?, descri=?, owner=? where proj_id=?";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Customer customer = Customers.get(i);
				ps.setInt(5, customer.getProj_id());
				ps.setString(1, customer.getOpprtunity_name());
				ps.setString(2, customer.getAsso_proj());
				ps.setString(3, customer.getDescri());
				ps.setString(4, customer.getOwner());
			}

			public int getBatchSize() {
				return Customers.size();
			}
		});

	}

	@Override
	public List<Customer> loadAllCustomers() {
		String sql = "SELECT * FROM customer";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Customer> result = new ArrayList<Customer>();
		for (Map<String, Object> row : rows) {
			Customer customer = new Customer();
			customer.setProj_id((Integer) row.get("proj_id"));
			customer.setOpprtunity_name((String) row.get("opprtunity_name"));
			customer.setAsso_proj((String) row.get("asso_proj"));
			customer.setDescri((String) row.get("descri"));
			customer.setOwner((String) row.get("owner"));
			result.add(customer);
		}

		return result;
	}
}
