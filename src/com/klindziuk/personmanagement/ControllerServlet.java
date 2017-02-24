package com.klindziuk.personmanagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControllerServlet extends HttpServlet {

	private PersonDAO personDAO;
	private PhoneDAO phoneDAO;
	List<Person> listPerson;



	public void init() {
		String  jdbcURL = "jdbc:mysql://localhost:3306/phonebook";
		String jdbcUsername = "root";
		String jdbcPassword = "root";

		personDAO = new PersonDAO(jdbcURL, jdbcUsername, jdbcPassword);
		phoneDAO  = new PhoneDAO(jdbcURL, jdbcUsername, jdbcPassword);

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
			case "/delete":
				deletePerson(request, response);
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
	List<Phone> listPhone = null;
		List<Person> listPerson = personDAO.listAllPersons();
		for(Person p : listPerson){
			 listPhone = phoneDAO.listAllPhones(2);
			p.setPhoneList(listPhone);
			request.setAttribute("listPhone", listPhone);
		}
		request.setAttribute("listPerson", listPerson);
		RequestDispatcher dispatcher = request.getRequestDispatcher("PersonList.jsp");
		dispatcher.forward(request, response);
	}


	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("PersonForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Person existingPerson = personDAO.getPerson(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("PersonForm.jsp");
		request.setAttribute("person", existingPerson);
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

	private void updatePerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String middlename = request.getParameter("middlename");

		Person person = new Person(id, name, surname, middlename);
		personDAO.updatePerson(person);
		response.sendRedirect("list");
	}

	private void deletePerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Person person = new Person(id);
		personDAO.deletePerson(person);
		response.sendRedirect("list");

	}

}
