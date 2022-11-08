package com.example.externalapiopgave.Controllers;

import com.example.externalapiopgave.dtos.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController

public class NameController {


    @RequestMapping("/name-info")
    public NameResponse getDetails(@RequestParam String name) {

        Mono<Age> age = getAge(name);
        Mono<Gender> gender = getGenderForName(name);
        Mono<Nationality> nationality = getNationality(name);

        var resMono = Mono.zip(age, gender, nationality).map(tuple -> {
            NameResponse ns = new NameResponse();
            ns.setAge(tuple.getT1().getAge());
            ns.setAgeCount(tuple.getT1().getCount());

            ns.setGender(tuple.getT2().getGender());
            ns.setGenderProbability(tuple.getT2().getProbability());

            ns.setCountry(tuple.getT3().getCountry().get(0).getCountry_id());
            ns.setCountryProbability(tuple.getT3().getCountry().get(0).getProbability());


            return ns;
        });
        NameResponse res = resMono.block();
        res.setName(name);

        return res;
    }





    // Create a method that returns a Mono<Age> object
    Mono<Age> getAge(String name) {
        return WebClient.create()
                .get()
                .uri("https://api.agify.io?name=" + name)
                .retrieve()
                .bodyToMono(Age.class);
    }

    Mono<Gender> getGenderForName(String name){
        return WebClient.create()
                .get()
                .uri("https://api.genderize.io?name="+name)
                .retrieve()
                .bodyToMono(Gender.class);
    }

    Mono<Nationality> getNationality(String name){
        return WebClient.create()
                .get()
                .uri("https://api.nationalize.io?name="+name)
                .retrieve()
                .bodyToMono(Nationality.class);
    }


}
