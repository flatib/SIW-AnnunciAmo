package it.uniroma3.siw.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.uniroma3.siw.model.Categoria;
import it.uniroma3.siw.repository.CategoriaRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initCategorie(CategoriaRepository categoriaRepository) {
        return args -> {
            if (categoriaRepository.count() == 0) {
                categoriaRepository.save(new Categoria("Elettronica", "Smartphone, PC, Tablet, TV, Console e Audio"));
                categoriaRepository.save(new Categoria("Motori", "Auto, Moto, Scooter e Veicoli commerciali"));
                categoriaRepository.save(new Categoria("Arredamento", "Mobili, Elettrodomestici, Fai-da-te e Giardino"));
                categoriaRepository.save(new Categoria("Abbigliamento", "Vestiti, Scarpe, Borse e Accessori"));
                categoriaRepository.save(new Categoria("Sport e Hobby", "Attrezzature sportive, Biciclette, Strumenti musicali"));
                categoriaRepository.save(new Categoria("Libri e Film", "Testi scolastici, Romanzi, Fumetti, Vinili e Videogiochi"));
                
                System.out.println("Categorie di default caricate con successo nel DB!");
            }
        };
    }
}