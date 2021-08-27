package getready.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import getready.model.Label;
import getready.repository.LabelRepository;

@Service
public class LabelService {

	@Autowired
	LabelRepository labelRepository;
	
	public List<Label> getAllLabels(){
		return labelRepository.findAll();
	}
	
	@Transactional
	public void createLabel(String name) {
		if (!labelRepository.findByName(name).isPresent())
			labelRepository.save(new Label(name));
	}
}
