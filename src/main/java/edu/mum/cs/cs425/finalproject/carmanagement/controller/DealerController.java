package edu.mum.cs.cs425.finalproject.carmanagement.controller;

import edu.mum.cs.cs425.finalproject.carmanagement.model.Dealer;
import edu.mum.cs.cs425.finalproject.carmanagement.model.User;
import edu.mum.cs.cs425.finalproject.carmanagement.service.IDealerService;
import edu.mum.cs.cs425.finalproject.carmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.time.LocalDate;

@Controller
//@RequestMapping(value = "/ecarmanagement/dealer")
public class DealerController {

    @Autowired
    private IDealerService dealerService;

    @Autowired
    private IUserService userService;

    @GetMapping(value={"/ecarmanagement/secured/dealer/list"})
    public ModelAndView listDealers(@RequestParam(defaultValue = "0") int pageno) {
        ModelAndView mav = new ModelAndView();
        Page<Dealer> dealers = dealerService.getAllDealers(pageno);
        mav.addObject("dealers", dealers);
        mav.addObject("dealersCount", dealers.getContent().size());
        mav.addObject("currentPageNo", pageno);
        mav.addObject("now", LocalDate.now());
        mav.setViewName("secured/dealer/list");
        return mav;
    }

    @GetMapping(value={"/ecarmanagement/secured/dealer/new"})
    public String newDealerForm(Model model) {
        model.addAttribute("dealer", new Dealer());
        model.addAttribute("now", LocalDate.now());
        return "secured/dealer/new";
    }

    @PostMapping(value = {"/ecarmanagement/secured/dealer/new"})
    public String registerNewDealer(
            @Valid
            @ModelAttribute("dealer")
            Dealer dealer,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/dealer/new";
        }
        dealerService.registerNewDealer(dealer);
        return "redirect:/ecarmanagement/secured/dealer/list";
    }

    @GetMapping(value = {"/ecarmanagement/secured/dealer/edit/{dealerId}"})
    public String editDealer(@PathVariable Long dealerId, Model model) {
        Dealer dealer = dealerService.getDealerById(dealerId);
        if (dealer != null) {
            model.addAttribute("dealer", dealer);
            return "secured/dealer/edit";
        }
        return "secured/dealer/list";
    }

    @PostMapping(value = {"/ecarmanagement/secured/dealer/edit"})
    public String updateDealer(@Valid @ModelAttribute("dealer") Dealer dealer,
                               @RequestParam(defaultValue = "0") Integer userId,
                                 BindingResult bindingResult, Model model) {
        User user = userService.getUserById(userId);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/dealer/edit";
        }
        dealer.setUser(user);
        dealer = dealerService.saveDealer(dealer);
        return "redirect:/ecarmanagement/secured/dealer/list";
    }

    @GetMapping(value = {"/ecarmanagement/secured/dealer/delete/{dealerId}"})
    public String deleteDealer(@PathVariable Long dealerId, Model model) {
        Dealer dealer = dealerService.getDealerById(dealerId);
        if(dealer.getUser().getId() != null) {
            userService.deleteUserById(dealer.getUser().getId());
        }
        else {
            dealerService.deleteDealerById(dealerId);
        }
        return "redirect:/ecarmanagement/secured/dealer/list";
    }

    @RequestMapping(value = "/ecarmanagement/secured/dealer/search", method = RequestMethod.GET)
    public ModelAndView searchDealers(@RequestParam(value = "searchString", required = false) String searchString, @RequestParam(defaultValue = "0") int pageno, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Page<Dealer> dealers = dealerService.searchDealers(searchString, pageno);
        modelAndView.addObject("dealers", dealers);
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("dealersCount", dealers.getContent().size());
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secured/dealer/list");
        return modelAndView;
    }
}
