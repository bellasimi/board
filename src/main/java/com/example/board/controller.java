package com.example.board;

import com.example.board.board.Board;
import com.example.board.board.BoardRepository;
import com.example.board.board.Reply;
import com.example.board.board.ReplyRepository;
import com.example.board.member.Member;
import com.example.board.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class controller {
    @Autowired
    private MemberRepository memberrepository;

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    ReplyRepository replyRepository;
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
    /*ID 찾기폼(전번)*/
    @RequestMapping("/searchId")
    public String searchId(){
        return "member/searchIdForm";
    }
    /*ID 찾기*/
    @RequestMapping("/findId")
    public String findId(@RequestParam("tel")String tel,Model model){
        Member user = memberrepository.findByTel(tel);
        if(user != null){
            model.addAttribute("id",user.getId());
            model.addAttribute("name",user.getName());
            return "member/findId";
        }
        else{
            model.addAttribute("message","그런회원은 존재하지 않습니다!!");
            return "loginform";
        }
    }

    /*비밀번호 찾기(아이디,전번)*/


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
        System.out.println("유저: "+ user);
        if(user.size() == 0){

            model.addAttribute("message","그런 회원은 존재하지 않습니다!!");
            return "loginform";
        }
        else{
            model.addAttribute("id",id);

            HttpSession session = request.getSession();
            session.setAttribute("logId",id);
            return "loginsuccess";
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
    //댓글 출력
    @RequestMapping("/boarddetail")
    public String readdetail(@RequestParam("seq")int seq,Model model,Board board){
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
        return "boarddetail";

    }
    //게시글 수정폼
    @RequestMapping("/modiform")
    public String modiform(@RequestParam("seq")int seq, HttpSession session,Model model){
        String id= (String) session.getAttribute("logId");
/*       만약 변수 선언 후 get방식으로 넘겼다면 String으로 값을 인식
         -> 위 함수에선 @RequestParam("seq")String seq으로 하고
         -> 받은 값은 int로 parse해줘야 됨 int seqBoard =Integer.parseInt(seq); */
        model.addAttribute("id",id);
        model.addAttribute("seq",seq);//
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
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("list2",boardList);

        return "boardlist";
    }
    //게시글 삭제
    @RequestMapping("/delete")
    public String delete(@RequestParam("seq")int seq,Model model){
        boardRepository.deleteById(seq);
        model.addAttribute("message","삭제완료!");
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("list2",boardList);

        return "boardlist";
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
    public String delreply(@RequestParam("seq")int seqReply,@RequestParam("seqB")String seqBoard){
        System.out.println("seqr"+seqReply);

        System.out.println("seqb"+seqBoard);
        replyRepository.deleteById(seqReply);

        return "redirect:boarddetail"+"?seq="+seqBoard;
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