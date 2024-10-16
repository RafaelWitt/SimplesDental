package br.com.simplesdental.entity;

import br.com.simplesdental.type.CargoType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rafael.Witt
 */
@Entity
@Table(name = "profissional")
public class Profissional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profissional")
    @JsonIgnore
    private Long id;
    @Basic(optional = false)
    @Column(name = "nome", length = 255)
    private String nome;
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo")
    private CargoType cargo;
    @Column(name = "nascimento")
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @Basic(optional = false)
    @Column(name = "created_date")
    @JsonIgnore
    @Temporal(TemporalType.DATE)
    private Date createdDate;    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profissional")
    private List<Contato> contatoList;

    public Profissional() {
    }

    public Profissional(Long id) {
        this.id = id;
    }

    public Profissional(String nome, CargoType cargo, Date nascimento, Date createdDate) {
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.createdDate = createdDate;
    }

    public Profissional(String nome, CargoType cargo, Date createdDate) {
        this.nome = nome;
        this.cargo = cargo;
        this.createdDate = createdDate;
    }

    public Profissional(String nome, CargoType cargo) {
        this.nome = nome;
        this.cargo = cargo;
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

    public CargoType getCargo() {
        return cargo;
    }

    public void setCargo(CargoType cargo) {
        this.cargo = cargo;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void setContatoList(List<Contato> contatosList) {
        this.contatoList = contatosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profissional)) {
            return false;
        }
        Profissional other = (Profissional) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.dahorta.entity.Profissionais[ id=" + id + " ]";
    }

}
