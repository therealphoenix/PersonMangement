package com.klindziuk.personmanagement.web;

import com.klindziuk.personmanagement.CurrentPerson;
import com.klindziuk.personmanagement.entity.Person;
import com.klindziuk.personmanagement.entity.Phone;
import com.klindziuk.personmanagement.DAO.PersonDAO;
import com.klindziuk.personmanagement.DAO.PhoneDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {

    public static final String NEW_PERSON = "/new";
    public static final String INSERT_PERSON = "/insert";
    public static final String NEW_PHONE = "/newphone";
    public static final String INSERT_PHONE = "/insertphone";
    public static final String UPDATE_PERSON = "/update";
    public static final String UPDATE_PHONE = "/updatephone";
    public static final String EDIT_PERSON = "/edit";
    public static final String EDIT_PHONE = "/editphone";
    public static final String DELETE_PERSON = "/delete";
    public static final String DELETE_PHONE = "/deletephone";
    public static final String PHONE_LIST = "listPhone";
    public static final String PERSON_LIST = "listPerson";
    public static final String PERSON = "person";
    public static final String PHONE = "phone";
    public static final String LIST = "list";
    public static final String SINGLE_PERSON_PHONE_LIST = "listPersonPhone";
    public static final String ID  = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String MIDDLENAME = "middlename";
    public static final String NUMBER = "number";

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
                case NEW_PERSON:
                    showNewForm(request, response);
                    break;
                case INSERT_PERSON:
                    insertPerson(request, response);
                    break;
                case NEW_PHONE:
                    showNewPhoneForm(request, response);
                    break;
                case INSERT_PHONE:
                    insertPhone(request, response);
                    break;
                case UPDATE_PHONE:
                    updatePhone(request, response);
                    break;
                case EDIT_PHONE:
                    showEditPhoneForm(request, response);
                    break;
                case DELETE_PERSON:
                    deletePerson(request, response);
                    break;
                case DELETE_PHONE:
                    deletePhone(request, response);
                    break;
                case EDIT_PERSON:
                    showEditForm(request, response);
                    break;
                case UPDATE_PERSON:
                    updatePerson(request, response);
                    break;
                default:
                    listPerson(request, response);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        //get full list of persons with phone numbers
    private void listPerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Phone> listPhone = phoneDAO.listAllPhones();
        List<Person> listPerson = personDAO.listAllPersons();
        request.setAttribute(PHONE_LIST, listPhone);
        request.setAttribute(PERSON_LIST, listPerson);
        RequestDispatcher dispatcher = request.getRequestDispatcher("PersonList.jsp");
        dispatcher.forward(request, response);
    }

	//show form for adding new person
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("PersonForm.jsp");
        dispatcher.forward(request, response);
    }
 	//show form for adding new phone numbers
    private void showNewPhoneForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Person existingPerson = personDAO.getPerson(CurrentPerson.id());
        request.setAttribute(PERSON, existingPerson);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddPhone.jsp");
        dispatcher.forward(request, response);
    }
       //show form for editing person and phone numbers of current person
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
                CurrentPerson.setId(Integer.parseInt(request.getParameter(ID)));
        Person existingPerson = personDAO.getPerson(CurrentPerson.id());
        List<Phone> listPersonPhone = phoneDAO.listPersonPhones(CurrentPerson.id());
        request.setAttribute(SINGLE_PERSON_PHONE_LIST, listPersonPhone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPerson.jsp");
        CurrentPerson.setEditPath(request.getScheme() + "://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getRequestURI() +
                "?" +
                request.getQueryString());
        request.setAttribute(PERSON, existingPerson);
        dispatcher.forward(request, response);
    }
       //show form for editing phone number
    private void showEditPhoneForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(ID));
        Person existingPerson = personDAO.getPerson(CurrentPerson.id());
        Phone existingPhone = phoneDAO.getPhone(id, CurrentPerson.id());
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddPhone.jsp");
        request.setAttribute(PERSON, existingPerson);
        request.setAttribute(PHONE, existingPhone);
        dispatcher.forward(request, response);
    }
       //add person to database
    private void insertPerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String middlename = request.getParameter(MIDDLENAME);
        Person newPerson = new Person(name, surname, middlename);
        personDAO.insertPerson(newPerson);
        response.sendRedirect(LIST);
    }
       
    private void insertPhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String number = request.getParameter(NUMBER);
        Phone newPhone = new Phone(CurrentPerson.id(), number);
        phoneDAO.insertPhone(newPhone, CurrentPerson.id());
        response.sendRedirect(CurrentPerson.editPath());
    }
       
    private void updatePerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        CurrentPerson.setId(Integer.parseInt(request.getParameter(ID)));
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String middlename = request.getParameter(MIDDLENAME);
        Person person = new Person(CurrentPerson.id(), name, surname, middlename);
        personDAO.updatePerson(person);
        response.sendRedirect(LIST);
    }
      
    private void updatePhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter(ID));
        String number = request.getParameter(NUMBER);
        Phone phone = new Phone(id, CurrentPerson.id(), number);
        phoneDAO.updatePhone(phone);
        response.sendRedirect(CurrentPerson.editPath());
    }

    private void deletePerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter(ID));
        Person person = new Person(id);
        personDAO.deletePerson(person);
        response.sendRedirect(LIST);
    }

    private void deletePhone(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter(ID));
        Phone phone = new Phone(id);
        phoneDAO.deletePhone(phone);
        response.sendRedirect(CurrentPerson.editPath());
    }

}