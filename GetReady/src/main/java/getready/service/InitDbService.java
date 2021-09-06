package getready.service;

import java.util.HashSet;
import java.util.Set;

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
		if(!userRepository.findByUsername("admin").isPresent()) {
			AppUser user = new AppUser("admin", passwordEncoder.encode("adminpass"));
			userRepository.save(user);
		}
		
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
		String answer = "SRP, OCP, LSP, ISP, DIP.";
		String info = "https://javatechonline.com/solid-principles-the-interface-segregation-principle/?fbclid=IwAR1Hc4-6k-XEDgzm0F3ky8RgAhg8NyjOQsth3at3danntIrqDsJObWIc95Y";
		Set<Label> labels = new HashSet<>();
		labels.add(labelRepository.findByName("OOP").get());		
		q1.setQuestion(question);
		q1.setAnswer(answer);
		q1.setLabels(labels);
		q1.setInfo(info);
		
		questionRepository.save(q1);
		
		Question q2 = new Question();
		question = "Can you make an abstract class final in Java?";
		answer = "No.";
		info = "https://www.java67.com/2017/07/can-you-make-abstract-class-method-final-in-java.html";
		labels.clear();
		labels.add(labelRepository.findByName("java").get());
		labels.add(labelRepository.findByName("OOP").get());
		q2.setAnswer(answer);
		q2.setQuestion(question);
		q2.setLabels(labels);
		q2.setInfo(info);
		
		questionRepository.save(q2);
		
		Question q3 = new Question();
		question = "Can you overload or override static methods in Java?";
		answer = "You can overload but can not override.";
		info = "https://javarevisited.blogspot.com/2013/03/can-we-overload-and-override-static-method-java.html#axzz74j42eQPf";
		labels.clear();
		labels.add(labelRepository.findByName("java").get());
		labels.add(labelRepository.findByName("OOP").get());
		q3.setAnswer(answer);
		q3.setQuestion(question);
		q3.setLabels(labels);
		q3.setInfo(info);
		
		questionRepository.save(q3);
		
		Question q4 = new Question();
		question = "Can you make an Abstract Class or Method Final in Java?";
		answer = "No.";
		info = "https://www.java67.com/2017/07/can-you-make-abstract-class-method-final-in-java.html";
		labels.clear();
		labels.add(labelRepository.findByName("java").get());
		labels.add(labelRepository.findByName("OOP").get());
		q4.setAnswer(answer);
		q4.setQuestion(question);
		q4.setLabels(labels);
		q4.setInfo(info);
		
		questionRepository.save(q4);
		
		
	}

}
