package br.com.simplesdental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Rafael.Witt
 */
@Entity
@Table(name = "contato")
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "contato")
    private String contato;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "profissional", referencedColumnName = "id_profissional")
    @ManyToOne(optional = false)
    private Profissional profissional;

    public Contato() {
    }

    public Contato(Long id) {
        this.id = id;
    }

    public Contato(Long id, String nome, Date createdDate) {
        this.id = id;
        this.nome = nome;
        this.createdDate = createdDate;
    }

    public Contato(String nome, Date createdDate) {
        this.nome = nome;
        this.createdDate = createdDate;
    }

    public Contato(String nome, Date createdDate, Profissional profissional) {
        this.nome = nome;
        this.createdDate = createdDate;
        this.profissional = profissional;
    }

    public Contato(String nome, String contato, Date createdDate, Profissional profissional) {
        this.nome = nome;
        this.contato = contato;
        this.createdDate = createdDate;
        this.profissional = profissional;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    @Override
    public String toString() {
        return "br.com.simplesdental.entity.Contato[ id=" + id + " ]";
    }

}
