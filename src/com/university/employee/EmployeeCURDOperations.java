package com.university.employee;

import com.university.common.connection.oracle.OracleDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EmployeeCURDOperations {
    public static void create() {

        try {
            final Scanner scan = new Scanner(System.in);
            System.out.println("Enter department id");
            final String dId = scan.nextLine();
            System.out.println("Enter employee first name");
            final String eFName = scan.nextLine();
            System.out.println("Enter employee last name");
            final String eLName = scan.nextLine();
            System.out.println("Enter employee Mobile No");
            final String mNo = scan.nextLine();
            System.out.println("Enter employee salary");
            final String salary = scan.nextLine();
            System.out.println("Enter DOB dd-MM-yyyy");
            final String dob = scan.nextLine();
            String sql = "INSERT INTO k_employee (department_id, first_name, last_name, mobile_no, salary, dob) VALUES (?, ?,?,?,? ,TO_DATE(?, 'dd-MM-YYYY'))";
            Connection con = OracleDBConnection.getConnection();
            PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, dId);
            pstmt.setString(2, eFName);
            pstmt.setString(3, eLName);
            pstmt.setString(4, mNo);
            pstmt.setString(5, salary);
            pstmt.setString(6, dob );
            final int row = pstmt.executeUpdate();
            OracleDBConnection.closeConnection(con);
            System.out.println(row + " Row inserted successfully ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            final String sql = "SELECT * FROM k_employee";
            final Connection con = OracleDBConnection.getConnection();
            final PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            final ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                System.out.println(res.getInt(1) + "  " + res.getInt(2) + "  " + res.getString(3) + "  " + res.getString(4) + "  " + res.getInt(5)+ "  " + res.getInt(6)+ "  " + res.getDate(7));
            }
            OracleDBConnection.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update() {

        try {
            final Scanner scan = new Scanner(System.in);
            System.out.println("Enter Employee ID ");
            final String id = scan.nextLine();
            System.out.println("Enter Emp Name for Update");
            String eName = scan.nextLine();
            final String sql = "UPDATE k_employee SET first_name = ? WHERE id =?";
            Connection con = OracleDBConnection.getConnection();
            PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, eName);
            pstmt.setString(2, id);
            final int row = pstmt.executeUpdate();
            OracleDBConnection.closeConnection(con);
            System.out.println(row + " Row updated successfully ");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void delete() {
        try {
            final Scanner scan = new Scanner(System.in);
            System.out.println("Enter Employee ID for DELETE");
            final String eId = scan.nextLine();
            final String sql = "DELETE FROM k_employee WHERE id = ?";
            System.out.println("Processing please wait...");
            final Connection con = OracleDBConnection.getConnection();
            final PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, eId);
            final int row = pstmt.executeUpdate();
            OracleDBConnection.closeConnection(con);
            System.out.println(row + " Row deleted successfully ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Press 1 for Create");
        System.out.println("Press 2 for Read");
        System.out.println("Press 3 for Update");
        System.out.println("Press 4 for Delete");
        final Scanner scan = new Scanner(System.in);
        final int operationType = scan.nextInt();

        switch (operationType) {
            case 1:
                create();
                break;
            case 2:
                read();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;
            default:
                System.out.println("Please Enter Correct Option");
                break;
        }

    }
}
