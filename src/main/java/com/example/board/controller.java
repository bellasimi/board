package com.example.board;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class controller {

    //landing page
    @RequestMapping("/")
    public String index(Model model){

        return "index";
    }
    //main
    @RequestMapping("/main")
    public String mainPage(HttpSession session,Model model){
        String id = (String) session.getAttribute("logId");
        if(id!=null){
            model.addAttribute("id",id);
            return "member/loginsuccess";
        }
        else{
            return "index";
        }
    }

}//controller
