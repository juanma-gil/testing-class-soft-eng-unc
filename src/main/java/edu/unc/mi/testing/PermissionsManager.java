package edu.unc.mi.testing;

import java.util.List;

public class PermissionsManager {
    private final User user;

    public PermissionsManager(User user) {
        this.user = user;
    }

    public int assignPermission() {
        if (user == null) {
            System.out.println("No se puede asignar permisos a un usuario nulo.");
            return -1;
        }

        if (!user.getRole().equals("admin")) {
            System.out.println("No se pueden asignar permisos a usuarios no administradores.");
            return -1;
        }

        String username = user.getUsername();
        System.out.println("Asignando permisos de superusuarios para: " + username);
        return 1;
    }

    public Boolean isThereAnyWordInPosts(String word) {
        if (word == null) {
            System.out.println("No se puede buscar los post de una palabra nula.");
            return false;
        }

        List<String> posts = user.getAllPostsContainingWord(word);
        for (String post : posts) {
            if (post.contains(word)) {
                System.out.println("Se ha encontrado la palabra " + word + " en los posts");
                return true;
            }
        }
        System.out.println("No se ha encontrado la palabra " + word + " en los posts");
        return false;
    }
}
