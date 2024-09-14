package com.itg.userservice.service;

import java.util.ArrayList;
import java.util.List;

import com.itg.userservice.entity.ShoppingCart;
import com.itg.userservice.entity.User;
import com.itg.userservice.repository.UserRepository;
import com.itg.userservice.service.request.LoginRequest;
import com.itg.userservice.service.request.SignupRequest;
import com.itg.userservice.service.request.UpdateUserRequest;
import com.itg.userservice.service.response.JwtResponse;
import com.itg.userservice.service.response.MessageResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Value("${jwt.issuer_uri}")
    String jwtIssuerUri;

    @Value("${jwt.client_id}")
    String jwtClientId;

    @Value("${jwt.client_secret}")
    String jwtClientSecret;

    @Value("${jwt.grant_type}")
    String jwtGrantType;

    @Value("${jwt.scope}")
    String jwtScope;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        User user;
        try {
            user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found!"));
        }

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        //if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
        // return ResponseEntity.badRequest().body(new MessageResponse("User credentials are not valid"));
        // }

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/realms/java-microservice-realm/protocol/openid-connect/token");

        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", jwtGrantType));
        params.add(new BasicNameValuePair("client_id", jwtClientId));
        params.add(new BasicNameValuePair("client_secret", jwtClientSecret));
        params.add(new BasicNameValuePair("username", "hakanyilmaz"));
        params.add(new BasicNameValuePair("password", "!=Hakan123"));
        // params.add(new BasicNameValuePair("scope", jwtScope));

        String accessToken = "";
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = httpClient.execute(httpPost);

            // Handle the response
            String responseBody = EntityUtils.toString(response.getEntity());

            accessToken = extractAccessToken(responseBody);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .ok(new JwtResponse(accessToken, user.getId(), user.getUsername(), user.getEmail()));
    }

    private static String extractAccessToken(String jsonResponse) {
        //jsonResponse = "{\n"
        //+ "    \"access_token\": \"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJtOWhLbHUwenkzbGx1RzBXSERtZnVtR0FpbG9WcU4yT1gwSkpaY3VxZDI4In0.eyJleHAiOjE3MjYzMzU1NTMsImlhdCI6MTcyNjI5OTU1MywianRpIjoiM2FkNGZiMGYtODc5Yi00YTcxLTlhYjEtYmVkZjMwNjMyZGJjIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9qYXZhLW1pY3Jvc2VydmljZS1yZWFsbSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJkZmQ1NzQ0Ny00NGQ3LTQ1OTktYTM2NC1hZDA0YTQwNzhjZWQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJzcHJpbmctY2xvdWQtY2xpZW50IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIvKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJkZWZhdWx0LXJvbGVzLWphdmEtbWljcm9zZXJ2aWNlLXJlYWxtIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJjbGllbnRIb3N0IjoiMTcyLjE4LjAuMSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LXNwcmluZy1jbG91ZC1jbGllbnQiLCJjbGllbnRBZGRyZXNzIjoiMTcyLjE4LjAuMSIsImNsaWVudF9pZCI6InNwcmluZy1jbG91ZC1jbGllbnQifQ.eqFSK0_4fRcS2kaqXzanj4gByRMbo1N6yqrQ3BnV2YIacmT1JbON15WLKJGQtGPoNRSPP17JtvJWbg296nDQLi1jNrbXbb4VRMmKbiZmX9cWyqdW3xNqe6KjqEz-EDCYDGWr9KZ4CqNK_Rg_FoyJDjE7poNOKmPb24xXWIB2-vz1eI4w9JMKVVEAUk9v-3_NDQ5SBt7FHPUpy5mv0BzC1Tfc--OmlGfLd1xAexCbyC12WFR8xkTVaHlHceBcAqC2U0k5HWlznSAWoR0nr9SUJX44dvk_4YM5AyNOF2Bri9ACOjtHmwUD6Xsp2G-81uG7FMRJRcmPJZyWEjML6JxigQ\",\n"
        //+ "    \"expires_in\": 36000,\n"
        //+ "    \"refresh_expires_in\": 0,\n"
        //+ "    \"token_type\": \"Bearer\",\n"
        //+ "    \"not-before-policy\": 0,\n"
        //+ "    \"scope\": \"email profile\"\n"
        //+ "}";
        // Use Jackson for JSON parsing
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            return rootNode.path("access_token").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use!"));
        }

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<?> deleteUser(Long userId) {
        try {
            // Check if the user exists
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found!"));

            try {
                // Check user's shopping cart
                ShoppingCart shoppingCart = restTemplate.getForObject(
                        "http://SHOPPING-CART-SERVICE/api/shopping-cart/by-name/" + user.getUsername(),
                        ShoppingCart.class);

                restTemplate.delete("http://SHOPPING-CART-SERVICE/api/shopping-cart/" + shoppingCart.getId());
            } catch (Exception e) {
                // If shopping cart not found, continue with user deletion
            }

            // Delete the user
            userRepository.delete(user);

            return ResponseEntity.ok(new MessageResponse("User account deleted successfully!"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new MessageResponse("Internal Server Error"));
        }
    }

    public ResponseEntity<?> updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        try {
            // Check if the user exists
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found!"));

            // Update password if provided
            if (updateUserRequest.getPassword() != null && !updateUserRequest.getPassword().isEmpty()) {
                PasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(updateUserRequest.getPassword()));
            }

            // Update email if provided
            if (updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().isEmpty()) {
                if (userRepository.existsByEmail(updateUserRequest.getEmail())) {
                    return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use!"));
                }
                user.setEmail(updateUserRequest.getEmail());
            }

            // Save the updated user
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("User account updated successfully!"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new MessageResponse("Internal Server Error"));
        }
    }
}
