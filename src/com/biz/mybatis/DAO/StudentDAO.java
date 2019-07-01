package com.biz.mybatis.DAO;

import java.util.List;

import com.biz.mybatis.model.StudentVO;

public interface StudentDAO {
	public List<StudentVO> selectAll();
	public StudentVO findByNum(String stNum);
	
	public int insert(StudentVO vo);
	public int update(StudentVO vo);
	public int delete(String stNum);
	
}
