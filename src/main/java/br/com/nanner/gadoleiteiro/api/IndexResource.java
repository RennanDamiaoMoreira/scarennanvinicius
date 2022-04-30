package br.com.nanner.gadoleiteiro.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

public class IndexResource {
    @GetMapping("/api/v1")
    @CrossOrigin("*")
    public String get() {return "GADO LEITEIRO API";}
}
