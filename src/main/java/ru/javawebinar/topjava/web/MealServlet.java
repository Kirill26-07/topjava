package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private static Storage storage = new InMemoryMealRepository();

    private static final String BASE_PATH = "/meals";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        req.setCharacterEncoding("UTF-8");
        switch (servletPath) {
            case BASE_PATH + "/add":
                req.setAttribute("meals", storage.addMeal(req.getParameter("description"), Integer.valueOf(req.getParameter("calories"))));
                log.info("User add meal");
                sendResponse(req, resp);
                break;
            case BASE_PATH + "/delete":
                req.setAttribute("meals", storage.deleteMeal(Long.valueOf(req.getParameter("id"))));
                log.info("User delete meal");
                sendResponse(req, resp);
                break;
            default:
                req.setAttribute("meals", storage.getAllMealWithExceeded());
                sendResponse(req, resp);
                break;
        }
    }

    private void sendResponse(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

}
