package coms309.backend.roundtrip.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import coms309.backend.roundtrip.models.Actor;
import coms309.backend.roundtrip.models.WorkoutVideo;
import coms309.backend.roundtrip.repositories.ActorRepository;
import coms309.backend.roundtrip.repositories.WorkoutVideoRepository;
import coms309.backend.roundtrip.vo.Message;

@Service
public class WorkoutVideoService {
	
	@Autowired
	WorkoutVideoRepository workoutVideoRepository;
	
	@Autowired
    ActorRepository actorRepository;
	
	@Autowired
	FileStorageService fileStorageService;
	
	/***Upload Video***/
	public Message uploadWorkoutVideo(MultipartFile file, String name, int aid)
	{
		try{
			String path = fileStorageService.storeFile(file);
			WorkoutVideo wvid = new WorkoutVideo(name, path);
			Actor actor = actorRepository.findById(aid);
	        actor.addWorkoutVideo(wvid);
	        wvid.setActor(actor);
	        try {
				workoutVideoRepository.save(wvid);
	        }
	        catch(Exception e) {
	        	return new Message("ERROR: path name should be unique", 1);
	        }
			return new Message("Upload Successful", 0);
		}
		catch(Exception e) {
			return new Message("Upload failed. Try again!", 1);
		} 
	}
	
	/***Get All Videos - Names only
	 * @throws Exception ***/
	public List<String> getAllWorkoutVideos(int aid) throws Exception
	{
		List<WorkoutVideo> wvideos = actorRepository.findById(aid).getAllWorkoutVideos();
		List<String> videos = new ArrayList<>();
		for(int i=0; i<wvideos.size(); i++)
		{
			videos.add(wvideos.get(i).getName());
		}
		return videos;
	}
	
	/***Get Video
	 * @throws Exception ***/
	public Resource getWorkoutVideo(int aid, int index) throws Exception
	{
		WorkoutVideo wvid = actorRepository.findById(aid).getWorkoutVideo(index);
		return fileStorageService.getResource(wvid.getPath());
	}
	
	/***Delete Video***/
	public Message deleteWorkoutVideo(int aid, int index)
	{
		Actor actor = actorRepository.findById(aid);
        WorkoutVideo wvid = actor.getWorkoutVideo(index);
        int id = wvid.getId();
        actor.removeWorkoutVideo(wvid);
        wvid.setActor(null);
        workoutVideoRepository.deleteById(id);
        return new Message("Delete Successful", 0);
	}
	
	/***Delete All Videos***/
	public Message deleteAllWorkoutVideos(int aid)
	{
		Actor actor = actorRepository.findById(aid);
		List<WorkoutVideo> wvideos = actor.getAllWorkoutVideos();
		WorkoutVideo w;
		for(int i=0; i<wvideos.size(); i++)
		{
		    w = wvideos.get(i);
		    int wid = w.getId();
		    actor.removeWorkoutVideo(w);
		    w.setActor(null);
		    workoutVideoRepository.deleteById(wid);
		}
        return new Message("Delete Successful", 0);
	}
	
}

