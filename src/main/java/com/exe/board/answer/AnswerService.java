package com.exe.board.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.exe.board.question.Question;
import com.exe.board.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	public Answer created(Question question,String content,SiteUser author) {
		
		Answer ans = new Answer();
		
		ans.setContent(content);
		ans.setCreatedDate(LocalDateTime.now());
		ans.setQuestion(question);
		
		answerRepository.save(ans);
		
		return ans;
	}

}
