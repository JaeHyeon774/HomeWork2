package Book2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookTest {
	public void printAllBooks() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		try {
			Class.forName(Constant.DRIVER);
			con = DriverManager.getConnection(Constant.url, Constant.USER, Constant.PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql.append(" select isbn, title, author, publisher, price, des from library ");

		System.out.println("*********************** 도서 목록  ***********************");
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			BookDTO dto = new BookDTO();

//			while (rs.next()) {
//				List<BookDTO> list = new ArrayList<BookDTO>();
//				dto.setIsbn(rs.getInt("isbn"));
//				dto.setTitle(rs.getString("title"));
//				dto.setAuthor(rs.getString("author"));
//				dto.setPublisher(rs.getString("publisher"));
//				dto.setPrice(rs.getInt("price"));
//				dto.setDesc(rs.getString("des"));
//				list.add(dto);
//				dto.show(list);
//			}
//		dto.show(rs);
			while (rs.next()) {
				dto.setIsbn(rs.getInt("isbn"));
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setPrice(rs.getInt("price"));
				dto.setDesc(rs.getString("des"));
				dto.show(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean insertStudent(int isbn, String title, String author, String publisher, int price, String des) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql2 = new StringBuffer();

		try {
			Class.forName(Constant.DRIVER);
			con = DriverManager.getConnection(Constant.url, Constant.USER, Constant.PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql2.append(" insert into library(isbn, title, author, publisher, price, des) ");
		sql2.append(" values (?,?,?,?,?,?) ");

		try {
			pstmt = con.prepareStatement(sql2.toString());
			pstmt.setInt(1, isbn);
			pstmt.setString(2, title);
			pstmt.setString(3, author);
			pstmt.setString(4, publisher);
			pstmt.setInt(5, price);
			pstmt.setString(6, des);

			rs = pstmt.executeQuery();
			if (rs.next() == true)
				flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		// isbn, title, author, publisher, price, des
		int isbn;
		String title;
		String author;
		String publisher;
		int price;
		String des;
		
		boolean s = true;
		
		BookTest bt = new BookTest();
		Scanner sc = new Scanner(System.in);

		System.out.println("1. 도서 목록 출력\n2. 도서 목록 추가\n3. 종료");
		while(s) {
			switch (sc.nextInt()) {
			case 1:
				bt.printAllBooks();
				break;
			case 2:
				System.out.println("ISBN : "); isbn = sc.nextInt(); sc.nextLine();
				System.out.println("Title : "); title = sc.nextLine();
				System.out.println("Author : "); author = sc.nextLine();
				System.out.println("Publisher : "); publisher = sc.nextLine();
				System.out.println("Price : "); price = sc.nextInt(); sc.nextLine();
				System.out.println("DESC : "); des = sc.nextLine();
				boolean a = bt.insertStudent(isbn, title, author, publisher, price, des);
				if(a == true) System.out.println("insert 되었습니다.");
				break;
			case 3:
				System.out.println("종료하겠습니다.");
				s = false;
			}
		}
	}
}
