package controllers;

import play.mvc.*;
import controllers.Checker;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok("Yey!");

    }

    public Result check(Http.Request request) {

        /*
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Allow", "*");
        response().setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent");
         */
        String input = request.getQueryString("input");

        try
        {
            String output = Checker.checkCode(input);
            return ok(output);
            //return ok(views.html.index.render());
        }
        catch(Exception e)
        {
            return ok("Sorry!: " + e.getMessage());
        }

    }

}
