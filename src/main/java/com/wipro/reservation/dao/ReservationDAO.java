package com.wipro.reservation.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wipro.reservation.bean.ReservationBean;
import com.wipro.reservation.util.DBUtil;

public class ReservationDAO {

    // Insert Record
    public String createRecord(ReservationBean bean) {

        String status = "FAIL";

        try (Connection con = DBUtil.getDBConnection()) {

            String sql = "INSERT INTO reservation_tb " +
                    "(RECORDID, CUSTOMERNAME, BOOKINGTYPE, BOOKING_DATE, CONFIRMATIONNO, AMOUNT, REMARKS) " +
                    "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bean.getRecordId());
            ps.setString(2, bean.getCustomerName());
            ps.setString(3, bean.getBookingType());

            java.sql.Date sqlDate =
                    new java.sql.Date(bean.getBookingDate().getTime());

            ps.setDate(4, sqlDate);

            ps.setString(5, bean.getConfirmationNo());
            ps.setInt(6, bean.getAmount());
            ps.setString(7, bean.getRemarks());

            int i = ps.executeUpdate();

            if (i > 0) status = bean.getRecordId();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Fetch Single Record
    public ReservationBean fetchRecord(String name, java.util.Date date) {

        ReservationBean bean = null;

        try (Connection con = DBUtil.getDBConnection()) {

            String sql = "SELECT * FROM reservation_tb " +
                    "WHERE CUSTOMERNAME=? AND BOOKING_DATE=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);

            java.sql.Date sqlDate =
                    new java.sql.Date(date.getTime());

            ps.setDate(2, sqlDate);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                bean = new ReservationBean();

                bean.setRecordId(rs.getString("RECORDID"));
                bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                bean.setBookingType(rs.getString("BOOKINGTYPE"));
                bean.setBookingDate(rs.getDate("BOOKING_DATE"));
                bean.setConfirmationNo(rs.getString("CONFIRMATIONNO"));
                bean.setAmount(rs.getInt("AMOUNT"));
                bean.setRemarks(rs.getString("REMARKS"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    // Check Record Exists
    public boolean recordExists(String name, java.util.Date date) {

        return fetchRecord(name, date) != null;
    }

    // Generate Record ID
    public String generateRecordID(String name, java.util.Date date) {

        String id = "";

        try (Connection con = DBUtil.getDBConnection()) {

            String sql = "SELECT MAX(SEQNO) FROM reservation_tb";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            int seq = 10;

            if (rs.next() && rs.getInt(1) > 0) {
                seq = rs.getInt(1) + 1;
            }

            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyyMMdd");

            String d = sdf.format(date);

            String part = name.substring(0, 2).toUpperCase();

            id = d + part + seq;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    // Fetch All Records
    public List<ReservationBean> fetchAllRecords() {

        List<ReservationBean> list = new ArrayList<>();

        try (Connection con = DBUtil.getDBConnection()) {

            String sql = "SELECT * FROM reservation_tb";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                ReservationBean bean =
                        new ReservationBean();

                bean.setRecordId(rs.getString("RECORDID"));
                bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                bean.setBookingType(rs.getString("BOOKINGTYPE"));
                bean.setBookingDate(rs.getDate("BOOKING_DATE"));
                bean.setConfirmationNo(rs.getString("CONFIRMATIONNO"));
                bean.setAmount(rs.getInt("AMOUNT"));
                bean.setRemarks(rs.getString("REMARKS"));

                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
