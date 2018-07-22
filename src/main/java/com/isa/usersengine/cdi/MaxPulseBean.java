package com.isa.usersengine.cdi;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class MaxPulseBean {

    public double getManMaxPulse(int age) {
        return 202 - (0.55 * age);
    }

    public double getWomanPulse(int age) {
        return 216 - (1.09 * age);
    }
}
