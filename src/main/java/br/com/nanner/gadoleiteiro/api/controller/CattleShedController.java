package br.com.nanner.gadoleiteiro.api.controller;

import br.com.nanner.gadoleiteiro.service.CattleShedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/api/v1/cattle_shed")
@RequiredArgsConstructor
public class CattleShedController {
    private final CattleShedService service;


}
