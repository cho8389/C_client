package carmen.domain;

import java.util.Date;

public class PayVO extends Criteria{
	private int pay_id;
	private int ordreq_id;
	private String emp_id;
	private String pay_state;
	private int pay_price;
	private Date pay_date;
	private Date pay_date_e;
	public int getPay_id() {
		return pay_id;
	}
	public void setPay_id(int pay_id) {
		this.pay_id = pay_id;
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
	public String getPay_state() {
		return pay_state;
	}
	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}
	public int getPay_price() {
		return pay_price;
	}
	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	public Date getPay_date_e() {
		return pay_date_e;
	}
	public void setPay_date_e(Date pay_date_e) {
		this.pay_date_e = pay_date_e;
	}
	public PayVO() {}
	public PayVO(int pay_id, int ordreq_id, String emp_id, String pay_state, int pay_price, Date pay_date,
			Date pay_date_e) {
		super();
		this.pay_id = pay_id;
		this.ordreq_id = ordreq_id;
		this.emp_id = emp_id;
		this.pay_state = pay_state;
		this.pay_price = pay_price;
		this.pay_date = pay_date;
		this.pay_date_e = pay_date_e;
	}
	@Override
	public String toString() {
		return "PayVO [pay_id=" + pay_id + ", ordreq_id=" + ordreq_id + ", emp_id=" + emp_id + ", pay_state=" + pay_state
				+ ", pay_price=" + pay_price + ", pay_date=" + pay_date + ", pay_date_e=" + pay_date_e + "]";
	}
}
