package br.com.erickmarques.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.erickmarques.representation.EmailStatusDTO;

@FeignClient(value = "setStatus", url = "http://localhost:8080/api/v1/email")
public interface EmailClient {
    
    @PutMapping(value ="/{id}")
    void setStatus(@PathVariable(value = "id") Long id,
                   @RequestBody EmailStatusDTO dto);
}
