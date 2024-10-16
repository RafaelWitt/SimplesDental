package br.com.simplesdental.controller;

import br.com.simplesdental.entity.Contato;
import br.com.simplesdental.entity.Profissional;
import br.com.simplesdental.repository.ProfissionalRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rafael.Witt
 */
@Tag(name = "Profissional", description = "Teste Pratico - Simples Dental")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProfissionalController {

    @Autowired
    ProfissionalRepository profissionalRepository;

    @Operation(summary = "Criar novo profissional", tags = {"profissional", "post"})
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = Profissional.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema())})})
    @PostMapping("/profissional")
    public ResponseEntity<Profissional> createProfissional(@RequestBody Profissional profissional) {
        try {
            Profissional _prof = profissionalRepository
                    .save(new Profissional(profissional.getNome(),
                            profissional.getCargo(),
                            profissional.getNascimento(), new Date()));
            return new ResponseEntity<>(_prof, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Busca o profissional atraves do Id",
            description = "Localize um profissional atraves do Id. O retorno do profissional será id, nome, cargo, data nascimento e data de criação.",
            tags = {"profissional"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Profissional.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema())})})
    @GetMapping("/profissional/{id}")
    public ResponseEntity<Profissional> getProfissionalById(@PathVariable("id") Long id) {
        Optional<Profissional> profissionalData = profissionalRepository.findById(id);

        if (profissionalData.isPresent()) {
            return new ResponseEntity<>(profissionalData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Altera o profissional atraves do Id", tags = {"profissional"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Contato.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())})})
    @PutMapping("/profissional/{id}")
    public ResponseEntity<Profissional> updateProfissional(@PathVariable("id") long id, @RequestBody Profissional prof) {
        Optional<Profissional> profData = profissionalRepository.findById(id);

        if (profData != null) {
            Profissional profissional = profData.get();
            profissional.setNome(prof.getNome());
            profissional.setCargo(prof.getCargo());
            profissional.setNascimento(prof.getNascimento());
            return new ResponseEntity<>(profissionalRepository.save(profissional), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Deleta o profissional atraves do Id", tags = {"profissional"})
    @ApiResponses({
        @ApiResponse(responseCode = "204", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema())})})
    @DeleteMapping("/profissional/{id}")
    public ResponseEntity<HttpStatus> deleteContato(@PathVariable("id") Long id) {
        try {
            profissionalRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
