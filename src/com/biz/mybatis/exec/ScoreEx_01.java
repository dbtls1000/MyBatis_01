package com.biz.mybatis.exec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.DAO.ScoreDAO;
import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.model.ScoreVO;

public class ScoreEx_01 {
	public static void main(String[] args) {
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		
		ScoreDAO scDao = (ScoreDAO)sqlSession.getMapper(ScoreDAO.class);
		
		List<ScoreVO> scList = scDao.selectAll();
		for(ScoreVO vo : scList) {
			System.out.println(vo);
		}
	}
}
