package fr.abes.referentiels.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTasks {
    @Caching(evict = {
            @CacheEvict(value = "langues"),
            @CacheEvict(value = "pays"),
            @CacheEvict(value = "geonames"),
            @CacheEvict(value = "languris"),
            @CacheEvict(value = "pcplibraries"),
            @CacheEvict(value = "pcp2rcr"),
            @CacheEvict(value = "pcp2rcrMulti")
            })
    @Scheduled(cron = "${cron.majCache}")
    public void videCaches() {
        System.out.println("Ok : caches vidés "+new Date());
    }
}
