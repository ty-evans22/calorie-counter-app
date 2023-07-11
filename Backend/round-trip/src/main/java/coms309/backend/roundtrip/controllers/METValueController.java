package coms309.backend.roundtrip.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coms309.backend.roundtrip.models.METValue;
import coms309.backend.roundtrip.repositories.METValueRepository;
import coms309.backend.roundtrip.vo.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "METValue Controller", description = "REST Apis related to MET Value details")
@RestController
@RequestMapping("/METValue")
public class METValueController {

	@Autowired 
	METValueRepository METrepo;
	
	//add exercise
    @ApiOperation(value = "Add MET value for exercise", response = Message.class, tags = "met-value-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
	@PostMapping(value="/add")
	public Message addExercise(@RequestBody METValue exercise) 
	{
		try {
	        METrepo.save(exercise);
	    }catch (Exception e){
	      return new Message("ERROR: Name has to be unique", 1);
	    }
		return new Message("Success", 0);  
	}
	
	//get all MET values
    @ApiOperation(value = "Get MET values for all exercises", response = Iterable.class, tags = "met-value-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @GetMapping(value="/all")
	public List<METValue> getExercise() 
	{
		return METrepo.findAll();
	}
	  
	//update MET for exercise
    @ApiOperation(value = "Update MET value for exercise", response = Message.class, tags = "met-value-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @PutMapping(value="/update/{name}/{MET}")
	public Message updateMETValue(@PathVariable String name, @PathVariable double MET) 
	{
		METValue exercise = METrepo.findByName(name);
		if(exercise == null) {
			return new Message("Exercise doesn't exist", 1);
		}
		exercise.setMET(MET);
		METrepo.save(exercise);
		return new Message("Success", 0);  
	}
	
	//update name for exercise
    @ApiOperation(value = "Update exercise name", response = Message.class, tags = "met-value-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
	@PutMapping(value="/update/{name}/{newname}")
	public Message updateName(@PathVariable String name, @PathVariable String newname) 
	{
		METValue exercise = METrepo.findByName(name);
		if(exercise == null) {
			return new Message("Exercise doesn't exist", 1);
		}
		exercise.setName(newname);
		try {
	        METrepo.save(exercise);
	    }catch (Exception e){
	      return new Message("ERROR: Name has to be unique", 1);
	    }
		return new Message("Success", 0); 
	}
	
	//delete exercise
    @ApiOperation(value = "Delete exercise", response = Message.class, tags = "met-value-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
	@DeleteMapping(value="/delete/{name}")
	public Message deleteExercise(@PathVariable String name) 
	{
		METValue exercise = METrepo.findByName(name);
		if(exercise == null) {
			return new Message("Exercise doesn't exist", 1);
		}
		METrepo.delete(exercise);
		return new Message("Success", 0); 
	}
	
	//delete all exercises
    @ApiOperation(value = "Delete all exercises", response = Message.class, tags = "met-value-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
	@DeleteMapping(value="/delete/all")
	public Message deleteAllExercise() 
	{
		METrepo.deleteAll();
		return new Message("Success", 0); 
	}	
}
