package com.example.board;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {

    //메인
    @RequestMapping("/")
    public String mainpage(Model model){

        return "index";
    }


}//controller
