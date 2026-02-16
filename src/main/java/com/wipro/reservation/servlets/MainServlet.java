package com.wipro.reservation.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.reservation.bean.ReservationBean;
import com.wipro.reservation.service.Administrator;
import com.wipro.reservation.util.InvalidInputException;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    Administrator admin = new Administrator();

    // Add Record
    public String addRecord(HttpServletRequest request)
            throws Exception {

        ReservationBean bean = new ReservationBean();

        bean.setCustomerName(
                request.getParameter("customerName"));

        bean.setBookingType(
                request.getParameter("bookingType"));

        String d = request.getParameter("bookingDate");

        Date date =
                new SimpleDateFormat("yyyy-MM-dd")
                        .parse(d);

        bean.setBookingDate(date);

        bean.setConfirmationNo(
                request.getParameter("confirmationNo"));

        bean.setAmount(
                Integer.parseInt(
                        request.getParameter("amount")));

        bean.setRemarks(
                request.getParameter("remarks"));

        return admin.addRecord(bean);
    }

    // View One Record
    public ReservationBean viewRecord(
            HttpServletRequest request)
            throws Exception {

        String name =
                request.getParameter("customerName");

        String d =
                request.getParameter("bookingDate");

        Date date =
                new SimpleDateFormat("yyyy-MM-dd")
                        .parse(d);

        return admin.viewRecord(name, date);
    }

    // View All
    public List<ReservationBean> viewAllRecords(
            HttpServletRequest request) {

        return admin.viewAllRecords();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String operation =
                request.getParameter("operation");

        try {

            if ("newRecord".equals(operation)) {

                String result =
                        addRecord(request);

                if ("FAIL".equals(result)) {

                    response.sendRedirect(
                            "error.html");

                } else {

                    response.sendRedirect(
                            "success.html");
                }

            } else if ("viewRecord".equals(operation)) {

                ReservationBean bean =
                        viewRecord(request);

                if (bean == null) {

                    request.setAttribute(
                            "msg",
                            "No matching records exists! Please try again!");

                    RequestDispatcher rd =
                            request.getRequestDispatcher(
                                    "displayReservation.jsp");

                    rd.forward(request, response);

                } else {

                    request.setAttribute(
                            "bean", bean);

                    RequestDispatcher rd =
                            request.getRequestDispatcher(
                                    "displayReservation.jsp");

                    rd.forward(request, response);
                }

            } else if ("viewAllRecords".equals(operation)) {

                List<ReservationBean> list =
                        viewAllRecords(request);

                if (list.isEmpty()) {

                    request.setAttribute(
                            "msg",
                            "No records available!");

                } else {

                    request.setAttribute(
                            "list", list);
                }

                RequestDispatcher rd =
                        request.getRequestDispatcher(
                                "displayAllReservations.jsp");

                rd.forward(request, response);
            }

        } catch (InvalidInputException e) {

            response.sendRedirect("error.html");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect("error.html");
        }
    }
}
