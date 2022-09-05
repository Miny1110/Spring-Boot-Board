package com.exe.board.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;
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
		ans.setAuthor(author);
		
		answerRepository.save(ans);
		
		return ans;
	}

//---------------------------------------------------------------------------------------------
	public Answer getAnswer(Integer id) {
		
		Optional<Answer> op = answerRepository.findById(id);
		
		if(op.isPresent())
			return op.get();
		else
			throw new DataNotFoundException("데이터가 없어요");
	}
	
}
