package com.biz.mybatis.service;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.DAO.StudentDAO;
import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.model.StudentVO;

/*
 * Service클래스에서 수행하던 DB관련 코드를
 * Dao클래스로 분리
 * 
 * Service클래스는 이제 Business Logic을 수행하는 코드
 */
public class StdService {
	private String st_num = null;
	Scanner scan = null;
	
	SqlSession sqlSession = null;
	StudentDAO stdDao = null;
	public StdService() {
		scan= new Scanner(System.in);
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		stdDao = (StudentDAO) sqlSession.getMapper(StudentDAO.class);
	}
	/*
	 * 키보드에서 학번을 입력받고 해다하는 학생정보가 있는지 검사
	 */
	public boolean isNotStd() {
		System.out.print("학번 >>");
		st_num = scan.nextLine();
		
		//학번에 해당하는 학생이 있으면 vo는 값을 갖고
		//없으면 null값을 갖는다.
		StudentVO vo = stdDao.findByNum(st_num);
		if(vo != null) {
			System.out.println(vo);
			System.out.println("-----------------------------------");
			return false;
		} else {
			System.out.println("학생정보가 없습니다 새로 입력하세요");
			return true;
		}
	}
	
	public void insertStd() {
		/*
		 * insertStd가 실행되는 경우는
		 * 학번을 입력했는데 학생정보를 못찾는 경우이다
		 * 학번을 입력하였을때 clas scorpe의 st_num에 저장하였으므로
		 * insertStd메서드에서 st_num을 그대로 사용할 수 있다.
		 */
		System.out.print("이름 >>");
		String st_name = scan.nextLine();
		System.out.print("전화번호 >>");
		String st_tel = scan.nextLine();
		System.out.print("학년 >>");
		String st_grade = scan.nextLine();
		int int_grade = 1;
		try {
			int_grade = Integer.valueOf(st_grade);
		} catch (Exception e) {
			
		}
		StudentVO vo = new StudentVO(st_num,st_name,st_tel,int_grade);
		stdDao.insert(vo);
	}
	public String getSt_num() {
		return this.st_num;
	}
	
}
