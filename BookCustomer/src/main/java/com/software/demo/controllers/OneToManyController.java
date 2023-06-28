package com.software.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Pattern.Flag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.software.demo.entity.Author;
import com.software.demo.entity.Bank;
import com.software.demo.entity.BankCusInfo;
import com.software.demo.entity.Book;
import com.software.demo.entity.Cart;
import com.software.demo.entity.Category;
import com.software.demo.entity.Customer;
import com.software.demo.entity.CustomerInfo;
import com.software.demo.entity.OrderDetail;
import com.software.demo.entity.Orders;
import com.software.demo.repository.AuthorRepository;
import com.software.demo.repository.BankRepository;
import com.software.demo.repository.BookRepository;
import com.software.demo.repository.CategoryRepository;
import com.software.demo.repository.CustomerInfoRepository;
import com.software.demo.repository.CustomerRepository;
import com.software.demo.repository.OrderDetailRepository;
import com.software.demo.repository.OrdersRepository;


@Controller
public class OneToManyController {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private OrderDetailRepository orderdetailRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CustomerInfoRepository customerinfoRepository;
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private HttpSession session;
	private List<Cart> cartList = new ArrayList<Cart>();
	
	
	@RequestMapping("/index")
	public String index(Model model) 
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "index";
		
	}
	
	@RequestMapping("/index1")
	public String index1(Model model) 
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "index1";
	}
	//////////////////////////Author Start////////////////////////////////////////
	

	
//////////////////////////Author End////////////////////////////////////////
	
	////////////////////////////////Book Start///////////////////////////////////////////
	@GetMapping("/books")
	public String book(Model model)
	{	
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		Book book = new Book();
		model.addAttribute("book", book);
		
		
		return "book";
	}
	
	
		
	@GetMapping("/viewbook")
	public String viewbook(Model model)
	{	
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		List<Book> booklist = bookRepository.findAll();
		model.addAttribute("booklist", booklist);
		return "viewbook";
	}
	

	@RequestMapping("/searchbook")
	public String searchbook(@RequestParam (name = "search") String Search, Model model)
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		List<Book> searchList = bookRepository.findByTitle(Search);
		model.addAttribute("booklist", searchList);
		return "viewall";
		
		
	}
	@RequestMapping("/searchbook1")
	public String searchbook1(@RequestParam (name = "search") String Search, Model model)
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		List<Book> searchList = bookRepository.findByTitle(Search);
		model.addAttribute("booklist", searchList);
		return "viewall1";
		
		
	}
	
	
	/////////////////////////////// Book End /////////////////////////////////////////

////////////////////////View ALl//////////////////////////////////////////////////////
	
	
	@GetMapping("/viewall")
	public String viewall(Model model, HttpServletRequest request)
	{
//		int page = 0;
//	    int size = 3;
//	    
//	    if (request.getParameter("page") != null && !request.getParameter("page").isEmpty())
//	    {
//	      page = Integer.parseInt(request.getParameter("page")) - 1;
//	    }
//	    
//	    if (request.getParameter("size") != null && !request.getParameter("size").isEmpty())
//	    {
//	      page = Integer.parseInt(request.getParameter("size"));
//	    }
		List<Book> booklist = bookRepository.findAll();
//		model.addAttribute("booklist", booklist);
	    model.addAttribute("booklist", bookRepository.findAll());
	    
	    List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "viewall";
	}
	
	@GetMapping("/store/{id}")
	public String store(Model model,@PathVariable(name="id")Integer id)
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		List<Book> booklist = bookRepository.findByCategory(id);
		model.addAttribute("booklist", booklist);
		return "viewall";
	}
	
	
//////////////////////View All End //////////////////////////////////
	
	/////////////////// View all1//////////////////////

	
	
	
	
	@GetMapping("/viewall1")
	public String viewall1(Model model, HttpServletRequest request)
	{
//		int page = 0;
//	    int size = 3;
//	    
//	    if (request.getParameter("page") != null && !request.getParameter("page").isEmpty())
//	    {
//	      page = Integer.parseInt(request.getParameter("page")) - 1;
//	    }
//	    
//	    if (request.getParameter("size") != null && !request.getParameter("size").isEmpty())
//	    {
//	      page = Integer.parseInt(request.getParameter("size"));
//	    }
		List<Book> booklist = bookRepository.findAll();
		model.addAttribute("booklist", bookRepository.findAll());
//		model.addAttribute("booklist", booklist);
	    
	    List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
	    
//	    model.addAttribute("booklist", bookRepository.findAll(PageRequest.of(page, size)));
		return "viewall1";
	}
	
	@GetMapping("/store1/{id}")
	public String store1(Model model,@PathVariable(name="id")Integer id)
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		List<Book> booklist = bookRepository.findByCategory(id);
		model.addAttribute("booklist", booklist);
		return "viewall1";
	}
	////////////////// View all 1 end////////////////////
	
	
	@RequestMapping("/viewdetails/{id}")
	public String viewdetails(@PathVariable(name="id")Integer id, Model model)
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		Book books = bookRepository.findById(id).get();
		model.addAttribute("books", books);
		return "viewdetails";
		
	}

	@RequestMapping("/viewdetails1/{id}")
	public String viewdetails1(@PathVariable(name="id")Integer id, Model model)
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		Book books = bookRepository.findById(id).get();
		model.addAttribute("books", books);
		return "viewdetails1";
		
	}
	@GetMapping("/mycart")
	public String myCart(Model model)
	{	
		cartList = (List<Cart>) session.getAttribute("cartList");
		
		int totalPrice = 0;
		
		if(cartList != null)
		{
			for(Cart c:cartList)
			{
				totalPrice += c.getPrice() * c.getQuantity();
				
				
			}
		}
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		model.addAttribute("mycartlist", cartList);
		model.addAttribute("totalprice", totalPrice);
		
		return "mycart";
	}
	
	@GetMapping("/mycart1")
	public String myCart1(Model model)
	{	
		cartList = (List<Cart>) session.getAttribute("cartList");
		
		int totalPrice = 0;
		
		if(cartList != null)
		{
			for(Cart c:cartList)
			{
				totalPrice += c.getPrice() * c.getQuantity();
				
				
			}
		}
		
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		List<OrderDetail> detaillist = (List<OrderDetail>) session.getAttribute("detaillist");
		
		model.addAttribute("mycartlist", cartList);
		model.addAttribute("totalprice", totalPrice);
		
		return "mycart1";
	}
	
	
	
	@GetMapping("/addtocart/{id}")
	public String addtocart(@PathVariable(name="id") Integer id, Model model)
	{
		Book book = bookRepository.findById(id).get();
		boolean flag = false;
		if (session.getAttribute("cartList") != null)
		{	
			cartList = (List<Cart>) session.getAttribute("cartList");
			for(Cart c:cartList)
			{
				if(id == c.getId())
				{	
					c.setQuantity(c.getQuantity()+1);
					flag=true;
					break;
				}
			}
			if(!flag)
			{
				Cart cart = new Cart();
				cart.setId(book.getId());
				cart.setTitle(book.getTitle());
				cart.setIsbn(book.getIsbn());
				cart.setPrice(book.getPrice());
				cart.setImg(book.getImg());
				cart.setFile(book.getFile());
				cart.setDescription(book.getDescription());
				cart.setAuthor(book.getAuthor());
				cart.setCategory(book.getCategory());
				cart.setQuantity(1);
				
				cartList.add(cart);
				session.setAttribute("cartList", cartList);
			
			}
		
		}
		else 
		{
			cartList = new ArrayList<Cart>();
			Cart cart = new Cart();
			cart.setId(book.getId());
			cart.setTitle(book.getTitle());
			cart.setIsbn(book.getIsbn());
			cart.setPrice(book.getPrice());
			cart.setImg(book.getImg());
			cart.setFile(book.getFile());
			cart.setDescription(book.getDescription());
			cart.setAuthor(book.getAuthor());
			cart.setCategory(book.getCategory());
			cart.setQuantity(1);
			
			cartList.add(cart);
			session.setAttribute("cartList", cartList);
			
			
			
		}
		
		
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "redirect:/viewall";
	}
	
	@GetMapping("/addtocart1/{id}")
	public String addtocart1(@PathVariable(name="id") Integer id, Model model)
	{
		Book book = bookRepository.findById(id).get();
		boolean flag = false;
		if (session.getAttribute("cartList") != null)
		{	
			cartList = (List<Cart>) session.getAttribute("cartList");
			for(Cart c:cartList)
			{
				if(id == c.getId())
				{	
					c.setQuantity(c.getQuantity()+1);
					flag=true;
					break;
				}
			}
			if(!flag)
			{
				Cart cart = new Cart();
				cart.setId(book.getId());
				cart.setTitle(book.getTitle());
				cart.setIsbn(book.getIsbn());
				cart.setPrice(book.getPrice());
				cart.setImg(book.getImg());
				cart.setFile(book.getFile());
				cart.setDescription(book.getDescription());
				cart.setAuthor(book.getAuthor());
				cart.setCategory(book.getCategory());
				cart.setQuantity(1);
				
				cartList.add(cart);
				session.setAttribute("cartList", cartList);
			
			}
		
		}
		else 
		{
			cartList = new ArrayList<Cart>();
			Cart cart = new Cart();
			cart.setId(book.getId());
			cart.setTitle(book.getTitle());
			cart.setIsbn(book.getIsbn());
			cart.setPrice(book.getPrice());
			cart.setImg(book.getImg());
			cart.setFile(book.getFile());
			cart.setDescription(book.getDescription());
			cart.setAuthor(book.getAuthor());
			cart.setCategory(book.getCategory());
			cart.setQuantity(1);
			
			cartList.add(cart);
			session.setAttribute("cartList", cartList);
		
			
		}
		
		
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "redirect:/viewall1";
	}
	
	@GetMapping("/orders")
	public String orders(Model model)	
	{
		Customer customer = (Customer) session.getAttribute("customer");
		if(customer == null)
		{
			return "redirect:/customerlogin1";
		}
		else
		{
			Orders order = new Orders();
			order.setOrderdate(new Date());
			order.setCustomer(customer);
			int totalPrice = 0;
			if(cartList != null)
			{
				for(Cart c:cartList)
				{
					totalPrice += c.getPrice() * c.getQuantity();
				}
			}
			order.setTotalprice(totalPrice);
			order.setStatus("Pending");

			Orders saveOrder = ordersRepository.save(order);
			List<OrderDetail> detaillist = new ArrayList<OrderDetail>();
			
			
			for( Cart c: cartList)
			{
				OrderDetail orderdetail = new OrderDetail();
				orderdetail.setQuantity(c.getQuantity());
				orderdetail.setQuotedprice(c.getQuantity()*c.getPrice());
				orderdetail.setBook(new Book(c.getId(), c.getTitle(), c.getIsbn(), c.getPrice(), c.getImg(), c.getFile(), c.getDescription(), c.getAuthor(), c.getCategory() ));
				orderdetail.setOrders(saveOrder);
				orderdetailRepository.save(orderdetail);
				detaillist.add(orderdetail);
			}
			List<Category> categorylist = categoryRepository.findAll();
			model.addAttribute("categorylist", categorylist);
			
			
			model.addAttribute("checkorder", saveOrder);		
			model.addAttribute("detaillist", detaillist);
			
			CustomerInfo customerinfo = new CustomerInfo();
			model.addAttribute("customerinfo", customerinfo);
			
			
			
			
			session.setAttribute("detaillist", detaillist);
			session.setAttribute("totalPrice", totalPrice);
			session.setAttribute("orders", order);
			
			model.addAttribute("bankcusinfo", new BankCusInfo());
			model.addAttribute("bankcard", new Bank());
			return "payment1";
		}
			
	}
	
	///////////////////// save customerinfo /////////////////
	@GetMapping("/savecustomer")
	public String savecustomer(@ModelAttribute CustomerInfo customerinfo, Model model)
	{
		Customer customer = (Customer) session.getAttribute("customer");
		customerinfo.setCustomer(customer);
		
		customerinfoRepository.save(customerinfo);
		
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "success";
	}
	///////////////////// save customer info end /////////////////////////
	
	
	@RequestMapping("/savebank")
	public String card(@ModelAttribute Bank bankcard, Model model)
	{
		Integer totalPrice=(Integer) session.getAttribute("totalPrice");
	
		Bank bankuser = bankRepository.findByCvvcard( bankcard.getCvv(), bankcard.getCardnum());
		
		if(bankuser != null)
		{
			if(totalPrice < bankuser.getBalance())
			{
			
				bankuser.setBalance(bankuser.getBalance()-totalPrice);
				bankRepository.save(bankuser);
				return "success";
			}
			else
			{
			
				return "redirect:/payment";
			}
		}
		return "index";
		
		
	}
	
	@RequestMapping("/savebankcustomer")
	public String saveBankCustomer(@ModelAttribute BankCusInfo bankcus, Model model)
	{
		

		
		Customer customer = (Customer) session.getAttribute("customer");
		CustomerInfo customerinfo = new CustomerInfo();
		customerinfo.setCustomer(customer);
		customerinfo.setStreet(bankcus.getCusfinfo().getStreet());
		customerinfo.setTownship(bankcus.getCusfinfo().getTownship());
		customerinfo.setState(bankcus.getCusfinfo().getState());
		customerinfo.setCountry(bankcus.getCusfinfo().getCountry());
		customerinfo.setPostcode(bankcus.getCusfinfo().getPostcode());
		customerinfoRepository.save(customerinfo);
		model.addAttribute("customerinfo", customerinfo);
		session.setAttribute("customerinfo", customerinfo);

		Integer totalPrice=(Integer) session.getAttribute("totalPrice");
	
		Bank bankuser = bankRepository.findByCvvcard( bankcus.getBank().getCvv(), bankcus.getBank().getCardnum());
		
		if(bankuser != null)
		{
			if(totalPrice < bankuser.getBalance())
			{
				List<OrderDetail> detaillist = (List<OrderDetail>) session.getAttribute("detaillist");
				model.addAttribute("detaillist", detaillist);
				
				model.addAttribute("totalPrice", totalPrice);
				
				Orders order = (Orders) session.getAttribute("orders");
				model.addAttribute("orders",order);
				
				model.addAttribute("customerinfo", customerinfo);
				
				
				
				bankuser.setBalance(bankuser.getBalance()-totalPrice);
				bankRepository.save(bankuser);
				return "receipt";
			}
			else
			{
			
				return "redirect:/orders";
			}
		}
		
		return "index";
		
	}
	
	
	/////////////////////// CV /////////////////////////////////////////
	
	///////////////////// Register /////////////////////////////////
	@GetMapping("/register")
	public String registerform(Model model)
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		model.addAttribute("customer", new Customer());
		return "register";
	}
	
	@PostMapping("/customers/save")
	public String saveCustomer(Customer customer, Model model)
	{
		System.out.println(customer.getName());
		System.out.println(customer.getEmail());
		System.out.println(customer.getAddress());
		System.out.println(customer.getPhone());
//		System.out.println(customer.getPassword());
		
		String pwd = customer.getPassword();
		BCryptPasswordEncoder bencoder = new BCryptPasswordEncoder();
		String enPassword = bencoder.encode(pwd);
		customer.setPassword(enPassword);
		
		customerRepository.save(customer);
		
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "redirect:/customerlogin";
		
	}
	/////////////////// Register END ///////////////////////////////
	//////////////// Customer Login//////////////////////////////
	@RequestMapping("customerlogin")
	public String customerlogin(Model model)
	{
		model.addAttribute("cuslogin", new Customer());
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "customerlogin";
	}
	@RequestMapping("customerlogin1")
	public String customerlogin1(Model model)
	{
		model.addAttribute("cuslogin", new Customer());
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "customerlogin1";
	}
	
	
	@GetMapping("login")
	public String login (@ModelAttribute Customer customer, Model model)
	{
		BCryptPasswordEncoder bencoder = new BCryptPasswordEncoder();
		Customer loginCustomer = customerRepository.findByEmail(customer.getEmail());
		
		
	if(loginCustomer != null && bencoder.matches(customer.getPassword(), loginCustomer.getPassword()))
	{
		session.setAttribute("customer", loginCustomer);
		return "index1";
	}
	else
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "redirect:/customerlogin";
	}
	

		
	}
	

	@GetMapping("login1")
	public String login1 (@ModelAttribute Customer customer)
	{
		BCryptPasswordEncoder bencoder = new BCryptPasswordEncoder();
		Customer loginCustomer = customerRepository.findByEmail(customer.getEmail());
		
		
	if(loginCustomer != null && bencoder.matches(customer.getPassword(), loginCustomer.getPassword()))
	{
		session.setAttribute("customer", loginCustomer);
		return "redirect:/mycart1";
	}
	else
	{
		
		return "redirect:/customerlogin1";
	}
	

		
	}
	
	
	@GetMapping("/logout")
	public String logout(Model model)
	{
	session.setAttribute("customer", null);
	session.removeAttribute("customer");
	List<Category> categorylist = categoryRepository.findAll();
	model.addAttribute("categorylist", categorylist);
	
	return "redirect:/customerlogin";
	
	}
	
	
	
	/////////////// Customer login end /////////////////////////
	
	///////////////////// Delete Cart//////////////////////////
	
	@GetMapping("/delete-cart/{id}")
    public String delCart(@PathVariable(name="id") Integer id, Model model) {
        List<Cart> cartremove= (List<Cart>) session.getAttribute("cartList");
        Iterator<Cart> iterator = cartremove.iterator();
        while (iterator.hasNext()) 
        {
            if (iterator.next().getId()==id) {
                iterator.remove();
            }
        }
        List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		

        return "redirect:/mycart";
    }
	
	/////////////////// Delete Cart END ////////////////////
	
	//////////// Contact Us //////////////////////////////
	

	@RequestMapping("/contact")
	public String contact(Model model) 
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "contact";
	}
	
	/////////// Contact Us END /////////////////
	@RequestMapping("/payment")
	public String payment(Model model) 
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		model.addAttribute("bankcard", new Bank());
		
		return "payment";
	}
	
	private Object Bank() {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping("/payment1")
	public String payment1(Model model) 
	{
		
		
		model.addAttribute("bankcusinfo", new BankCusInfo());
		
		
		
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "payment1";
	}
	
	@RequestMapping("/receipt")
	public String receipt(Model model) 
	{
	
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		
		return "receipt";
	}
	
	
}
