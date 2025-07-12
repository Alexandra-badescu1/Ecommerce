package ecom_project.demo.Config;

import ecom_project.demo.Service.JWTService;
import ecom_project.demo.Service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class JwtFilterTest {

    @Mock
    private JWTService jwtService;

    @Mock
    private MyUserDetailsService myUserDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtFilter jwtFilter;

    private String token = "testToken";
    private String userName = "testUser";

    @BeforeEach
    void setUp() {
        // Initializare obiecte mock și setup pentru fiecare test
        SecurityContextHolder.clearContext();  // Curățăm contextul pentru un test curat
    }

    @AfterEach
    void tearDown() {
        // Cleanup dacă este necesar
    }

    @Test
    void doFilterInternal_shouldSetAuthenticationWhenTokenIsValid() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtService.extractUsername(token)).thenReturn(userName);
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        when(myUserDetailsService.loadUserByUsername(userName)).thenReturn(userDetails);
        when(jwtService.validateToken(token, userDetails)).thenReturn(true);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(jwtService, times(1)).extractUsername(token);
        verify(myUserDetailsService, times(1)).loadUserByUsername(userName);
        verify(jwtService, times(1)).validateToken(token, userDetails);

        // Verificăm că autentificarea a fost setată în SecurityContext
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilterInternal_shouldNotSetAuthenticationWhenTokenIsInvalid() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtService.extractUsername(token)).thenReturn(userName);
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        when(myUserDetailsService.loadUserByUsername(userName)).thenReturn(userDetails);
        when(jwtService.validateToken(token, userDetails)).thenReturn(false);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(jwtService, times(1)).extractUsername(token);
        verify(myUserDetailsService, times(1)).loadUserByUsername(userName);
        verify(jwtService, times(1)).validateToken(token, userDetails);

        // Verificăm că autentificarea NU a fost setată în SecurityContext
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilterInternal_shouldNotProcessIfNoAuthorizationHeader() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(jwtService, never()).extractUsername(anyString());
        verify(myUserDetailsService, never()).loadUserByUsername(anyString());
        verify(jwtService, never()).validateToken(anyString(), any());
        verify(filterChain, times(1)).doFilter(request, response);  // Verificăm că filtrul continuă cu următorul pas
    }
}
