package coms309.backend.roundtrip.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import coms309.backend.roundtrip.models.Actor;
import coms309.backend.roundtrip.service.ActorService;
import coms309.backend.roundtrip.vo.LoginVO;
import coms309.backend.roundtrip.vo.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value = "Actor Controller", description = "REST Apis related to actor details")
@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    ActorService actorService;

    //GET
    @ApiOperation(value = "Get list of all the actors ", response = Iterable.class, tags = "actor-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @GetMapping(value = "/all", produces="application/json")
    public @ResponseBody List<Actor> getAllUsers(){
        return actorService.getAllActors();
    }

    @ApiOperation(value = "Get specific actor", response = Actor.class, tags = "actor-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @GetMapping(value = "/getActor", produces = "application/json")
    public @ResponseBody Actor getActor(@RequestBody LoginVO loginVO){
        return actorService.validateActor(loginVO);
    }

    //POST
    @ApiOperation(value = "Register an actor", response = Message.class, tags = "actor-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @PostMapping(value = "/registerActor", produces = "application/json")
    public @ResponseBody Message registerActor(@RequestBody Actor actor){
        return actorService.registerActor(actor.getFirstName(), actor.getMiddleName(), actor.getLastName(),
        		actor.getUserName(), actor.getEmail(), actor.getPassword(), actor.getAge(), actor.getHeightInches(),
        		actor.getWeightLbs(), actor.getRole());
    }

    //PUT
    //name
    @ApiOperation(value = "Update actor name", response = Message.class, tags = "actor-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @PutMapping(value = "/updateActorName/{first}/{middle}/{last}", produces = "application/json")
    public @ResponseBody Message updateActorName(@RequestBody LoginVO loginVO, @PathVariable Map<String, String> pathMap){
        return actorService.updateActorName(loginVO, pathMap.get("first"), pathMap.get("middle"), pathMap.get("last"));
    }
    
    //username
    @ApiOperation(value = "Update actor username", response = Message.class, tags = "actor-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @PutMapping(value = "/updateActorUserName/{name}", produces = "application/json")
    public @ResponseBody Message updateUserName(@RequestBody LoginVO loginVO, @PathVariable String name){
        return actorService.updateActorUserName(loginVO, name);
    }
    
    //email
    @ApiOperation(value = "Update actor email", response = Message.class, tags = "actor-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @PutMapping(value = "/updateActorEmail/{email}", produces = "application/json")
    public @ResponseBody Message updateActorEmail(@RequestBody LoginVO loginVO, @PathVariable String email){
        return actorService.updateActorEmail(loginVO, email);
    }
    
    //password
    @ApiOperation(value = "Update actor password", response = Message.class, tags = "actor-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @PutMapping(value = "/updateActorPassword/{pwd}", produces = "application/json")
    public @ResponseBody Message updateActorPassword(@RequestBody LoginVO loginVO, @PathVariable String pwd){
        return actorService.updateActorPassword(loginVO, pwd);
    }
    
    //age
    @ApiOperation(value = "Update actor age", response = Message.class, tags = "actor-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @PutMapping(value = "/updateActorAge/{age}", produces = "application/json")
    public @ResponseBody Message updateActorAge(@RequestBody LoginVO loginVO, @PathVariable int age){
        return actorService.updateActorAge(loginVO, age);
    }
    
    //ht wt
    @ApiOperation(value = "Update actor height and weight", response = Message.class, tags = "actor-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @PutMapping(value = "/updateActorDetails/{ht}/{wt}", produces = "application/json")
    public @ResponseBody Message updateActorPassword(@RequestBody LoginVO loginVO, @PathVariable int ht,
    		@PathVariable double wt){
        return actorService.updateActorHtWt(loginVO, ht, wt);
    }


    //DELETE
    //specific actor
    @ApiOperation(value = "Delete specific actor", response = Message.class, tags = "actor-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @DeleteMapping(value = "/deleteActor", produces = "application/json")
    public @ResponseBody Message deleteActor(@RequestBody LoginVO loginVO) {
    	return actorService.deleteActor(loginVO);
    }
    
    //all actors
    @ApiOperation(value = "Delete all actors", response = Message.class, tags = "actor-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @DeleteMapping(value = "/deleteAllActors", produces = "application/json")
    public @ResponseBody Message deleteAllActors() {
    	return actorService.deleteAllActors();
    }
}