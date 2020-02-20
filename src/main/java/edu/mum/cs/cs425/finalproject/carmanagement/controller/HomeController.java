package edu.mum.cs.cs425.finalproject.carmanagement.controller;

import edu.mum.cs.cs425.finalproject.carmanagement.model.CarModel;
import edu.mum.cs.cs425.finalproject.carmanagement.model.Make;
import edu.mum.cs.cs425.finalproject.carmanagement.service.CarModelService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.MakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MakeService makeService;

    @Autowired
    private CarModelService carModelService;

    @GetMapping(value = {"/ecarmanagement"})
    public String index(Model model) {
        List<Make> makes = makeService.getAllMakes();
        List<CarModel> carModels = carModelService.getAllCarModels();
        model.addAttribute("makes", makes);
        model.addAttribute("carModels", carModels);
        return "public/home/index";
    }

    @GetMapping(value = {"/ecarmanagement/home"})
    public String home(Model model) {
        List<Make> makes = makeService.getAllMakes();
        List<CarModel> carModels = carModelService.getAllCarModels();
        model.addAttribute("makes", makes);
        model.addAttribute("carModels", carModels);
        return "public/home/home";
    }

    @GetMapping(value = {"/ecarmanagement/login"})
    public String login() {
        return "public/home/login";
    }

    @GetMapping(value = {"/ecarmanagement/about"})
    public String about() {
        return "public/home/about";
    }

    @GetMapping(value = {"/ecarmanagement/public/virtualtour"})
    public String virtualtour() {
        return "public/home/virtualtour";
    }
}
