package com.example.supplychainback.API.Controller.Auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        openBrowser();
        return "index";
    }

    private void openBrowser() {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("http://localhost:8080"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }


}
