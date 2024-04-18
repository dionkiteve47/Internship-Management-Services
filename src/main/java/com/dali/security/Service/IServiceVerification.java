package com.dali.security.Service;

import com.dali.security.Entity.Verification;

import java.util.Optional;

public interface IServiceVerification {
    public void ajouterVerification(Verification verification);
    public Optional<Verification> getToken(String token);
    public int setConfirmedAt(String token);
}
