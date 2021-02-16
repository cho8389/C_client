package carmen.domain;

import java.util.Date;

public class OrdVO extends Criteria {
	private int ord_id;
	private int ordreq_id;
	private String emp_id;
	private Date ord_date;
	private int ord_amount;
	private int ord_cost;
	private int ord_price;
	private Date ord_indate;
	private int ord_inamount;
	private String ord_state;
	private Date ord_date_e;
	public int getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
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
	public Date getOrd_date() {
		return ord_date;
	}
	public void setOrd_date(Date ord_date) {
		this.ord_date = ord_date;
	}
	public int getOrd_amount() {
		return ord_amount;
	}
	public void setOrd_amount(int ord_amount) {
		this.ord_amount = ord_amount;
	}
	public int getOrd_cost() {
		return ord_cost;
	}
	public void setOrd_cost(int ord_cost) {
		this.ord_cost = ord_cost;
	}
	public int getOrd_price() {
		return ord_price;
	}
	public void setOrd_price(int ord_price) {
		this.ord_price = ord_price;
	}
	public Date getOrd_indate() {
		return ord_indate;
	}
	public void setOrd_indate(Date ord_indate) {
		this.ord_indate = ord_indate;
	}
	public int getOrd_inamount() {
		return ord_inamount;
	}
	public void setOrd_inamount(int ord_inamount) {
		this.ord_inamount = ord_inamount;
	}
	
	public String getOrd_state() {
		return ord_state;
	}
	public void setOrd_state(String ord_state) {
		this.ord_state = ord_state;
	}
	public Date getOrd_date_e() {
		return ord_date_e;
	}
	public void setOrd_date_e(Date ord_date_e) {
		this.ord_date_e = ord_date_e;
	}
	public OrdVO() {}
	public OrdVO(int ord_id, int ordreq_id, String emp_id, Date ord_date, int ord_amount, int ord_cost, int ord_price,
			Date ord_indate, int ord_inamount, String ord_state, Date ord_date_e) {
		super();
		this.ord_id = ord_id;
		this.ordreq_id = ordreq_id;
		this.emp_id = emp_id;
		this.ord_date = ord_date;
		this.ord_amount = ord_amount;
		this.ord_cost = ord_cost;
		this.ord_price = ord_price;
		this.ord_indate = ord_indate;
		this.ord_inamount = ord_inamount;
		this.ord_state = ord_state;
		this.ord_date_e = ord_date_e;
	}
	@Override
	public String toString() {
		return "OrdVO [ord_id=" + ord_id + ", ordreq_id=" + ordreq_id + ", emp_id=" + emp_id + ", ord_date=" + ord_date
				+ ", ord_amount=" + ord_amount + ", ord_cost=" + ord_cost + ", ord_price=" + ord_price + ", ord_indate="
				+ ord_indate + ", ord_inamount=" + ord_inamount + ", ord_state=" + ord_state + ", ord_date_e="
				+ ord_date_e + "]";
	}
}
