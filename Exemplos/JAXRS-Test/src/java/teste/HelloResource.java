/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author helderdarocha
 */
@Stateless
@Path("teste")
public class HelloResource {

    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<html><body><h1>Hello!!</h1></body></html>";
    }
}
