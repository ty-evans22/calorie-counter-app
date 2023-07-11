package com.experiments.exp1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {
	@Autowired
    ClientRepository clientRepository;

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping(method = RequestMethod.POST, path = "/clients/new")
    public String saveClient(Client client) {
        clientRepository.save(client);
        return "New Client "+ client.getName() + " Saved";
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/clients/create")
    public String createDummyData() {
        Client c1 = new Client(1, "Gavin Smith", "Ames");
        Client c2 = new Client(2, "Frank Sinatra", "Chicago");
        Client c3 = new Client(3, "Tyler Evans", "Ames");
        Client c4 = new Client(4, "John Doe", "Miami");
        clientRepository.save(c1);
        clientRepository.save(c2);
        clientRepository.save(c3);
        clientRepository.save(c4);
        return "Successfully created dummy data";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/clients")
    public List<Client> getAllClients() {
        logger.info("Entered into Controller Layer");
        List<Client> results = clientRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/client/{clientId}")
    public Optional<Client> findClientById(@PathVariable("clientId") int id) {
        logger.info("Entered into Controller Layer");
        Optional<Client> results = clientRepository.findById(id);
        return results;
    }
    
    @RequestMapping(method = RequestMethod.DELETE, path = "/clients")
    public String deleteClients() {
    	clientRepository.deleteAll();
    	return "All clients deleted.";
    }
}
