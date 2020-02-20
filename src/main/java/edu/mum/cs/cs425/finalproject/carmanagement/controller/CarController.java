package edu.mum.cs.cs425.finalproject.carmanagement.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import edu.mum.cs.cs425.finalproject.carmanagement.model.*;
import edu.mum.cs.cs425.finalproject.carmanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import edu.mum.cs.cs425.finalproject.carmanagement.model.Car;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Condition;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Make;
import edu.mum.cs.cs425.finalproject.carmanagement.model.CarModel;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Style;
import edu.mum.cs.cs425.finalproject.carmanagement.service.CarService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.ConditionService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.IDealerService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.MakeService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.SecurityService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.CarModelService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.StyleService;


@Controller
public class CarController {
	
	@Autowired 
	private CarService carService;
	
	@Autowired 
	private IDealerService dealerService;
	
	@Autowired 
	private ConditionService conditionService;
	
	@Autowired 
	private MakeService makeService;
	
	@Autowired 
	private CarModelService carModelService;
	
	@Autowired 
	private StyleService styleService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/ecarmanagement/car/favorite")
    public ModelAndView favoriteCars(@RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        String username = securityService.getCurrentUserName();
        Customer customer = securityService.getCustomerByUserName(username);
//        Page<Car> cars = this.carService.getAllCarsPaged(pageno);
        List<Car> cars = customer.getSavedCars();
        modelAndView.addObject("cars", cars);

        modelAndView.addObject("carsCount", cars.size());
//        modelAndView.addObject("currentPageNo", pageno);
//        modelAndView.addObject("now", LocalDate.now());
        modelAndView.setViewName("secured/car/favorite");
        return modelAndView;
    }

    @GetMapping(value = {"/ecarmanagement/car/save/{carId}"})
    public String addFavoriteCar(@PathVariable Long carId, Model model) {
        Car car = carService.getCarById(carId);
        String username = securityService.getCurrentUserName();
        Customer customer = securityService.getCustomerByUserName(username);
        customer.addFavoriteCar(car);
        customerService.saveCustomer(customer);
        return "redirect:/ecarmanagement/car/detail/"+carId;
    }

    @GetMapping(value = "/ecarmanagement/car/detail/{carId}")
    public String viewCarDetail(@PathVariable Long carId, Model model) {
        Car car = carService.getCarById(carId);
        if (car != null) {
            model.addAttribute("car", car);
            List<Condition> conditions = conditionService.getAllConditions();
            List<Make> makes = makeService.getAllMakes();
            List<CarModel> carModels = carModelService.getAllCarModels();
            List<Style> styles = styleService.getAllStyles();
            model.addAttribute("conditions", conditions);
            model.addAttribute("makes", makes);
            model.addAttribute("carModels", carModels);
            model.addAttribute("styles", styles);
            model.addAttribute("years", carService.getYears());
            String username = securityService.getCurrentUserName();
            if(username == null || username.equals("anonymousUser")) {
                return "public/car/detail";
            } else {
                return "secured/car/detail";
            }
        }
        return "public/car/search";
    }

    @GetMapping(value = "/ecarmanagement/car/search")
    public ModelAndView searchCars(@RequestParam(defaultValue = "0") int makeCode,
                                   @RequestParam(defaultValue = "0") int modelCode,
                                   @RequestParam(defaultValue = "") String zip) {
        ModelAndView modelAndView = new ModelAndView();
        Make make = this.makeService.findMakeById(makeCode);
        CarModel model = this.carModelService.findCarModelById(modelCode);
        List<Car> cars = this.carService.searchCars(make, model, zip);
        List<Make> makes = makeService.getAllMakes();
        List<CarModel> carModels = carModelService.getAllCarModels();
        modelAndView.addObject("cars", cars);
        modelAndView.addObject("searchResultTotal", cars.size());
        modelAndView.addObject("makeCode", make);
        modelAndView.addObject("modelCode", model);
        modelAndView.addObject("makes", makes);
        modelAndView.addObject("carModels", carModels);
        modelAndView.addObject("zipCode", zip);
        String username = securityService.getCurrentUserName();
        if(username == null || username.equals("anonymousUser")) {
            modelAndView.setViewName("public/car/search");
        } else {
            modelAndView.setViewName("secured/car/search");
        }
        return modelAndView;
    }

	@GetMapping(value = "/ecarmanagement/secured/car/list")
	public ModelAndView listCars(@RequestParam(defaultValue = "0") int pageno) {
		ModelAndView modelAndView = new ModelAndView();
		Page<Car> cars = null;
		if(securityService.isAdmin())
			cars = this.carService.getAllCarsPaged(pageno);
		else if(securityService.isDealer()) {
			String userName = securityService.getCurrentUserName();
			Dealer dealer = securityService.getDealerByUserName(userName);
			cars = this.carService.getAllCarsPagedByDealer(pageno, dealer);
		}
			
        modelAndView.addObject("cars", cars);        
        modelAndView.addObject("carsCount", cars.getTotalPages());        
        modelAndView.addObject("currentPageNo", pageno); 
        modelAndView.addObject("now", LocalDate.now());
        modelAndView.setViewName("secured/car/list");
        
        boolean isDealer = securityService.isDealer();
        return modelAndView;
	}
	
	@GetMapping(value = {"/ecarmanagement/secured/car/new"})
    public String displayNewCarForm(Model model) {
        List<Condition> conditions = conditionService.getAllConditions();
        List<Make> makes = makeService.getAllMakes();
        List<CarModel> carModels = carModelService.getAllCarModels();
        List<Style> styles = styleService.getAllStyles();
        List<Dealer> dealers = dealerService.getAllDealers();
        model.addAttribute("car", new Car());
        model.addAttribute("dealers", dealers);  
        model.addAttribute("conditions", conditions);  
        model.addAttribute("makes", makes);  
        model.addAttribute("carModels", carModels);  
        model.addAttribute("styles", styles);  
        model.addAttribute("years", carService.getYears());  
        model.addAttribute("now", LocalDate.now());       
        
        return "secured/car/new";
    }
	
	
	

	@PostMapping(value = {"/ecarmanagement/secured/car/new"})
    public String addNewCar(HttpServletRequest request, @RequestParam("carImg") MultipartFile carImg, @Valid @ModelAttribute("car") Car car,    								    		
                                     BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            List<Dealer> dealers = dealerService.getAllDealers();
            List<Condition> conditions = conditionService.getAllConditions();
            List<Make> makes = makeService.getAllMakes();
            List<CarModel> carModels = carModelService.getAllCarModels();
            List<Style> styles = styleService.getAllStyles();
            model.addAttribute("dealers", dealers);  
            model.addAttribute("conditions", conditions);  
            model.addAttribute("makes", makes);  
            model.addAttribute("carModels", carModels);  
            model.addAttribute("styles", styles);  
            model.addAttribute("years", carService.getYears());
            model.addAttribute("now", LocalDate.now());  
            return "secured/car/new";
        }
		String imgPath = doUpload(request, carImg);
		if(imgPath != null)
			car.setImagePath(imgPath);
		
		if(securityService.isDealer()) {
			String userName = securityService.getCurrentUserName();
			Dealer dealer = securityService.getDealerByUserName(userName);
			car.setDealer(dealer);
		}
        carService.saveCar(car);
        return "redirect:/ecarmanagement/secured/car/list";
    }
	
	@GetMapping(value = {"/ecarmanagement/secured/car/edit/{carId}"})
    public String editCar(@PathVariable Long carId, Model model) {
        Car car = carService.getCarById(carId);
        if (car != null) {
        	model.addAttribute("car", car);
        	List<Dealer> dealers = dealerService.getAllDealers();
        	List<Condition> conditions = conditionService.getAllConditions();
            List<Make> makes = makeService.getAllMakes();
            List<CarModel> carModels = carModelService.getAllCarModels();
            List<Style> styles = styleService.getAllStyles();
            model.addAttribute("dealers", dealers);  
            model.addAttribute("conditions", conditions);  
            model.addAttribute("makes", makes);  
            model.addAttribute("carModels", carModels);  
            model.addAttribute("styles", styles); 
            model.addAttribute("years", carService.getYears());
            return "secured/car/edit";
        }
        return "secured/car/list";
    }

    @PostMapping(value = {"/ecarmanagement/secured/car/edit"})
    public String updateCar(HttpServletRequest request, @RequestParam("carImg") MultipartFile carImg, @Valid @ModelAttribute("car") Car car,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("errors", bindingResult.getAllErrors());
        	List<Dealer> dealers = dealerService.getAllDealers();
        	List<Condition> conditions = conditionService.getAllConditions();
            List<Make> makes = makeService.getAllMakes();
            List<CarModel> carModels = carModelService.getAllCarModels();
            List<Style> styles = styleService.getAllStyles(); 
            model.addAttribute("dealers", dealers); 
            model.addAttribute("conditions", conditions);  
            model.addAttribute("makes", makes);  
            model.addAttribute("carModels", carModels);  
            model.addAttribute("styles", styles); 
            model.addAttribute("years", carService.getYears());
            return "secured/car/edit";
        }
        String imgPath = doUpload(request, carImg);
		if(imgPath != null)
			car.setImagePath(imgPath);
		
        if(securityService.isDealer()) {
			String userName = securityService.getCurrentUserName();
			Dealer dealer = securityService.getDealerByUserName(userName);
			car.setDealer(dealer);
		}
        
        car = carService.saveCar(car);
        return "redirect:/ecarmanagement/secured/car/list";
    }
    
    @GetMapping(value = {"/ecarmanagement/secured/car/delete/{carId}"})
    public String deleteCar(@PathVariable Long carId, Model model) {
    	carService.deleteCarById(carId);
        return "redirect:/ecarmanagement/secured/car/list";
    }
    
  //no paging
//	@GetMapping(value = "/ecarmanagement/secured/car/search")
//	public ModelAndView searchCars(@RequestParam String searchString) {
//		ModelAndView modelAndView = new ModelAndView();
//		List<Car> cars = this.carService.searchCars(searchString);
//        modelAndView.addObject("cars", cars);
//        modelAndView.addObject("searchString", "");
//        modelAndView.addObject("carsCount", cars.size());        
//        modelAndView.setViewName("car/list");
//        return modelAndView;
//	}
	
//	@GetMapping(value = "/ecarmanagement/secured/car/search")
//	public ModelAndView searchCars(@RequestParam(defaultValue = "0") int pageno, @RequestParam String searchString) {
//		ModelAndView modelAndView = new ModelAndView();
//		Page<Car> cars = this.carService.searchCarsPaged(pageno, searchString);
//        modelAndView.addObject("cars", cars);
//        modelAndView.addObject("searchString", searchString);
//        modelAndView.addObject("carsCount", cars.getSize());
//        modelAndView.addObject("currentPageNo", pageno);
//        modelAndView.setViewName("car/list");
//        return modelAndView;
//	}
    
    private String doUpload(HttpServletRequest request, MultipartFile carImg) {
         
         // Root Directory. 
		String reletiveFolder = File.separator + "upload";
		String uploadRootPath = request.getServletContext().getRealPath(reletiveFolder);
         
    
         File uploadRootDir = new File(uploadRootPath);
         // Create directory if it not exists.
         if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
         }        
    
        //carImg           
        String fileName = carImg.getOriginalFilename();
        String imgPath = null;
        if (fileName != null && fileName.length() > 0) {
           try {
        	  String filePath = uploadRootDir.getAbsolutePath() + File.separator + fileName;
              // Create the file at server
              File serverFile = new File(filePath);

              BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
              stream.write(carImg.getBytes());
              stream.close();
              imgPath = reletiveFolder + File.separator + fileName;
           } catch (Exception e) {              
           }
        }     
     
        return imgPath;
    }    

}
