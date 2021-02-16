package carmen.domain;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReqVO2 {
	
	private IntegerProperty ordreq_id;
	private StringProperty emp_id;
	private ObjectProperty<Date> ordreq_date;
	private ObjectProperty<Date> ordreq_edate;
	private StringProperty ordreq_customer;
	private StringProperty ordreq_name;
	private IntegerProperty ordreq_amount;
	private StringProperty ordreq_purchase;
	private IntegerProperty ordreq_price;
	private IntegerProperty ordreq_cost;
	private StringProperty ordreq_region;
	private StringProperty ordreq_state;
	private StringProperty ordreq_type;
	private ObjectProperty<Date> ordreq_date_e;
	
	public int getOrdreq_id() {
		return ordreq_id.get();
	}
	public void setOrdreq_id(int ordreq_id) {
		this.ordreq_id.set(ordreq_id);
	}
	public IntegerProperty ordreq_idProperty() {
        return ordreq_id;
    }
	
	public String getEmp_id() {
		return emp_id.get();
	}
	public void setEmp_id(String emp_id) {
		this.emp_id.set(emp_id);
	}
	public StringProperty emp_idProperty() {
        return emp_id;
    }
	
	public Date getOrdreq_date() {
		return ordreq_date.get();
	}
	public void setOrdreq_date(Date ordreq_date) {
		this.ordreq_date.set(ordreq_date);
	}
	public ObjectProperty<Date> ordreq_dateProperty() {
        return ordreq_date;
    }
	
	public Date getOrdreq_edate() {
		return ordreq_edate.get();
	}
	public void setOrdreq_edate(Date ordreq_edate) {
		this.ordreq_edate.set(ordreq_edate);
	}
	public ObjectProperty<Date> ordreq_edateProperty() {
        return ordreq_edate;
    }
	
	public String getOrdreq_customer() {
		return ordreq_customer.get();
	}
	public void setOrdreq_customer(String ordreq_customer) {
		this.ordreq_customer.set(ordreq_customer);
	}
	public StringProperty ordreq_customerProperty() {
        return ordreq_customer;
    }
	
	public String getOrdreq_name() {
		return ordreq_name.get();
	}
	public void setOrdreq_name(String ordreq_name) {
		this.ordreq_name.set(ordreq_name);
	}
	public StringProperty ordreq_nameProperty() {
        return ordreq_name;
    }
	
	public int getOrdreq_amount() {
		return ordreq_amount.get();
	}
	public void setOrdreq_amount(int ordreq_amount) {
		this.ordreq_amount.set(ordreq_amount);
	}
	public IntegerProperty ordreq_amountProperty() {
        return ordreq_amount;
    }
	
	public String getOrdreq_purchase() {
		return ordreq_purchase.get();
	}
	public void setOrdreq_purchase(String ordreq_purchase) {
		this.ordreq_purchase.set(ordreq_purchase);
	}
	public StringProperty ordreq_purchaseProperty() {
        return ordreq_purchase;
    }
	
	public int getOrdreq_price() {
		return ordreq_price.get();
	}
	public void setOrdreq_price(int ordreq_price) {
		this.ordreq_price.set(ordreq_price);
	}
	public IntegerProperty ordreq_priceProperty() {
        return ordreq_price;
    }
	
	public int getOrdreq_cost() {
		return ordreq_cost.get();
	}
	public void setOrdreq_cost(int ordreq_cost) {
		this.ordreq_cost.set(ordreq_cost);
	}
	public IntegerProperty ordreq_costProperty() {
        return ordreq_cost;
    }
	
	public String getOrdreq_region() {
		return ordreq_region.get();
	}
	public void setOrdreq_region(String ordreq_region) {
		this.ordreq_region.set(ordreq_region);
	}
	public StringProperty ordreq_regionProperty() {
        return ordreq_region;
    }
	
	public String getOrdreq_state() {
		return ordreq_state.get();
	}
	public void setOrdreq_state(String ordreq_state) {
		this.ordreq_state.set(ordreq_state);
	}
	public StringProperty ordreq_stateProperty() {
        return ordreq_state;
    }
	
	public String getOrdreq_type() {
		return ordreq_type.get();
	}
	public void setOrdreq_type(String ordreq_type) {
		this.ordreq_type.set(ordreq_type);
	}
	public StringProperty ordreq_typeProperty() {
        return ordreq_type;
    }
	
	public Date getOrdreq_date_e() {
		return ordreq_date_e.get();
	}
	public void setOrdreq_date_e(Date ordreq_date_e) {
		this.ordreq_date_e.set(ordreq_date_e);
	}
	public ObjectProperty<Date> ordreq_date_eProperty() {
        return ordreq_date_e;
    }
	/**
     * 디폴트 생성자
     */
    public ReqVO2() {}
    
    public ReqVO2(int ordreq_id, String emp_id, Date ordreq_date, Date ordreq_edate, String ordreq_customer,
			String ordreq_name, int ordreq_amount, String ordreq_purchase, int ordreq_price, int ordreq_cost,
			String ordreq_region, String ordreq_state, String ordreq_type, Date ordreq_date_e) {
		super();
		this.ordreq_id = new SimpleIntegerProperty(ordreq_id);
		this.emp_id = new SimpleStringProperty(emp_id);
		this.ordreq_date = new SimpleObjectProperty<Date>(ordreq_date);
		this.ordreq_edate = new SimpleObjectProperty<Date>(ordreq_edate);
		this.ordreq_customer = new SimpleStringProperty(ordreq_customer);
		this.ordreq_name = new SimpleStringProperty(ordreq_name);
		this.ordreq_amount = new SimpleIntegerProperty(ordreq_amount);
		this.ordreq_purchase = new SimpleStringProperty(ordreq_purchase);
		this.ordreq_price = new SimpleIntegerProperty(ordreq_price);
		this.ordreq_cost = new SimpleIntegerProperty(ordreq_cost);
		this.ordreq_region = new SimpleStringProperty(ordreq_region);
		this.ordreq_state = new SimpleStringProperty(ordreq_state);
		this.ordreq_type = new SimpleStringProperty(ordreq_type);
		this.ordreq_date_e = new SimpleObjectProperty<Date>(ordreq_date_e);
	}

}
