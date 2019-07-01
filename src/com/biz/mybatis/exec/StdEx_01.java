package com.biz.mybatis.exec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.DAO.StudentDAO;
import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.model.StudentVO;

public class StdEx_01 {
	public static void main(String[] args) {
		/*
		 * SessionFactory에게 DBMS에 연결할 Session을 요청
		 * Session : DBMS에 연결할 Connection pool
		 */
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		
		/*
		 * Session에서 mapper를 요청
		 * StudentDao interface를 이용해서
		 * stdmapper.xml에 설정된 SQL과 mapping하여
		 * StudentDao(Imp)클래스를 생성하고
		 * 그 클래스로 stdDao를 초기화 하라.
		 */
		StudentDAO stdDao = (StudentDAO)sqlSession.getMapper(StudentDAO.class);
		
		List<StudentVO> stdList = null;
		stdList = stdDao.selectAll();
		
		for(StudentVO vo : stdList) {
			System.out.println(vo);
		}
	}
}
