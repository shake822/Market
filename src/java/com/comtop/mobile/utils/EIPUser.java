package com.comtop.mobile.utils;

import java.security.interfaces.RSAPublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.collection.internal.PersistentSet;

import com.comtop.mobile.market.User;

public class EIPUser {

	public ArrayList<User> getAllUsers() {
		PersistentSet s =null;
		ArrayList<User> list = new ArrayList<User>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		try {
			RSAPublicKey recoveryPubKey = RSAHelper
					.generateRSAPublicKey(
							RSAHelper
									.hexStringToByte("00E90E149B399F471597E07A27AA29D9EF"),
							RSAHelper.hexStringToByte("010001"));
			System.out.println(RSAHelper.decrypt(recoveryPubKey,
					"2F27859D7C999D04EA2E2FC07FC03664"));
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.10.5.227:1521:mobile", "top", "top");
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery(" SELECT SCREENNAME AS account,PASSWORD_ AS password ,FIRSTNAME AS name FROM USER_ U");
			while (rs.next()) {
				User user = new User();
				user.setAccount(rs.getString("account"));
				user.setPassword(MD5Tools.toMD5(RSAHelper.decrypt(
						recoveryPubKey, rs.getString("password"))));
				user.setUsername(rs.getString("name"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}

}
