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
    public  String adminboard(Model model){
        List<Board> boardList =boardRepository.findAll();
        model.addAttribute("list",boardList);
        return "admin/adminboard";
    }
    /*관리자 게시판삭제 */
    @RequestMapping("/adminBDel")
     public String adminBDel(@RequestParam("seq")int seqBoard){
        boardRepository.deleteById(seqBoard);
        return "admin/adminboard";
    }

    /*관리자 회원관리*/
    @RequestMapping("/memberList")
    public String memberList(Model model){
        List<Member> memberlist = memberRepository.findAll();
        model.addAttribute("list",memberlist);
        return "admin/memberList";
    }

    /*관리자 회원 강퇴*/
    @RequestMapping("delMember")
    public String delMember(@RequestParam("seq")int seq){
        memberRepository.deleteById(seq);
        return "admin/memberList";
    }
}
