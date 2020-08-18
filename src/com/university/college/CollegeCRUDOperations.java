package com.university.college;

import com.university.common.connection.oracle.OracleDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CollegeCRUDOperations {

    public static void create() {

        try {
            final Scanner scan = new Scanner(System.in);
            System.out.println("Enter university id");
            final String uId = scan.nextLine();
            System.out.println("Enter College name");
            final String cName = scan.nextLine();
            System.out.println("Enter Establishment Date dd-MM-yyyy");
            final String cDate = scan.nextLine();
            String sql = "INSERT INTO k_college (university_id, name, establishment_date) VALUES (?, ?, TO_DATE(?, 'dd-MM-YYYY'))";
            Connection con = OracleDBConnection.getConnection();
            PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, uId);
            pstmt.setString(2, cName);
            pstmt.setString(3, cDate);
            final int row = pstmt.executeUpdate();
            OracleDBConnection.closeConnection(con);
            System.out.println(row + " Row inserted successfully ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            final String sql = "SELECT * FROM k_college";
            final Connection con = OracleDBConnection.getConnection();
            final PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            final ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                System.out.println(res.getInt(1) + "  " + res.getInt(2) + "  " + res.getString(3) + "  " + res.getDate(4));
            }
            OracleDBConnection.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update() {

        try {
            final Scanner scan = new Scanner(System.in);
            System.out.println("Enter College ID ");
            final String id = scan.nextLine();
            System.out.println("Enter College Name for Update");
            String cName = scan.nextLine();
            final String sql = "UPDATE k_college SET name = ? WHERE id =?";
            Connection con = OracleDBConnection.getConnection();
            PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, cName);
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
            System.out.println("Enter College ID for DELETE");
            final String cId = scan.nextLine();
            final String sql = "DELETE FROM k_college WHERE id = ?";
            System.out.println("Processing please wait...");
            final Connection con = OracleDBConnection.getConnection();
            final PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, cId);
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
