package com.example.board.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MypageRepository extends JpaRepository<Mypage,Integer> {

    public List<Mypage> findById(String id);

    /* SQL은 테이블의 컬럼명*/
    @Query(value="select seqMypage, id, seqBoard from mypage where seqMypage > ?1", nativeQuery =true)
    public List<Mypage> selectSQLById1(int seqMypage);
    @Query(value="select seqMypage, id, seqBoard from mypage where seqMypage > :seq", nativeQuery =true)
    public List<Mypage> selectSQLById2(@Param(value="seq")int seqMypage);
    @Query(value="select seqMypage, id, seqBoard from mypage where seqMypage > :#{#myParam.seqMypage}", nativeQuery =true )
    public List<Mypage> selectSQLById3(@Param(value="myParam")Mypage myParam);


}
