package me.test;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import me.domain.User;
import me.mapping.UserMapperI;

public class Test {
	
	public static void main(String[] args){
		//1.mybatis的CURD - 基于注解实现
//		testAdd();
		testUpdate();
		testDelete();
		testGetAll();
		testGetUser();
	}
	
	public static void testAdd(){
//		SqlSession sqlSession = MybatisUtil.getSqlSession(false);
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		User user = new User("用户孤傲苍狼i",20);
		//执行插入操作
//		String statement = "me.mapping.userMapper.addUser";
//		int result = sqlSession.insert(statement, user);
		//得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
		UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
		int result = mapper.add(user);
		//手动提交事务
//		sqlSession.commit();
		//使用SqlSession执行完SQL之后需要关闭SqlSession
		sqlSession.close();
		System.out.println("插入影响条数："+result);
	}
	
	public static void testUpdate(){
		User user = new User("孤傲苍狼i-update",231);
		user.setId(5);
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
//		String statement = "me.mapping.userMapper.updateUser";
//		int result = sqlSession.update(statement,user);
		UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
		int result = mapper.update(user);
		sqlSession.close();
		System.out.println("update影响条数："+result);
	}
	
	public static void testDelete(){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
//		String statement = "me.mapping.userMapper.deleteUser";
//		int result = sqlSession.delete(statement,4);
		UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
		int result = mapper.deleteById(6);
		sqlSession.close();
		System.out.println("delete影响条数："+result);
	}
	
	public static void testGetAll(){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
//		String statement = "me.mapping.userMapper.getAllUsers";
//		List<User> list = sqlSession.selectList(statement);
		UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
		List<User> list = mapper.findAll();
		sqlSession.close();
		System.out.println("条数："+list.size()+" 内容为："+list);
	}
	
	public static void testGetUser(){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
//		String statement = "me.mapping.userMapper.getUser";
//		User user = sqlSession.selectOne(statement,1);
		UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
		User user = mapper.findById(5);
		System.out.println("取到的数据："+user);
	}
}
