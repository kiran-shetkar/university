package com.university.student;

import com.university.common.connection.oracle.OracleDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentCRUDOperatons {

    public static void create() {

        try {
            final Scanner scan = new Scanner(System.in);
            System.out.println("Enter Department id");
            final String dId = scan.nextLine();
            System.out.println("Enter first name");
            final String fName = scan.nextLine();
            System.out.println("Enter last name");
            final String lName = scan.nextLine();
            System.out.println("Enter Mobile No");
            final String mNo = scan.nextLine();
            String sql = "INSERT INTO k_student (department_id, first_name, last_name, mobile_no) VALUES (?, ?, ?, ?)";
            Connection con = OracleDBConnection.getConnection();
            PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, dId);
            pstmt.setString(2, fName);
            pstmt.setString(3, lName);
            pstmt.setString(4, mNo);
            final int row = pstmt.executeUpdate();
            OracleDBConnection.closeConnection(con);
            System.out.println(row + " Row inserted successfully ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            final String sql = "SELECT * FROM k_student";
            final Connection con = OracleDBConnection.getConnection();
            final PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            final ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                System.out.println(res.getInt(1) + " " + res.getInt(2) + "  " + res.getString(3) + "  " + res.getString(4) + "  " + res.getInt(5));
            }
            OracleDBConnection.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update() {

        try {
            final Scanner scan = new Scanner(System.in);
            System.out.println("Enter Student ID ");
            final String id = scan.nextLine();
            System.out.println("Enter Name for Update");
            String sName = scan.nextLine();
            final String sql = "UPDATE k_student SET first_name = ? WHERE id =?";
            Connection con = OracleDBConnection.getConnection();
            PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, sName);
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
            System.out.println("Enter Student ID for DELETE");
            final String sId = scan.nextLine();
            final String sql = "DELETE FROM k_student WHERE id = ?";
            System.out.println("Processing please wait...");
            final Connection con = OracleDBConnection.getConnection();
            final PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, sId);
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
