package ecom_project.demo.Config;

import ecom_project.demo.Service.MyUserDetailsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {

    @Mock
    private MyUserDetailsService myUserDetailsService;

    @Mock
    private JwtFilter jwtFilter;

    @InjectMocks
    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        // Se poate configura comportamentul mock-urilor
    }

    @AfterEach
    void tearDown() {
        // Cleanup, dacă este necesar
    }

    @Test
    void securityFilterChain() throws Exception {
        // Arrange
        HttpSecurity httpSecurity = mock(HttpSecurity.class);
        SecurityFilterChain securityFilterChain = securityConfig.securityFilterChain(httpSecurity);

        // Act & Assert
        assertNotNull(securityFilterChain);

        // Verificăm că metodele sunt apelate corect
        verify(httpSecurity, times(1)).csrf(any());
        verify(httpSecurity, times(1)).authorizeHttpRequests(any());
        verify(httpSecurity, times(1)).sessionManagement(any());
        verify(httpSecurity, times(1)).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Test
    void authenticationProvider() {
        // Act
        AuthenticationProvider provider = securityConfig.authenticationProvider();

        // Assert
        assertNotNull(provider);
        assertTrue(provider instanceof DaoAuthenticationProvider);

        DaoAuthenticationProvider daoProvider = (DaoAuthenticationProvider) provider;
        //assertNotNull(daoProvider.getUserDetailsService());
        //assertNotNull(daoProvider.getPasswordEncoder());
    }

    @Test
    void authenticationManager() throws Exception {
        // Arrange
        AuthenticationManager authenticationManager = securityConfig.authenticationManager(mock(AuthenticationConfiguration.class));

        // Act & Assert
        assertNotNull(authenticationManager);
    }
}
