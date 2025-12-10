package xyz.larchy.sicei.Services.Implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.larchy.sicei.CustomExceptions.NotFoundException;
import xyz.larchy.sicei.Models.SessionDynamoEntity;
import xyz.larchy.sicei.Repository.SessionRepository;
import xyz.larchy.sicei.Services.ISessionService;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SessionService implements ISessionService {

    private final SessionRepository sessionRepository;
    private final AlumnoService alumnoService;
    private final PasswordEncoder passwordEncoder;
    private static final Random random = new Random();

    @Override
    public String login(int id, String password) {
        var guessAlumno = alumnoService.getAlumno(id);
        if(guessAlumno.isEmpty()) {
            throw new NotFoundException("Alumno con id " + id + " no encontrado");
        }

        var alumno = guessAlumno.get();
        if(!passwordEncoder.matches(password, alumno.getPassword()))
        {
            throw new RuntimeException("Contraseña invalida");
        }

        String sessionString = generateSessionString();

        var session = SessionDynamoEntity.builder()
                .fecha(Instant.now())
                .alumnoId(alumno.getId())
                .active(true)
                .sessionString(sessionString)
                .build();
        sessionRepository.save(session);

        return sessionString;
    }

    @Override
    public boolean verifySession(String sessionString){
        var guessSession = sessionRepository.findBySessionString(sessionString);
        if(guessSession.isEmpty()) {
            return false;
        }
        var session  = guessSession.get();
        if(session.isActive()) {
            return true;
        }
        return false;
    }

    @Override
    public  boolean  logoutSession(String sessionString){
        var sessionActive = sessionRepository.findBySessionString(sessionString);
        if(sessionActive.isEmpty()){

            return false;
        }
        var session = sessionActive.get();
        session.setActive(false);
        sessionRepository.save(session);
        return true;
    }

    private String generateSessionString(){
        final int TARGET_LENGTH = 128;
        final String ALPHABET =
                "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(TARGET_LENGTH);

        for (int i = 0; i < TARGET_LENGTH; i++) {

            int randomIndex = random.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(randomIndex));
        }
        return sb.toString();
    }


}
