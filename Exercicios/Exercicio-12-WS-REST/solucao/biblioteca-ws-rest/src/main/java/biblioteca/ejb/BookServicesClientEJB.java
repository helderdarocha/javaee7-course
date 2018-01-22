/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Assunto;
import biblioteca.jpa.Livro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// Crie uma conta https://openlibrary.org/account/create
@Stateful
public class BookServicesClientEJB implements Serializable {

    @PersistenceContext(unitName = "biblioteca-PU")
    EntityManager em;

    @EJB
    LivroService livroService;
    @EJB
    AssuntoService assuntoService;

    // Cadastre-se no site http://isbndb.com/ e obtenha uma API-KEY
    private static final String API_KEY = "9PKUOR97";

    public String getCoverForISBN(String isbn) {
        return "http://covers.openlibrary.org/b/isbn/" + isbn + "-M.jpg";
    }

    public Livro updateFromIsbnDB(String isbn) {
        Livro livro = null;

        try {
            livro = livroService.findByISBN(isbn);
        } catch (Exception e) {
            if (livro == null) {
                livro = new Livro();
                livro.setIsbn(isbn);
            }
        }
        System.out.println(">>>>>>isbn " + isbn);

        try {

            String urlString = "http://isbndb.com/api/v2/json/" + API_KEY + "/book/" + isbn;

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15 * 1000);
            connection.connect();

            Reader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JsonArray root = null;
            try (JsonReader jsonReader = Json.createReader(reader)) {
                JsonObject jsonObject = jsonReader.readObject();
                root = jsonObject.getJsonArray("data");
            }

            if (root == null) {
                System.out.println("No data for " + isbn);
                return null;
            } else {
                JsonObject data = root.getJsonObject(0);
                String dewey = data.getJsonString("dewey_normal").getString();
                String title = data.getJsonString("title").getString();

                System.out.println("titulo " + title);
                System.out.println("dewey " + dewey);

                if (title != null) {
                    livro.setTitulo(title);
                }
                if (dewey != null && dewey.length() >= 3) {
                    if (dewey.indexOf(".") == 3) {
                        dewey = dewey.substring(0, 3);
                    }
                    Assunto assunto = assuntoService.getAssunto(dewey, 3);
                    livro.setAssunto(assunto);
                }
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(BookServicesClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BookServicesClientEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livro;
    }

}
