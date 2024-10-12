/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zane
 */
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String LOGIN = "Login";
    private static final String LOGGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String SEARCH = "search";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String DELETE = "delete";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            }
            if (action.equalsIgnoreCase(LOGIN)) {
                url = LOGGIN_CONTROLLER;
            } else if (action.equalsIgnoreCase(LOGOUT)) {
                url = LOGOUT_CONTROLLER;
            } else if (action.equalsIgnoreCase(LOGOUT)) {
                url = LOGOUT_CONTROLLER;
            } else if (action.equalsIgnoreCase(SEARCH)) {
                url = SEARCH_CONTROLLER;
            } else if (action.equalsIgnoreCase(UPDATE)) {
                url = UPDATE_CONTROLLER;
            } else if (action.equalsIgnoreCase(DELETE)) {
                url = DELETE_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
