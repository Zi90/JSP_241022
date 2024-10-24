package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import orm.DatabaseBuilder;


public class BoardDAOImpl implements BoardDao {
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	// DB와 연결 => mybatis 사용하여 연결 (mybatisConfig, db연결클래스, mapper)
	
	// DB의 연결을 위한 객체
	private SqlSession sql;
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insert(BoardVO bvo) {
		// mapper로 연결
		log.info(">>>> insert DAO in!!");
		// sql.insert(mapper의 namespace.id, 객체);
		// insert / update / delete 구문은 commit 필요
		int isOk = sql.insert("BoardMapper.add", bvo);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<BoardVO> getList() {
		log.info(">>>> getList DAO in!!");
		return sql.selectList("BoardMapper.list");
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info(">>>>> getDetail DAO in!!");
		return sql.selectOne("BoardMapper.detail", bno);
	}

	@Override
	public int update(BoardVO bvo) {
		log.info(">>>>> update DAO in!!");
		int isOk = sql.update("BoardMapper.up", bvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int delete(int bno) {
		// TODO Auto-generated method stub
		int isOk = sql.delete("BoardMapper.del", bno);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int readCount(int bno) {
		int isOk = sql.update("BoardMapper.read", bno);
		if(isOk > 0) sql.commit();
		return isOk;
	}
}
