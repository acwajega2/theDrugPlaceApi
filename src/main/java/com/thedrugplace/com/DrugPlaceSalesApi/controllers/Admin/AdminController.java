package com.thedrugplace.com.DrugPlaceSalesApi.controllers.Admin;

import com.mongodb.client.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ops/admin")
public class AdminController {

    private final MongoTemplate drugplaceDb;
    private final Logger logger = LoggerFactory.getLogger("drugPlaceSalesApi");

    public AdminController(MongoClient client) {
        this.drugplaceDb  = new MongoTemplate(client, "thedrugplace");
    }
    
    
    
    //------------
}
