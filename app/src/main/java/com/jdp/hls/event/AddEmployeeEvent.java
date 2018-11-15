package com.jdp.hls.event;

import com.jdp.hls.model.entiy.Employee;

/**
 * Description:TODO
 * Create Time:2018/11/15 0015 下午 4:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddEmployeeEvent {
    private Employee employee;

    public AddEmployeeEvent(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
