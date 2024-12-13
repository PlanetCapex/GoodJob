package org.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "goodClient", url = "${good.service.url}")
public interface GoodClient {
    @GetMapping("/api/good/{id}")
    Good getGoodById(@PathVariable Long id);
}
