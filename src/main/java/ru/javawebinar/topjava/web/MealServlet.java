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

    private Storage storage = new InMemoryMealRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = req.getContextPath();
        req.setCharacterEncoding("UTF-8");
        if (contextPath.equals("/add")) {
            req.setAttribute("meals", storage.addMeal((String) req.getAttribute("description"), (int) req.getAttribute("calories")));
            log.info("User add meal");
            sendResponse(req, resp);
        } else if (contextPath.equals("/delete")) {
            req.setAttribute("meals", storage.deleteMeal((long) req.getAttribute("id")));
            log.info("User delete meal");
            sendResponse(req, resp);
        } else {
            req.setAttribute("meals", storage.getAllMealWithExceeded());
            sendResponse(req, resp);
        }
    }

    private void sendResponse(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

}
