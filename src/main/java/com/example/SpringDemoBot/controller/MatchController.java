package com.example.SpringDemoBot.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringDemoBot.exception.ResourceNotFoundException;
import com.example.SpringDemoBot.model.Match;
import com.example.SpringDemoBot.repository.MatchRepository;

@RestController
@RequestMapping()
public class MatchController {
	@Autowired
	private MatchRepository matchRepository;

	@GetMapping("/matches")
	public List<Match> getAllMatches() {
		return matchRepository.findAll();
	}

	@GetMapping("/matches/{id}")
	public ResponseEntity<Match> getMatchById(@PathVariable(value = "id") Long matchId)
			throws ResourceNotFoundException {
		Match match = matchRepository.findById(matchId)
				.orElseThrow(() -> new ResourceNotFoundException("Match not found for this id : " + matchId));
		return ResponseEntity.ok().body(match);
	}

	@GetMapping("/getbyname/{name}")
	public ResponseEntity<List<Match>> getMatchByName(@PathVariable(value = "name") String matchName)
			throws ResourceNotFoundException {
		List<Match> match = matchRepository.findAll();
		Collections.reverse(match);
		List<Match> result = new ArrayList<Match>();
		int count =0;
		for(Match des: match){
			if(des.getName().equals(matchName) && count<7){
				result.add(des);
				count++;
			}
		}
		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/getplayerstats/{name}")
	public ResponseEntity<float[]> getPlayerStats(@PathVariable(value = "name") String matchName)
			throws ResourceNotFoundException {
		List<Match> match = matchRepository.findAll();
		float[] arr = new float[15];
		for(Match des: match){
			if(des.getName().equals(matchName)){
				arr[0]++;
				arr[1]+=des.getRating();
				arr[2]+=des.getSmokeKill();
				arr[3]+=des.getOpenKill();
				arr[4]+=des.getThreeKill();
				arr[5]+=des.getFourKill();
				arr[6]+=des.getAce();
				arr[7]+=des.getFlash();
				arr[8]+=des.getTrade();
				arr[9]+=des.getWallbang();
				arr[10]+=des.getClutchOne();
				arr[11]+=des.getClutchTwo();
				arr[12]+=des.getClutchThree();
				arr[13]+=des.getClutchFour();
				arr[14]+=des.getClutchFive();
			}
		}
		arr[1]/=arr[0];
		return ResponseEntity.ok().body(arr);
	}

	@GetMapping("/getrating")
	public ResponseEntity<String[]> getRating()
			throws ResourceNotFoundException {
		List<Match> doceho = matchRepository.findAll();

		float[] desmond = new float[2];
		float[] blackVision = new float[2];
		float[] lakich = new float[2];
		float[] tilt = new float[2];
		float[] nekit = new float[2];

		for(Match des: doceho){
			if(des.getName().equals("desmond")){
				desmond[0]+=1;
				desmond[1]+=des.getRating();
			}
			else if(des.getName().equals("BlackVision")){
				blackVision[0]+=1;
				blackVision[1]+=des.getRating();
			}
			else if(des.getName().equals("Tilt")){
				tilt[0]+=1;
				tilt[1]+=des.getRating();
			}
			else if(des.getName().equals("Lakich")){
				lakich[0]+=1;
				lakich[1]+=des.getRating();
			}
			else if (des.getName().equals("221w33")){
				nekit[0]+=1;
				nekit[1]+=des.getRating();
			}
		}
		int num=0;
		float max=desmond[1]/desmond[0];
		float[] arr = {desmond[1]/desmond[0],blackVision[1]/blackVision[0],
				tilt[1]/tilt[0],lakich[1]/lakich[0],nekit[1]/nekit[0]};
		for(int i =1; i<5;i++){
			if(arr[i] > max){
				num = i;
				max = arr[i];
			}
		}
		String[] res = new String[3];
		if(num==0){
			res = new String[]{"Desmond",String.valueOf(desmond[0]), String.valueOf(desmond[1]/desmond[0])};
		}
		else if(num==1){
			res = new String[]{"BlackVision",String.valueOf(blackVision[0]), String.valueOf(blackVision[1]/blackVision[0])};
		}
		else if(num==2){
			res = new String[]{"Tilt",String.valueOf(tilt[0]), String.valueOf(tilt[1]/tilt[0])};
		}
		else if(num==3){
			res = new String[]{"Lakich",String.valueOf(lakich[0]), String.valueOf(lakich[1]/lakich[0])};
		}
		else {
			res = new String[]{"221w33",String.valueOf(nekit[0]), String.valueOf(nekit[1]/nekit[0])};
		}
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/getopenkill")
	public ResponseEntity<String[]> getOpenKill()
			throws ResourceNotFoundException {
		List<Match> doceho = matchRepository.findAll();

		float[] desmond = new float[2];
		float[] blackVision = new float[2];
		float[] lakich = new float[2];
		float[] tilt = new float[2];
		float[] nekit = new float[2];

		for(Match des: doceho){
			if(des.getName().equals("desmond")){
				desmond[0]+=1;
				desmond[1]+=des.getOpenKill();
			}
			else if(des.getName().equals("BlackVision")){
				blackVision[0]+=1;
				blackVision[1]+=des.getOpenKill();
			}
			else if(des.getName().equals("Tilt")){
				tilt[0]+=1;
				tilt[1]+=des.getOpenKill();
			}
			else if(des.getName().equals("Lakich")){
				lakich[0]+=1;
				lakich[1]+=des.getOpenKill();
			}
			else if (des.getName().equals("221w33")){
				nekit[0]+=1;
				nekit[1]+=des.getOpenKill();
			}
		}
		int num=0;
		float max=desmond[1]/desmond[0];
		float[] arr = {desmond[1]/desmond[0],blackVision[1]/blackVision[0],
				tilt[1]/tilt[0],lakich[1]/lakich[0],nekit[1]/nekit[0]};
		for(int i =1; i<5;i++){
			if(arr[i] > max){
				num = i;
				max = arr[i];
			}
		}
		String[] res = new String[3];
		if(num==0){
			res = new String[]{"Desmond",String.valueOf(desmond[0]), String.valueOf(desmond[1]/desmond[0])};
		}
		else if(num==1){
			res = new String[]{"BlackVision",String.valueOf(blackVision[0]), String.valueOf(blackVision[1]/blackVision[0])};
		}
		else if(num==2){
			res = new String[]{"Tilt",String.valueOf(tilt[0]), String.valueOf(tilt[1]/tilt[0])};
		}
		else if(num==3){
			res = new String[]{"Lakich",String.valueOf(lakich[0]), String.valueOf(lakich[1]/lakich[0])};
		}
		else {
			res = new String[]{"221w33",String.valueOf(nekit[0]), String.valueOf(nekit[1]/nekit[0])};
		}
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/getflash")
	public ResponseEntity<String[]> getFlash()
			throws ResourceNotFoundException {
		List<Match> doceho = matchRepository.findAll();

		float[] desmond = new float[2];
		float[] blackVision = new float[2];
		float[] lakich = new float[2];
		float[] tilt = new float[2];
		float[] nekit = new float[2];

		for(Match des: doceho){
			if(des.getName().equals("desmond")){
				desmond[0]+=1;
				desmond[1]+=des.getFlash();
			}
			else if(des.getName().equals("BlackVision")){
				blackVision[0]+=1;
				blackVision[1]+=des.getFlash();
			}
			else if(des.getName().equals("Tilt")){
				tilt[0]+=1;
				tilt[1]+=des.getFlash();
			}
			else if(des.getName().equals("Lakich")){
				lakich[0]+=1;
				lakich[1]+=des.getFlash();
			}
			else if (des.getName().equals("221w33")){
				nekit[0]+=1;
				nekit[1]+=des.getFlash();
			}
		}
		int num=0;
		float max=desmond[1]/desmond[0];
		float[] arr = {desmond[1]/desmond[0],blackVision[1]/blackVision[0],
				tilt[1]/tilt[0],lakich[1]/lakich[0],nekit[1]/nekit[0]};
		for(int i =1; i<5;i++){
			if(arr[i] > max){
				num = i;
				max = arr[i];
			}
		}
		String[] res = new String[3];
		if(num==0){
			res = new String[]{"Desmond",String.valueOf(desmond[0]), String.valueOf(desmond[1]/desmond[0])};
		}
		else if(num==1){
			res = new String[]{"BlackVision",String.valueOf(blackVision[0]), String.valueOf(blackVision[1]/blackVision[0])};
		}
		else if(num==2){
			res = new String[]{"Tilt",String.valueOf(tilt[0]), String.valueOf(tilt[1]/tilt[0])};
		}
		else if(num==3){
			res = new String[]{"Lakich",String.valueOf(lakich[0]), String.valueOf(lakich[1]/lakich[0])};
		}
		else {
			res = new String[]{"221w33",String.valueOf(nekit[0]), String.valueOf(nekit[1]/nekit[0])};
		}
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/gettrade")
	public ResponseEntity<String[]> getTrade()
			throws ResourceNotFoundException {
		List<Match> doceho = matchRepository.findAll();

		float[] desmond = new float[2];
		float[] blackVision = new float[2];
		float[] lakich = new float[2];
		float[] tilt = new float[2];
		float[] nekit = new float[2];

		for(Match des: doceho){
			if(des.getName().equals("desmond")){
				desmond[0]+=1;
				desmond[1]+=des.getTrade();
			}
			else if(des.getName().equals("BlackVision")){
				blackVision[0]+=1;
				blackVision[1]+=des.getTrade();
			}
			else if(des.getName().equals("Tilt")){
				tilt[0]+=1;
				tilt[1]+=des.getTrade();
			}
			else if(des.getName().equals("Lakich")){
				lakich[0]+=1;
				lakich[1]+=des.getTrade();
			}
			else if (des.getName().equals("221w33")){
				nekit[0]+=1;
				nekit[1]+=des.getTrade();
			}
		}
		int num=0;
		float max=desmond[1]/desmond[0];
		float[] arr = {desmond[1]/desmond[0],blackVision[1]/blackVision[0],
				tilt[1]/tilt[0],lakich[1]/lakich[0],nekit[1]/nekit[0]};
		for(int i =1; i<5;i++){
			if(arr[i] > max){
				num = i;
				max = arr[i];
			}
		}
		String[] res = new String[3];
		if(num==0){
			res = new String[]{"Desmond",String.valueOf(desmond[0]), String.valueOf(desmond[1]/desmond[0])};
		}
		else if(num==1){
			res = new String[]{"BlackVision",String.valueOf(blackVision[0]), String.valueOf(blackVision[1]/blackVision[0])};
		}
		else if(num==2){
			res = new String[]{"Tilt",String.valueOf(tilt[0]), String.valueOf(tilt[1]/tilt[0])};
		}
		else if(num==3){
			res = new String[]{"Lakich",String.valueOf(lakich[0]), String.valueOf(lakich[1]/lakich[0])};
		}
		else {
			res = new String[]{"221w33",String.valueOf(nekit[0]), String.valueOf(nekit[1]/nekit[0])};
		}
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/getwallbang")
	public ResponseEntity<String[]> getWallbang()
			throws ResourceNotFoundException {
		List<Match> doceho = matchRepository.findAll();

		float[] desmond = new float[2];
		float[] blackVision = new float[2];
		float[] lakich = new float[2];
		float[] tilt = new float[2];
		float[] nekit = new float[2];

		for(Match des: doceho){
			if(des.getName().equals("desmond")){
				desmond[0]+=1;
				desmond[1]+=des.getWallbang();
			}
			else if(des.getName().equals("BlackVision")){
				blackVision[0]+=1;
				blackVision[1]+=des.getWallbang();
			}
			else if(des.getName().equals("Tilt")){
				tilt[0]+=1;
				tilt[1]+=des.getWallbang();
			}
			else if(des.getName().equals("Lakich")){
				lakich[0]+=1;
				lakich[1]+=des.getWallbang();
			}
			else if (des.getName().equals("221w33")){
				nekit[0]+=1;
				nekit[1]+=des.getWallbang();
			}
		}
		int num=0;
		float max=desmond[1]/desmond[0];
		float[] arr = {desmond[1]/desmond[0],blackVision[1]/blackVision[0],
				tilt[1]/tilt[0],lakich[1]/lakich[0],nekit[1]/nekit[0]};
		for(int i =1; i<5;i++){
			if(arr[i] > max){
				num = i;
				max = arr[i];
			}
		}
		String[] res = new String[3];
		if(num==0){
			res = new String[]{"Desmond",String.valueOf(desmond[0]), String.valueOf(desmond[1]/desmond[0])};
		}
		else if(num==1){
			res = new String[]{"BlackVision",String.valueOf(blackVision[0]), String.valueOf(blackVision[1]/blackVision[0])};
		}
		else if(num==2){
			res = new String[]{"Tilt",String.valueOf(tilt[0]), String.valueOf(tilt[1]/tilt[0])};
		}
		else if(num==3){
			res = new String[]{"Lakich",String.valueOf(lakich[0]), String.valueOf(lakich[1]/lakich[0])};
		}
		else {
			res = new String[]{"221w33",String.valueOf(nekit[0]), String.valueOf(nekit[1]/nekit[0])};
		}
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/getthreekill")
	public ResponseEntity<String[]> getThreeKill()
			throws ResourceNotFoundException {
		List<Match> doceho = matchRepository.findAll();

		float[] desmond = new float[2];
		float[] blackVision = new float[2];
		float[] lakich = new float[2];
		float[] tilt = new float[2];
		float[] nekit = new float[2];

		for(Match des: doceho){
			if(des.getName().equals("desmond")){
				desmond[0]+=1;
				desmond[1]+=des.getThreeKill();
			}
			else if(des.getName().equals("BlackVision")){
				blackVision[0]+=1;
				blackVision[1]+=des.getThreeKill();
			}
			else if(des.getName().equals("Tilt")){
				tilt[0]+=1;
				tilt[1]+=des.getThreeKill();
			}
			else if(des.getName().equals("Lakich")){
				lakich[0]+=1;
				lakich[1]+=des.getThreeKill();
			}
			else if (des.getName().equals("221w33")){
				nekit[0]+=1;
				nekit[1]+=des.getThreeKill();
			}
		}
		int num=0;
		float max=desmond[1]/desmond[0];
		float[] arr = {desmond[1]/desmond[0],blackVision[1]/blackVision[0],
				tilt[1]/tilt[0],lakich[1]/lakich[0],nekit[1]/nekit[0]};
		for(int i =1; i<5;i++){
			if(arr[i] > max){
				num = i;
				max = arr[i];
			}
		}
		String[] res = new String[3];
		if(num==0){
			res = new String[]{"Desmond",String.valueOf(desmond[0]), String.valueOf(desmond[1]/desmond[0])};
		}
		else if(num==1){
			res = new String[]{"BlackVision",String.valueOf(blackVision[0]), String.valueOf(blackVision[1]/blackVision[0])};
		}
		else if(num==2){
			res = new String[]{"Tilt",String.valueOf(tilt[0]), String.valueOf(tilt[1]/tilt[0])};
		}
		else if(num==3){
			res = new String[]{"Lakich",String.valueOf(lakich[0]), String.valueOf(lakich[1]/lakich[0])};
		}
		else {
			res = new String[]{"221w33",String.valueOf(nekit[0]), String.valueOf(nekit[1]/nekit[0])};
		}
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/getfourkill")
	public ResponseEntity<String[]> getFourKill()
			throws ResourceNotFoundException {
		List<Match> doceho = matchRepository.findAll();

		float[] desmond = new float[2];
		float[] blackVision = new float[2];
		float[] lakich = new float[2];
		float[] tilt = new float[2];
		float[] nekit = new float[2];

		for(Match des: doceho){
			if(des.getName().equals("desmond")){
				desmond[0]+=1;
				desmond[1]+=des.getFourKill();
			}
			else if(des.getName().equals("BlackVision")){
				blackVision[0]+=1;
				blackVision[1]+=des.getFourKill();
			}
			else if(des.getName().equals("Tilt")){
				tilt[0]+=1;
				tilt[1]+=des.getFourKill();
			}
			else if(des.getName().equals("Lakich")){
				lakich[0]+=1;
				lakich[1]+=des.getFourKill();
			}
			else if (des.getName().equals("221w33")){
				nekit[0]+=1;
				nekit[1]+=des.getFourKill();
			}
		}
		int num=0;
		float max=desmond[1]/desmond[0];
		float[] arr = {desmond[1]/desmond[0],blackVision[1]/blackVision[0],
				tilt[1]/tilt[0],lakich[1]/lakich[0],nekit[1]/nekit[0]};
		for(int i =1; i<5;i++){
			if(arr[i] > max){
				num = i;
				max = arr[i];
			}
		}
		String[] res = new String[3];
		if(num==0){
			res = new String[]{"Desmond",String.valueOf(desmond[0]), String.valueOf(desmond[1]/desmond[0])};
		}
		else if(num==1){
			res = new String[]{"BlackVision",String.valueOf(blackVision[0]), String.valueOf(blackVision[1]/blackVision[0])};
		}
		else if(num==2){
			res = new String[]{"Tilt",String.valueOf(tilt[0]), String.valueOf(tilt[1]/tilt[0])};
		}
		else if(num==3){
			res = new String[]{"Lakich",String.valueOf(lakich[0]), String.valueOf(lakich[1]/lakich[0])};
		}
		else {
			res = new String[]{"221w33",String.valueOf(nekit[0]), String.valueOf(nekit[1]/nekit[0])};
		}
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/getace")
	public ResponseEntity<String[]> getAce()
			throws ResourceNotFoundException {
		List<Match> doceho = matchRepository.findAll();

		float[] desmond = new float[2];
		float[] blackVision = new float[2];
		float[] lakich = new float[2];
		float[] tilt = new float[2];
		float[] nekit = new float[2];

		for(Match des: doceho){
			if(des.getName().equals("desmond")){
				desmond[0]+=1;
				desmond[1]+=des.getAce();
			}
			else if(des.getName().equals("BlackVision")){
				blackVision[0]+=1;
				blackVision[1]+=des.getAce();
			}
			else if(des.getName().equals("Tilt")){
				tilt[0]+=1;
				tilt[1]+=des.getAce();
			}
			else if(des.getName().equals("Lakich")){
				lakich[0]+=1;
				lakich[1]+=des.getAce();
			}
			else if (des.getName().equals("221w33")){
				nekit[0]+=1;
				nekit[1]+=des.getAce();
			}
		}
		int num=0;
		float max=desmond[1]/desmond[0];
		float[] arr = {desmond[1]/desmond[0],blackVision[1]/blackVision[0],
				tilt[1]/tilt[0],lakich[1]/lakich[0],nekit[1]/nekit[0]};
		for(int i =1; i<5;i++){
			if(arr[i] > max){
				num = i;
				max = arr[i];
			}
		}
		String[] res = new String[3];
		if(num==0){
			res = new String[]{"Desmond",String.valueOf(desmond[0]), String.valueOf(desmond[1]/desmond[0])};
		}
		else if(num==1){
			res = new String[]{"BlackVision",String.valueOf(blackVision[0]), String.valueOf(blackVision[1]/blackVision[0])};
		}
		else if(num==2){
			res = new String[]{"Tilt",String.valueOf(tilt[0]), String.valueOf(tilt[1]/tilt[0])};
		}
		else if(num==3){
			res = new String[]{"Lakich",String.valueOf(lakich[0]), String.valueOf(lakich[1]/lakich[0])};
		}
		else {
			res = new String[]{"221w33",String.valueOf(nekit[0]), String.valueOf(nekit[1]/nekit[0])};
		}
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/getclutches")
	public ResponseEntity<String[]> getClutches()
			throws ResourceNotFoundException {
		List<Match> doceho = matchRepository.findAll();

		int[] desmond = new int[7];
		int[] blackVision = new int[7];
		int[] lakich = new int[7];
		int[] tilt = new int[7];
		int[] nekit = new int[7];

		for(Match des: doceho){
			if(des.getName().equals("desmond")){
				desmond[0]+=des.getClutchOne()+des.getClutchTwo()+ des.getClutchThree()+ des.getClutchFour()+des.getClutchFive();
				desmond[1]+=des.getClutchOne();
				desmond[2]+=des.getClutchTwo();
				desmond[3]+=des.getClutchThree();
				desmond[4]+=des.getClutchFour();
				desmond[5]+=des.getClutchFive();
				desmond[6]+=1;
			}
			else if(des.getName().equals("BlackVision")){
				blackVision[0]+=des.getClutchOne()+des.getClutchTwo()+ des.getClutchThree()+ des.getClutchFour()+des.getClutchFive();
				blackVision[1]+=des.getClutchOne();
				blackVision[2]+=des.getClutchTwo();
				blackVision[3]+=des.getClutchThree();
				blackVision[4]+=des.getClutchFour();
				blackVision[5]+=des.getClutchFive();
				blackVision[6]+=1;
			}
			else if(des.getName().equals("Tilt")){
				tilt[0]+=des.getClutchOne()+des.getClutchTwo()+ des.getClutchThree()+ des.getClutchFour()+des.getClutchFive();
				tilt[1]+=des.getClutchOne();
				tilt[2]+=des.getClutchTwo();
				tilt[3]+=des.getClutchThree();
				tilt[4]+=des.getClutchFour();
				tilt[5]+=des.getClutchFive();
				tilt[6]+=1;
			}
			else if(des.getName().equals("Lakich")){
				lakich[0]+=des.getClutchOne()+des.getClutchTwo()+ des.getClutchThree()+ des.getClutchFour()+des.getClutchFive();
				lakich[1]+=des.getClutchOne();
				lakich[2]+=des.getClutchTwo();
				lakich[3]+=des.getClutchThree();
				lakich[4]+=des.getClutchFour();
				lakich[5]+=des.getClutchFive();
				lakich[6]+=1;
			}
			else if (des.getName().equals("221w33")){
				nekit[0]+=des.getClutchOne()+des.getClutchTwo()+ des.getClutchThree()+ des.getClutchFour()+des.getClutchFive();
				nekit[1]+=des.getClutchOne();
				nekit[2]+=des.getClutchTwo();
				nekit[3]+=des.getClutchThree();
				nekit[4]+=des.getClutchFour();
				nekit[5]+=des.getClutchFive();
				nekit[6]+=1;
			}
		}
		int num=0;
		int max=desmond[0];
		int[] arr = {desmond[0],blackVision[0],tilt[0],lakich[0],nekit[0]};
		for(int i =1; i<5;i++){
			if(arr[i] > max){
				num = i;
				max = arr[i];
			}
		}
		String[] res = new String[8];
		if(num==0){
			res = new String[]{"Desmond",String.valueOf(desmond[0]), String.valueOf(desmond[1]),
					String.valueOf(desmond[2]), String.valueOf(desmond[3]),
					String.valueOf(desmond[4]), String.valueOf(desmond[5]), String.valueOf(desmond[6])};
		}
		else if(num==1){
			res = new String[]{"BlackVision",String.valueOf(blackVision[0]), String.valueOf(blackVision[1]),
					String.valueOf(blackVision[2]), String.valueOf(blackVision[3]),
					String.valueOf(blackVision[4]), String.valueOf(blackVision[5]), String.valueOf(blackVision[6])};
		}
		else if(num==2){
			res = new String[]{"Tilt",String.valueOf(tilt[0]), String.valueOf(tilt[1]),
					String.valueOf(tilt[2]), String.valueOf(tilt[3]),
					String.valueOf(tilt[4]), String.valueOf(tilt[5]), String.valueOf(tilt[6])};
		}
		else if(num==3){
			res = new String[]{"Lakich",String.valueOf(lakich[0]), String.valueOf(lakich[1]),
					String.valueOf(lakich[2]), String.valueOf(lakich[3]),
					String.valueOf(lakich[4]), String.valueOf(lakich[5]), String.valueOf(lakich[6])};
		}
		else {
			res = new String[]{"221w33",String.valueOf(nekit[0]), String.valueOf(nekit[1]),
					String.valueOf(nekit[2]), String.valueOf(nekit[3]),
					String.valueOf(nekit[4]), String.valueOf(nekit[5]), String.valueOf(nekit[6])};
		}
		return ResponseEntity.ok().body(res);
	}

	@PostMapping("/matches")
	public Match createMatch(@Valid @RequestBody Match match) {
		return matchRepository.save(match);
	}

	@PutMapping("/matches/{id}")
	public ResponseEntity<Match> updateMatch(@PathVariable(value = "id") Long desmondId,
											   @Valid @RequestBody Match matchDetails) throws ResourceNotFoundException {
		Match match = matchRepository.findById(desmondId)
				.orElseThrow(() -> new ResourceNotFoundException("Desmond not found for this id : " + desmondId));

		match.setName(matchDetails.getName());
		match.setData(matchDetails.getData());
		match.setRating(matchDetails.getRating());
		match.setSmokeKill(matchDetails.getSmokeKill());
		match.setOpenKill(matchDetails.getOpenKill());
		match.setThreeKill(matchDetails.getThreeKill());
		match.setFourKill(matchDetails.getFourKill());
		match.setAce(matchDetails.getAce());
		match.setFlash(matchDetails.getFlash());
		match.setTrade(matchDetails.getThreeKill());
		match.setWallbang(matchDetails.getWallbang());
		match.setClutchOne(matchDetails.getClutchOne());
		match.setClutchTwo(matchDetails.getClutchTwo());
		match.setClutchThree(matchDetails.getClutchThree());
		match.setClutchFour(matchDetails.getClutchFour());
		match.setClutchFive(matchDetails.getClutchFive());
		match.setType(matchDetails.getType());


		final Match updatedMatch = matchRepository.save(match);
		return ResponseEntity.ok(updatedMatch);
	}

	@DeleteMapping("/matches/{id}")
	public Map<String, Boolean> deleteMatch(@PathVariable(value = "id") Long desmondId)
			throws ResourceNotFoundException {
		Match match = matchRepository.findById(desmondId)
				.orElseThrow(() -> new ResourceNotFoundException("Desmond not found for this id : " + desmondId));

		matchRepository.delete(match);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
