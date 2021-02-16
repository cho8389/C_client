package carmen.domain;

import java.util.Date;

public class CalVO extends Criteria {
	private int cal_id;
	private int ordreq_id;
	private String emp_id;
	private Date cal_date;
	private int cal_price;
	private String cal_bank;
	private String cal_name;
	private String cal_state;
	private String cal_bill;
	private String cal_check;
	private Date cal_chkdate;
	private String cal_note;
	private Date cal_date_e;
	public int getCal_id() {
		return cal_id;
	}
	public void setCal_id(int cal_id) {
		this.cal_id = cal_id;
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
	public Date getCal_date() {
		return cal_date;
	}
	public void setCal_date(Date cal_date) {
		this.cal_date = cal_date;
	}
	public int getCal_price() {
		return cal_price;
	}
	public void setCal_price(int cal_price) {
		this.cal_price = cal_price;
	}
	public String getCal_bank() {
		return cal_bank;
	}
	public void setCal_bank(String cal_bank) {
		this.cal_bank = cal_bank;
	}
	public String getCal_name() {
		return cal_name;
	}
	public void setCal_name(String cal_name) {
		this.cal_name = cal_name;
	}
	public String getCal_state() {
		return cal_state;
	}
	public void setCal_state(String cal_state) {
		this.cal_state = cal_state;
	}
	public String getCal_bill() {
		return cal_bill;
	}
	public void setCal_bill(String cal_bill) {
		this.cal_bill = cal_bill;
	}
	public String getCal_check() {
		return cal_check;
	}
	public void setCal_check(String cal_check) {
		this.cal_check = cal_check;
	}
	public Date getCal_chkdate() {
		return cal_chkdate;
	}
	public void setCal_chkdate(Date cal_chkdate) {
		this.cal_chkdate = cal_chkdate;
	}
	public String getCal_note() {
		return cal_note;
	}
	public void setCal_note(String cal_note) {
		this.cal_note = cal_note;
	}
	public Date getCal_date_e() {
		return cal_date_e;
	}
	public void setCal_date_e(Date cal_date_e) {
		this.cal_date_e = cal_date_e;
	}
	public CalVO() {}
	public CalVO(int cal_id, int ordreq_id, String emp_id, Date cal_date, int cal_price, String cal_bank,
			String cal_name, String cal_state, String cal_bill, String cal_check, Date cal_chkdate, String cal_note,
			Date cal_date_e) {
		super();
		this.cal_id = cal_id;
		this.ordreq_id = ordreq_id;
		this.emp_id = emp_id;
		this.cal_date = cal_date;
		this.cal_price = cal_price;
		this.cal_bank = cal_bank;
		this.cal_name = cal_name;
		this.cal_state = cal_state;
		this.cal_bill = cal_bill;
		this.cal_check = cal_check;
		this.cal_chkdate = cal_chkdate;
		this.cal_note = cal_note;
		this.cal_date_e = cal_date_e;
	}
	@Override
	public String toString() {
		return "CalVO [cal_id=" + cal_id + ", ordreq_id=" + ordreq_id + ", emp_id=" + emp_id + ", cal_date=" + cal_date
				+ ", cal_price=" + cal_price + ", cal_bank=" + cal_bank + ", cal_name=" + cal_name + ", cal_state="
				+ cal_state + ", cal_bill=" + cal_bill + ", cal_check=" + cal_check + ", cal_chkdate=" + cal_chkdate
				+ ", cal_note=" + cal_note + ", cal_date_e=" + cal_date_e + "]";
	}
}
