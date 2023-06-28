package com.software.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

import com.software.demo.entity.Admin;
import com.software.demo.entity.Author;
import com.software.demo.entity.Book;
import com.software.demo.entity.Category;
import com.software.demo.entity.Customer;
import com.software.demo.entity.Income;
import com.software.demo.entity.OrderDetail;
import com.software.demo.entity.Orders;
import com.software.demo.repository.AdminRepository;
import com.software.demo.repository.AuthorRepository;
import com.software.demo.repository.BookRepository;
import com.software.demo.repository.CategoryRepository;
import com.software.demo.repository.CustomerRepository;
import com.software.demo.repository.OrderDetailRepository;
import com.software.demo.repository.OrdersRepository;


@Controller
public class OneToManyController {
	@Autowired
	private HttpSession session;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private OrderDetailRepository orderdetailRepository;
	@Autowired
	private AdminRepository adminRepository;
	
	@RequestMapping("/index")
	public String index() 
	{
		return "index";
	}
	
	//////////////////////////Author Start////////////////////////////////////////
	
	@GetMapping("/authors")
	public String author(Model model)
	{
		Author author = new Author();
		model.addAttribute("author", author);
	
		
		return "author";
	}
	
	@PostMapping("/authors/save")
	public String saveAuthor(Author author)
	{
		
		authorRepository.save(author);
		return "success";
	}
	
	@GetMapping("/viewauthor")
	public String viewauthor(Model model)
	{
		List<Author> authorlist = authorRepository.findAll();
		model.addAttribute("authorlist", authorlist);
		return "viewauthor";
	}
	@GetMapping("/editauthor/{id}")
	public String editauthor(@PathVariable(name="id") Integer id, Model model)
	{	
	
		model.addAttribute("author", authorRepository.findById(id));
		return "editauthor";
		
	}
	
	@RequestMapping("/updateauthor")
	public String updateauthor(@ModelAttribute Author author)
	{
		Author updateauthor = authorRepository.findById(author.getId()).get();
		updateauthor.setName(author.getName());
		updateauthor.setPassword(author.getPassword());
		updateauthor.setEmail(author.getEmail());
		authorRepository.save(updateauthor);
		return "redirect:/viewauthor";
	}
	
	@GetMapping("/deleteauthor/{id}")
	public String deleteauthor(@PathVariable(name="id") Integer id)
	{
		authorRepository.deleteById(id);
		return "redirect:/viewauthor";
	}
	
	@RequestMapping("/searchauthor")
	public String searchauthor(@RequestParam (name = "search") String Search, Model model)
	{
		List<Author> searchList = authorRepository.findByName(Search);
		model.addAttribute("authorlist", searchList);
		return "viewauthor";
		
	}
	
//////////////////////////Author End////////////////////////////////////////
	
	////////////////////////////////Book Start///////////////////////////////////////////
	@GetMapping("/books")
	public String book(Model model)
	{	
		Book book = new Book();
		model.addAttribute("book", book);
		List<Category> categories = categoryRepository.findAll();
		List<Author> authors = authorRepository.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("authors", authors);
		return "book";
	}
	
	@PostMapping("/books/save")
	public String saveBook(Book book,@RequestParam("picture") MultipartFile pfile,@RequestParam("pdf") MultipartFile ffile,Model model) throws IOException
	{	
			String fname = StringUtils.cleanPath(pfile.getOriginalFilename());
			String ffname = StringUtils.cleanPath(ffile.getOriginalFilename());
			
			String uploadDir ="uploads/";
			book.setImg(fname);
			book.setFile(ffname);
			Path fpath = Paths.get(uploadDir);
			Path uploadedFile= null;
			Path uploadedFile1= null;
			if (!Files.exists(fpath))
				{
				 Path  upPath=Files.createDirectories(fpath);
				 uploadedFile =upPath.resolve(fname);
				 Files.copy(pfile.getInputStream(), uploadedFile,StandardCopyOption.REPLACE_EXISTING);
				 uploadedFile1 =upPath.resolve(ffname);
				 Files.copy(ffile.getInputStream(), uploadedFile1,StandardCopyOption.REPLACE_EXISTING);
				}
			else {
				uploadedFile = fpath;
				uploadedFile1 = fpath;
			// store the uploaded file into  that path
				 uploadedFile =uploadedFile.resolve(fname);
			// store the uploaded file onto the server 
			Files.copy(pfile.getInputStream(), uploadedFile,StandardCopyOption.REPLACE_EXISTING);
			
		
			// store the uploaded file into  that path
				 uploadedFile1 =uploadedFile1.resolve(ffname);
			// store the uploaded file onto the server 
			Files.copy(ffile.getInputStream(), uploadedFile1,StandardCopyOption.REPLACE_EXISTING);
		
			bookRepository.save(book);
			
			}
			return "success";
			
		}
			
		
	@GetMapping("/viewbook")
	public String viewbook(Model model, HttpServletRequest request)
	{
//		 	int page = 0;
//		    int size = 4;
//		    
//		    if (request.getParameter("page") != null && !request.getParameter("page").isEmpty())
//		    {
//		      page = Integer.parseInt(request.getParameter("page")) - 1;
//		    }
//		    
//		    if (request.getParameter("size") != null && !request.getParameter("size").isEmpty())
//		    {
//		      page = Integer.parseInt(request.getParameter("size"));
//		    }
		    
		List<Book> booklist = bookRepository.findAll();
		model.addAttribute("booklist", booklist);
		    
//		model.addAttribute("booklist", bookRepository.findAll(PageRequest.of(page, size)));
		return "viewbook";
	}
	
	@GetMapping("/editbook/{id}")
	public String editbook(@PathVariable(name="id") Integer id, Model model)
	{	
		List<Category> categories = categoryRepository.findAll();
		List<Author> authors = authorRepository.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("authors", authors);
		model.addAttribute("book", bookRepository.findById(id));
		return "editbook";
		
	}
	
	@PostMapping("/updatebook")
	public String updatebook(@ModelAttribute Book book,@RequestParam("picture") MultipartFile pfile,@RequestParam("pdf") MultipartFile ffile,Model model) throws IOException
	{
		Book updatebook = bookRepository.findById(book.getId()).get();
		updatebook.setTitle(book.getTitle());
		updatebook.setIsbn(book.getIsbn());
		updatebook.setPrice(book.getPrice());
		updatebook.setAuthor(book.getAuthor());
		updatebook.setCategory(book.getCategory());
		updatebook.setImg(book.getImg());
		
		String fname = StringUtils.cleanPath(pfile.getOriginalFilename());
		String ffname = StringUtils.cleanPath(ffile.getOriginalFilename());
		String uploadDir ="uploads/";
		book.setImg(fname);
		book.setFile(ffname);
		Path fpath = Paths.get(uploadDir);
		Path uploadedFile= null;
		Path uploadedFile1= null;
		if (!Files.exists(fpath))
			{
			 Path  upPath=Files.createDirectories(fpath);
			 uploadedFile =upPath.resolve(fname);
			 Files.copy(pfile.getInputStream(), uploadedFile,StandardCopyOption.REPLACE_EXISTING);
			 uploadedFile1 =upPath.resolve(ffname);
			 Files.copy(ffile.getInputStream(), uploadedFile1,StandardCopyOption.REPLACE_EXISTING);
			}
		else {
			uploadedFile = fpath;
			uploadedFile1 = fpath;
		// store the uploaded file into  that path
			 uploadedFile =uploadedFile.resolve(fname);
		// store the uploaded file onto the server 
		Files.copy(pfile.getInputStream(), uploadedFile,StandardCopyOption.REPLACE_EXISTING);
		
	
		// store the uploaded file into  that path
			 uploadedFile1 =uploadedFile1.resolve(ffname);
		// store the uploaded file onto the server 
		Files.copy(ffile.getInputStream(), uploadedFile1,StandardCopyOption.REPLACE_EXISTING);
	
		bookRepository.save(book);
		
		}
	
		bookRepository.save(updatebook);
		
		
		
		return "redirect:/viewbook";
	}
	
	@GetMapping("/deletebook/{id}")
	public String deletebook(@PathVariable(name="id") Integer id)
	{
		bookRepository.deleteById(id);
		return "redirect:/viewbook";
	}
	
	
	@RequestMapping("/searchbook")
	public String searchbook(@RequestParam (name = "search") String Search, Model model)
	{
		List<Book> searchList = bookRepository.findByTitle(Search);
		model.addAttribute("booklist", searchList);
		
		
		return "viewbook";
		
	}
	/////////////////////////////// Book End /////////////////////////////////////////
	@GetMapping("/categories")
	public String category(Model model)
	{
		Category category = new Category();
		model.addAttribute("category", category);
		return "category";
	}
	
	@PostMapping("/categories/save")
	public String saveCategory(Category category)
	{
		categoryRepository.save(category);
		return "success";
	}
	@GetMapping("/viewcategory")
	public String viewcategory(Model model)
	{
		List<Category> categorylist = categoryRepository.findAll();
		model.addAttribute("categorylist", categorylist);
		return "viewcategory";
	}
	
	@GetMapping("/editcategory/{id}")
	public String editcategory(@PathVariable(name="id") Integer id, Model model)
	{	
	
		model.addAttribute("category", categoryRepository.findById(id));
		return "editcategory";
		
	}
	
	@RequestMapping("/updatecategory")
	public String updatecategory(@ModelAttribute Category category)
	{
		Category updatecategory = categoryRepository.findById(category.getId()).get();
		updatecategory.setName(category.getName());
		
		categoryRepository.save(updatecategory);
		return "redirect:/viewcategory";
	}
	
	@GetMapping("/deletecategory/{id}")
	public String deletecategory(@PathVariable(name="id") Integer id)
	{
		categoryRepository.deleteById(id);
		return "redirect:/viewcategory";
	}
	@RequestMapping("/searchcategory")
	public String searchcategory(@RequestParam (name = "search") String Search, Model model)
	{
		List<Category> searchList = categoryRepository.findByName(Search);
		model.addAttribute("categorylist", searchList);
		return "viewcategory";
		
	}
	
	////////////// Customer /////////////////////////
//	@GetMapping("/deletecategory/{id}")
//	public String deletecategory(@PathVariable(name="id") Integer id)
//	{
//		categoryRepository.deleteById(id);
//		return "redirect:/viewcategory";
//	}
	@GetMapping("/viewcustomer")
	public String viewcustomer(Model model)
	{
		List<Customer> customerlist = customerRepository.findAll();
		model.addAttribute("customerlist", customerlist);
		return "viewcustomer";
	}
	
	@GetMapping("deletecustomer/{id}")
	public String deletecustomer(@PathVariable(name="id") Integer id)
	{
		customerRepository.deleteById(id);
		return "redirect:/viewcustomer";
	}
	
	///////////// Customer End ///////////////////
	
	
	
	
	
	
	
////////////////////////View ALl//////////////////////////////////////////////////////
	@RequestMapping("/viewall")
	public String viewall() 
	{
		return "viewall";
	}
	@GetMapping("/viewall")
	public String viewall(Model model)
	{
		List<Book> booklist = bookRepository.findAll();
		model.addAttribute("booklist", booklist);
		return "viewall";
	}
	@GetMapping("/vieworder")
	public String vieworder(Model model)
	{	
		List<Orders> orderList = new ArrayList<Orders>();
		orderList = ordersRepository.findAll();
		model.addAttribute("orderlist", orderList);
		return "ViewOrder";
	}
	@GetMapping("/details/{id}")
	public String Details(Model model, @PathVariable(name="id") Integer id)
	{	
		
		Orders orders = ordersRepository.findById(id).get();
		model.addAttribute("orders", orders);
		List<OrderDetail> dataList = orderdetailRepository.findByOrderId(id);
		model.addAttribute("detailslist", dataList);
		return "details";
	}
	
	
	@GetMapping("/confirm/{id}")
	public String confirm(@PathVariable(name="id") Integer id, @ModelAttribute Orders order)
	{
		Orders confirm = ordersRepository.findById(id).get();
		confirm.setStatus("Confirm");
		ordersRepository.save(confirm);
		return "redirect:/vieworder";
	}
	//////////////////////////////Admin Login//////////////////////////
	@RequestMapping("/adminlogin")
	public String adminlogin(Model model)
	{
		model.addAttribute("adminloginn", new Admin());
		return "adminlogin";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute Admin admin)
	{	
	Admin loginadmin = adminRepository.findByEmailandPassword(admin.getEmail(), admin.getPassword());
		
	if(loginadmin != null)
	{
		session.setAttribute("admin", loginadmin);
		return "index";
	}
	else 
	{
		return "redirect:/adminlogin";
	}
//	return "index";
	
		
	}
	
////////////////////////Log out Start /////////////////////////////
	
	@GetMapping("/logout")
	public String logout(Model model)
	{
	session.removeAttribute("admin");
	
	return "redirect:/adminlogin";
	}

//////////////////////// Log out End //////////////////////////////
	
	////////////////////////// Admin Login End////////////////////////
	
	
	///////////////////// Daily Income Start //////////////////////////////
	@RequestMapping("/dailyincome")
	public String dailyincome(Model model) 
	{
		Map<Date, Integer> mapDaily = new HashMap<Date, Integer>();
		
		List<Income> incomeList = ordersRepository.findByDailyIncome();
		for(Income income: incomeList)
		{
			mapDaily.put(income.getOrderDate(), income.getTotalPrice());
		}
		model.addAttribute("mapDaily", mapDaily);
		model.addAttribute("incomeList" , incomeList); 

			return "dailyincome";
	}
	///////////////////// Daily Income End //////////////////////////////
	
	///////////////////// Monthly Income Start //////////////////////////////
	@RequestMapping("/monthlyincome")
	public String monthlyincome(Model model) 
	{
		  Map<String, Integer> mapMonthly = new HashMap<String, Integer>();
		    List <Income> monthly = ordersRepository.findByMonthlyIncome();
		    for(Income income: monthly)
		    {
		      mapMonthly.put(income.getMonth()+" - "+income.getYear(), income.getTotalPrice());
		    }
		    model.addAttribute("mapMonthly" , mapMonthly);

		    model.addAttribute("monthly", monthly);
		    return "monthlyincome";
	}
	///////////////////// Monthly Income End //////////////////////////////
	
	///////////////////// Year Income Start //////////////////////////////
	@RequestMapping("/yearlyincome")
	public String yearlyincome(Model model) 
	
	{	
		Map<String, Integer> mapYearly = new HashMap<String, Integer>();
		List<Income> yearlist = ordersRepository.findByYearlyIncome();
		for(Income income: yearlist)
		{
			mapYearly.put(income.getYear(), income.getTotalPrice());
		}
		model.addAttribute("mapYearly", mapYearly);
		model.addAttribute("yearlist" , yearlist);
			return "yearlyincome";
	}
	
	@GetMapping("/")
	public String getPieChart(Model model)
	{
		
		return"yearlyincome";
	}
	///////////////////// Year Income End //////////////////////////////
	
	
	
	
}
