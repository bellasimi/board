package com.example.board.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    //회원가입폼
    @RequestMapping("/memberForm")
    public String memberform(Model model){

        return "member/memberForm";
    }
    /*id 중복체크*/
    @RequestMapping("/checkId")
    @ResponseBody
    public String checkId(@RequestParam("id")String id){
        Member member = memberRepository.findById(id);
        if(member != null){
            return "사용 불가능한 ID입니다!";
        }
        else {
            return "사용가능한 ID입니다.";
        }
    }
    //회원가입완료
    @RequestMapping("/memberSave")
    public String memberSave(Member member ,Model model) {
        String id = member.getId();
        Member user = memberRepository.findById(id);
        if ( id == "admin") {
            model.addAttribute("message", "admin은 사용 불가능한 ID입니다.");
            return "member/memberForm";
        }
        else if(user != null){
            model.addAttribute("message", "사용 불가능한 ID입니다.");
            return "member/memberForm";
        }
        else {

            memberRepository.save(member);
            model.addAttribute("name", member.getName());
            model.addAttribute("id", member.getId());
            return "member/memberSave";

        }
    }


    /*ID 찾기폼(전번)*/
    @RequestMapping("/searchId")
    public String searchId(){
        return "member/searchIdForm";
    }
    /*ID 찾기*/
    @RequestMapping("/findId")
    public String findId(@RequestParam("tel")String tel,Model model){
        Member user = memberRepository.findByTel(tel);
        if(user != null){
            model.addAttribute("id",user.getId());
            model.addAttribute("name",user.getName());
            return "member/findId";
        }
        else{
            model.addAttribute("message","그런회원은 존재하지 않습니다!!");
            return "member/login";
        }
    }
    /*비밀번호 찾기 폼*/
    @RequestMapping("/searchPw")
    public String searchPw(){

        return "member/searchPwForm";
    }

    /*비밀번호 찾기(아이디,전번)*/
    @RequestMapping("/findPw")
    public String findPw(@RequestParam("id")String id, @RequestParam("tel")String tel,Model model ){
        Member user = memberRepository.findById(id);
        if(user != null){
            model.addAttribute("id",id);
            model.addAttribute("pw",user.getPw());
            return "member/findPw";
        }
        else{
            model.addAttribute("message","그런 회원은 존재하지 않습니다! 다시 입력하세요!");
            return "member/login";
        }

    }

    //로그인폼
    @RequestMapping("/loginform")
    public String loginform(){

        return "member/login";
    }
    //로그인후 화면
    @RequestMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pw,
                        HttpServletRequest request, Model model){
        List<Member> user = memberRepository.findByIdAndPw(id,pw);
        System.out.println("유저: "+ user);
        if(user.size() == 0){
            model.addAttribute("message","그런 회원은 존재하지 않습니다!!");
            return "member/login";
        }
        else{
            model.addAttribute("id",id);
            HttpSession session = request.getSession();
            session.setAttribute("logId",id);
            return "member/loginsuccess";
        }
    }
    //로그아웃
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }

}
