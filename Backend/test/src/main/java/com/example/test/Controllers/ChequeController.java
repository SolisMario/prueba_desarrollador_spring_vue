package com.example.test.Controllers;

import com.example.test.Entities.ChequeEntity;
import com.example.test.Request.ChequeRequest;
import com.example.test.Services.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cheque")
@CrossOrigin("*")
public class ChequeController {
    @Autowired
    private final ChequeService chequeService;

    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @PostMapping()
    public ChequeEntity saveCheque(@RequestBody ChequeRequest chequeRequest){
        return chequeService.createCheque(chequeRequest);
    }

    @GetMapping()
    public ArrayList<ChequeEntity> readCheques() { return chequeService.readCheques(); }

    @PutMapping("/cancel")
    public ChequeEntity deleteCheque(@RequestBody ChequeRequest chequeRequest) {
        return chequeService.cancelCheque(chequeRequest);
    }

    @PutMapping("/pagar")
    public ChequeEntity pagarCheque(@RequestBody ChequeRequest chequeRequest) {
        return chequeService.pagarCheque(chequeRequest);
    }

    @GetMapping("/{Id}")
    public ArrayList<ChequeEntity> getChequesByChequera(@PathVariable("Id") int id) { return chequeService.GetChequesByChequeraId(id); }

}
