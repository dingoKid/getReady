package getready.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import getready.service.LabelService;

@RestController
@RequestMapping("/api/labels")
public class LabelController {

	@Autowired
	LabelService labelService;
	
	@GetMapping
	public List<String> getLabels() {
		return labelService.getAllLabels().stream()
			.map(label -> label.getName())
			.collect(Collectors.toList());
	}
	
	@GetMapping("/save/{labelName}")
	public List<String> addLabel(@PathVariable String labelName) {
		labelService.createLabel(labelName);
		return getLabels();
	}
	
}
