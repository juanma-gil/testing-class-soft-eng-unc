package edu.unc.mi.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PermissionsManagerTest {
    @Mock
    private User user;
    private PermissionsManager pm;
    @BeforeEach
    void setUp() {
        user = mock(User.class);
        pm = new PermissionsManager(user);
    }

    @Test
    @DisplayName("GIVEN a user with admin role WHEN assignPermission() THEN return 1")
    public void assignPermissions() {
        Integer expected = 1;

        when(user.getRole()).thenReturn("admin");
        when(user.getUsername()).thenReturn("Juanma");

        Integer result = pm.assignPermission();

        verify(user, times(1)).getRole();
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("GIVEN a null user WHEN assignPermission() THEN returns -1")
    public void assignPermissions2() {
        Integer expected = -1;
        pm = new PermissionsManager(null);
        Integer result = pm.assignPermission();

        verify(user, never()).getRole();
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("GIVEN a user without permissions WHEN assignPermission() THEN returns -1")
    public void assignPermissions3() {
        Integer expected = -1;

        when(user.getRole()).thenReturn("employee");

        Integer result = pm.assignPermission();
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("GIVEN a word that isn't in the list WHEN getAllPostsContainingWord() THEN return false")
    void getAllPostsContainingWord() {
        List<String> filteredPosts = new ArrayList<>();
        filteredPosts.add("Estoy estudiando Ing Software");
        filteredPosts.add("Soy un desarrollador de software");

        when(user.getAllPostsContainingWord("Java")).thenReturn(filteredPosts);

        Assertions.assertFalse(pm.isThereAnyWordInPosts("Java"));
        verify(user, times(1)).getAllPostsContainingWord("Java");
    }

    @Test
    @DisplayName("GIVEN a null string WHEN getAllPostsContainingWord() THEN return false")
    void getAllPostsContainingWord2() {
        verify(user, never()).getAllPostsContainingWord(null);
        Assertions.assertFalse(pm.isThereAnyWordInPosts(null));
    }

    @Test
    @DisplayName("GIVEN a word that is in the list WHEN getAllPostsContainingWord() THEN return true")
    void getAllPostsContainingWord3() {
        List<String> filteredPosts = new ArrayList<>();
        filteredPosts.add("Estoy estudiando Ing software");
        filteredPosts.add("Soy un desarrollador de software");

        when(user.getAllPostsContainingWord("software")).thenReturn(filteredPosts);

        Assertions.assertTrue(pm.isThereAnyWordInPosts("software"));
        verify(user, times(1)).getAllPostsContainingWord("software");
    }
}