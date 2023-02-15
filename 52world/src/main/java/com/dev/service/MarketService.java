package com.dev.service;

import java.util.List;

import com.dev.dao.ItemsDAO;
import com.dev.dao.MemberDAO;
import com.dev.dao.MiniroomDAO;
import com.dev.dao.MusicDAO;
import com.dev.vo.ItemsVO;
import com.dev.vo.MiniroomVO;
import com.dev.vo.MusicVO;

public class MarketService {

	private static MarketService instance = new MarketService();

	private MarketService() {
	}

	public static MarketService getInstance() {
		if (instance == null) {
			instance = new MarketService();
		}
		return instance;
	}

	MiniroomDAO minidao = new MiniroomDAO();
	MusicDAO musicdao = new MusicDAO();
	ItemsDAO idao = new ItemsDAO();
	MemberDAO member = new MemberDAO();
	
	// 미니룸 전체조회
	public List<MiniroomVO> getAllMiniroom() {
		return minidao.getAllMiniroom();
	}

	// 내가 안산 미니룸
	public List<MiniroomVO> remainMiniroom(String id) {
		return minidao.remainMiniroom(id);
	}

	// 내가 산 미니룸
	public List<MiniroomVO> myMiniroom(String id) {
		return minidao.myMiniroom(id);
	}

	// 브금 전체조회
	public List<MusicVO> getAllMusic() {
		return musicdao.getAllMusic();
	}
	// 브금 단건조회
	public MusicVO getOneMusic(int id) {
		return musicdao.searchOne(id);
	}

	// 내가 안산 브금
	public List<MusicVO> remainMusic(String id) {
		return musicdao.remainMusic(id);
	}

	// 내가 산 브금
	public List<MusicVO> myMusic(String id) {
		return musicdao.myMusic(id);
	}
	
	// 검색
	public List<MusicVO> searchMusic(String search, String id) {
		return musicdao.searchMusic(search, id);
	}
	
	// 브금 구매
	public void buyMusic(ItemsVO vo, int price) {
		idao.buyMusic(vo);
		member.updateDotori(price, vo.getBuyerId());
	}
	
	// 미니룸 구매
	public void buyMiniroom(ItemsVO vo, int price) {
		idao.buyMiniroom(vo);
		member.updateDotori(price, vo.getBuyerId());
	}
}
