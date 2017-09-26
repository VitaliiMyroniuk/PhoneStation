package ua.company.myroniuk.controller;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.controller.command.CommandFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class describes the main controller servlet.
 *
 * @author Vitalii Myroniuk
 */
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * The methods provides the common process for http {@code GET} and {@code POST} methods.
     *
     * @param request http servlet request.
     * @param response http servlet response.
     * @throws ServletException if a specific servlet error occurs.
     * @throws IOException if an error occurs during the input or output.
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = (String) request.getAttribute("uri");
        Command command = CommandFactory.createCommand(uri);
        String page = command.execute(request, response);
        if (page.startsWith("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            getServletContext().getRequestDispatcher(page).forward(request, response);
        }
    }
}