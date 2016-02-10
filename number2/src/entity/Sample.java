package entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonIgnoreProperties(ignoreUnknown=true) 
public class Sample {
	
	@JsonProperty
	Long id;
	
	@JsonProperty
	String name;
	
	@JsonProperty
	Boolean active;
	
	@JsonProperty
	Long count;
	
	@JsonProperty("address_ids")
	List<Long> addressIds;
	
	@JsonProperty
	@JsonDeserialize(contentAs=Account.class)
	List<Account> accounts;
	
	
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class Account {
		
		@JsonProperty
		Long id;
		
		@JsonProperty
		String name;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public String toString() {
			return "{id: "+id+" | name: "+name+"}";
		}
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public Long getCount() {
		return count;
	}


	public void setCount(Long count) {
		this.count = count;
	}


	public List<Long> getAddressIds() {
		return addressIds;
	}


	public void setAddressIds(List<Long> addressIds) {
		this.addressIds = addressIds;
	}


	public List<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d,\n"
				+ "name: %s\n"
				+ "active: %s\n"
				+ "count: %d\n" 
				+ "address IDs: [%s]\n"
				+ "accounts: [%s]",
				id, name, active, count, 
				nullSafeGet(addressIds).stream().map(l -> l.toString()).collect(Collectors.joining(", ")),
				nullSafeGet(accounts).stream().map(Account::toString).collect(Collectors.joining(", ")));
	}
	
	protected <T> Collection<T> nullSafeGet(Collection<T> c) {
		if (c != null) {
			return c;
		} else {
			return Collections.emptyList();
		}
	}

}
