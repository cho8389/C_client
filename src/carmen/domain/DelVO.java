package carmen.domain;

import java.util.Date;

public class DelVO extends Criteria {
	private int del_id;
	private int ordreq_id;
	private String emp_id;
	private String del_state;
	private Date del_date;
	private String del_name;
	private String del_customer;
	private int del_amount;
	private int del_cost;
	private int del_price;
	private String del_type;
	private Date del_date_e;
	public int getDel_id() {
		return del_id;
	}
	public void setDel_id(int del_id) {
		this.del_id = del_id;
	}
	public int getOrdreq_id() {
		return ordreq_id;
	}
	public void setOrdreq_id(int ordreq_id) {
		this.ordreq_id = ordreq_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getDel_state() {
		return del_state;
	}
	public void setDel_state(String del_state) {
		this.del_state = del_state;
	}
	public Date getDel_date() {
		return del_date;
	}
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}
	public String getDel_name() {
		return del_name;
	}
	public void setDel_name(String del_name) {
		this.del_name = del_name;
	}
	public String getDel_customer() {
		return del_customer;
	}
	public void setDel_customer(String del_customer) {
		this.del_customer = del_customer;
	}
	public int getDel_amount() {
		return del_amount;
	}
	public void setDel_amount(int del_amount) {
		this.del_amount = del_amount;
	}
	public int getDel_cost() {
		return del_cost;
	}
	public void setDel_cost(int del_cost) {
		this.del_cost = del_cost;
	}
	public int getDel_price() {
		return del_price;
	}
	public void setDel_price(int del_price) {
		this.del_price = del_price;
	}
	public String getDel_type() {
		return del_type;
	}
	public void setDel_type(String del_type) {
		this.del_type = del_type;
	}
	public Date getDel_date_e() {
		return del_date_e;
	}
	public void setDel_date_e(Date del_date_e) {
		this.del_date_e = del_date_e;
	}
	public DelVO() {}
	public DelVO(int del_id, int ordreq_id, String emp_id, String del_state, Date del_date, String del_name,
			String del_customer, int del_amount, int del_cost, int del_price, String del_type, Date del_date_e) {
		super();
		this.del_id = del_id;
		this.ordreq_id = ordreq_id;
		this.emp_id = emp_id;
		this.del_state = del_state;
		this.del_date = del_date;
		this.del_name = del_name;
		this.del_customer = del_customer;
		this.del_amount = del_amount;
		this.del_cost = del_cost;
		this.del_price = del_price;
		this.del_type = del_type;
		this.del_date_e = del_date_e;
	}
	@Override
	public String toString() {
		return "DelVO [del_id=" + del_id + ", ordreq_id=" + ordreq_id + ", emp_id=" + emp_id + ", del_state="
				+ del_state + ", del_date=" + del_date + ", del_name=" + del_name + ", del_customer=" + del_customer
				+ ", del_amount=" + del_amount + ", del_cost=" + del_cost + ", del_price=" + del_price + ", del_type="
				+ del_type + ", del_date_e=" + del_date_e + "]";
	}
}
