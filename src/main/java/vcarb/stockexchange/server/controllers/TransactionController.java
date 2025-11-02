package vcarb.stockexchange.server.controllers;

import io.swagger.v3.core.util.Json;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vcarb.stockexchange.server.MainController;

import java.util.List;

@RestController
public class TransactionController {

 /*   // ===== Transactions =====

    @GetMapping("/transaction/all")
    public String getAllTransaction(){
        setAll();
        List<MainController.Transaction> response = transactionsList;
        return Json.pretty(response);
    }

    @GetMapping("transaction/{transactionID}")
    public String getUserTransaction(@PathVariable Long transactionID){
        setAll();
        MainController.Transaction response = getById(transactionsList,transactionID, MainController.Transaction::getTransactionId);
        return Json.pretty(response);
    }*/
}
