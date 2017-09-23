package ua.company.myroniuk.controller;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.controller.command.CommandFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Vitalii Myroniuk
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String query = (String) request.getAttribute("query");
            Command command = CommandFactory.createCommand(query);
            String page = command.execute(request, response);
            getServletContext().getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            getServletContext().getRequestDispatcher(Command.ERROR_JSP).forward(request, response);
        }
    }
}