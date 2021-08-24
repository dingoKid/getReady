package getready.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import getready.model.AppUser;
import getready.model.Label;
import getready.model.Question;
import getready.repository.AppUserRepository;
import getready.repository.LabelRepository;
import getready.repository.QuestionRepository;

@Service
public class InitDbService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	LabelRepository labelRepository;
	
	@Autowired
	AppUserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	public void addUser() {
		AppUser user = new AppUser("admin", passwordEncoder.encode("adminpass"));
		userRepository.save(user);
		
	}
	
	@Transactional
	public void addLabels() {
		labelRepository.save(new Label("OOP"));
		labelRepository.save(new Label("annotation"));
		labelRepository.save(new Label("java"));
		labelRepository.save(new Label("spring"));
		labelRepository.save(new Label("web"));
	}
	
	@Transactional
	public void addQuestions() {
		Question q1 = new Question();
		
		String question = "What are the solid principles?";
		String answer = "SRP, OCP, LSP, ISP, DIP";
		List<Label> labels = new ArrayList<>();
		labels.add(labelRepository.findByName("OOP").get());		
		q1.setQuestion(question);
		q1.setAnswer(answer);
		q1.setLabels(labels);	
		
		questionRepository.save(q1);
		
		Question q2 = new Question();
		question = "sss";
		answer = "kkk";
		List<Label> l2 = new ArrayList<>();
		l2.add(labelRepository.findByName("java").get());
		l2.add(labelRepository.findByName("spring").get());
		l2.add(labelRepository.findByName("OOP").get());
		q2.setAnswer(answer);
		q2.setQuestion(question);
		q2.setLabels(l2);
		
		questionRepository.save(q2);
		
		
	}

}
