package com.biz.mybatis.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.DAO.ScoreDAO;
import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.model.ScoreVO;

public class ScoreService {
	private String st_num = null;
	Scanner scan = null;
	
	SqlSession sqlSession = null;
	ScoreDAO scDao = null;
	
	public ScoreService() {
		scan= new Scanner(System.in);
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		scDao = (ScoreDAO) sqlSession.getMapper(ScoreDAO.class);
	}
	
	public void setSt_num(String st_num) {
		this.st_num = st_num;
	}
	
	public void viewScore(String st_num) {
		if(st_num == null) {
			System.out.println("학번을 입력해 주십시오");
			return;
		}
		System.out.println(st_num+" 학생의 성적리스트");
		System.out.println("--------------------------------");
		System.out.println("과목\t\t점수");
		System.out.println("================================");
		List<ScoreVO> scList = scDao.findByNum(st_num);
		for(ScoreVO vo : scList) {
			System.out.printf("%s\t\t%3d\n",vo.getSc_subject(),vo.getSc_score());
		}
		System.out.println("--------------------------------");
	}
	public void insertScore(String st_num) {
		while(true) {
			System.out.print("과목(-E:종료) >>");
			String str_subject = scan.nextLine();
			if(str_subject.equals("-E"))break;
			
			System.out.print("점수 >>");
			String str_score = scan.nextLine();
			try {
				int intScore = Integer.valueOf(str_score);
				ScoreVO vo = new ScoreVO(0,st_num,str_subject,intScore);
				scDao.insert(vo);
			}catch (Exception e) {
				System.out.println("점수는 숫자로만 입력하세요");
				continue;
			}
			
		}
		
	}
	
	
	
	
}
