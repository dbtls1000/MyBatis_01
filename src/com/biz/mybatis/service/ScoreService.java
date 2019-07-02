package com.biz.mybatis.service;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.DAO.ScoreDAO;
import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.model.ScoreVO;
import com.biz.mybatis.util.Contract;

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
		
		Set<String> subKeys = Contract.SubList.keySet();
		
		while(true) {
			System.out.println("=================================");
			int index = 0;
			for(String subKey : subKeys) {
				System.out.print(subKey + " : " + Contract.SubList.get(subKey));
				if(++index % 5 == 0) System.out.println();
				else System.out.print("  ");
			}
			System.out.println("\n=================================");
			
			System.out.print("과목번호(-9:종료) >>");
			String str_subject = scan.nextLine();
			if(str_subject.equals("-9"))break;
			
			System.out.print("과목점수 >>");
			String str_score = scan.nextLine();
			try {
				int intScore = Integer.valueOf(str_score);
				ScoreVO vo = new ScoreVO(0,st_num,str_subject,intScore);
				scDao.insert(vo);
			} catch (NumberFormatException ne) {
				System.out.println("점수는 숫자만 입력해야 합니다.");
			} catch (PersistenceException pe) {
				if(pe.getMessage().contains("U_ST_NUM_SUBJECT")) {
					System.out.println("이미 입력된 과목입니다");
				} else {
					pe.printStackTrace();
				}
			} catch (Exception e) {
				//개발자모드
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	
}
