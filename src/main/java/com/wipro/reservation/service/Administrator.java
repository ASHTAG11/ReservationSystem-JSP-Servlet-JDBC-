package com.wipro.reservation.service;

import java.util.Date;
import java.util.List;

import com.wipro.reservation.bean.ReservationBean;
import com.wipro.reservation.dao.ReservationDAO;
import com.wipro.reservation.util.InvalidInputException;

public class Administrator {

    ReservationDAO dao = new ReservationDAO();

    // Add Record
    public String addRecord(ReservationBean bean)
            throws InvalidInputException {

        // Validation 1
        if (bean == null ||
            bean.getCustomerName() == null ||
            bean.getBookingDate() == null) {

            throw new InvalidInputException();
        }

        // Validation 2
        if (bean.getCustomerName().length() < 2) {
            return "INVALID CUSTOMER NAME";
        }

        // Validation 3
        if (bean.getBookingDate().after(new Date())) {
            return "INVALID DATE";
        }

        // Validation 4
        if (dao.recordExists(
                bean.getCustomerName(),
                bean.getBookingDate())) {

            return "ALREADY EXISTS";
        }

        // Generate ID
        String id = dao.generateRecordID(
                bean.getCustomerName(),
                bean.getBookingDate());

        bean.setRecordId(id);

        // Insert
        return dao.createRecord(bean);
    }

    // View One Record
    public ReservationBean viewRecord(
            String name,
            Date date) {

        return dao.fetchRecord(name, date);
    }

    // View All Records
    public List<ReservationBean> viewAllRecords() {

        return dao.fetchAllRecords();
    }
}
