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

			try {
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				BookDTO dto = new BookDTO();

				while (rs.next()) {
					List<BookDTO> list = new ArrayList<BookDTO>();
					dto.setIsbn(rs.getInt("isbn"));
					dto.setTitle(rs.getString("title"));
					dto.setAuthor(rs.getString("author"));
					dto.setPublisher(rs.getString("publisher"));
					dto.setPrice(rs.getInt("price"));
					dto.setDesc(rs.getString("des"));
					list.add(dto);
					dto.toString(list);
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

	public boolean insertStudent(BookDTO book) {
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
			pstmt.setInt(1, book.getIsbn());
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPublisher());
			pstmt.setInt(5, book.getPrice());
			pstmt.setString(6, book.getDesc());

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
		boolean s = true;
		
		BookDTO book = new BookDTO();
		BookTest bt = new BookTest();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. 도서 목록 출력\n2. 도서 목록 추가\n3. 종료");
		while(s) {
			switch (sc.nextInt()) {
			case 1:
				bt.printAllBooks();
				break;
			case 2:
				System.out.println("ISBN : "); book.setIsbn(sc.nextInt()); sc.nextLine();
				System.out.println("Title : "); book.setTitle(sc.nextLine());
				System.out.println("Author : "); book.setAuthor(sc.nextLine());
				System.out.println("Publisher : "); book.setPublisher(sc.nextLine());
				System.out.println("Price : "); book.setPrice(sc.nextInt()); sc.nextLine();
				System.out.println("DESC : "); book.setDesc(sc.nextLine());
				boolean a = bt.insertStudent(book);
				if(a == true) System.out.println("insert 되었습니다.");
				break;
			case 3:
				System.out.println("종료하겠습니다.");
				s = false;
			}
		}
	}
}
