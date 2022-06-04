package com.mindgate.main.repository;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.pojo.EmployeeDetails;
import com.mindgate.main.pojo.LoginDetails;

public class EmployeeDetailsRepository implements EmployeeDetailsRepositoryInterface {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private int result;
	private static final String INSERT_EMPLOYEE_DETAILS = "INSERT INTO EMPLOYEE_DETAILS(LOGIN_ID,EMPLOYEE_NAME,ADDRESS"
			+ ",DATE_OF_BIRTH,CONTACT_NO,EMAIL,DATE_OF_JOINING,"
			+ "MANAGER_NO,DESIGNATION) VALUES (?,?,?,?,?,?,?,?,?);";
	private static final String UPDATE_LOGIN_DETAILS_BY_EMPLOYEE_ID = "UPDATE EMPLOYEE_DETAILS SET LOGIN_ID=?,EMPLOYEE_NAME=?,ADDRESS=?"
			+ ",DATE_OF_BIRTH=?,CONTACT_NO=?,EMAIL=?,DATE_OF_JOINING=?,MANAGER_NO=?,DESIGNATION=?  WHERE EMPLOYEE_ID=?)";
	private static final String DELETE_LOGIN_DETAILS_BY_ID = "DELETE FROM LOGIN_DETAILS WHERE LOGIN_ID=?";
	private static final String GET_LOGINDETAILS_BY_ID = "SELECT * FROM LOGIN_DETAILS WHERE LOGIN_ID=?";
	private static final String GET_ALL_LOGIN_DETAILS = "SELECT* FROM LOGIN_DETAILS";

	@Override
	public boolean addNewEmployee(EmployeeDetails employeeDetails) {
		result = jdbcTemplate.update(INSERT_EMPLOYEE_DETAILS, employeeDetails.getLoginDetails().getLoginId(),
				employeeDetails.getEmployeeName(), employeeDetails.getAddress(), employeeDetails.getDateOfBirth(),
				employeeDetails.getContactNo(), employeeDetails.getEmailId(), employeeDetails.getDateOfJoining(),
				employeeDetails.getManagersNo(), employeeDetails.getDesignation());

		if (result > 0)
			return true;

		return false;
	}

	@Override
	public boolean updateEmployeeDetails(EmployeeDetails employeeDetails) {
		result = jdbcTemplate.update(UPDATE_LOGIN_DETAILS_BY_EMPLOYEE_ID,
				new Object[] { employeeDetails.getLoginDetails().getLoginId(), employeeDetails.getEmployeeName(),
						employeeDetails.getAddress(), employeeDetails.getDateOfBirth(), employeeDetails.getContactNo(),
						employeeDetails.getEmailId(), employeeDetails.getDateOfJoining(),
						employeeDetails.getManagersNo(), employeeDetails.getDesignation(),
						employeeDetails.getEmployeeId() });

		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteEmployeeByEmployeeId(int employeeId) {

		result = jdbcTemplate.update(DELETE_LOGIN_DETAILS_BY_ID, employeeId);
		if (result > 0)
			return true;

		return false;
	}

	@Override
	public EmployeeDetails getEmployeeByEmployeeId(int employeeId) {

		return null;
	}

	@Override
	public List<EmployeeDetails> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	public static final class EmployeeDetailsRowMapper implements RowMapper<EmployeeDetails> {

		@Override
		public EmployeeDetails mapRow(ResultSet resultSet, int arg1) throws SQLException {

			int employeeId = resultSet.getInt("Employee_Id");
			String employeeName = resultSet.getString("Employee_Name");
			Date dateOfBirth = resultSet.getDate("Date_Of_Birth");
			String address = resultSet.getString("Address");
			Date dateOfJoining = resultSet.getDate("Date_Of_Joining");// unable to store in local date
			String emailId = resultSet.getString("Email");
			int managersNo = resultSet.getInt("MANAGER_NO");
			long contactNo = resultSet.getLong("Contact_No");
			String designation = resultSet.getString("DESIGNATION");
			int loginId = resultSet.getInt("Login_Id");
			LoginDetails loginDetails = new LoginDetails();
			loginDetails.setLoginId(loginId);

			EmployeeDetails employeeDetails = new EmployeeDetails();

			employeeDetails.setEmployeeId(employeeId);
			employeeDetails.setEmployeeName(employeeName);
			//employeeDetails.setDateOfBirth();// unable to set date in the object

			

			return null;
		}

	}
}
