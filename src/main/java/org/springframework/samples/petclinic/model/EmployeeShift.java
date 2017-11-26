package org.springframework.samples.petclinic.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "employeeShift")
public class EmployeeShift  extends BaseEntity{
	@Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

	@Column(name = "start_time")
    @Temporal(TemporalType.TIME)
	private Date start_time;
	
	@Column(name = "end_time")
    @Temporal(TemporalType.TIME)
	private Date end_time;
	
	@ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

   
	public EmployeeShift() {
        this.date = new Date();
    }
	
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


   
}
