package com.dali.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    private static final  String SECRET_KEY="56D40EE25F18E3D51ED1F90294B1637221BB21535775E4AAB110801BBF65907D";// format Hexadecimal

    private String secretKey;
    public String extractUsername(String token) {

        return extratClaim(token,Claims::getSubject);//Claims::getSubject heya l'implementation mte3 apply w testama référence de méthode w najmou zeda l'implentation na3mlouha a travers lambda expression
    }

    public <T> T extratClaim(String token, Function<Claims,T> claimsResolver)//extractClaim heya méhode générique najmou nesta3mlouha bch traja3 ay type(int,string,double ....)
            //Function <Claims,T> heya interface fonctionnele tekhou un ensemble de claims de traja3 haja de type T eli houwa type de retour
            //claimsReslver : instance de l'interface qui va implémenter la méhode apply soit b lamda expression wela référence de méthode
    {
        final  Claims claims=extractAllClaims(token);
        return  claimsResolver.apply(claims); // apply heya la méthode abstraite de l'interface fonctionnel de lezemha implémentation
    }

    public String generateToken(UserDetails userDetails) // sans extra-claims
    {
        return generateToken(new HashMap<>(),userDetails);
    }
    public String  generateToken // methode qui va générer le token
            (
            Map<String,Object> extraclaims, // map fiha les extras claims eli najmou nzidouhom lel token
            UserDetails userDetails)  //objet qui contient des infos sur le user exp : nom ,mot de passe , roles.... w bih houwa bch n3amrou claims
    {
        return Jwts.builder()// traja3 un constructeur Jwt eli houwa bch yasna3 token
                .setClaims(extraclaims) // nzidou claims mte3na
                .setSubject(userDetails.getUsername()) // nzidou subject eli houwa username
                .setIssuedAt(new Date(System.currentTimeMillis())) // lwa9et  eli 3tina fih token b second
                .setExpiration(new Date(System.currentTimeMillis() +1000*60*24)) // wa9teh youfa token  fel cas hedhi token ywali maash valide baaed 24 se3a
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // clé de signature + l'algoritme eli bch nesta3mlouh
                .compact(); // tasna3 token
    }

    public boolean isTokenValid(String token,UserDetails userDetails) // pour verifier idha ken token adheka appartient lel user adheka wela lee
    {
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));//verfier si username eli f token w eli userdetails kif kif et tokon n'est pas encore expiré
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // bch ncompariw date expiration eli f token maa date lyoum , idha ken expiration date 9bal date lyoum nraj3ou true donc token expiré
    }

    private Date extractExpiration(String token) {
         return extratClaim(token, Claims::getExpiration); // bch nkharjou expiration date mel token
    }

    private Claims extractAllClaims(String token)// permet de retourner tt les claims  a traver la méthode getBody()
    {
        // Un parseur JWT est responsable de décomposer le JWT en ses composants (header, payload, signature)
        // , de vérifier la signature,
        // et d'extraire les revendications (claims) du JWT.


        return Jwts.parserBuilder()//traja3 JwtParseurBuilder yaani constructeur mte3 parser
                .setSigningKey(getSignInKey())//Cette partie configure la clé de signature utilisée par le parseur pour vérifier la signature du JWT
                //clé de signature hedha yest7a9ou lparser bch yaamel recalcul lel signature w baaed ythabet f signature recalculé w signature eli f token
                // clé de signature fel cas réel dima ykoun partagé entre les parties de l'application
                .build()// traja3 un JwtParser : Cela finalise la configuration du parseur, et le résultat est un parseur JWT prêt à être utilisé.
                .parseClaimsJws(token)//heya lpartie eli taamel a la fois verification
                // (tekhou clé de signature eli fel partie w taamel recalcul lel signature w t9arenha maa signature eli fel token)
                // w taamel zeda décompostion lel token mte3na(header,body,signature)
                .getBody(); // nekhdhou lpartie body eli fiha claims

    }


    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // t7awel secret_key en un tableau binaire

        return Keys.hmacShaKeyFor(keyBytes);// n7awlouh signKey lel format HMAC
    }


}
