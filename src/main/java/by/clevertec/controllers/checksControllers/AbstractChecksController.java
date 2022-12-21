package by.clevertec.controllers.checksControllers;

import by.clevertec.controllers.AbstractController;
import by.clevertec.model.dao.ChecksMemoryDAOImpl;
import by.clevertec.model.dao.IChecksDAO;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "AbstractChecksController")
public abstract class AbstractChecksController extends AbstractController {
    protected IChecksDAO checksDAO;

    public AbstractChecksController() {
        checksDAO = new ChecksMemoryDAOImpl();
    }
}
