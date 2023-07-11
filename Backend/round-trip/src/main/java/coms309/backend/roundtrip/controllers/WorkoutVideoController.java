package coms309.backend.roundtrip.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import coms309.backend.roundtrip.service.WorkoutVideoService;
import coms309.backend.roundtrip.vo.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "WorkoutVideo Controller", description = "REST Apis related to actor (trainer) workout videos")
@RestController
@RequestMapping("/actors/{aid}/workoutVideos")
public class WorkoutVideoController {

	@Autowired 
	WorkoutVideoService workoutVideoService;
	
	//upload video
	@ApiOperation(value = "Upload a workout video for specific actor", response = Message.class, tags = "workout-video-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
	@PostMapping(value="/upload")
	public Message uploadVideo(@RequestParam("file") MultipartFile file,
	  @RequestParam("name") String name, @PathVariable int aid) 
	{
		return workoutVideoService.uploadWorkoutVideo(file, name, aid);    
	}
	  
	//get list of all videos - names only
	@ApiOperation(value = "Get all workout videos (names) for specific actor", response = Iterable.class, tags = "workout-video-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
	@GetMapping(value="/all")
	public List<String> getAllWorkoutVideos(@PathVariable int aid) throws Exception 
	{
		return workoutVideoService.getAllWorkoutVideos(aid);   
	}
	
	//get video from list - index in all list
	@ApiOperation(value = "Get specific workout video for specific actor", response = Resource.class, tags = "workout-video-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
	@GetMapping(value="/getVideo/{index}")
	public Resource getWorkoutVideo(@PathVariable int aid, @PathVariable int index) throws Exception 
	{
		return workoutVideoService.getWorkoutVideo(aid, index);   
	}
	
	//delete video from list - index in all list
	@ApiOperation(value = "Delete specific workout video for specific actor", response = Message.class, tags = "workout-video-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
	@DeleteMapping(value="/deleteVideo/{index}")
	public Message deleteWorkoutVideo(@PathVariable int aid, @PathVariable int index) 
	{
		return workoutVideoService.deleteWorkoutVideo(aid, index);   
	}
	
	//delete all
	@ApiOperation(value = "Delete all workout videos for specific actor", response = Message.class, tags = "workout-video-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
	@DeleteMapping(value="/all")
	public Message deleteAllWorkoutVideos(@PathVariable int aid) 
	{
		return workoutVideoService.deleteAllWorkoutVideos(aid);   
	} 
}
