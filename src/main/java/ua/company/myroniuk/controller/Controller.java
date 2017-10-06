package ua.company.myroniuk.controller;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.controller.command.CommandFactory;
import ua.company.myroniuk.controller.command.Invoker;
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
    private static final CommandFactory commandFactory = new CommandFactory();
    private static final Invoker invoker = new Invoker();

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
        Command command = commandFactory.getCommand(uri);
        invoker.setCommand(command);
        String path = invoker.execute(request, response);
        if (path.startsWith("redirect:")) {
            response.sendRedirect(path.replace("redirect:", ""));
        } else {
            getServletContext().getRequestDispatcher(path).forward(request, response);
        }
    }
}