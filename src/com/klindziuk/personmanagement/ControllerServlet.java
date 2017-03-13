package com.klindziuk.personmanagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {

    private PersonDAO personDAO;
    private PhoneDAO phoneDAO;

    public void init() {
        personDAO = new PersonDAO();
        phoneDAO = new PhoneDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertPerson(request, response);
                    break;
                case "/newPhone":
                    showNewPhoneForm(request, response);
                    break;
                case "/insertPhone":
                    insertPhone(request, response);
                    break;
                case "/updatePhone":
                    updatePhone(request, response);
                    break;
                case "/editPhone":
                    showEditPhoneForm(request, response);
                    break;
                case "/delete":
                    deletePerson(request, response);
                    break;
                case "/deletePhone":
                    deletePhone(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updatePerson(request, response);
                    break;
                default:
                    listPerson(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listPerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Phone> listPhone = phoneDAO.listAllPhones();
        List<Person> listPerson = personDAO.listAllPersons();
        request.setAttribute("listPhone", listPhone);
        request.setAttribute("listPerson", listPerson);
        RequestDispatcher dispatcher = request.getRequestDispatcher("PersonList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("PersonForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewPhoneForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Person existingPerson = personDAO.getPerson(CurrentPerson.id());
        request.setAttribute("person", existingPerson);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddPhone.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
                CurrentPerson.setId(Integer.parseInt(request.getParameter("id")));
        Person existingPerson = personDAO.getPerson(CurrentPerson.id());
        List<Phone> listPersonPhone = phoneDAO.listPersonPhones(CurrentPerson.id());
        request.setAttribute("listPersonPhone", listPersonPhone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPerson.jsp");
        CurrentPerson.setEditPath(request.getScheme() + "://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getRequestURI() +
                "?" +
                request.getQueryString());
        request.setAttribute("person", existingPerson);
        dispatcher.forward(request, response);
    }

    private void showEditPhoneForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Person existingPerson = personDAO.getPerson(CurrentPerson.id());
        Phone existingPhone = phoneDAO.getPhone(id, CurrentPerson.id());
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddPhone.jsp");
        request.setAttribute("person", existingPerson);
        request.setAttribute("phone", existingPhone);
        dispatcher.forward(request, response);
    }

    private void insertPerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String middlename = request.getParameter("middlename");
        Person newPerson = new Person(name, surname, middlename);
        personDAO.insertPerson(newPerson);
        response.sendRedirect("list");
    }

    private void insertPhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String number = request.getParameter("number");
        Phone newPhone = new Phone(CurrentPerson.id(), number);
        phoneDAO.insertPhone(newPhone, CurrentPerson.id());
        response.sendRedirect(CurrentPerson.editPath());
    }

    private void updatePerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        CurrentPerson.setId(Integer.parseInt(request.getParameter("id")));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String middlename = request.getParameter("middlename");
        Person person = new Person(CurrentPerson.id(), name, surname, middlename);
        personDAO.updatePerson(person);
        response.sendRedirect("list");
    }

    private void updatePhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String number = request.getParameter("number");
        Phone phone = new Phone(id, CurrentPerson.id(), number);
        phoneDAO.updatePhone(phone);
        response.sendRedirect(CurrentPerson.editPath());

    }

    private void deletePerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Person person = new Person(id);
        personDAO.deletePerson(person);
        response.sendRedirect("list");
    }

    private void deletePhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Phone phone = new Phone(id);
        phoneDAO.deletePhone(phone);
        response.sendRedirect(CurrentPerson.editPath());
    }

}