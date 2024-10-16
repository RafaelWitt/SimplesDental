package br.com.simplesdental.controller;

import br.com.simplesdental.entity.Contato;
import br.com.simplesdental.entity.Profissional;
import br.com.simplesdental.repository.ContatoRepository;
import br.com.simplesdental.repository.ProfissionalRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
@Tag(name = "Contato", description = "Teste Pratico - Simple Dental")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    ContatoRepository contatoRepository;
    @Autowired
    ProfissionalRepository profissionalRepository;

    @Operation(summary = "Criar novo contato", tags = {"contato", "post"})
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = Contato.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema())})})
    @PostMapping("/profissional/{idProfissional}/contato")
    public ResponseEntity<Contato> createContato(@PathVariable(value = "idProfissional") Long idProfissional,
            @RequestBody Contato contatoRequest) {
        Contato contato = profissionalRepository.findById(idProfissional).map(profissional -> {
            contatoRequest.setProfissional(profissional);
            return contatoRepository.save(contatoRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Profissional with id = " + idProfissional));

        return new ResponseEntity<>(contato, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Busca o contato atraves do Id",
            description = "Busca o contato atraves do id. A resposta e a entidade contato contendo id, nome, contato e a data de criação.",
            tags = {"contato"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Profissional.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema())})})
    @GetMapping("/contato/{id}")
    public ResponseEntity<Contato> getContatoById(@PathVariable("id") long id) {
        Optional<Contato> contatoData = contatoRepository.findById(id);

        if (contatoData.isPresent()) {
            return new ResponseEntity<>(contatoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Altera o Contato atraves do Id", tags = {"contato"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Contato.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "404", content = {
            @Content(schema = @Schema())})})
    @PutMapping("/contato/{id}")
    public ResponseEntity<Contato> updateContato(@PathVariable("id") long id, @RequestBody Contato contatoRequest) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

        contato.setContato(contatoRequest.getContato());
        contato.setNome(contatoRequest.getNome());

        return new ResponseEntity<>(contatoRepository.save(contato), HttpStatus.OK);
    }

    @Operation(summary = "Deleta o contato atraves do Id", tags = {"contato"})
    @ApiResponses({
        @ApiResponse(responseCode = "204", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema())})})
    @DeleteMapping("/contato/{id}")
    public ResponseEntity<HttpStatus> deleteContato(@PathVariable("id") Long id) {
        try {
            contatoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/profissional/{idProfissional}/contato")
    public ResponseEntity<List<Contato>> deleteAllContatoOfProfissional(@PathVariable(value = "idProfissional") Long idProfissional) {
        if (!profissionalRepository.existsById(idProfissional)) {
            throw new ResourceNotFoundException("Não foi localizado Profissional com o id = " + idProfissional);
        }

        contatoRepository.deleteByProfissionalId(idProfissional);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Operation(summary = "Busca os contatos atraves do Id do profissional", tags = {"contato"})
    @ApiResponses({
        @ApiResponse(responseCode = "204", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema())})})
    @GetMapping("/profissional/{idProfissional}/contato")
    public ResponseEntity<List<Contato>> getAllContatoByProfissionalId(@PathVariable(value = "idProfissional") Long idProfissional) {
        if (!profissionalRepository.existsById(idProfissional)) {
            throw new ResourceNotFoundException("Não foi localizado Profissional com o id = " + idProfissional);
        }

        List<Contato> contatos = contatoRepository.findByProfissionalId(idProfissional);
        return new ResponseEntity<>(contatos, HttpStatus.OK);
    }
}
