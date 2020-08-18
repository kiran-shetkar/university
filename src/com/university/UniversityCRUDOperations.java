package com.university;

import com.university.common.connection.oracle.OracleDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UniversityCRUDOperations {
    private static void create() {
        try {
            final Scanner scan = new Scanner(System.in);
            System.out.println("Enter University Name");
            final String uName = scan.nextLine();
            System.out.println("Enter University Establishment Date dd-MM-yyyy");
            final String uEstablishmentDate = scan.next();
            final String sql = "INSERT INTO k_university(name, establishment_date) VALUES (?, TO_DATE(?,'dd-MM-YYYY'))";
            System.out.println("Processing please wait...");
            final Connection con = OracleDBConnection.getConnection();
            final PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, uName);
            pstmt.setString(2, uEstablishmentDate);
            final int i = pstmt.executeUpdate();
            OracleDBConnection.closeConnection(con);
            System.out.println("Data inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readAll() {
        try {
            final String sql = "SELECT * FROM k_university";
            final Connection con = OracleDBConnection.getConnection();
            final PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            final ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getDate(3));
            }
            OracleDBConnection.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void update() {
        try {
            final Scanner scan = new Scanner(System.in);
            System.out.println("Enter University ID for Update");
            final String id = scan.nextLine();
            System.out.println("Enter University Name");
            final String uName = scan.nextLine();
            System.out.println("Enter University Establishment Date dd-MM-yyyy");
            final String uEstablishmentDate = scan.next();
            final String sql = "UPDATE k_university SET name = ? , establishment_date = TO_DATE(?,'dd-MM-YYYY') WHERE id = ? ";
            System.out.println("Processing please wait...");
            final Connection con = OracleDBConnection.getConnection();
            final PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, uName);
            pstmt.setString(2, uEstablishmentDate);
            pstmt.setString(3, id);
            int row = pstmt.executeUpdate();
            OracleDBConnection.closeConnection(con);
            System.out.println("Records updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void delete() {
        try {
            final Scanner scan = new Scanner(System.in);
            System.out.println("Enter University ID for DELETE");
            final String id = scan.nextLine();
            final String sql = "DELETE FROM k_university WHERE id = ?";
            System.out.println("Processing please wait...");
            final Connection con = OracleDBConnection.getConnection();
            final PreparedStatement pstmt = OracleDBConnection.getPreparedStatement(con, sql);
            pstmt.setString(1, id);
            final int row = pstmt.executeUpdate();
            OracleDBConnection.closeConnection(con);
            System.out.println("Records deleted successfully.");
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
                readAll();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }
}
