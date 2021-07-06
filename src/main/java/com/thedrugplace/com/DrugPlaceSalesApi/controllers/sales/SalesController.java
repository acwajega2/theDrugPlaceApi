package com.thedrugplace.com.DrugPlaceSalesApi.controllers.sales;


import com.mongodb.client.MongoClient;
import com.thedrugplace.com.DrugPlaceSalesApi.database.Sales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RestController
@RequestMapping("/api/ops/sales")
public class SalesController {

    private final MongoTemplate drugplaceDb;
    private final Logger logger = LoggerFactory.getLogger("drugPlaceSalesApi");


    public SalesController(MongoClient client) {

        this.drugplaceDb  = new MongoTemplate(client, "thedrugplace");
    }

    //--------------> Posting a new Sale ---------------------------
    @PostMapping(value = "/newsale")
    public NewSaleResponse saveNewSale (@RequestBody NewSaleRequest newSaleRequest){


        logger.info("New Sales Request");
        Query query = Query.query(new Criteria().andOperator(
                where("entryDate").is(newSaleRequest.getEntryDate()),
                where("branchId").is(newSaleRequest.getBranchId())

        ));

        logger.info("New Sales Request 2");
        try{
            Sales newSale = drugplaceDb.findOne(query,Sales.class);
//
            logger.info("New Sales Request 3");
//
            if (newSale == null){
                logger.info("New Sales Request 4");

                //------> Record is new
                newSale = new Sales();
                newSale.setAmount(newSaleRequest.getAmount());
                newSale.setBranchId(newSaleRequest.getBranchId());
                newSale.setEntryDate(newSaleRequest.getEntryDate());
                newSale.setStaffId(newSaleRequest.getStaffName());
                drugplaceDb.save(newSale);


            }
            else

            {
                logger.info("New Sales Request 4");
                //-----> record is going to be update
                newSale.setAmount(newSaleRequest.getAmount());
                drugplaceDb.save(newSale);
                //("Step H");
            }

        }
        catch (Exception e){
          logger.error("Error :{}",e.getMessage());
        }

        //-------------> clean code for nullable ---->
        logger.info("New Sales Request 5");
        //("Step I");
        NewSaleResponse newSaleResponse = new NewSaleResponse();
        newSaleResponse.setBranchId(newSaleRequest.getBranchId());
        newSaleResponse.setExtTransactionId(newSaleRequest.getExtTransactionId());
        newSaleResponse.setEntryDate(newSaleRequest.getEntryDate());
        newSaleResponse.setStatus("SUCCESS");
        newSaleResponse.setStaffName(newSaleRequest.getStaffName());
        newSaleResponse.setStatusCode("200");
        newSaleResponse.setDescription("Operation was successful");
        logger.info("New Sales Request 6");
        return newSaleResponse;
    }
    //-------------End of posting new sale



    //----------------------> Get All Sales --------------------------------------
    @PostMapping(value = "/getAllSales")
    public Optional<List<Sales>> getAllSales (){
        

        List<Sales> salesList = drugplaceDb.findAll(Sales.class);


        //-


        return Optional.of(salesList);


    }


    //----------------------> End of Getting All Sales---------------------------------
}
