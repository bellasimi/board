package com.example.board;

import com.example.board.board.Board;
import com.example.board.board.BoardRepository;
import com.example.board.member.Member;
import com.example.board.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class controller {
    @Autowired
    private MemberRepository memberrepository;

    @Autowired
    private BoardRepository boardRepository;

    //메인
    @RequestMapping("/")
    public String mainpage(Model model){
        return "main";
    }
    //회원가입폼
    @RequestMapping("/memberForm")
    public String memberform(Model model){
        return "memberForm";
    }
    //회원가입완료
    @RequestMapping("/memberSave")
    public String memberSave(Member member, Model model) {
        memberrepository.save(member);
        model.addAttribute("name", member.getName());
        model.addAttribute("id", member.getId());
        return "memberSave";
    }
    //회원목록
    @RequestMapping("/memberList")
    public String memberList(Model model){
        List<Member> memberlist = memberrepository.findAll();
        model.addAttribute("list",memberlist);
        return "memberList";
    }
    //로그인폼
    @RequestMapping("/loginform")
    public String loginform(){
        return "login";
    }
    //로그인후 화면
    @RequestMapping("login")
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pw,
                        HttpServletRequest request, Model model){
        List<Member> user = memberrepository.findByIdAndPw(id,pw);
        if(user != null){
            model.addAttribute("id",id);

            HttpSession session = request.getSession();
            session.setAttribute("logId",id);
            return "loginsuccess";
        }
        else{
            model.addAttribute("message","그런 회원은 존재하지 않습니다!!");
            return "login";
        }
    }
    //글작성폼
    @RequestMapping("/write")
    public String writeform(Model model,HttpSession session){
        String id = (String) session.getAttribute("logId");
        /*//세션에 id 저장됐는지 확인
        System.out.println("==================================");
        System.out.printf("아이디는"+id+"입니다");
        System.out.println("==================================");*/
        model.addAttribute("id",id);
        return "writeform";
    }
    //글저장 후 게시판으로
    @RequestMapping("/writeSave")
    public String writesave(Board board,Model model,HttpSession session) {
        boardRepository.save(board);
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("list2", boardList);
        return "boardlist";
    }
    //게시판보기
    @RequestMapping("/boardlist")
    public String boardlist(Board board,Model model){
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("list2",boardList);
        return "boardlist";
    }
    //글읽기
    @RequestMapping("/boarddetail")
    public String readdetail(@RequestParam("seq")int seq,Model model,Board board){
        Optional<Board> boardfind = boardRepository.findById(seq);
        Board detail = boardfind.get();
        model.addAttribute("detail",detail);
        return "boarddetail";
    }
    //게시글 수정폼
    @RequestMapping("/modiform")
    public String modiform(@RequestParam("seq")int seq, HttpSession session,Model model){
       String id= (String) session.getAttribute("logId");
       model.addAttribute("id",id);
       model.addAttribute("seq",seq);
       return "modify";
    }
    //게시글 수정
    @RequestMapping("/modify")
    public String modify(Board board,Model model){
        Date date = new Date();
        SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
        String creDate = date2.format(date);
        board.setCreDate(creDate);
        boardRepository.save(board);
        model.addAttribute("message","수정완료!");

        return "redirect:boardlist";
    }
    //게시글 삭제
    @RequestMapping("/delete")
    public String delete(@RequestParam("seq")int seq,Model model){
        boardRepository.deleteById(seq);
        model.addAttribute("message","삭제완료!");

        return "redirect:boardlist";
    }

}