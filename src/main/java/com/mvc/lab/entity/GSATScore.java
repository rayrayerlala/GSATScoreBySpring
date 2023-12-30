package com.mvc.lab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class GSATScore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 讓序號按順序產生 (1~n)
	private Integer id;
	
	private String name;
	private Integer chineseScore;
	private Integer englishScore;
	private Integer mathematicsScore;
	private Integer naturalScienceScore;
	private Integer socialStudiesScore;
	
	private Integer totalScore;
	private Double averageScore;
	public GSATScore(String name, Integer chineseScore, Integer englishScore, Integer mathematicsScore,
			Integer naturalScienceScore, Integer socialStudiesScore) {
		this.name = name;
		this.chineseScore = chineseScore;
		this.englishScore = englishScore;
		this.mathematicsScore = mathematicsScore;
		this.naturalScienceScore = naturalScienceScore;
		this.socialStudiesScore = socialStudiesScore;
		this.totalScore = chineseScore + englishScore + mathematicsScore + naturalScienceScore + socialStudiesScore;
		this.averageScore = totalScore / 5.0;
	}
	
	public void updateScore(String subjectName, Integer score) {
        switch (subjectName.toLowerCase()) {
            case "chinese":
                this.chineseScore = score;
                break;
            case "english":
                this.englishScore = score;
                break;
            case "mathematics":
                this.mathematicsScore = score;
                break;
            case "naturalscience":
                this.naturalScienceScore = score;
                break;
            case "socialstudies":
                this.socialStudiesScore = score;
                break;
            default:
                break;
        }
     // 更新總分和平均分
        this.totalScore = chineseScore + englishScore + mathematicsScore + naturalScienceScore + socialStudiesScore;
        this.averageScore = totalScore / 5.0;
    }
	
}
