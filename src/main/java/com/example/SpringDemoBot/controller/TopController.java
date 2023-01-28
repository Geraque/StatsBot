package com.example.SpringDemoBot.controller;

import com.example.SpringDemoBot.exception.ResourceNotFoundException;
import com.example.SpringDemoBot.model.Top;
import com.example.SpringDemoBot.repository.TopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TopController {
	@Autowired
	private TopRepository topRepository;

	@GetMapping("/top")
	public List<Top> getAllTop() {
		return topRepository.findAll();
	}

	@GetMapping("/top/{id}")
	public ResponseEntity<Top> getTopById(@PathVariable(value = "id") Long topId)
			throws ResourceNotFoundException {
		Top top = topRepository.findById(topId)
				.orElseThrow(() -> new ResourceNotFoundException("Top not found for this id : " + topId));
		return ResponseEntity.ok().body(top);
	}

	@GetMapping("/top/getbyname/{name}")
	public ResponseEntity<List<Top>> getTopByName(@PathVariable(value = "name") String topName)
			throws ResourceNotFoundException {
		List<Top> top = topRepository.findAll();
		List<Top> result = new ArrayList<Top>();
		for(Top des: top){
			if(des.getName().equals(topName)){
				result.add(des);
			}
		}
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("/top")
	public Top createTop(@Valid @RequestBody Top top) {
		return topRepository.save(top);
	}

	@PutMapping("/top/{id}")
	public ResponseEntity<Top> updateTop(@PathVariable(value = "id") Long topId,
			@Valid @RequestBody Top topDetails) throws ResourceNotFoundException {
		Top top = topRepository.findById(topId)
				.orElseThrow(() -> new ResourceNotFoundException("Top not found for this id : " + topId));

		top.setName(topDetails.getName());
		top.setRating(topDetails.getRating());
		top.setYear(topDetails.getYear());
		top.setPlace(topDetails.getPlace());


		final Top updatedTop = topRepository.save(top);
		return ResponseEntity.ok(updatedTop);
	}

	@DeleteMapping("/top/{id}")
	public Map<String, Boolean> deleteTop(@PathVariable(value = "id") Long topId)
			throws ResourceNotFoundException {
		Top top = topRepository.findById(topId)
				.orElseThrow(() -> new ResourceNotFoundException("Top not found for this id : " + topId));

		topRepository.delete(top);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
