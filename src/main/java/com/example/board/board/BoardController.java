package com.example.board.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    ReplyRepository replyRepository;

    //글작성폼
    @RequestMapping("/write")
    public String writeform(Model model, HttpSession session){
        String id = (String) session.getAttribute("logId");
        /*//세션에 id 저장됐는지 확인
        System.out.println("==================================");
        System.out.printf("아이디는"+id+"입니다");
        System.out.println("==================================");*/
        model.addAttribute("id",id);
        return "board/writeform";
    }
    //글저장 후 게시판으로
    @RequestMapping("/writeSave")
    public String writesave(Board board,Model model,HttpSession session) {
        boardRepository.save(board);
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("list2", boardList);
        return "board/boardlist";
    }
    //게시판보기
    @RequestMapping("/boardlist")
    public String boardlist(Board board,Model model){
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("list2",boardList);
        return "board/boardlist";
    }
    //글읽기
    //댓글 출력
    @RequestMapping("/boarddetail")
    public String readdetail(@RequestParam("seq")int seq, Model model, Board board, HttpSession session){

        boolean exist = boardRepository.existsById(seq);
        /*해당글 삭제여부 확인 */
        if(exist == false){
            model.addAttribute("message","해당글은 삭제됐습니다!");
            String id = (String) session.getAttribute("logId");
            List<Board> myBoard = boardRepository.findById(id);
            List<Reply> myReply = replyRepository.findAllById(id);
            model.addAttribute("myBoardList",myBoard);
            model.addAttribute("myReplyList",myReply);
            model.addAttribute("id",id);
            return "mypage/mypage";
        }
        else{
            Optional<Board> boardfind = boardRepository.findById(seq);
            Board detail = boardfind.get();
            model.addAttribute("detail",detail);
            List<Reply> replylist = replyRepository.findAllBySeqBoard(seq);

            /*대댓글 여부 확인 */
            int reReplyCount = (int) replylist.stream().filter(s ->s.getRindex().equals("1")).count();
            /*댓글 group by group 후 order by seqReply  */
            if( reReplyCount >0){
                replylist =replylist.stream().sorted(Comparator.comparing(Reply::getRgroup).thenComparing(Reply::getSeqReply)).collect(Collectors.toList());}


            model.addAttribute("replylist",replylist);
            return "board/boarddetail";
        }

    }
    //게시글 수정폼
    @RequestMapping("/modiform")
    public String modiform(@RequestParam("seq")int seq, HttpSession session,Model model){
        String id= (String) session.getAttribute("logId");
/*       만약 변수 선언 후 get방식으로 넘겼다면 String으로 값을 인식
         -> 위 함수에선 @RequestParam("seq")String seq으로 하고
         -> 받은 값은 int로 parse해줘야 됨 int seqBoard =Integer.parseInt(seq); */
        Optional<Board> board = boardRepository.findById(seq);
        Board detail = board.get();
        model.addAttribute("detail",detail);
        model.addAttribute("id",id);
        model.addAttribute("seq",seq);//
        return "board/modify";
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
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("list2",boardList);

        return "board/boardlist";
    }
    //게시글 삭제
    @RequestMapping("/delete")
    public String delete(@RequestParam("seq")int seq,Model model){
        boardRepository.deleteById(seq);
        model.addAttribute("message","삭제완료!");
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("list2",boardList);

        return "board/boardlist";
    }
    //댓글 달기
    @RequestMapping("/reply")
    public String reply(Reply reply, Model model){
        reply.setRgroup(String.valueOf(reply.getSeqReply()));
        replyRepository.save(reply);
        Optional<Reply> reply2  =replyRepository.findById(reply.getSeqReply());
        reply.setRgroup(String.valueOf(reply2.get().getSeqReply()));
        replyRepository.save(reply);
        return "redirect:boarddetail?seq="+reply.getSeqBoard();
    }
    //댓글 삭제
    @RequestMapping("/delreply")
    public String delreply(@RequestParam("seq")int seqReply,@RequestParam("seqB")int seqBoard){
        System.out.println("seqr"+seqReply);

        System.out.println("seqb"+seqBoard);
        replyRepository.deleteById(seqReply);
        boolean exist = boardRepository.existsById(seqBoard);
        /*해당글 삭제여부 확인 */
        if(exist == false) {
            return "redirect:mypage";
        }
        else {
            return "redirect:boarddetail" + "?seq=" + seqBoard;
        }
    }
    //댓글 수정

    @RequestMapping("/modireply")
    public String modireply(@RequestParam("seqBoard")String seqBoard,@RequestParam("seqReply")String seqReply,
                            @RequestParam("id")String id,@RequestParam("pwReply")String pwReply,@RequestParam("replytext")String replytext,Model model){
       /* System.out.println("시퀀스1: "+seqBoard);System.out.println("시퀀스2: "+seqReply);
        System.out.println("아이디: "+id);*/
        int seqR = Integer.parseInt(seqReply);
        int seqB =Integer.parseInt(seqBoard);

        Reply reply = new Reply(seqR,seqB,seqReply,id,replytext,pwReply,"0");
        replyRepository.save(reply);
        return "redirect:boarddetail"+"?seq="+seqB;
    }
    //대댓글
    @RequestMapping("rereply")
    public String rereply(@RequestParam("seqBoard")String seqBoard,@RequestParam("Rgroup")String Rgroup,
                          @RequestParam("id")String id,@RequestParam("pwReply")String pwReply,@RequestParam("replytext")String replytext,Model model){
        int seqB = Integer.parseInt(seqBoard);

        Reply reply = new Reply();

        reply.setSeqBoard(seqB);
        reply.setRgroup(Rgroup);
        reply.setId(id);
        reply.setReplytext(replytext);
        reply.setPwReply(pwReply);
        reply.setRindex("1");
        System.out.println(reply);
        replyRepository.save(reply);
        return "redirect:boarddetail"+"?seq="+seqB;
    }
}
