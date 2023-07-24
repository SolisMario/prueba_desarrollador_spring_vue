package com.example.test.Controllers;

import com.example.test.Entities.ChequeraEntity;
import com.example.test.Request.ChequeraRequest;
import com.example.test.Services.ChequeraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/chequera")
@CrossOrigin("*")
public class ChequeraController {
    @Autowired
    private final ChequeraService chequeraService;

    public ChequeraController(ChequeraService chequeraService) {
        this.chequeraService = chequeraService;
    }

    @PostMapping()
    public ChequeraEntity saveChequera(@RequestBody ChequeraRequest chequeraRequest){
        return chequeraService.createChequera(chequeraRequest);
    }

    @GetMapping()
    public ArrayList<ChequeraEntity> readChequeras() { return chequeraService.readChequeras(); }

//    @PutMapping()
//    public ChequeraEntity updateChequera(@RequestBody ChequeraRequest chequeraRequest) {
//        return chequeraService.updateChequera(chequeraRequest);
//    }

    @DeleteMapping("/{Id}")
    public void deleteChequera(@PathVariable("Id") int id) {
        chequeraService.deleteChequera(id);
    }

    @GetMapping("/{Id}")
    public ArrayList<ChequeraEntity> getChequerasByCuenta(@PathVariable("Id") int id) { return chequeraService.GetChequerasByClienteId(id); }


}
