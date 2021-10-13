package com.example.board.admin;

import com.example.board.board.Board;
import com.example.board.board.BoardRepository;
import com.example.board.board.ReplyRepository;
import com.example.board.member.Member;
import com.example.board.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    ReplyRepository replyRepository;

    /*관리자 게시판*/
    @RequestMapping("/adminboard")
    public  String adminboard(Model model, HttpSession session){
        List<Board> boardList =boardRepository.findAll();
        model.addAttribute("list",boardList);
        String id = (String) session.getAttribute("logId");
        model.addAttribute("id",id);
        return "admin/adminboard";
    }
    /*관리자 게시판삭제 */
    @RequestMapping("/adminBDel")
     public String adminBDel(@RequestParam("seq")int seqBoard, HttpSession session,Model model){
        boardRepository.deleteById(seqBoard);
        return "redirect:adminboard";
    }

    /*관리자 회원관리*/
    @RequestMapping("/memberList")
    public String memberList(Model model,HttpSession session){
        List<Member> memberlist = memberRepository.findAll();
        model.addAttribute("list",memberlist);
        String id = (String) session.getAttribute("logId");
        model.addAttribute("id",id);
        return "admin/memberList";
    }

    /*관리자 회원 강퇴*/
    @RequestMapping("delMember")
    public String delMember(@RequestParam("seq")int seq,HttpSession session,Model model){
        memberRepository.deleteById(seq);
        String id = (String) session.getAttribute("logId");
        model.addAttribute("id",id);
        return "redirect:memberList";
    }
}
