package br.com.nanner.gadoleiteiro;

import br.com.nanner.gadoleiteiro.model.entity.CattleShed;
import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.entity.Situation;
import br.com.nanner.gadoleiteiro.model.entity.Usuario;
import br.com.nanner.gadoleiteiro.model.repository.*;
import br.com.nanner.gadoleiteiro.service.CattleShedService;
import br.com.nanner.gadoleiteiro.utils.UpdateFile;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.InetAddress;
import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class GadoLeiteiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(GadoLeiteiroApplication.class, args);
    }

    @Autowired
    private SituationRepository situationRepository;
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private CattleShedRepository cattleShedRepository;
    @Autowired
    private CowRepository cowRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ProductionRepository productionRepository;


    @Bean
    public CommandLineRunner runner() {
        return (args) -> {
//            System.out.println(InetAddress.getLocalHost());
//            eventRepository.deleteAll();
//            productionRepository.deleteAll();
//            cowRepository.deleteAll();
            UpdateFile.update();
//            cattleShedRepository.deleteAll();
//            if (situationRepository.count() == 0) {
//                updateSituation();
//            }
//            userRepository.deleteAll();
//            if (cattleShedRepository.count() == 0)
//                cattleShedRepository.save(CattleShed.builder().name("curral primario").description("curral auto criado").build());
//
//            if (userRepository.count() == 0) {
//                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//                userRepository.save(Usuario.builder().admin(true).username("ADM").login("ADM").password(passwordEncoder.encode("123")).build());
//                userRepository.save(Usuario.builder().admin(false).username("teste").login("teste").password(passwordEncoder.encode("123")).build());
//            }
        };
    }


    private void updateSituation() {
        ArrayList<Situation> list = new ArrayList<Situation>();
        list.add(Situation.builder().name("Bezerra").description("Fêmea até 12 meses de idade").id(Long.parseLong("1")).build());
        list.add(Situation.builder().name("Novilha Aberta").description("Fêmea com mais de 12 meses de idade").id(Long.parseLong("2")).build());
        list.add(Situation.builder().name("Novilha Coberta").description("Fêmea que ainda não pariu e que já foi inseminada ou coberta,porém e prenhez ainda não foi confirmada").id(Long.parseLong("3")).build());
        list.add(Situation.builder().name("Novilha Prenhe").description("Fêmea que ainda não pariu nenhuma vez e está com prenhez confirmada").id(Long.parseLong("4")).build());
        list.add(Situation.builder().name("Vaca em Lactação Vazia").description("Vaca produzindo leite e que ainda não houve qualquer cobertura após o parto").id(Long.parseLong("5")).build());
        list.add(Situation.builder().name("Vaca em Lactação Coberta").description("Vaca produzindo leite, inseminada ou coberta, porém, a prenhez ainda não foi confirmada").id(Long.parseLong("6")).build());
        list.add(Situation.builder().name("Vaca em Lactação Prenhe").description("Vaca produzindo leite ou com prenhez confirmada").id(Long.parseLong("7")).build());
        list.add(Situation.builder().name("Vaca Seca Prenhe").description("Vaca que não está produzindo leite e com prenhez confirmada").id(Long.parseLong("8")).build());
        list.add(Situation.builder().name("Vaca Seca Coberta").description("Vaca que não está produzindo leite, inseminada ou coberta, porém, a prenhez ainda não foi confirmada").id(Long.parseLong("9")).build());
        list.add(Situation.builder().name("Vaca Seca Vazia").description("Vaca que não está produzindo leite e está vazia (aberta)").id(Long.parseLong("10")).build());

        situationRepository.saveAll(list);

    }
}
