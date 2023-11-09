package co.edu.iudigital.parqueadero.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
/*
    private final long extraMillisExpirationToken = 1000L*60*60*24;

    public static final String SECRET_KEY= "4034403339373640252637215478263821445036356d4e265378395278383637";
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + extraMillisExpirationToken))
                .claim("rol",userDetails.getAuthorities().toString())
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        return !isTokenExpired(token) && checkUser(token,userDetails);
    }

    private boolean checkUser(String token,UserDetails userDetails){
        Claims claims = extractAllClaims(token);
        try {
            return claims.getSubject().equals(userDetails.getUsername()) && claims.get("rol").equals(userDetails.getAuthorities().toString());
        } catch (Exception e) {
            return false;
        }
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public String getTokenFromRequest(HttpServletRequest request){
        return request.getHeader("Authorization").substring(7);
    }*/
}

