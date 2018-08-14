package com.javasampleapproach.batchcsvpostgresql.model;

public class Customer {
	private Integer proj_id;
	private String opprtunity_name;
	private String asso_proj;
	private String descri;
	private String owner;

	public Customer() {
	}

	public Customer(Integer proj_id, String opprtunity_name, String asso_proj, String descri, String owner) {
		this.proj_id = proj_id;
		this.opprtunity_name = opprtunity_name;
		this.asso_proj = asso_proj;
		this.descri = descri;
		this.owner = owner;
	}

	public Integer getProj_id() {
		return proj_id;
	}

	public void setProj_id(Integer proj_id) {
		this.proj_id = proj_id;
	}

	public String getOpprtunity_name() {
		return opprtunity_name;
	}

	public void setOpprtunity_name(String opprtunity_name) {
		this.opprtunity_name = opprtunity_name;
	}

	public String getAsso_proj() {
		return asso_proj;
	}

	public void setAsso_proj(String asso_proj) {
		this.asso_proj = asso_proj;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return String.format("Customer[proj_id=%d , firstName='%s', lastName='%s', descri='%s', owner='%s']", proj_id, opprtunity_name, asso_proj, descri, owner);
	}
}
