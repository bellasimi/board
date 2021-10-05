package com.example.board.mypage;

import com.example.board.board.Board;
import com.example.board.board.BoardRepository;
import com.example.board.board.Reply;
import com.example.board.board.ReplyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MypageController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    ReplyRepository replyRepository;

    /* 마이페이지 */
    @RequestMapping("/mypage")
    public String mypage(HttpSession session,Model model){
        String id = (String) session.getAttribute("logId");
        List<Board> myBoard = boardRepository.findById(id);
        List<Reply> myReply = replyRepository.findAllById(id);
        model.addAttribute("myBoardList",myBoard);
        model.addAttribute("myReplyList",myReply);
        model.addAttribute("id",id);

        return "mypage/mypage";
    }


}