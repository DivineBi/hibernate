package fr.ensitech.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "utilisateur")
@NamedQueries({
        @NamedQuery(name = "user::findAll", query = "FROM User u"),
        @NamedQuery(name = "User::ByNomAndPrenom", query = "FROM User u WHERE u.nom = ?1 AND u.prenom = ?2" )
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 45)
    @NotEmpty
    @Length(min = 2, max = 45)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 45)
    @NotEmpty
    @Length(min = 2, max = 45)
    private String prenom;

    @Column(name = "email", nullable = false, length = 48, unique = true)
    @NotEmpty
    @Length(min = 6, max = 48)
    private String email;

    @Column(name = "password", nullable = false, length = 24)
    @NotEmpty
    @Length(min = 12, max = 24)
    private String password;

    @Column(name = "date_naissance", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Column(name = "actif", nullable = false)
    private Boolean actif;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Adresse> adresses;

    public User() {
        this.adresses = new ArrayList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public List<Adresse> getAdresses() {
        return adresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", datenaissance=" + dateNaissance +
                ", actif=" + actif +
                '}';
    }
}
