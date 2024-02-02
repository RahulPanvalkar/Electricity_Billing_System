package com.ebs.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebs.dao.SecurityQuestionsDao;
import com.ebs.entities.AnswersOfQuestions;
import com.ebs.utility.DBConnectionManager;

@WebServlet("/ResetAdminPasswordServlet")
public class ResetAdminPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("id"));
		String birthPlace = request.getParameter("birth-place");
		String teacherName= request.getParameter("teacher-name");
		String sport= request.getParameter("sport");
		
		HttpSession session = request.getSession();
		
		Connection con = DBConnectionManager.getConnection();
		SecurityQuestionsDao qDao = new SecurityQuestionsDao(con);
		AnswersOfQuestions answers = qDao.getAnswers(userId);
		
		if (answers != null && birthPlace.equals(answers.getBirthPlace()) 
				&& teacherName.equals(answers.getTeachersName()) && sport.equals(answers.getSport())) {
			response.sendRedirect("ChangePassword.jsp?reqType=Reset");
		} else {
			session.setAttribute("error", "One or more Invalid answers, please try again");
			response.sendRedirect("ChangeAdminPassword.jsp");
		}

	}
}
