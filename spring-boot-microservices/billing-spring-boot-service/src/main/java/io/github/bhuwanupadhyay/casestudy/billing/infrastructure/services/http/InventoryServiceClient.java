package io.github.bhuwanupadhyay.casestudy.billing.infrastructure.services.http;

import java.math.BigDecimal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "InventoryService", url = "${InventoryService.url}")
public interface InventoryServiceClient {

  @GetMapping("/items/{itemId}")
  ItemInfoResource getItemInfo(@PathVariable("itemId") String itemId);

  record ItemInfoResource(String itemId, BigDecimal price) {
  }
}
