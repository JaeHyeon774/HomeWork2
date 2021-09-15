package Book2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
 * Book 정보를 저장할 클래스에 생성자와 Encapsulation을 적용하여 생성 isbn, title, author, publisher, pice, desc)
 * BookDTO 클래스가 가지고 있는 정보를 문자열로 리턴시켜주는 toString()을 출력을 참고해서 작성
 * BookDTO 객체 생성할 BookTest 클래스 작성. BookTest 결과 화면이 다음과 같이 출력되도록 BookDTO 클래스 객체 생성하여 toString()를 호출하는 main()을 작성*/

public class BookDTO {
	private int isbn;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private String desc;
	
	public BookDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookDTO(int isbn, String title, String author, String publisher, int price, String desc) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.desc = desc;
	}
	
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void show(ResultSet rs) {
		try {
			while(rs.next()) {
				System.out.println(rs.getInt("isbn") + "\t |"+rs.getString("title")+"\t |"+rs.getString("author")+
						"\t |"+rs.getString("publisher")+"\t |"+rs.getInt("price")+"\t |"+rs.getString("des"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void show(BookDTO dto) {
		// TODO Auto-generated method stub
		System.out.println(dto.isbn + "\t |"+dto.title+"\t |"+
	dto.author+"\t |"+dto.publisher+"\t |"+dto.price+"\t |"+dto.desc);
	}
	
	public void show(List<BookDTO> list) {
		// TODO Auto-generated method stub
		for(int i = 0; i<list.size(); i++) {
			System.out.println(list.get(i).isbn + "\t |"+list.get(i).title+"\t |"+
		list.get(i).author+"\t |"+list.get(i).publisher+"\t |"+list.get(i).price+"\t |"+list.get(i).desc);
		}
	}
}
