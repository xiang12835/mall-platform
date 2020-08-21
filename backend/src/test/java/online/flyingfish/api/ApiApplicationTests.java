package online.flyingfish.api;

import online.flyingfish.api.model.Book;
import online.flyingfish.api.model.User;
import online.flyingfish.api.service.impl.BookServiceImpl;
import online.flyingfish.api.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

//	@Autowired
//	private BookServiceImpl bookServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Before
	public void before() {
		System.out.println("=============Start Test=============");
	}

//	@Test
//	public void bookAddTest() {
//		Book book1=new Book();
//		book1.setId(111);
//		book1.setName("十八岁那年的雨季");
//		book1.setPrice((float) 32.34);
//		bookServiceImpl.addBook(book1);
//	}
//
//
//	@Test
//	public void bookDelTest() {
//		bookServiceImpl.delBookById(111);
//	}
//
//	@Test
//	public void bookUpdateTest() {
//		Book book2=new Book();
//		book2.setId(3);
//		book2.setName("C++");
//		book2.setPrice((float) 120.20);
//		bookServiceImpl.updateBookById(book2);
//	}
//
//	@Test
//	public void bookQueryTest() {
//		System.out.println("你好啊图书管理系统");
//		List<Book> list = bookServiceImpl.getBook();
//		int len = list.size();
//		for (int i = 0; i < len; i++) {
//			System.out.print(list.get(i).getId() + " ");
//			System.out.print(list.get(i).getName() + " ");
//			System.out.print(list.get(i).getPrice() + " ");
//			System.out.println();
//		}
//	}

	@Test
	public void userAddTest() {
		User user1=new User();
		user1.setId(222);
		user1.setName("zhangsan");
		user1.setEmail("zhangsan@qq.com");
		userServiceImpl.addUser(user1);

	}

	@Test
	public void userDelTest() {
		userServiceImpl.delUserById(222);
	}

	@Test
	public void userUpdateTest() {
		User user2=new User();
		user2.setId(3);
		user2.setName("ha3");
		user2.setEmail("h3@qq.com");
		userServiceImpl.updateUserById(user2);
	}

	@Test
	public void userQueryTest() {
		System.out.println("你好啊爱学习的人们");
		List<User> list = userServiceImpl.getUser();
		int len = list.size();
		for (int i = 0; i < len; i++) {
			System.out.print(list.get(i).getId() + " ");
			System.out.print(list.get(i).getName() + " ");
			System.out.print(list.get(i).getEmail() + " ");
			System.out.println();
		}
	}



}

