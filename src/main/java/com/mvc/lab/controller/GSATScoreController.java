package com.mvc.lab.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.javafaker.Faker;
import com.mvc.lab.entity.GSATScore;
import com.mvc.lab.repository.GSATScoreRepository;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/gsat")
public class GSATScoreController {
	
	@Autowired
	private GSATScoreRepository gsatScoreRepository;
	
	// 隨機生成一筆資料列紀錄
	@GetMapping("/add")
	@ResponseBody
	public String addScoreRandom() {
		Faker faker = new Faker();
		Random random = new Random();
		
		String name = faker.name().fullName();
		Integer chineseScore = random.nextInt(15) + 1;
		Integer englishScore = random.nextInt(15) + 1;
		Integer mathematicsScore = random.nextInt(15) + 1;
		Integer naturalScienceScore = random.nextInt(15) + 1;
		Integer socialStudiesScore = random.nextInt(15) + 1;
		
		GSATScore gsatScore = new GSATScore(name, chineseScore, englishScore, mathematicsScore, naturalScienceScore, socialStudiesScore);
		// 儲存至 table
		gsatScoreRepository.save(gsatScore);
		
		return gsatScore + "新增完畢";
		
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<GSATScore> getGSATScoreById(@PathVariable("id") Integer id){
		return gsatScoreRepository.findById(id);
	}
	
	@GetMapping("/")
	public  String findAllGSATScores(Model model) {
		List<GSATScore> scores = gsatScoreRepository.findAll();
		
		model.addAttribute("scores", scores);
		
		return "gsat_scores";
	}
	// 更新成績
	@PutMapping("/{id}")
	@ResponseBody
	@Transactional
	public String updateScore(@PathVariable("id") Integer id, String subjectName, Integer score) {
		Optional<GSATScore> gsatScoreOpt = gsatScoreRepository.findById(id);
		gsatScoreOpt.ifPresent(gsatScore ->{
			gsatScore.updateScore(subjectName, score);
			//gsatScoreRepository.saveAndFlush(gsatScore);
		});
		
		return "修改成功";
	}
}
