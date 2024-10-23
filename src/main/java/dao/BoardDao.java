package dao;

import java.util.List;

import domain.BoardVO;

public interface BoardDao {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

}
