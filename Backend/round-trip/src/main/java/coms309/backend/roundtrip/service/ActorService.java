package coms309.backend.roundtrip.service;

import java.util.List;

import coms309.backend.roundtrip.models.Recipe;
import coms309.backend.roundtrip.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms309.backend.roundtrip.models.Actor;
import coms309.backend.roundtrip.models.Role;
import coms309.backend.roundtrip.repositories.ActorRepository;
import coms309.backend.roundtrip.vo.LoginVO;
import coms309.backend.roundtrip.vo.Message;

@Service
public class ActorService {

	@Autowired
    ActorRepository actorRepository;

    @Autowired
    RecipeRepository recipeRepository;
	
	/***Actor Registration***/
    public Message registerActor(String firstName, String middleName, String lastName, String userName, String email, 
    		String password, int age, int heightInches, double weightLbs, Role role){

        Actor actor = new Actor(firstName, middleName, lastName, userName, email, password, age, heightInches, 
        		weightLbs, role);
        try {
            actorRepository.save(actor);
        }catch (Exception e){
            return new Message("ERROR: Email has to be unique", 1);
        }
        return new Message("Register Successful", 0);
    }

	/***Actor Updates***/
	//update name
    public Message updateActorName(LoginVO loginVO, String firstName, String middleName, String lastName){
        Actor actor = actorRepository.findByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
        if (actor == null){
            return new Message("Credentials Don't Exist", 1);
        }
        actor.setFirstName(firstName);
        actor.setMiddleName(middleName);
        actor.setLastName(lastName);
        actorRepository.save(actor);
        return new Message("Change Successful", 0);
    }
    
    //update username
    public Message updateActorUserName(LoginVO loginVO, String userName){
        Actor actor = actorRepository.findByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
        if (actor == null){
            return new Message("Credentials Don't Exist", 1);
        }
        actor.setUserName(userName);
        actorRepository.save(actor);
        return new Message("Change Successful", 0);
    }
    
    //update email
    public Message updateActorEmail(LoginVO loginVO, String email){
        Actor actor = actorRepository.findByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
        if (actor == null){
            return new Message("Credentials Don't Exist", 1);
        }
        actor.setEmail(email);
        try {
            actorRepository.save(actor);
        }
        catch(Exception e)
        {
        	 return new Message("ERROR: Email has to be unique", 1);
        }

        return new Message("Change Successful", 0);
    }
    
    //update password
    public Message updateActorPassword(LoginVO loginVO, String password){
        Actor actor = actorRepository.findByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
        if (actor == null){
            return new Message("Credentials Don't Exist", 1);
        }
        actor.setPassword(password);
        actorRepository.save(actor);
        return new Message("Change Successful", 0);
    }
    
    //update age 
    public Message updateActorAge(LoginVO loginVO, int age){
        Actor actor = actorRepository.findByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
        if (actor == null){
            return new Message("Credentials Don't Exist", 1);
        }
        actor.setAge(age);
        actorRepository.save(actor);
        return new Message("Change Successful", 0);
    }
    
    //update physical details
    public Message updateActorHtWt(LoginVO loginVO, int ht, double wt) {
        Actor actor = actorRepository.findByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
        if (actor == null) {
            return new Message("Credentials Don't Exist", 1);
        }
        actor.setHeightInches(ht);
        actor.setWeightLbs(wt);
        actorRepository.save(actor);
        return new Message("Change Successful", 0);
    }

    /***Validate Actor***/
    public Actor validateActor(LoginVO loginVO){
        return actorRepository.findByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
    }

    /***Get all actors***/
    public List<Actor> getAllActors(){
        return actorRepository.findAll();
    }
    
    /***Delete Actor***/
    public Message deleteActor(LoginVO loginVO) {
    	Actor actor = actorRepository.findByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
        if (actor == null){
            return new Message("Credentials Don't Exist", 1);
        }
        for (Recipe r : actor.getAllRecipes())
            recipeRepository.delete(r);
        actorRepository.delete(actor);
        return new Message("Delete Successful", 0);
    }
    
    /***Delete All Actors***/
    public Message deleteAllActors() {
    	List<Actor> actors = actorRepository.findAll();
    	Actor a;
    	for(int i=0; i<actors.size(); i++)
    	{
    		a = actors.get(i);
            for (Recipe r : a.getAllRecipes())
                recipeRepository.delete(r);
    		actorRepository.delete(a);
    	}
        return new Message("Delete Successful", 0);
    }

}
