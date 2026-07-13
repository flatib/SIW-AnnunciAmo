package it.uniroma3.siw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Credentials credentials;

    @OneToMany(mappedBy = "autore", cascade = CascadeType.REMOVE)
    private List<Annuncio> annunci;

    @OneToMany(mappedBy = "mittente", cascade = CascadeType.REMOVE)
    private List<Messaggio> messaggiInviati;
    
    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.REMOVE)
    private List<Messaggio> messaggiRicevuti;

    public User() {
    }

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public List<Annuncio> getAnnunci() {
        return annunci;
    }

    public void setAnnunci(List<Annuncio> annunci) {
        this.annunci = annunci;
    }

    public List<Messaggio> getMessaggiInviati() {
        return messaggiInviati;
    }

    public void setMessaggiInviati(List<Messaggio> messaggi) {
        this.messaggiInviati = messaggi;
    }

    public List<Messaggio> getMessaggiRicevuti() {
		return messaggiRicevuti;
	}

	public void setMessaggiRicevuti(List<Messaggio> messaggiRicevuti) {
		this.messaggiRicevuti = messaggiRicevuti;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
