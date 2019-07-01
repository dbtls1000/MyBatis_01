package com.biz.mybatis.DAO;

import java.util.List;

import com.biz.mybatis.model.ScoreVO;

public interface ScoreDAO {
	public List<ScoreVO> selectAll();
	public ScoreVO findBySeq(long sc_seq);
	public List<ScoreVO> findByNum(String sc_st_num);
	
	public int insert(ScoreVO vo);
	public int update(ScoreVO vo);
	public int delete(String stNum);

}
