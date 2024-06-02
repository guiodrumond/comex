package br.com.alura.comex_nova_versao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComexController {
    @RequestMapping("/comex")
    public String bemvindo(){
        return "Bem vindo ao Comex!";
    }
}
