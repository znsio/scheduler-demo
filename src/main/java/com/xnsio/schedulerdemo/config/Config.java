package com.xnsio.schedulerdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@Profile("!component-tests")
@EnableScheduling
public class Config {
}
